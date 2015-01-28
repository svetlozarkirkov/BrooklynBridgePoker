
package brooklynbridgepoker;


public class PlayCard {
	private int rank;   // the card's rank (if the face is "2" the rank is 1, "A" - rank 13)
	private String face;    //the card's face (example: "J")
	private char suit;  // the card's suit("D" for diamond, etc...)
	private String imgPath; // the path to the card image in /images
        
	public PlayCard(){  //default constructor
		
	}
	
        public String getImgPath(){ // gets the image path in format "2D" , "AC"
            return imgPath;
        }
	public int getRank(){   // gets the card rank
		return rank;
	}
	public String getFace(){    //gets the card face
		return face;
	}
	public char getSuit(){  // gets the card suit
		return suit;
	}
        public void setImgPath(String imgPath){ //sets the image path (could be improved)
            this.imgPath="/brooklynbridgepoker/images/cards/"+imgPath+".png";
        }
	public void setRank(int rank){  //sets the rank of the card
		this.rank = rank;
	}
	public void setFace(String face){   // sets the face of the card
		this.face = face;
	}
	public void setSuit(char suit){ //sets the suit of the card
		this.suit = suit;
	}
}
