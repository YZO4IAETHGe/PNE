package org.pneditor.petrinet.models.petrinet;

public class ClearingArc extends InArc {
    // The constructor initializes a ClearingArc with a place and a weight of 1, the weight
	//is set to 1, thus this arc can only step when its place has at least one token
    public ClearingArc(Place place,Transition transition) {
        super(1, place,transition);
    }
    // Method to perform the step action, which removes all tokens from the place
  
    public void step() {
        getPlace().removeToken(getPlace().getToken());
    }
}
