package org.pneditor.petrinet.models.petrinet;

public class Main {
    public static void main(String[] args) {
        // Create a new instance of the PetriNet
        PetriNet petrinet = new PetriNet();
        
        // Add a place with 2 tokens to the Petri net
        
        Place place1 = petrinet.addPlace(2);
       
        
        // Add another place with 3 tokens to the Petri net
        
        Place place2 = petrinet.addPlace(3); 
      
        
        Transition transition1 = petrinet.addTransition();
    
        // Add an inArc from place1 with a weight of 1
        petrinet.addArcIn(transition1, 1, place1);
        
        // Add an outArc to place2 with a weight of 2
        petrinet.addArcOut(transition1, 2, place2);
       
        // Display the token count in both places before triggering the transition
        System.out.println("Before :");
        System.out.println("place1 : " + place1.getToken());
        System.out.println("place2 : " + place2.getToken());

        // Trigger the transition to update token counts
 
        petrinet.trigger(transition1);
      
       
        // Display the token count in both places after triggering the transition
        System.out.println("After :");
        System.out.println("place1 : " + place1.getToken());
        System.out.println("place2 : " + place2.getToken());
    }
}
