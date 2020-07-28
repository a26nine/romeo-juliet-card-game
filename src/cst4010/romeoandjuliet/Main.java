package cst4010.romeoandjuliet;

import cst4010.romeoandjuliet.menu.MainMenuOperation;

import java.util.Scanner;

public class Main {
    public static void mainMenu() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("* Romeo & Juliet * (v1.20200502)");
            System.out.println("1. Play 'Player vs Player' mode" + "\n" +
                    "2. Play 'Player vs AI' mode" + "\n" +
                    "3. Game description" + "\n" +
                    "4. Quit the game");
            System.out.println("\n" + "Enter your choice (1-4): ");
            choice = scanner.nextInt();
            new MainMenuOperation(choice);
        } while (choice < 1 || choice > 4);
    }

    public static void main(String[] args) {
        try {
            mainMenu();
        } catch (Exception e) {
            System.out.println("\n" + "Invalid Choice! Exiting game..");
        }
    }
}