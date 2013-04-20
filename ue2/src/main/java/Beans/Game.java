/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Matthes Laptop
 */
public class Game {

  private int round;
  private long startTime; 
  private Player player1;
  private Player player2;
  private int[] oilPosition;
  private boolean finished;
  private final static SimpleDateFormat format = new SimpleDateFormat("mm:ss");

  public Game() {
  }

  public Game(Player player1, Player player2){
    this.player1 = player1;
    this.player2 = player2;
    oilPosition = new int[]{2, 5};
    finished = false;
    startTime = System.currentTimeMillis();
  }


  public void performDice(Player currentPlayer){
    checkFinish(currentPlayer);
    currentPlayer.setDiceResult((int)(Math.random()*3+1));
    currentPlayer.setPosition(currentPlayer.getPositon()+currentPlayer.getDiceResult());
    if(currentPlayer.getPositon()== 2 || currentPlayer.getPositon() == 5){
      resetPlayer(currentPlayer);
    }
    checkFinish(currentPlayer);
  }

  private void resetPlayer(Player player){
    player.setPosition(0);
  }

  private void checkFinish(Player player){
    if(player.getPositon() >= 7){
      finished = true;
    }
  }
  
  public String getLeader(){
      if(player1.getPositon() > player2.getPositon())
      {
          return player1.getPlayerName();
      }
      else if (player1.getPositon() < player2.getPositon()){
          return player2.getPlayerName();
      }
      return "mehrere";
  }

  public int getRound() {
    return round;
  }

  public String getTime() {
    return format.format(System.currentTimeMillis() - startTime);
  }

  public Player getPlayer1() {
    return player1;
  }

  public Player getPlayer2() {
    return player2;
  }

  public boolean isFinished() {
    return finished;
  }
}
