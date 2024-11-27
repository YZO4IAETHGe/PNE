package org.pneditor.petrinet.adapters.petrinet;

import org.pneditor.petrinet.AbstractTransition;
import org.pneditor.petrinet.models.petrinet.Transition;

public class TransitionAdapter extends AbstractTransition {
	
	Transition adaptee;

	public TransitionAdapter(String label, Transition adaptee) {
		super(label);
		this.adaptee=adaptee;
	}

}
