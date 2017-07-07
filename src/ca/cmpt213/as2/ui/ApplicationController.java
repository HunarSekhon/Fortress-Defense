package ca.cmpt213.as2.ui;

import ca.cmpt213.as2.gamelogic.Fortress;
import ca.cmpt213.as2.gamelogic.InputValidity;

import java.util.Scanner;

/**
 * Main class for the application.
 * Creates a Fortress object, applies damage taken from GameBoard. Controls game win/lose situation.
 */
public class ApplicationController {
    public static void main(String args[]) {
        welcomeMessage();
        GameBoard gameBoard = new GameBoard(10);
        Fortress fortress = new Fortress(1500);
        System.out.println("Fortress Structure Left: " + fortress.getHealth() + ".");
        boolean isFortressAlive = true;
        int tankFirePower = 1;
        while (tankFirePower != 0) {
            String userShot = getInput();
            while (!InputValidity.isValidInput(userShot)) {
                userShot = getInput();
            }
            gameBoard.fireShotCoordinate(userShot);
            tankFirePower = gameBoard.tankFireAll();
            fortress.reduceHealth(tankFirePower);
            System.out.println();
            gameBoard.displayBoard();
            System.out.println("Fortress Structure Left: " + fortress.getHealth() + ".");
            if (fortress.getHealth() == 0) {
                isFortressAlive = false;
                break;
            }
        }
        if (isFortressAlive) {
            System.out.println("Congratulations! You won!");
            System.out.println("Fortress Structure Left: " + fortress.getHealth() + ".");
        } else {
            System.out.println("I'm sorry, your fortress has been smashed!\n");
            gameBoard.revealAll();
            System.out.println("Fortress Structure Left: " + fortress.getHealth() + ".");
        }
    }

    private static String getInput() {
        System.out.print("Enter your move: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    private static void welcomeMessage() {
        System.out.println("--------------------------------");
        System.out.println("Welcome to Fortress Defense!");
        System.out.println("by Darryl Fonseka & Hunar Sekhon");
        System.out.println("--------------------------------");
    }
}
