
package brooklynbridgepoker;

import java.util.Map;


public class Pot {
    
    private int currentPotTotal;    // total sum in the pot
    private Map<String,Integer> playersInPot;   // players who contribute to the pot
    
    public Pot(){   // default constructor
        
    }
    
    public int getCurrentPotTotal(){    // gets the current pot sum
        return currentPotTotal;
    }
    
    public Map<String,Integer> getPlayersInPot(){   // gets map of the players and their bets
        return playersInPot;
    }
    
    public void setCurrentPotTotal(int bet){    // updates the pot total sum
        this.currentPotTotal+=bet;
    }
    
    public void insertPlayerInPot(String name, int bet){    // inserts new player into the pot contributors
        this.playersInPot.put(name, bet);
    }
    
    public void removePlayerInPot(String name){     // removes a player from the pot
        this.playersInPot.remove(name);
    }
    
    public void clearPot(){     // clears the pot before a new round
        this.playersInPot.clear();
        this.currentPotTotal=0;
    }
}
