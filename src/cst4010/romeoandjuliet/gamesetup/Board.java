package cst4010.romeoandjuliet.gamesetup;

import java.util.ArrayList;

public class Board {
    public final Object[][] cardPosition;
    final ArrayList<Card> cardList;
    int index = 0;

    public Board(Pack pack) {
        cardList = pack.getPack();
        cardPosition = new Object[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i != 0 && j != 0 && !(i == 1 && j == 7) && !(i == 7 && j == 1)) {
                    cardPosition[i][j] = cardList.get(index);
                    index++;
                }
            }
        }
    }

    public void displayBoard(int p1X, int p1Y, int p2X, int p2Y) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i == 0) && (j == 0)) {
                    System.out.printf("%12s", " ");
                } else if ((i == 0) && (j != 0)) {
                    System.out.printf("%12s", j);
                } else if ((i != 0) && (j == 0)) {
                    System.out.printf("%12s", i);
                } else if (i == 1 && j == 7) {
                    if (p1X == 1 && p1Y == 7) {
                        System.out.printf("%12s", "(\u2654) " + "|\u2655|");
                    } else if (p2X == 1 && p2Y == 7) {
                        System.out.printf("%12s", "(\u265A) " + "|\u2655|");
                    } else {
                        System.out.printf("%12s", "|\u2655|");
                    }
                } else if (i == 7 && j == 1) {
                    if (p2X == 7 && p2Y == 1) {
                        System.out.printf("%12s", "(\u265A) " + "|\u265B|");
                    } else if (p1X == 7 && p1Y == 1) {
                        System.out.printf("%12s", "(\u2654) " + "|\u265B|");
                    } else {
                        System.out.printf("%12s", "|\u265B|");
                    }
                } else {
                    if (p1X == i && p1Y == j) {
                        System.out.printf("%12s", "(\u2654) " + cardPosition[i][j]);
                    } else if (p2X == i && p2Y == j) {
                        System.out.printf("%12s", "(\u265A) " + cardPosition[i][j]);
                    } else {
                        System.out.printf("%12s", cardPosition[i][j]);
                    }
                }
            }

            System.out.printf("%n");
        }
    }

    public String getCardString(int x, int y) {
        return cardPosition[x][y].toString();
    }
}