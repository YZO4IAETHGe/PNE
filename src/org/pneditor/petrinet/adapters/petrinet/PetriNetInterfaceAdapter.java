package org.pneditor.petrinet.adapters.petrinet;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.PetriNetInterface;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.UnimplementedCaseException;
import org.pneditor.petrinet.models.petrinet.InArc;
import org.pneditor.petrinet.models.petrinet.OutArc;
import org.pneditor.petrinet.models.petrinet.PetriNet;
import org.pneditor.petrinet.models.petrinet.Place;
import org.pneditor.petrinet.models.petrinet.Transition;

public class PetriNetInterfaceAdapter extends PetriNetInterface {
	
	PetriNet adaptee;
	
	
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
			InArc inarc=adaptee.addArcIn(((TransitionAdapter)destination).adaptee, 0, ((PlaceAdapter)source).adaptee);
			arc = new ArcAdapter(inarc,this.adaptee.getTransitions());
		}
		else {
			if (!source.isPlace() && destination.isPlace()) {
				OutArc outarc=adaptee.addArcOut(((TransitionAdapter)source).adaptee, 0, ((PlaceAdapter)destination).adaptee);
				arc = new ArcAdapter(outarc,this.adaptee.getTransitions());
			}
			else {
				throw new UnimplementedCaseException("Need a place and a transition");
			}
		}
		return arc;
	}

	@Override
	public AbstractArc addInhibitoryArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractArc addResetArc(AbstractPlace place, AbstractTransition transition)
			throws UnimplementedCaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removePlace(AbstractPlace place) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTransition(AbstractTransition transition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeArc(AbstractArc arc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEnabled(AbstractTransition transition) throws ResetArcMultiplicityException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void fire(AbstractTransition transition) throws ResetArcMultiplicityException {
		// TODO Auto-generated method stub
		
	}

}
