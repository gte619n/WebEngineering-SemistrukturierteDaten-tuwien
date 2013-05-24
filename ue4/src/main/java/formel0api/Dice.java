package formel0api;

import java.util.Random;

public class Dice {
    Random random = null;

    public Dice() {
        random = new Random();
    }

    public int roll() {
        return random.nextInt(3) + 1;
    }
}
