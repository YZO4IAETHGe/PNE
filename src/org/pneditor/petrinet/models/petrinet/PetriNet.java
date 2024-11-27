package org.pneditor.petrinet.models.petrinet;

import java.util.ArrayList;

public class PetriNet {
	// ArrayList to hold the places in the Petri net
	private ArrayList<Place> places;
	// ArrayList to hold the transitions in the Petri net
	private ArrayList<Transition> transitions;

	// Constructor to initialize the Petri net
	public PetriNet() {
		places = new ArrayList<>();
		transitions = new ArrayList<>();
	}
	
	public ArrayList<Place> getPlaces() {
		return places;
	}

	public ArrayList<Transition> getTransitions() {
		return transitions;
	}

	// Method to add a new place with a specified number of tokens
	public Place addPlace(int token) {
		Place p=new Place(token);
		places.add(p);
		return p;
	}
	// Method to remove a Place from the PetriNet. Before deleting the place 
	//we must be careful to delete all the arcs linked to this place
	public void removePlace(Place place) {
		for (Transition t : this.transitions) {
			t.getInArcs().removeIf(inarc -> inarc.getPlace().getId() == place.getId()); 
			t.getOutArcs().removeIf(outarc -> outarc.getPlace().getId() == place.getId()); 
		}
		for (int i=0;i<this.places.size();i++){
			if (this.places.get(i).getId()==place.getId()) {
				this.places.remove(i);
				break;
			}
		}
	}
	
	// Method to add a new transition to the Petri net
	public Transition addTransition() {
		Transition t=new Transition();
		transitions.add(t);
		return t;
	}
	//Method to remove a Transition. In this case the arcs associated with this 
	//transition will disappear with the transition since they are contained by 
	//the transition.
	public void removeTransition(Transition t) {
		for (int i=0;i<this.transitions.size();i++){
			if (this.transitions.get(i).getId()==t.getId()) {
				this.transitions.remove(i);
				break;
			}
		}
	}
	//Method to add an ArcIn between Transition t and Place p with a weight
	public InArc addArcIn(Transition t, int weight, Place p) {
		return t.addArcIn(weight, p);
	}
	//Method to add an Inhibitor Arc between Transition t and Place p
	public InhibitorArc addArcInhibitor(Transition t,Place p) {
		return t.addArcInhibitor(p);
	}
	//Method to add a Clearing Arc between Transtion t and Place p
	public ClearingArc addArcClearing(Transition t, Place p) {
		return t.addArcClearing(p);
	}
	//Method to add an ArcOut between Transition t and Place p
	public OutArc addArcOut(Transition t, int weight, Place p) {
		return t.addArcOut(weight, p);
	}
	//Method to remove the ArcIn between Transition t and Place p
	public void removeArcIn(Transition t, Place p) {
		t.removeArcIn(p);	
	}
	//Method to remove the ArcOut between Transition t and Place p
	public void removeArcOut(Transition t, Place p) {
		t.removeArcOut(p);
	}
	//Method to trigger a transition
	public void trigger(Transition t) {
		this.transitions.stream()
		.filter(t1-> t1.getId()==t.getId())
		.forEach(Transition::trigger);
	}

}



