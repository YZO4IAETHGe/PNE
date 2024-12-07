package org.pneditor.petrinet.models.petrinet;

import java.util.ArrayList;

public class Transition {
	private static int IdCount=0;
	private int id;
	// List to hold incoming arcs (InArcs) associated with this transition
	private ArrayList<InArc> inArcs;
	// List to hold outgoing arcs (OutArcs) associated with this transition
	private ArrayList<OutArc> outArcs;

	// Constructor to initialize the Transition with empty lists for arcs
	public Transition() {
		id=IdCount++;
		inArcs = new ArrayList<InArc>(); 
		outArcs = new ArrayList<OutArc>(); 
	}

	public static int getIdCount() {
		return IdCount;
	}
	
	public static void setIdCount(int idCount) {
		IdCount = idCount;
	}

	public int getId() {
		return id;
	}

	public ArrayList<InArc> getInArcs() {
		return inArcs;
	}

	public ArrayList<OutArc> getOutArcs() {
		return outArcs;
	}
	
	// Method to add an inArc with a specified weight and a reference to a place
	public InArc addArcIn(int weight, Place place) {
		InArc inarc=null;
		boolean canAdd=true;
		for (int i=0;i<this.inArcs.size();i++) {
			if (inArcs.get(i).getPlace().getId()==place.getId()) {
				canAdd=false;
				break;
			}
		}
		if (canAdd) {
			inarc=new InArc(weight,place,this);
			inArcs.add(inarc);
		}
		else {
			System.out.println("No duplicate arcs between the same transition and place");
		}
		return inarc;
	}

	// Method to add an inhibitor arc  to the inArcs
	public InhibitorArc addArcInhibitor(Place place) {
		InhibitorArc inhibitor_arc=null;
		boolean canAdd=true;
		for (int i=0;i<this.inArcs.size();i++) {
			if (inArcs.get(i).getPlace().getId()==place.getId()) {
				canAdd=false;
				break;
			}
		}
		if (canAdd) {
			inhibitor_arc=new InhibitorArc(place,this);
			inArcs.add(inhibitor_arc); 
		}
		else {
			System.out.println("No duplicate arcs between the same transition and place");
		}
		return inhibitor_arc;
	}



	// Method to add a clearing arc to the inArcs
	public ClearingArc addArcClearing(Place place) {
		ClearingArc clearing_arc=null;
		boolean canAdd=true;
		for (int i=0;i<this.inArcs.size();i++) {
			if (inArcs.get(i).getPlace().getId()==place.getId()) {
				canAdd=false;
				break;
			}
		}
		if (canAdd) {
			clearing_arc=new ClearingArc(place,this);
			inArcs.add(clearing_arc); 
		}
		else {
			System.out.println("No duplicate arcs between the same transition and place");
		}
		return clearing_arc;
	}



	// Method to add an outArc arc with a specified weight and a reference to a place
	public OutArc addArcOut(int weight, Place place) {
		OutArc outarc=null;
		boolean canAdd=true;
		for (int i=0;i<this.outArcs.size();i++) {
			if (outArcs.get(i).getPlace().getId()==place.getId()) {
				canAdd=false;
				break;
			}
		}
		if (canAdd) {
			outarc=new OutArc(weight, place,this);
			outArcs.add(outarc);
		}
		else {
			System.out.println("No duplicate arcs between the same transition and place");
		}
		return outarc;
	}
	
	public void removeArcIn(Place place) {
		 for (int i=0;i<this.inArcs.size();i++) {
			 if (this.inArcs.get(i).getPlace().getId()==place.getId()) {
				 this.inArcs.remove(i);
				 break;
			 }
		 }
	}
	
	public void removeArcOut(Place place) {
		 for (int i=0;i<this.outArcs.size();i++) {
			 if (this.outArcs.get(i).getPlace().getId()==place.getId()) {
				 this.outArcs.remove(i);
				 break;
			 }
		 }
	}



	// Method to trigger the transition, checking if it can occur and updating arcs accordingly
	public void trigger() {
		boolean canTrigger = true; 

		// Check if all  inArcs allow the transition to step
		for (InArc arc : inArcs) {
			if (!arc.canStep()) { 
				canTrigger = false;
				break;
			}
		}

		// If the transition can be triggered, step through all inArcs and outArcs
		if (canTrigger) {
			for (InArc arc : inArcs) {
				arc.step(); 
			}
			for (OutArc arc : outArcs) {
				arc.step(); 
			}
		} else {
			System.out.println("Transition not triggerable "); 
		}
	}

}
