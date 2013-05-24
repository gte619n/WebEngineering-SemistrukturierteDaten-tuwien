package formel0api;

import java.text.SimpleDateFormat;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "game")
@SessionScoped
public class Game {

    private static final int LAST_FIELD = 6;
    private Player player = null;
    private Player computer = null;
    private Player winner = null;
    private Dice dice = new Dice();
    private boolean gameOver = false;
    private long gamestarttime = System.currentTimeMillis();
    private long spenttime;
    private int round = 0;
    private final static SimpleDateFormat format = new SimpleDateFormat("mm:ss");
    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

    public Game(Player player, Player computer) {
        this.player = player;
        this.computer = computer;
        gameOver = false;
    }

    public boolean isGameOver() {
        return this.gameOver;
    }

    public String getSpentTime() {
        if (!gameOver) {
            spenttime = System.currentTimeMillis() - this.gamestarttime;
        }
        return format.format(spenttime);
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
            winner = player;
        }
        round++;
        return score;
    }

    public String getLeader() {
        if (player.getPosition() > computer.getPosition()) {
            return player.getUsername();
        } else if (computer.getPosition() > player.getPosition()) {
            return computer.getUsername();
        } else {
            return "beide";
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Player getComputer() {
        return computer;
    }

    public Player getWinner() {
        return winner;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getStartDate() {
        return format.format(gamestarttime);
    }

}
