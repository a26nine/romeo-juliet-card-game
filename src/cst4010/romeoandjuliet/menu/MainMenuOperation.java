package cst4010.romeoandjuliet.menu;

import cst4010.romeoandjuliet.Main;
import cst4010.romeoandjuliet.gameplay.Game;

import java.util.Scanner;

public class MainMenuOperation {
    public MainMenuOperation(int choice) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String p1Name, p2Name;

        switch (choice) {
            case 1:
                System.out.println("\n" + "Enter Player 1 (\u2654) Name: ");
                p1Name = scanner.nextLine();

                System.out.println("\n" + "Enter Player 2 (\u265A) Name: ");
                p2Name = scanner.nextLine();

                new Game(p1Name, p2Name);

            case 2:
                System.out.println("\n" + "Enter Player (\u2654) Name: ");
                p1Name = scanner.nextLine();

                new Game(p1Name);

            case 3:
                final String rulesURL = "https://www.parlettgames.uk/oricards/romeo.html";
                System.out.println("\n" + "Romeo & Juliet card game by David Parlett" + "\n" +
                        "Game Rules: " + rulesURL + "\n\n" +
                        "Adapted from an unpublished game called Card Maze by Eric Solomon.\n");
                Main.mainMenu();

            case 4:
                System.out.println("\n" +
                        "Exiting game..");
                System.exit(0);

            default:
                System.out.println("\n" +
                        "Invalid choice! Please try again.." + "\n");
                break;
        }
    }
}