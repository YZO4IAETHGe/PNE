package org.pneditor.petrinet.models.petrinet;

public class Arc {
	 // The weight of the Arc
	private static int IdCount=0;
    private int weight;
    private int id;
    
    // The place associated with this Arc
    private Place place;
    private Transition transition;

	public Arc(int weight, Place place, Transition transition) {
    	this.transition=transition;
    	if (place==null) {
    		throw new NullPointerException("Null place was given");
    	}
    	else {
    		this.place=place;
    	}
    	this.place=place;
    	if (weight<0) {
    		throw new IllegalArgumentException("Weight cannot be negative");
    	}
    	else {
    		this.weight=weight;	
    	}
    	this.id=IdCount++;
    }
	
	public int getId() {
		return this.id;
	}

	public void set(int id) {
		this.id=id;
	}
	
    public int getWeight() {
		return weight;
	}

    public void setWeight(int weight) {
    	this.weight=weight;
    }
    
	public Place getPlace() {
		return place;
	}
	public Transition getTransition() {
		return transition;
	}
}	

