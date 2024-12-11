package org.pneditor.petrinet.adapters.petrinet;

import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.models.petrinet.Place;

//The PlaceAdapter class acts as an adapter between the generic AbstractPlace class
//and the specific Place implementation in the PetriNet model.
public class PlaceAdapter extends AbstractPlace {

	// Instance of the specific Place implementation being adapted.
	protected Place adaptee;

	// Constructor to initialize the adapter with a label and a specific Place instance.
	public PlaceAdapter(String label,Place place) {
		super(label);
		this.adaptee=place;
	}

	// Adds one token to the place by delegating the operation to the adapted Place instance.
	@Override
	public void addToken() {
		adaptee.addToken(1);
	}

	// Removes one token from the place by delegating the operation to the adapted Place instance.
	@Override
	public void removeToken() {
		adaptee.removeToken(1);

	}

	// Retrieves the current number of tokens in the place by delegating to the adapted Place instance.
	@Override
	public int getTokens() {
		return adaptee.getToken();
	}

	// Sets the number of tokens in the place to the specified value.
    // This is done by first removing all existing tokens and then adding the specified number of tokens.
	@Override
	public void setTokens(int tokens) {
		adaptee.removeToken(adaptee.getToken());
		adaptee.addToken(tokens);

	}

}
