package org.pneditor.petrinet.models.petrinet1;

public class InhibitorArc extends InArc {
    // The constructor initializes an InhibitorArc with a place and a weight of 0 
    public InhibitorArc(Place place) {
        super(0, place); 
    }
    
    // Method to check if the inhibitor arc can step, which is true if the place has no tokens
    public boolean canStep() {
        return getPlace().getToken() == 0; 
    }
}