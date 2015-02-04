/** Objects of this class represents 
 *	a human player of the game 
 *	it asks the player if he wants another card 
 *	it is a extension of the player object
 */

import java.util.Scanner;


public class Human extends Player{
	
	public Human(String Name, int Points){
		super(Name, Points);	
	}
	
	public boolean makeChoise(Scanner scan){

		System.out.println("Do you want to draw another card?(1/0)");
		if(scan.nextInt() == 1){
			return true;
		}
		else{
			return false;
		}
	}
	

}
