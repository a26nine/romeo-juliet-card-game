package cst4010.romeoandjuliet.gameplay;

import cst4010.romeoandjuliet.Main;
import cst4010.romeoandjuliet.gameplay.ai.AIPlay;
import cst4010.romeoandjuliet.gameplay.moves.*;
import cst4010.romeoandjuliet.gameplay.rules.ValidateMove;
import cst4010.romeoandjuliet.gameplay.rules.WinningMove;
import cst4010.romeoandjuliet.gamesetup.Board;
import cst4010.romeoandjuliet.gamesetup.Pack;
import cst4010.romeoandjuliet.gamesetup.Player;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {

    final Pack pack;
    final Board board;
    Player p1, p2;
    AIPlay aiPlay;

    final Scanner scanner = new Scanner(System.in);

    public Game(String p1Name, String p2Name) throws InterruptedException {
        playerSetup(p1Name, p2Name);

        System.out.println("\n" + "Shuffling the pack..");
        pack = new Pack();

        System.out.println("Arranging the board.." + "\n");
        board = new Board(pack);

        board.displayBoard(p1.getXPosition(), p1.getYPosition(), p2.getXPosition(), p2.getYPosition());

        play(false);
    }

    public Game(String p1Name) throws InterruptedException {
        playerSetup(p1Name, "Bob (AI)");

        System.out.println("\n" + "Shuffling the pack..");
        pack = new Pack();

        System.out.println("Arranging the board.." + "\n");
        board = new Board(pack);

        board.displayBoard(p1.getXPosition(), p1.getYPosition(), p2.getXPosition(), p2.getYPosition());

        play(true);
    }

    public void playerSetup(String p1Name, String p2Name) {
        p1 = new Player(p1Name, 1);
        p2 = new Player(p2Name, 2);
    }

    public void play(boolean isAI) throws InterruptedException {
        boolean p1Play = true, validMove, isJoker = false, gameOver = false;

        int turn = 1, x, y, choice;

        Swap swap = new Swap();
        WinningMove winningMove = new WinningMove();
        ValidateMove validateMove = new ValidateMove();

        while (!gameOver) {
            if (turn == 1) {
                if (p1Play) {
                    System.out.println("\n" + "<Move " + turn + ">");

                    do {
                        System.out.println("X: ");
                        x = scanner.nextInt();
                        System.out.println("Y: ");
                        y = scanner.nextInt();
                        KingMove kingMove = new KingMove();

                        if ((kingMove.checkMove(p1.getXPosition(), p1.getYPosition(), x, y)) == 1) {
                            p1.setPosition(x, y);
                            System.out.println("\n" + "Valid move!" + "\n" +
                                    p1.getPlayerName() +
                                    "'s new position: [" +
                                    p1.getXPosition() + "][" +
                                    p1.getYPosition() + "]" + "\n");
                            validMove = true;
                        } else {
                            System.out.println("Invalid move, try again!" + "\n");
                            validMove = false;
                        }
                    } while (!validMove);

                    p1Play = !p1Play;
                }

                board.displayBoard(p1.getXPosition(), p1.getYPosition(), p2.getXPosition(), p2.getYPosition());

                if (!p1Play) {
                    System.out.println("\n" + "<Move " + turn + ">");
                    if (isAI) {
                        aiPlay = new AIPlay(board, p1.getXPosition(), p1.getYPosition(),
                                p2.getXPosition(), p2.getYPosition(), true);
                        aiPlay.bestMove();
                        p2.setPosition(aiPlay.getBestX(), aiPlay.getBestY());
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("\n" + p2.getPlayerName() +
                                "'s new position: [" +
                                p2.getXPosition() + "][" +
                                p2.getYPosition() + "]" + "\n");
                        validMove = true;
                    } else {
                        do {
                            System.out.println("X: ");
                            x = scanner.nextInt();
                            System.out.println("Y: ");
                            y = scanner.nextInt();
                            KingMove kingMove = new KingMove();

                            if ((kingMove.checkMove(p2.getXPosition(), p2.getYPosition(), x, y)) == 1) {
                                p2.setPosition(x, y);
                                System.out.println("\n" + "Valid move!" + "\n" +
                                        p2.getPlayerName() +
                                        "'s new position: [" +
                                        p2.getXPosition() + "][" +
                                        p2.getYPosition() + "]" + "\n");
                                validMove = true;
                            } else {
                                System.out.println("Invalid move, try again!" + "\n");
                                validMove = false;
                            }
                        } while (!validMove);
                    }

                    p1Play = !p1Play;
                }
                turn++;
                board.displayBoard(p1.getXPosition(), p1.getYPosition(), p2.getXPosition(), p2.getYPosition());
            } else {
                if (p1Play) {
                    System.out.println("\n" + "<Move " + turn + ">");
                    choice = moveChoice();

                    if (choice == 1) {
                        String card = board.getCardString(p1.getXPosition(), p1.getYPosition());

                        if (card.contains("JOKER")) {
                            do {
                                System.out.println("X: ");
                                x = scanner.nextInt();
                                System.out.println("Y: ");
                                y = scanner.nextInt();

                                JokerMove jokerMove = new JokerMove();

                                if (!validateMove.checkMove(x, y, p2.getXPosition(), p2.getYPosition(),
                                        1)) {
                                    System.out.println("Invalid move, try again!" + "\n");
                                    validMove = false;
                                } else if ((jokerMove.checkMove(p1.getXPosition(), p1.getYPosition(),
                                        x, y)) == 1) {
                                    p1.setPosition(x, y);
                                    System.out.println("\n" + "Valid move!" + "\n" +
                                            p1.getPlayerName() +
                                            "'s new position: [" +
                                            p1.getXPosition() + "][" +
                                            p1.getYPosition() + "]" + "\n");
                                    validMove = true;
                                } else {
                                    System.out.println("Invalid move, try again!" + "\n");
                                    validMove = false;
                                }
                            } while (!validMove);
                        } else if (card.contains("K")) {
                            do {
                                System.out.println("X: ");
                                x = scanner.nextInt();
                                System.out.println("Y: ");
                                y = scanner.nextInt();

                                KingMove kingMove = new KingMove();

                                if (!validateMove.checkMove(x, y, p2.getXPosition(), p2.getYPosition(),
                                        1)) {
                                    System.out.println("Invalid move, try again!" + "\n");
                                    validMove = false;
                                } else if ((kingMove.checkMove(p1.getXPosition(), p1.getYPosition(),
                                        x, y)) == 1) {
                                    p1.setPosition(x, y);
                                    System.out.println("\n" + "Valid move!" + "\n" +
                                            p1.getPlayerName() +
                                            "'s new position: [" +
                                            p1.getXPosition() + "][" +
                                            p1.getYPosition() + "]" + "\n");
                                    validMove = true;
                                } else {
                                    System.out.println("Invalid move, try again!" + "\n");
                                    validMove = false;
                                }
                            } while (!validMove);
                        } else if (card.contains("J")) {
                            do {
                                System.out.println("X: ");
                                x = scanner.nextInt();
                                System.out.println("Y: ");
                                y = scanner.nextInt();

                                KnightMove knightMove = new KnightMove();

                                if (!validateMove.checkMove(x, y, p2.getXPosition(), p2.getYPosition(),
                                        1)) {
                                    System.out.println("Invalid move, try again!" + "\n");
                                    validMove = false;
                                } else if ((knightMove.checkMove(p1.getXPosition(), p1.getYPosition(),
                                        x, y)) == 1) {
                                    p1.setPosition(x, y);
                                    System.out.println("\n" + "Valid move!" + "\n" +
                                            p1.getPlayerName() +
                                            "'s new position: [" +
                                            p1.getXPosition() + "][" +
                                            p1.getYPosition() + "]" + "\n");
                                    validMove = true;
                                } else {
                                    System.out.println("Invalid move, try again!" + "\n");
                                    validMove = false;
                                }
                            } while (!validMove);
                        } else if (card.contains("\u2660") || card.contains("\u2663")) {
                            do {
                                String cardFace = board.getCardString(p1.getXPosition(), p1.getYPosition());
                                int moveCount = Integer.parseInt(cardFace.substring(1, 3).trim());

                                System.out.println("\nMove " + moveCount + " steps, right or left (R/L): ");
                                String rl = scanner.next();

                                BlackNumeralMove blackNumeralMove = new BlackNumeralMove();

                                y = blackNumeralMove.getYPositionCount(p1.getYPosition(), moveCount, rl);

                                if (!validateMove.checkMove(p1.getXPosition(), y,
                                        p2.getXPosition(), p2.getYPosition(), 1)) {
                                    System.out.println("Invalid move, try again!" + "\n");
                                    validMove = false;
                                } else {
                                    if (y == 0) {
                                        System.out.println("Invalid move, try again!" + "\n");
                                        validMove = false;
                                    } else {
                                        p1.setPosition(p1.getXPosition(), y);
                                        System.out.println("\n" + "Valid move!" + "\n" +
                                                p1.getPlayerName() +
                                                "'s new position: [" +
                                                p1.getXPosition() + "][" +
                                                p1.getYPosition() + "]" + "\n");
                                        validMove = true;
                                    }
                                }
                            } while (!validMove);
                        } else if (card.contains("\u2661") || card.contains("\u2662")) {
                            do {
                                String cardFace = board.getCardString(p1.getXPosition(), p1.getYPosition());
                                int moveCount = Integer.parseInt(cardFace.substring(1, 3).trim());

                                System.out.println("\nMove " + moveCount + " steps, up or down (U/D): ");
                                String ud = scanner.next();

                                RedNumeralMove redNumeralMove = new RedNumeralMove();

                                x = redNumeralMove.getXPositionCount(p1.getXPosition(), moveCount, ud);

                                if (!validateMove.checkMove(x, p1.getYPosition(),
                                        p2.getXPosition(), p2.getYPosition(), 1)) {
                                    System.out.println("Invalid move, try again!" + "\n");
                                    validMove = false;
                                } else {
                                    p1.setPosition(x, p1.getYPosition());
                                    System.out.println("\n" + "Valid move!" + "\n" +
                                            p1.getPlayerName() +
                                            "'s new position: [" +
                                            p1.getXPosition() + "][" +
                                            p1.getYPosition() + "]" + "\n");
                                    validMove = true;
                                }

                                if (x == 0) {
                                    System.out.println("Invalid move, try again!" + "\n");
                                    validMove = false;
                                }
                            } while (!validMove);
                        } else {
                            break;
                        }

                        p1Play = !p1Play;
                    }

                    if (choice == 2) {
                        int jokerX, jokerY;
                        System.out.println("\n" + "Enter JOKER position.. ");

                        do {
                            System.out.println("X: ");
                            jokerX = scanner.nextInt();
                            System.out.println("Y: ");
                            jokerY = scanner.nextInt();

                            String card = board.getCardString(jokerX, jokerY);

                            if (card.contains("JOKER")) {
                                isJoker = true;
                            } else {
                                System.out.println("Card is not a JOKER card, try again!" + "\n");
                            }
                        } while (!isJoker);

                        System.out.println("\n" + "Enter card position.. ");

                        do {
                            System.out.println("X: ");
                            x = scanner.nextInt();
                            System.out.println("Y: ");
                            y = scanner.nextInt();

                            if (swap.getSwapX() == x && swap.getSwapY() == y) {
                                System.out.println("Cannot perform swap on the same card again!" +
                                        "Please select another card to swap.. \n");
                                validMove = false;
                            } else if (board.getCardString(x, y).contains("JOKER")) {
                                System.out.println("Cannot perform swap of joker with joker! " +
                                        "Please select another card to swap..\n");
                                validMove = false;
                            } else if ((x == p2.getXPosition() && y == p2.getYPosition()) ||
                                    (jokerX == p2.getXPosition() && jokerY == p2.getYPosition()) ||
                                    (x == p1.getXPosition() && y == p1.getYPosition()) ||
                                    (jokerX == p1.getXPosition() && jokerY == p1.getYPosition())) {
                                System.out.println("Cannot perform swap on card occupied by self or the opponent! " +
                                        "Please select another card to swap..\n");
                                validMove = false;
                            } else if (swap.checkSwap(x, y, jokerX, jokerY) == 1) {
                                board.cardPosition[jokerX][jokerY] = board.cardPosition[x][y];
                                board.cardPosition[x][y] = "JOKER";
                                System.out.println("\n" + "Valid swap!" + "\n" +
                                        "JOKER swapped from [" + jokerX + "][" + jokerY + "]" +
                                        " to [" + x + "][" + y + "]" + "\n");
                                swap.storeSwap(jokerX, jokerY);
                                validMove = true;
                            } else {
                                System.out.println("Invalid swap, try again!" + "\n");
                                validMove = false;
                            }

                        } while (!validMove);

                        p1Play = !p1Play;
                    }
                }

                if ((winningMove.checkMove(p1.getXPosition(), p1.getYPosition())) == 1) {
                    gameOver = true;
                    System.out.println(p1.getPlayerName() + " wins!" + "\n");
                    board.displayBoard(p1.getXPosition(), p1.getYPosition(), p2.getXPosition(), p2.getYPosition());
                    break;
                }

                board.displayBoard(p1.getXPosition(), p1.getYPosition(), p2.getXPosition(), p2.getYPosition());

                if (!p1Play) {
                    System.out.println("\n" + "<Move " + turn + ">");
                    if (isAI) {
                        aiPlay = new AIPlay(board, p1.getXPosition(), p1.getYPosition(),
                                p2.getXPosition(), p2.getYPosition(), false);
                        aiPlay.bestMove();
                        p2.setPosition(aiPlay.getBestX(), aiPlay.getBestY());
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("\n" + p2.getPlayerName() +
                                "'s new position: [" +
                                p2.getXPosition() + "][" +
                                p2.getYPosition() + "]" + "\n");
                        validMove = true;
                        p1Play = !p1Play;
                    } else {
                        choice = moveChoice();

                        if (choice == 1) {
                            String card = board.getCardString(p2.getXPosition(), p2.getYPosition());

                            if (card.contains("JOKER")) {
                                do {
                                    System.out.println("X: ");
                                    x = scanner.nextInt();
                                    System.out.println("Y: ");
                                    y = scanner.nextInt();

                                    JokerMove jokerMove = new JokerMove();

                                    if (!validateMove.checkMove(x, y, p1.getXPosition(), p1.getYPosition(),
                                            2)) {
                                        System.out.println("Invalid move, try again!" + "\n");
                                        validMove = false;
                                    } else if ((jokerMove.checkMove(p2.getXPosition(), p2.getYPosition(),
                                            x, y)) == 1) {
                                        p2.setPosition(x, y);
                                        System.out.println("\n" + "Valid move!" + "\n" +
                                                p2.getPlayerName() +
                                                "'s new position: [" +
                                                p2.getXPosition() + "][" +
                                                p2.getYPosition() + "]" + "\n");
                                        validMove = true;
                                    } else {
                                        System.out.println("Invalid move, try again!" + "\n");
                                        validMove = false;
                                    }
                                } while (!validMove);
                            } else if (card.contains("K")) {
                                do {
                                    System.out.println("X: ");
                                    x = scanner.nextInt();
                                    System.out.println("Y: ");
                                    y = scanner.nextInt();

                                    KingMove kingMove = new KingMove();

                                    if (!validateMove.checkMove(x, y, p1.getXPosition(), p1.getYPosition(),
                                            2)) {
                                        System.out.println("Invalid move, try again!" + "\n");
                                        validMove = false;
                                    } else if ((kingMove.checkMove(p2.getXPosition(), p2.getYPosition(),
                                            x, y)) == 1) {
                                        p2.setPosition(x, y);
                                        System.out.println("\n" + "Valid move!" + "\n" +
                                                p2.getPlayerName() +
                                                "'s new position: [" +
                                                p2.getXPosition() + "][" +
                                                p2.getYPosition() + "]" + "\n");
                                        validMove = true;
                                    } else {
                                        System.out.println("Invalid move, try again!" + "\n");
                                        validMove = false;
                                    }
                                } while (!validMove);
                            } else if (card.contains("J")) {
                                do {
                                    System.out.println("X: ");
                                    x = scanner.nextInt();
                                    System.out.println("Y: ");
                                    y = scanner.nextInt();

                                    KnightMove knightMove = new KnightMove();

                                    if (!validateMove.checkMove(x, y, p1.getXPosition(), p1.getYPosition(),
                                            2)) {
                                        System.out.println("Invalid move, try again..!" + "\n");
                                        validMove = false;
                                    } else if ((knightMove.checkMove(p2.getXPosition(), p2.getYPosition(),
                                            x, y)) == 1) {
                                        p2.setPosition(x, y);
                                        System.out.println("\n" + "Valid move!" + "\n" +
                                                p2.getPlayerName() +
                                                "'s new position: [" +
                                                p2.getXPosition() + "][" +
                                                p2.getYPosition() + "]" + "\n");
                                        validMove = true;
                                    } else {
                                        System.out.println("Invalid move, try again!" + "\n");
                                        validMove = false;
                                    }
                                } while (!validMove);
                            } else if (card.contains("\u2660") || card.contains("\u2663")) {
                                do {
                                    String cardFace = board.getCardString(p2.getXPosition(), p2.getYPosition());
                                    int moveCount = Integer.parseInt(cardFace.substring(1, 3).trim());

                                    System.out.println("\nMove " + moveCount + " steps, right or left (R/L): ");
                                    String rl = scanner.next();

                                    BlackNumeralMove blackNumeralMove = new BlackNumeralMove();

                                    y = blackNumeralMove.getYPositionCount(p2.getYPosition(), moveCount, rl
                                    );

                                    if (!validateMove.checkMove(p2.getXPosition(), y,
                                            p1.getXPosition(), p1.getYPosition(), 2)) {
                                        System.out.println("Invalid move, try again!" + "\n");
                                        validMove = false;
                                    } else {
                                        p2.setPosition(p2.getXPosition(), y);
                                        System.out.println("\n" + "Valid move!" + "\n" +
                                                p2.getPlayerName() +
                                                "'s new position: [" +
                                                p2.getXPosition() + "][" +
                                                p2.getYPosition() + "]" + "\n");
                                        validMove = true;
                                    }

                                    if (y == 0) {
                                        System.out.println("Invalid move, try again!" + "\n");
                                        validMove = false;
                                    }
                                } while (!validMove);
                            } else if (card.contains("\u2661") || card.contains("\u2662")) {
                                do {
                                    String cardFace = board.getCardString(p2.getXPosition(), p2.getYPosition());
                                    int moveCount = Integer.parseInt(cardFace.substring(1, 3).trim());

                                    System.out.println("\nMove " + moveCount + " steps, up or down (U/D): ");
                                    String ud = scanner.next();

                                    RedNumeralMove redNumeralMove = new RedNumeralMove();

                                    x = redNumeralMove.getXPositionCount(p2.getXPosition(), moveCount, ud);

                                    if (!validateMove.checkMove(x, p2.getYPosition(),
                                            p1.getXPosition(), p1.getYPosition(), 2)) {
                                        System.out.println("Invalid move, try again!" + "\n");
                                        validMove = false;
                                    } else {
                                        p2.setPosition(x, p2.getYPosition());
                                        System.out.println("\n" + "Valid move!" + "\n" +
                                                p2.getPlayerName() +
                                                "'s new position: [" +
                                                p2.getXPosition() + "][" +
                                                p2.getYPosition() + "]" + "\n");
                                        validMove = true;
                                    }

                                    if (x == 0) {
                                        System.out.println("Invalid move, try again!" + "\n");
                                        validMove = false;
                                    }
                                } while (!validMove);
                            } else {
                                break;
                            }

                            p1Play = !p1Play;
                        }

                        if (choice == 2) {
                            int jokerX, jokerY;
                            System.out.println("\n" + "Enter JOKER position.. ");

                            do {
                                System.out.println("X: ");
                                jokerX = scanner.nextInt();
                                System.out.println("Y: ");
                                jokerY = scanner.nextInt();

                                String card = board.getCardString(jokerX, jokerY);

                                if (card.contains("JOKER")) {
                                    isJoker = true;
                                } else {
                                    System.out.println("Card is not a JOKER card, try again!" + "\n");
                                }
                            } while (!isJoker);

                            System.out.println("\n" + "Enter card position.. ");

                            do {
                                System.out.println("X: ");
                                x = scanner.nextInt();
                                System.out.println("Y: ");
                                y = scanner.nextInt();

                                //Swap swap = new Swap();

                                if (swap.getSwapX() == x && swap.getSwapY() == y) {
                                    System.out.println("Cannot perform swap on the same card again!" +
                                            "Please select another card to swap.. \n");
                                    validMove = false;
                                } else if ((x == p2.getXPosition() && y == p2.getYPosition()) ||
                                        (jokerX == p2.getXPosition() && jokerY == p2.getYPosition()) ||
                                        (x == p1.getXPosition() && y == p1.getYPosition()) ||
                                        (jokerX == p1.getXPosition() && jokerY == p1.getYPosition())) {
                                    System.out.println("Cannot perform swap on card occupied by self or the opponent! " +
                                            "Please select another card to swap..\n");
                                    validMove = false;
                                } else if (board.getCardString(x, y).contains("JOKER")) {
                                    System.out.println("Cannot perform swap of joker with joker! " +
                                            "Please select another card to swap..\n");
                                    validMove = false;
                                } else if (swap.checkSwap(x, y, jokerX, jokerY) == 1) {
                                    board.cardPosition[jokerX][jokerY] = board.cardPosition[x][y];
                                    board.cardPosition[x][y] = "JOKER";
                                    System.out.println("\n" + "Valid swap!" + "\n" +
                                            "JOKER swapped from [" + jokerX + "][" + jokerY + "]" +
                                            " to [" + x + "][" + y + "]" + "\n");
                                    swap.storeSwap(jokerX, jokerY);
                                    validMove = true;
                                } else {
                                    System.out.println("Invalid swap, try again!" + "\n");
                                    validMove = false;
                                }

                            } while (!validMove);

                            p1Play = !p1Play;
                        }
                    }
                }

                if ((winningMove.checkMove(p2.getXPosition(), p2.getYPosition())) == 1) {
                    gameOver = true;
                    System.out.println(p2.getPlayerName() + " wins!" + "\n");
                    board.displayBoard(p1.getXPosition(), p1.getYPosition(), p2.getXPosition(), p2.getYPosition());
                    break;
                }

                turn++;
                board.displayBoard(p1.getXPosition(), p1.getYPosition(), p2.getXPosition(), p2.getYPosition());
            }
        }

        System.out.println("Play again? (Y/N)");

        String restart = scanner.next();

        if (restart.charAt(0) == 'Y' || restart.charAt(0) == 'y') {
            Main.mainMenu();
        } else {
            System.exit(0);
        }
    }

    public int moveChoice() {
        int choice;

        do {
            System.out.println("\n1. Make a move" + "\n" +
                    "2. Swap a card");
            System.out.println("Enter your choice (1-2): ");
            choice = scanner.nextInt();
        } while (choice < 1 || choice > 2);

        return choice;
    }
}