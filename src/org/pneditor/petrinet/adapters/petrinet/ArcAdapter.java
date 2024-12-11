package org.pneditor.petrinet.adapters.petrinet;

import org.pneditor.petrinet.AbstractArc;
import org.pneditor.petrinet.AbstractNode;

import org.pneditor.petrinet.ResetArcMultiplicityException;
import org.pneditor.petrinet.models.petrinet.Arc;
import org.pneditor.petrinet.models.petrinet.ClearingArc;

import org.pneditor.petrinet.models.petrinet.InhibitorArc;
import org.pneditor.petrinet.models.petrinet.OutArc;

//The ArcAdapter class acts as an adapter between the generic AbstractArc class 
//and specific implementations of Arc in the PetriNet model.
public class ArcAdapter extends AbstractArc {

	// Instance of the specific Arc implementation being adapted.
	Arc adaptee;
	
	// Associated transition and place adapters representing 
    // the source and destination nodes of the arc.
	TransitionAdapter transition;
	PlaceAdapter place;

	// Constructor to initialize the adapter with a specific Arc, TransitionAdapter, and PlaceAdapter.
	public ArcAdapter(Arc arc,TransitionAdapter transition, PlaceAdapter place) {
		adaptee=arc;
		this.transition = transition;
		this.place = place;
	}
	
	// Returns the source node of the arc. 
    // If the arc is an OutArc, the source is a transition; otherwise, it's a place.
	@Override
	public AbstractNode getSource() {
		if (adaptee instanceof OutArc) {
			return transition;
		}
		else {
			return place;
		}
	}

	// Returns the destination node of the arc.
    // If the arc is an OutArc, the destination is a place; otherwise, it's a transition.
	@Override
	public AbstractNode getDestination() {
		if (adaptee instanceof OutArc) {
			return place;
		}
		else {
			return transition;
		}
	}

	// Determines if the arc is a reset arc (ClearingArc).
	@Override
	public boolean isReset() {
		return adaptee instanceof ClearingArc;
	}

	// Determines if the arc is a regular arc (not ClearingArc or InhibitorArc).
	@Override
	public boolean isRegular() {
		return !((adaptee instanceof ClearingArc) || (adaptee instanceof InhibitorArc));
	}

	// Determines if the arc is an inhibitory arc (InhibitorArc).
	@Override
	public boolean isInhibitory() {
		return adaptee instanceof InhibitorArc;
	}

	// Retrieves the multiplicity (weight) of the arc.
    // Throws a ResetArcMultiplicityException if the arc is a reset arc, as reset arcs do not have a multiplicity.
	@Override
	public int getMultiplicity() throws ResetArcMultiplicityException {
		if (this.isReset()) {
			throw new ResetArcMultiplicityException();
		} else {
			return adaptee.getWeight();
		}
	}

	// Sets the multiplicity (weight) of the arc.
    // Throws a ResetArcMultiplicityException if the arc is a reset arc.
    // Ensures that the multiplicity is non-negative.
	@Override
	public void setMultiplicity(int multiplicity) throws ResetArcMultiplicityException {
		if (this.isReset()) {
			throw new ResetArcMultiplicityException();
		}
		else {
			if (multiplicity >= 0) {
				adaptee.setWeight(multiplicity);
			}
			else {
				throw new IllegalArgumentException("Multiplicity cannot be negative.");
			}
		}
	}
}


