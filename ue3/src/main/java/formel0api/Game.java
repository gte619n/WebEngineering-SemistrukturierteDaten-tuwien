/**
 * <copyright>
 *
 * Copyright (c) 2013 http://www.big.tuwien.ac.at All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * </copyright>
 */
package formel0api;

/**
 * Class representing a Formel 0 game
 */
public class Game {

  private static final int LAST_FIELD = 6;
  private Player player;
  private Player computer;
  private Dice dice = new Dice();
  private boolean gameOver = false;
  private long gamestarttime = System.currentTimeMillis();
  private long spenttime;


  public Game(Player player, Player computer) {
      this.player = player;
      this.computer = computer;
  }

  public boolean isGameOver() {
    return this.gameOver;
  }

  public long getSpentTime() {
    if (!gameOver) {
      spenttime = System.currentTimeMillis() - this.gamestarttime;
    }
    return spenttime;
  }

  public int rollthedice(Player player) {
    if (gameOver) {
      throw new IllegalArgumentException(
        "Game is over. Rolling the dice is not allowed.");
    }

    int score = dice.roll();

    int position = player.getPosition();

      /**
       * Move on field
       */
      int newposition = Math.min(position + score, LAST_FIELD);
      player.setPosition(newposition);

      /**
       * Test if deadly field was reached
       */
      if (newposition == 2 || newposition == 5) {
        newposition = 0;
        player.setPosition(newposition);
      }

      /**
       * Test if the figure of the player reached the end and the game is over
       */
      if (newposition == LAST_FIELD) { // player reached end
        gameOver = true;
      }

      return score;
    }

  public Player getLeader() {
    if (player.getPosition() > computer.getPosition()) {
      return player;
    } else if (computer.getPosition() > player.getPosition()) {
      return computer;
    } else {
      return null;
    }
  }

  public Player getPlayer() {
    return player;
  }

  public Player getComputer() {
    return computer;
  }
}
