package model;

import java.io.Serializable;



public enum Suit implements Serializable{
	
	Clubs(0), Diamonds(1), Spades(2), Hearts(3);
	
	 private int code;
	 
	 private Suit(int c) {
	   code = c;
	 }
	 
	 public int getCode() {
	   return code;
	 }

};   


