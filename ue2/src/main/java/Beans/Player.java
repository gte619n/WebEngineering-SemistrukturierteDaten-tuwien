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
    
    private int positon;
    private int diceResult;
    private String playerName;

    private int t = 0;
    
    public Player(){
    }
    
    public Player(String playerName ){
       this.playerName = playerName;  
       this.positon = 0;
    }

    public int counterTest(){
        t++;
        return t;
    }
    
    
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getDiceResult() {
        return diceResult;
    }

    public void setDiceResult(int diceResult) {
        this.diceResult = diceResult;
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
