package ca.cmpt213.as2.gamelogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Generates 4 random coordinates used for each tank in the GameBoard. Keeps track of coordinates used.
 * Currently only supports a 10 x 10 grid
 */
public class TankPlacement {

    private List<String> usedCoordinates;
    private final String[][] VALID_COORDINATES;

    private enum Directions {
        UP, DOWN, LEFT, RIGHT
    }

    public TankPlacement() {
        usedCoordinates = new ArrayList<>();
        VALID_COORDINATES = new String[][]{
                {"a1", "a2", "a3", "a4", "a5", "a6", "a7", "a8", "a9", "a10"},
                {"b1", "b2", "b3", "b4", "b5", "b6", "b7", "b8", "b9", "b10"},
                {"c1", "c2", "c3", "c4", "c5", "c6", "c7", "c8", "c9", "c10"},
                {"d1", "d2", "d3", "d4", "d5", "d6", "d7", "d8", "d9", "d10"},
                {"e1", "e2", "e3", "e4", "e5", "e6", "e7", "e8", "e9", "e10"},
                {"f1", "f2", "f3", "f4", "f5", "f6", "f7", "f8", "f9", "f10"},
                {"g1", "g2", "g3", "g4", "g5", "g6", "g7", "g8", "g9", "g10"},
                {"h1", "h2", "h3", "h4", "h5", "h6", "h7", "h8", "h9", "h10"},
                {"i1", "i2", "i3", "i4", "i5", "i6", "i7", "i8", "i9", "i10"},
                {"j1", "j2", "j3", "j4", "j5", "j6", "j7", "j8", "j9", "j10"},
        };
    }

    public String[] randomPlacement() {

        String[] tankCoordinates = new String[4];

        int xIndex = new Random().nextInt(VALID_COORDINATES[0].length - 1);
        int yIndex = new Random().nextInt(VALID_COORDINATES.length - 1);
        while (usedCoordinates.contains(VALID_COORDINATES[xIndex][yIndex])) {
            xIndex = new Random().nextInt(VALID_COORDINATES[0].length - 1);
            yIndex = new Random().nextInt(VALID_COORDINATES.length - 1);
        }
        String selectedCoordinate = VALID_COORDINATES[xIndex][yIndex];
        tankCoordinates[0] = selectedCoordinate;

        int coordinateIndex = 1;
        while (coordinateIndex < 4) {
            usedCoordinates.add(VALID_COORDINATES[xIndex][yIndex]);
            List<Directions> directionsSet = new ArrayList<>();
            directionsSet.add(Directions.UP);
            directionsSet.add(Directions.DOWN);
            directionsSet.add(Directions.LEFT);
            directionsSet.add(Directions.RIGHT);
            Collections.shuffle(directionsSet);
            while (!isWithinBoundaries(xIndex, yIndex, directionsSet.get(0))) {
                directionsSet.remove(0);
            }
            while (usedCoordinates.contains(convertDirections(xIndex, yIndex, directionsSet.get(0)))) {
                directionsSet.remove(0);
            }
            if (directionsSet.get(0) == Directions.UP) {
                String tankCompartmentCoordinate = VALID_COORDINATES[xIndex][yIndex - 1];
                tankCoordinates[coordinateIndex] = tankCompartmentCoordinate;
                yIndex--;
            } else if (directionsSet.get(0) == Directions.DOWN) {
                String tankCompartmentCoordinate = VALID_COORDINATES[xIndex][yIndex + 1];
                tankCoordinates[coordinateIndex] = tankCompartmentCoordinate;
                yIndex++;
            } else if (directionsSet.get(0) == Directions.LEFT) {
                String tankCompartmentCoordinate = VALID_COORDINATES[xIndex - 1][yIndex];
                tankCoordinates[coordinateIndex] = tankCompartmentCoordinate;
                xIndex--;
            } else if (directionsSet.get(0) == Directions.RIGHT) {
                String tankCompartmentCoordinate = VALID_COORDINATES[xIndex + 1][yIndex];
                tankCoordinates[coordinateIndex] = tankCompartmentCoordinate;
                xIndex++;
            }
            coordinateIndex++;
        }
        return tankCoordinates;
    }

    private boolean isWithinBoundaries(int xIndex, int yIndex, Directions direction) {
        if ((yIndex - 1 >= 0) && (direction == Directions.UP)) {
            return true;
        } else if ((yIndex + 1 <= VALID_COORDINATES.length - 1) && (direction == Directions.DOWN)) {
            return true;
        } else if ((xIndex - 1 >= 0) && (direction == Directions.LEFT)) {
            return true;
        } else if ((xIndex + 1 <= VALID_COORDINATES[0].length - 1) && (direction == Directions.RIGHT)) {
            return true;
        } else {
            return false;
        }
    }

    private String convertDirections(int xIndex, int yIndex, Directions direction) {
        if ((yIndex - 1 >= 0) && (direction == Directions.UP)) {
            return VALID_COORDINATES[xIndex][yIndex - 1];
        } else if ((yIndex + 1 <= VALID_COORDINATES.length - 1) && (direction == Directions.DOWN)) {
            return VALID_COORDINATES[xIndex][yIndex + 1];
        } else if ((xIndex - 1 >= 0) && (direction == Directions.LEFT)) {
            return VALID_COORDINATES[xIndex - 1][yIndex];
        } else if ((xIndex + 1 <= VALID_COORDINATES[0].length - 1) && (direction == Directions.RIGHT)) {
            return VALID_COORDINATES[xIndex + 1][yIndex];
        } else {
            return null;
        }
    }

}