package org.pneditor.petrinet.models.petrinet;

public class Place {

	private static int IdCount=0;

	// The number of tokens currently in this place
	private int token;
	private int id;
	
	// Constructor to initialize the Place with a specified number of tokens
	public Place(int token) {
		if (token < 0) {
			throw new IllegalArgumentException("Token count cannot be negative");
		} else {
			this.token = token; 
			this.id=IdCount++;
		}
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

	// Method to retrieve the current number of tokens in the place
	public int getToken() {
		return token; 
	}

	// Method to add tokens to the place
	public void addToken(int token) {
		if (token < 0) {
			System.out.println("Unable to add negative tokens");
		} else {
			this.token += token; 
		}
	}

	// Method to remove a specified number of tokens from the place
	public void removeToken(int token) {
		// Check if there are enough tokens to remove
		if (token<0) {
			System.out.println("Unable to remove negative tokens");
		}
		else if (this.token >= token) {
			this.token -= token; 	
		} else {
			System.out.println("Not enough token"); 
		}
	}
}
