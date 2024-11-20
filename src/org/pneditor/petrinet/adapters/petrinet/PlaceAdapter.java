package org.pneditor.petrinet.adapters.petrinet;

import org.pneditor.petrinet.AbstractPlace;
import org.pneditor.petrinet.models.petrinet.Place;

public class PlaceAdapter extends AbstractPlace {
	
	Place adaptee;
	
	public PlaceAdapter(String label,Place place) {
		super(label);
		this.adaptee=place;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addToken() {
		
		
		
	}

	@Override
	public void removeToken() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTokens() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setTokens(int tokens) {
		// TODO Auto-generated method stub
		
	}

}
