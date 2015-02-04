package poker.ui;

public class Pot {
    
    private int currentPotTotal;    // total sum in the pot
    
    public Pot(){   // default constructor
        
    }
    
    public int getCurrentPotTotal(){    // gets the current pot sum
        return currentPotTotal;
    }
    
    
    public void setCurrentPotTotal(int bet){    // updates the pot total sum
        this.currentPotTotal += bet;
    }
    
    
    public void clearPot(){     // clears the pot before a new round
        this.currentPotTotal = 0;
    }
}
