package ca.cmpt213.as2.gamelogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class for tank objects in the game, created in GameBoard.
 * Stores own coordinate location. Firepower diminishes along with health. Does not fire shots if out of commission
 */
public class Tank {
    private String[] locationCoordinates;
    private List<String> hitCoordinates;
    private int damagedCells;

    public Tank(String[] locationCoordinates) {
        damagedCells = 0;
        this.locationCoordinates = locationCoordinates;
        hitCoordinates = new ArrayList<>();
    }

    public void setLocationCoordinates(String[] location) {
        locationCoordinates = location;
    }

    public int getFirePower() {
        final int NO_DAMAGE = 20;
        final int MINOR_DAMAGE = 5;
        final int MAJOR_DAMAGE = 2;
        final int CRITICAL_DAMAGE = 1;
        if (damagedCells == 0) {
            return NO_DAMAGE;
        } else if (damagedCells == 1) {
            return MINOR_DAMAGE;
        } else if (damagedCells == 2) {
            return MAJOR_DAMAGE;
        } else if (damagedCells == 3) {
            return CRITICAL_DAMAGE;
        } else {
            return 0;
        }
    }

    public boolean isHit(String coordinateShot) {
        if (Arrays.asList(locationCoordinates).contains(coordinateShot)) {
            if (!hitCoordinates.isEmpty() && hitCoordinates.contains(coordinateShot)) {
                return true;
            } else {
                increaseDamage();
                hitCoordinates.add(coordinateShot);
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean outOfCommission() {
        return damagedCells == 4;
    }

    private void increaseDamage() {
        damagedCells++;
    }
}
