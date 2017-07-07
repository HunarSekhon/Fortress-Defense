package ca.cmpt213.as2.gamelogic;

/**
 * Fortress class for the game.
 * Used by ApplicationController; fortress damage is increased according to total tank firepower
 */
public class Fortress {
    private int health;

    public Fortress(int initHealth) {
        health = initHealth;
    }

    public int getHealth() {
        if (health < 0) {
            return 0;
        } else {
            return health;
        }
    }

    public void reduceHealth(int damage) {
        health -= damage;
    }
}
