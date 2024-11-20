package org.pneditor.petrinet.adapters.petrinet;

import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.models.petrinet.Place;

public class PlaceAdapter extends AbstractPlace {

	Place adaptee;

	public PlaceAdapter(String label,Place place) {
		super(label);
		this.adaptee=place;
	}

	@Override
	public void addToken() {
		adaptee.addToken(1);
	}

	@Override
	public void removeToken() {
		adaptee.removeToken(1);

	}

	@Override
	public int getTokens() {
		return adaptee.getToken();
	}

	@Override
	public void setTokens(int tokens) {
		adaptee.removeToken(adaptee.getToken());
		adaptee.addToken(tokens);

	}

}
