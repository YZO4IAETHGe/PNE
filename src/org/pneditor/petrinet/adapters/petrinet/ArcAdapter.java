package org.pneditor.petrinet.adapters.petrinet;

import java.util.ArrayList;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.petrinet.Arc;
import org.pneditor.petrinet.models.petrinet.ClearingArc;
import org.pneditor.petrinet.models.petrinet.InArc;
import org.pneditor.petrinet.models.petrinet.InhibitorArc;
import org.pneditor.petrinet.models.petrinet.OutArc;
import org.pneditor.petrinet.models.petrinet.Transition;

public class ArcAdapter extends AbstractArc {

	Arc adaptee;
	ArrayList<Transition> transitions;

	public ArcAdapter(Arc arc,ArrayList<Transition> transitions) {
		adaptee=arc;
		this.transitions=transitions;
	}
	@Override
	public AbstractNode getSource() {
		if (adaptee instanceof OutArc) {
			for (Transition t : transitions) {
				for (OutArc outarc: t.getOutArcs()) {
					if (outarc.getId()==adaptee.getId()) {
						AbstractNode source=new TransitionAdapter("transition",t);
						return source;
					}
				}
			}
		}
		else {
			AbstractNode source=new PlaceAdapter("place",adaptee.getPlace());
			return source;
		}
		return null;
	}

	@Override
	public AbstractNode getDestination() {
		if (adaptee instanceof InArc) {
			for (Transition t : transitions) {
				for (InArc inarc: t.getInArcs()) {
					if (inarc.getId()==adaptee.getId()) {
						AbstractNode source=new TransitionAdapter("transition",t);
						return source;
					}
				}
			}
		}
		else {
			AbstractNode source=new PlaceAdapter("place",adaptee.getPlace());
			return source;
		}
		return null;
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
		if (adaptee instanceof InhibitorArc) {
			throw new ResetArcMultiplicityException();
		} else {
			return adaptee.getWeight();
		}
	}

	@Override
	public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
		if (this.isRegular()) {
			if (multiplicity < 0) {
				throw new IllegalArgumentException("Multiplicity cannot be negative.");
			}
			adaptee.setWeight(multiplicity);
		}
		else
			throw new ResetArcMultiplicityException();
	}
}


