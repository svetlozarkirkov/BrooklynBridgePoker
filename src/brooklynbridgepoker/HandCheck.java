
package brooklynbridgepoker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class HandCheck {
    
    
    public static int checkHand(ArrayList<PlayCard> cards){
        
        int handRank=0;
        int highestCardRank=0;
        int cardRanksSum=0;
        
        //  50  -  High Card
        //  100  -  One Pair
        //  150  -  Two Pairs
        //  200  -  Three Of A Kind
        //  250  - Straight
        //  300  -  Flush
        //  350  -  Full House
        //  400  -  Four Of A Kind
        //  450  -  Straight Flush
        //  500  -  Royal Flush
        
        
                Collections.sort(cards, new Comparator<PlayCard>() {    // sort by rank (has to be tested)
                    @Override public int compare(PlayCard cardOne, PlayCard cardTwo) {
                        return cardOne.getRank() - cardTwo.getRank(); // Ascending
                    }
                });

            boolean royalFlush = false;
            boolean straightFlush = false;
            boolean fourKind = false;
            boolean fullHouse = false;
            boolean flush = false;
            boolean straight = false;
            boolean threeKind = false;
            boolean twoPairs = false;
            boolean onePair = false;
            boolean highCard = false;
            
            for (int i = 0; i < 5; i++){
                cardRanksSum+=cards.get(i).getRank();
                if(cards.get(i).getRank()>highestCardRank){
                    highestCardRank=cards.get(i).getRank();
                }
            }
            

            if (cards.get(0).getRank()==cards.get(1).getRank()-1 && 
                    cards.get(1).getRank()==cards.get(2).getRank()-1 &&
                    cards.get(2).getRank()==cards.get(3).getRank()-1 && 
                    cards.get(3).getRank()==cards.get(4).getRank()-1){

                straight = true;
                handRank = 250;

                if (cards.get(0).getSuit()==cards.get(1).getSuit() && 
                        cards.get(1).getSuit()==cards.get(2).getSuit() && 
                        cards.get(2).getSuit()==cards.get(3).getSuit() &&
                        cards.get(3).getSuit()==cards.get(4).getSuit()){

                    straightFlush = true;
                    straight = false;
                    handRank = 450;

                    if(cards.get(4).getRank()==13){
                        royalFlush = true;
                        straightFlush = false;
                        handRank = 500;
                    }
            }
    }
            

            if (straight==false && straightFlush == false && royalFlush == false){
                
                if (cards.get(0).getRank()==cards.get(1).getRank() &&
                        cards.get(1).getRank()==cards.get(2).getRank() && 
                        cards.get(2).getRank()==cards.get(3).getRank()
                        || 
                        cards.get(1).getRank()==cards.get(2).getRank() &&
                        cards.get(2).getRank()==cards.get(3).getRank() && 
                        cards.get(3).getRank()==cards.get(4).getRank()){
                    
                    fourKind = true;
                    handRank = 400;
                }
                else if (cards.get(0).getRank()==cards.get(1).getRank() && 
                        cards.get(1).getRank()==cards.get(2).getRank() && 
                        cards.get(2).getRank() != cards.get(3).getRank() && 
                        cards.get(3).getRank() == cards.get(4).getRank()
                        ||
                        cards.get(0).getRank()==cards.get(1).getRank() && 
                        cards.get(1).getRank()!=cards.get(2).getRank() && 
                        cards.get(2).getRank() == cards.get(3).getRank() && 
                        cards.get(3).getRank() == cards.get(4).getRank()){
                    
                    fullHouse = true;
                    handRank = 350;
                }
                else if (cards.get(0).getSuit()==cards.get(1).getSuit() && 
                        cards.get(1).getSuit()==cards.get(2).getSuit() && 
                        cards.get(2).getSuit()==cards.get(3).getSuit() &&
                        cards.get(3).getSuit()==cards.get(4).getSuit()){
                    
                    flush = true;
                    handRank = 300;
                   // break outerloop;
                }
                else if (cards.get(0).getRank()==cards.get(1).getRank() && 
                        cards.get(1).getRank()==cards.get(2).getRank() && 
                        cards.get(2).getRank() != cards.get(3).getRank() && 
                        cards.get(3).getRank() != cards.get(4).getRank()
                        ||
                        cards.get(0).getRank()!=cards.get(1).getRank() && 
                        cards.get(1).getRank()!=cards.get(2).getRank() && 
                        cards.get(2).getRank() == cards.get(3).getRank() && 
                        cards.get(3).getRank() == cards.get(4).getRank()
                        ||
                        cards.get(0).getRank()!=cards.get(1).getRank() && 
                        cards.get(1).getRank()==cards.get(2).getRank() && 
                        cards.get(2).getRank() == cards.get(3).getRank() && 
                        cards.get(3).getRank() != cards.get(4).getRank()){
                    
                    threeKind = true;
                    handRank = 200;
                    //break outerloop;
                }
                else if (cards.get(0).getRank()==cards.get(1).getRank() && 
                        cards.get(1).getRank()!=cards.get(2).getRank() && 
                        cards.get(2).getRank() == cards.get(3).getRank() && 
                        cards.get(3).getRank() != cards.get(4).getRank() &&
                        cards.get(0).getRank() != cards.get(4).getRank()
                        ||
                        cards.get(0).getRank()==cards.get(1).getRank() && 
                        cards.get(1).getRank()!=cards.get(2).getRank() && 
                        cards.get(2).getRank() != cards.get(3).getRank() && 
                        cards.get(3).getRank() == cards.get(4).getRank() &&
                        cards.get(0).getRank() != cards.get(4).getRank()
                        ||
                        cards.get(0).getRank()!=cards.get(1).getRank() && 
                        cards.get(1).getRank()==cards.get(2).getRank() && 
                        cards.get(2).getRank()!= cards.get(3).getRank() && 
                        cards.get(3).getRank()== cards.get(4).getRank() &&
                        cards.get(0).getRank()!=cards.get(4).getRank()
                        ){
                    
                    twoPairs=true;
          
                    
                    handRank = 150;
                   // break outerloop;
                }
                else if (cards.get(0).getRank()==cards.get(1).getRank() && 
                        cards.get(1).getRank()!=cards.get(2).getRank() && 
                        cards.get(2).getRank()!=cards.get(3).getRank() && 
                        cards.get(3).getRank()!=cards.get(4).getRank()
                        ||
                        cards.get(0).getRank()!=cards.get(1).getRank() && 
                        cards.get(1).getRank()==cards.get(2).getRank() && 
                        cards.get(2).getRank()!=cards.get(3).getRank() && 
                        cards.get(3).getRank()!=cards.get(4).getRank()
                        ||
                        cards.get(0).getRank()!=cards.get(1).getRank() && 
                        cards.get(1).getRank()!=cards.get(2).getRank() && 
                        cards.get(2).getRank()==cards.get(3).getRank() && 
                        cards.get(3).getRank()!=cards.get(4).getRank()
                        ||
                        cards.get(0).getRank()!=cards.get(1).getRank() && 
                        cards.get(1).getRank()!=cards.get(2).getRank() && 
                        cards.get(2).getRank()!=cards.get(3).getRank() && 
                        cards.get(3).getRank()==cards.get(4).getRank()){
                        
                        onePair = true;
                        handRank=100;
                                                
                       // break outerloop;
                        
                }
                else{
                    highCard=true;
                    handRank=50;
                   // break outerloop;
                }
            }
            
            
            
            
        return handRank+cardRanksSum;
        }
    }
