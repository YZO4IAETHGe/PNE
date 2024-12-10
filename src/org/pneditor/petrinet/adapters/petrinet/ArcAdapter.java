package org.pneditor.petrinet.adapters.petrinet;




import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;

import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.petrinet.Arc;
import org.pneditor.petrinet.models.petrinet.ClearingArc;

import org.pneditor.petrinet.models.petrinet.InhibitorArc;
import org.pneditor.petrinet.models.petrinet.OutArc;

public class ArcAdapter extends AbstractArc {

	Arc adaptee;
	TransitionAdapter transition;
	PlaceAdapter place;

	public ArcAdapter(Arc arc,TransitionAdapter transition, PlaceAdapter place) {
		adaptee=arc;
		this.transition = transition;
		this.place = place;
	}
	@Override
	public AbstractNode getSource() {
		if (adaptee instanceof OutArc) {
			return transition;
		}
		else {
			return place;
		}
	}

	@Override
	public AbstractNode getDestination() {
		if (adaptee instanceof OutArc) {
			return place;
		}
		else {
			return transition;
		}
	}


	@Override
	public boolean isReset() {
		return adaptee instanceof ClearingArc;
	}

	@Override
	public boolean isRegular() {
		return !((adaptee instanceof ClearingArc) || (adaptee instanceof InhibitorArc));
	}

	@Override
	public boolean isInhibitory() {
		return adaptee instanceof InhibitorArc;
	}

	@Override
	public int getMultiplicity() throws ResetArcMultiplicityException {
		if (this.isReset()) {
			throw new ResetArcMultiplicityException();
		} else {
			return adaptee.getWeight();
		}
	}

	@Override
	public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
		if (this.isReset()) {
			throw new ResetArcMultiplicityException();
		}
		else {
			if (multiplicity >= 0) {
				adaptee.setWeight(multiplicity);
			}
			else {
				throw new IllegalArgumentException("Multiplicity cannot be negative.");
			}
		}
	}
}


