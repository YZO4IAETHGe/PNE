package org.pneditor.petrinet.models.petrinet;

public class InArc extends Arc {
	// Constructor to initialize the InArc with a specified weight and place
	public InArc(int weight, Place place,Transition transition) {
		super(weight,place,transition);
	}

	// Method to check if the transition can step based on the current token count in the place
	public boolean canStep() {
		return getPlace().getToken() >= getWeight(); 
	}

	// Method to perform the step action, removing the required tokens from the place
	public void step() {
		getPlace().removeToken(getWeight());
	}
}
