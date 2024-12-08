package org.pneditor.petrinet.models.petrinet;

public class OutArc extends Arc   {
    // Constructor to initialize the OutArc with a specified weight and place
    public OutArc(int weight, Place place,Transition transition) {
        super(weight,place,transition);   
    }
    
    // Method to perform the step action, which adds tokens to the place according to the outArc's weight
    public void step() {
        getPlace().addToken(getWeight()); 
    }
}
