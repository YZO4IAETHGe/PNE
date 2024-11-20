package org.pneditor.petrinet.adapters.petrinet;

import java.util.ArrayList;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;
import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.petrinet.Arc;
import org.pneditor.petrinet.models.petrinet.ClearingArc;
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
					if (outarc.g)
				}
			}
			
		}
	}

	@Override
	public AbstractNode getDestination() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isReset() {
		if (adaptee instanceof ClearingArc) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isRegular() {
		if ((adaptee instanceof ClearingArc) || (adaptee instanceof InhibitorArc)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isInhibitory() {
		if (adaptee instanceof InhibitorArc) {
			return true;
		}
		return false;
	}

	@Override
	public int getMultiplicity() throws ResetArcMultiplicityException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
		// TODO Auto-generated method stub
		
	}

}
