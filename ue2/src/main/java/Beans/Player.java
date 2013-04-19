/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author Matthes Laptop
 */
public class Player {
    
    private String playerName;
    
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getDiceResult() {
        return diceResult;
    }

    public void setDiceResult(int diceResult) {
        this.diceResult = diceResult;
    }
    private int positon;
    private int diceResult;
    
    public Player(){
        this.playerName = "Maxi";
        this.positon = 0;
    }
    
    public Player(String playerName ){
       this.playerName = playerName;  
       this.positon = 0;
    }
    
    public String getPlayerName() {
        return playerName;
    }

    public int getPositon() {
        return positon;
    }
    
    public void setPosition(int pos){
        this.positon = pos;
    }
    
    

}
