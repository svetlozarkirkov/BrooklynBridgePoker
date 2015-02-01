package poker.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class HandCheck {
    public static String handName;
    public static int checkHand(ArrayList<PlayCard> currentCards){
        
        int handRank=0;
        int highestCardRank=0;
        int cardRanksSum=0;
        
        //  1    -  Jacks or Better
        //  2    -  Two Pairs
        //  3    -  Three Of A Kind
        //  4    -  Straight
        //  6    -  Flush
        //  9    -  Full House
        //  25   -  Four Of A Kind
        //  50   -  Straight Flush
        //  250  -  Royal Flush
        
        ArrayList<PlayCard> cards = new ArrayList();
        for (PlayCard card: currentCards){
            cards.add(card);
        }
        
        
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
            boolean jacksOrBetter = false;
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
                handName="STRAIGHT";
                handRank = 4;

                if (cards.get(0).getSuit()==cards.get(1).getSuit() && 
                        cards.get(1).getSuit()==cards.get(2).getSuit() && 
                        cards.get(2).getSuit()==cards.get(3).getSuit() &&
                        cards.get(3).getSuit()==cards.get(4).getSuit()){

                    straightFlush = true;
                    straight = false;
                    handName = "STRAIGHT FLUSH";
                    handRank = 50;

                    if(cards.get(4).getRank()==13){
                        royalFlush = true;
                        straightFlush = false;
                        handName = "ROYAL FLUSH";
                        handRank = 250;
                    }
            }
    }
            
            outerloop:
            if (straight==false && straightFlush == false && royalFlush == false){
                
                if (cards.get(0).getRank()==cards.get(1).getRank() &&
                        cards.get(1).getRank()==cards.get(2).getRank() && 
                        cards.get(2).getRank()==cards.get(3).getRank()
                        || 
                        cards.get(1).getRank()==cards.get(2).getRank() &&
                        cards.get(2).getRank()==cards.get(3).getRank() && 
                        cards.get(3).getRank()==cards.get(4).getRank()){
                    
                    fourKind = true;
                    handRank = 25;
                    handName = "FOUR OF A KIND";
                    break outerloop;
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
                    handRank = 9;
                    handName = "FULL HOUSE";
                    break outerloop;
                }
                else if (cards.get(0).getSuit()==cards.get(1).getSuit() && 
                        cards.get(1).getSuit()==cards.get(2).getSuit() && 
                        cards.get(2).getSuit()==cards.get(3).getSuit() &&
                        cards.get(3).getSuit()==cards.get(4).getSuit()){
                    
                    flush = true;
                    handRank = 6;
                    handName = "FLUSH";
                    break outerloop;
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
                    handRank = 3;
                    handName = "THREE OF A KIND";
                    break outerloop;
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
                    handRank = 2;
                    handName = "TWO PAIRS";
                    break outerloop;
                }
                else if (cards.get(0).getRank()==cards.get(1).getRank() && 
                        cards.get(1).getRank()!=cards.get(2).getRank() && 
                        cards.get(2).getRank()!=cards.get(3).getRank() && 
                        cards.get(3).getRank()!=cards.get(4).getRank() &&
                        cards.get(0).getRank()>=10
                        ||
                        cards.get(0).getRank()!=cards.get(1).getRank() && 
                        cards.get(1).getRank()==cards.get(2).getRank() && 
                        cards.get(2).getRank()!=cards.get(3).getRank() && 
                        cards.get(3).getRank()!=cards.get(4).getRank() &&
                        cards.get(1).getRank()>=10
                        ||
                        cards.get(0).getRank()!=cards.get(1).getRank() && 
                        cards.get(1).getRank()!=cards.get(2).getRank() && 
                        cards.get(2).getRank()==cards.get(3).getRank() && 
                        cards.get(3).getRank()!=cards.get(4).getRank() &&
                        cards.get(2).getRank()>=10
                        ||
                        cards.get(0).getRank()!=cards.get(1).getRank() && 
                        cards.get(1).getRank()!=cards.get(2).getRank() && 
                        cards.get(2).getRank()!=cards.get(3).getRank() && 
                        cards.get(3).getRank()==cards.get(4).getRank() &&
                        cards.get(3).getRank()>=10){
                        
                        jacksOrBetter = true;
                        handRank=1;
                        handName = "JACKS OR BETTER";
                        break outerloop;
                }
                else{
                    highCard=true;
                    handRank=0;
                    handName="TRY AGAIN";
                }
            }
           
        return handRank;
        }
    }