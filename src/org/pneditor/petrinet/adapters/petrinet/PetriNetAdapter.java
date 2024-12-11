package org.pneditor.petrinet.adapters.petrinet;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.PetriNetInterface;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.UnimplementedCaseException;
import org.pneditor.petrinet.models.petrinet.ClearingArc;
import org.pneditor.petrinet.models.petrinet.InArc;
import org.pneditor.petrinet.models.petrinet.InhibitorArc;
import org.pneditor.petrinet.models.petrinet.OutArc;
import org.pneditor.petrinet.models.petrinet.PetriNet;
import org.pneditor.petrinet.models.petrinet.Place;
import org.pneditor.petrinet.models.petrinet.Transition;

public class PetriNetAdapter extends PetriNetInterface {
	//Petrinet of our model
	PetriNet adaptee=new PetriNet();

	// Adds a new place to the Petri net and returns its abstract representation
	public AbstractPlace addPlace() {
		Place place = adaptee.addPlace(0);
		AbstractPlace abstractPlace = new PlaceAdapter(String.valueOf(place.getId()), place);
		return abstractPlace;
	}
	 // Adds a new transition to the Petri net and returns its abstract representation
	@Override
	public AbstractTransition addTransition() {
		Transition t = adaptee.addTransition();
		AbstractTransition abstractTransition = new TransitionAdapter(String.valueOf(t.getId()),t);
		return abstractTransition;
	}
	 // Adds a regular arc between a source and a destination node
	@Override
	public AbstractArc addRegularArc(AbstractNode source, AbstractNode destination) throws UnimplementedCaseException {
		if (source.isPlace() && !destination.isPlace()) {
			InArc inarc=adaptee.addArcIn(((TransitionAdapter)destination).adaptee, 1, ((PlaceAdapter)source).adaptee);
			ArcAdapter arc = new ArcAdapter(inarc, (TransitionAdapter)destination, (PlaceAdapter)source);
			return arc;
		}
		else {
			if (!source.isPlace() && destination.isPlace()) {
				OutArc outarc=adaptee.addArcOut(((TransitionAdapter)source).adaptee, 1, ((PlaceAdapter)destination).adaptee);
				ArcAdapter arc = new ArcAdapter(outarc,(TransitionAdapter)source,(PlaceAdapter)destination);
				return arc;
			}
			else {
				throw new UnimplementedCaseException("Need a place and a transition");
			}
		}
	}
	// Adds an inhibitory arc from a place to a transition
	@Override
	public AbstractArc addInhibitoryArc(AbstractPlace place, AbstractTransition transition) throws UnimplementedCaseException {
		InhibitorArc inhibitor_arc=adaptee.addArcInhibitor(((TransitionAdapter)transition).adaptee, ((PlaceAdapter)place).adaptee);
		AbstractArc arc=new ArcAdapter(inhibitor_arc,(TransitionAdapter)transition ,(PlaceAdapter)place);
		return arc;
	}
	// Adds a reset arc from a place to a transition
	@Override
	public AbstractArc addResetArc(AbstractPlace place, AbstractTransition transition) throws UnimplementedCaseException {
		ClearingArc clearing_arc=adaptee.addArcClearing(((TransitionAdapter)transition).adaptee, ((PlaceAdapter)place).adaptee);
		AbstractArc arc=new ArcAdapter(clearing_arc, (TransitionAdapter)transition ,(PlaceAdapter)place);
		return arc;
	}
	 // Removes a place from the PetriNet
	@Override
	public void removePlace(AbstractPlace place) {
		adaptee.removePlace(((PlaceAdapter)place).adaptee);
	}
	// Removes a transition from the PetriNet
	@Override
	public void removeTransition(AbstractTransition transition) {
		adaptee.removeTransition(((TransitionAdapter)transition).adaptee);
	}
	// Removes an arc from the PetriNet
	@Override
	public void removeArc(AbstractArc arc) {
		AbstractNode source=arc.getSource();
		AbstractNode destination=arc.getDestination();
		if (source.isPlace() && !destination.isPlace()) {
			adaptee.removeArcIn(((TransitionAdapter)destination).adaptee, ((PlaceAdapter)source).adaptee);
		}
		else {
			adaptee.removeArcOut(((TransitionAdapter)source).adaptee, ((PlaceAdapter)destination).adaptee);
		}
	}
	// Checks if a transition is enabled (can fire)
	@Override
	public boolean isEnabled(AbstractTransition transition) throws ResetArcMultiplicityException {
		Transition t=((TransitionAdapter)transition).adaptee;
		boolean canTrigger = true; 
		for (InArc arc : t.getInArcs()) {
			if (!arc.canStep()) { 
				canTrigger = false;
				break;
			}
		}
		return canTrigger;
	}
	// Fires a transition in the Petri net
	@Override
	public void fire(AbstractTransition transition) throws ResetArcMultiplicityException {
		((TransitionAdapter)transition).adaptee.trigger();
	}

}
