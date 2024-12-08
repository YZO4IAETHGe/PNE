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

	PetriNet adaptee=new PetriNet();


	public AbstractPlace addPlace() {
		Place place = adaptee.addPlace(0);
		AbstractPlace abstractPlace = new PlaceAdapter(String.valueOf(place.getId()), place);
		return abstractPlace;
	}

	@Override
	public AbstractTransition addTransition() {
		Transition t = adaptee.addTransition();
		AbstractTransition abstractTransition = new TransitionAdapter(String.valueOf(t.getId()),t);
		return abstractTransition;
	}

	@Override
	public AbstractArc addRegularArc(AbstractNode source, AbstractNode destination) throws UnimplementedCaseException {
		AbstractArc arc=null;
		if (source.isPlace() && !destination.isPlace()) {
			InArc inarc=adaptee.addArcIn(((TransitionAdapter)destination).adaptee, 1, ((PlaceAdapter)source).adaptee);
			arc = new ArcAdapter(inarc, getTransitions(), getPlaces());
		}
		else {
			if (!source.isPlace() && destination.isPlace()) {
				OutArc outarc=adaptee.addArcOut(((TransitionAdapter)source).adaptee, 1, ((PlaceAdapter)destination).adaptee);
				arc = new ArcAdapter(outarc, getTransitions(), getPlaces());
			}
			else {
				throw new UnimplementedCaseException("Need a place and a transition");
			}
		}
		return arc;
	}

	@Override
	public AbstractArc addInhibitoryArc(AbstractPlace place, AbstractTransition transition) throws UnimplementedCaseException {
		InhibitorArc inhibitor_arc=adaptee.addArcInhibitor(((TransitionAdapter)transition).adaptee, ((PlaceAdapter)place).adaptee);
		AbstractArc arc=new ArcAdapter(inhibitor_arc, getTransitions(), getPlaces());
		return arc;
	}

	@Override
	public AbstractArc addResetArc(AbstractPlace place, AbstractTransition transition) throws UnimplementedCaseException {
		ClearingArc clearing_arc=adaptee.addArcClearing(((TransitionAdapter)transition).adaptee, ((PlaceAdapter)place).adaptee);
		AbstractArc arc=new ArcAdapter(clearing_arc, getTransitions(), getPlaces());
		return arc;
	}

	@Override
	public void removePlace(AbstractPlace place) {
		adaptee.removePlace(((PlaceAdapter)place).adaptee);
	}

	@Override
	public void removeTransition(AbstractTransition transition) {
		adaptee.removeTransition(((TransitionAdapter)transition).adaptee);
	}

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

	@Override
	public void fire(AbstractTransition transition) throws ResetArcMultiplicityException {
		((TransitionAdapter)transition).adaptee.trigger();
	}

}
