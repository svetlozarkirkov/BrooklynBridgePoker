package model;

import java.io.Serializable;

/** Objects of this class represents 
 *	a human player of the game 
 *	it asks the player if he wants another card 
 *	it is a extension of the player object
 */

public class Human extends Player implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Human(String Name, int Points, int id){
		super(Name, Points,id);	
	}

}
