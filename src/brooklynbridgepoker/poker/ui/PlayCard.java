package poker.ui;

public class PlayCard {
	private int rank;   // the card's rank (if the face is "2" the rank is 1, "A" - rank 13)
	private String face;    //the card's face (example: "J")
	private char suit;  // the card's suit("D" for diamond, etc...)
	private String imgPath; // the path to the card face in /images
        private final String imgPathFlipped = "/brooklynbridgepoker/resources/images/cards/b2fv.png"; // path to the image of a card back in /images
        private String image;   // current image used
        private int cardState = 0;  // 0 = the card is not shown, 1 = the card is shown
        
	public PlayCard(){  //default constructor
		
	}
	
        public String getImgPath(){ // gets the image path in format "2D" , "AC"
            return image;
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
        public void setImage(String image){ //sets the image path (could be improved)
            this.imgPath="/brooklynbridgepoker/resources/images/cards/"+image+".png";
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
                
        public void flipCard (){    // hides the card
                this.image=imgPathFlipped;
                this.cardState=0;
        }
        public void unflipCard(){   // unhides the card
            this.image=imgPath;
            this.cardState=1;
        }
        
        public int getCardState(){  // gets whether the card is shown or flipped
            return this.cardState;
        }
}