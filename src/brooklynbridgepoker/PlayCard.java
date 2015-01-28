
package brooklynbridgepoker;


public class PlayCard {
	private int rank;
	private String face;
	private char suit;
	private String imgPath;
        
	public PlayCard(){
		
	}
	
        public String getImgPath(){
            return imgPath;
        }
	public int getRank(){
		return rank;
	}
	public String getFace(){
		return face;
	}
	public char getSuit(){
		return suit;
	}
        public void setImgPath(String imgPath){
            this.imgPath="/brooklynbridgepoker/images/cards/"+imgPath+".png";
        }
	public void setRank(int rank){
		this.rank = rank;
	}
	public void setFace(String face){
		this.face = face;
	}
	public void setSuit(char suit){
		this.suit = suit;
	}
}
