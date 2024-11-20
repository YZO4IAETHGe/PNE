package org.pneditor.petrinet.models.petrinet;

public class Arc {
	 // The weight of the Arc
    private int weight;
    
    // The place associated with this Arc
    private Place place;

	public Arc(int weight, Place place) {
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
    }
	
    public int getWeight() {
		return weight;
	}

	public Place getPlace() {
		return place;
	}
}

