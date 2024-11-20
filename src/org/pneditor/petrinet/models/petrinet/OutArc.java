package org.pneditor.petrinet.models.petrinet;

public class OutArc extends Arc   {
    // Constructor to initialize the OutArc with a specified weight and place
    public OutArc(int weight, Place place) {
        super(weight,place);   
    }
    
    // Method to perform the step action, which adds tokens to the place according to the outArc's weight
    public void step() {
        getPlace().addToken(getWeight()); 
    }
}
