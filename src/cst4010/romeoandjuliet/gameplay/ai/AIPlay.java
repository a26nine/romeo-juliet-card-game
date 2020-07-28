package cst4010.romeoandjuliet.gameplay.ai;

import cst4010.romeoandjuliet.gameplay.moves.*;
import cst4010.romeoandjuliet.gameplay.rules.ValidateMove;
import cst4010.romeoandjuliet.gamesetup.Board;

public class AIPlay {

    final Board board;
    final String card;
    final int p1X;
    final int p1Y;
    final int p2X;
    final int p2Y;
    int score;
    int score1;
    int score2;
    int bestScore = 0;
    int bestX;
    int bestY;
    final ValidateMove validateMove = new ValidateMove();
    final JokerMove jokerMove = new JokerMove();
    final KingMove kingMove = new KingMove();
    final KnightMove knightMove = new KnightMove();
    final BlackNumeralMove blackNumeralMove = new BlackNumeralMove();
    final RedNumeralMove redNumeralMove = new RedNumeralMove();

    public AIPlay(Board board, int p1X, int p1Y, int p2X, int p2Y, boolean isFirstMove) {
        this.board = board;
        if (isFirstMove) {
            this.card = "";
        } else {
            this.card = board.getCardString(p2X, p2Y);
        }
        this.p1X = p1X;
        this.p1Y = p1Y;
        this.p2X = p2X;
        this.p2Y = p2Y;
    }

    public int getBestX() {
        return bestX;
    }

    public int getBestY() {
        return bestY;
    }

    public void bestMove() {
        if (card.contains("JOKER")) {
            for (int i = 1; i < 8; i++) {
                for (int j = 1; j < 8; j++) {
                    if (validateMove.checkMove(i, j, p1X, p1Y, 2)) {
                        if (jokerMove.checkMove(p2X, p2Y, i, j) == 1) {
                            score = ((7 - i) + 1) * j;
                            if (score > bestScore) {
                                bestScore = score;
                                bestX = i;
                                bestY = j;
                            }
                        }
                    }
                }
            }
        } else if (card.contains("K")) {
            for (int i = 1; i < 8; i++) {
                for (int j = 1; j < 8; j++) {
                    if (validateMove.checkMove(i, j, p1X, p1Y, 2)) {
                        if (kingMove.checkMove(p2X, p2Y, i, j) == 1) {
                            score = ((7 - i) + 1) * j;
                            if (score > bestScore) {
                                bestScore = score;
                                bestX = i;
                                bestY = j;
                            }
                        }
                    }
                }
            }
        } else if (card.contains("J")) {
            for (int i = 1; i < 8; i++) {
                for (int j = 1; j < 8; j++) {
                    if (validateMove.checkMove(i, j, p1X, p1Y, 2)) {
                        if (knightMove.checkMove(p2X, p2Y, i, j) == 1) {
                            score = ((7 - i) + 1) * j;
                            if (score > bestScore) {
                                bestScore = score;
                                bestX = i;
                                bestY = j;
                            }
                        }
                    }
                }
            }
        } else if (card.contains("\u2660") || card.contains("\u2663")) {
            bestX = p2X;
            String cardFace = board.getCardString(p2X, p2Y);
            int moveCount = Integer.parseInt(cardFace.substring(1, 3).trim());
            int newPosition1 = blackNumeralMove.getYPositionCount(p2Y, moveCount, "l");
            if (validateMove.checkMove(bestX, newPosition1, p1X, p1Y, 2)) {
                score1 = ((7 - p2X) + 1) * newPosition1;
            } else {
                score1 = 0;
            }
            int newPosition2 = blackNumeralMove.getYPositionCount(p2Y, moveCount, "r");
            if (validateMove.checkMove(bestX, newPosition2, p1X, p1Y, 2)) {
                score2 = ((7 - p2X) + 1) * newPosition2;
            } else {
                score2 = 0;
            }
            if (score1 > score2) {
                bestScore = score1;
                bestY = newPosition1;
            } else {
                bestScore = score2;
                bestY = newPosition2;
            }
        } else if (card.contains("\u2661") || card.contains("\u2662")) {
            bestY = p2Y;
            String cardFace = board.getCardString(p2X, p2Y);
            int moveCount = Integer.parseInt(cardFace.substring(1, 3).trim());
            int newPosition1 = redNumeralMove.getXPositionCount(p2X, moveCount, "u");
            if (validateMove.checkMove(newPosition1, bestY, p1X, p1Y, 2)) {
                score1 = ((7 - newPosition1) + 1) * p2Y;
            } else {
                score1 = 0;
            }
            int newPosition2 = redNumeralMove.getXPositionCount(p2X, moveCount, "d");
            if (validateMove.checkMove(newPosition2, bestY, p1X, p1Y, 2)) {
                score1 = ((7 - newPosition2) + 1) * p2Y;
            } else {
                score1 = 0;
            }
            if (score1 > score2) {
                bestScore = score1;
                bestX = newPosition1;
            } else {
                bestScore = score2;
                bestX = newPosition2;
            }
        } else {
            for (int i = 1; i < 8; i++) {
                for (int j = 1; j < 8; j++) {
                    if (validateMove.checkMove(i, j, p1X, p1Y, 2)) {
                        if (kingMove.checkMove(p2X, p2Y, i, j) == 1) {
                            score = ((7 - i) + 1) * j;
                            if (score > bestScore) {
                                bestScore = score;
                                bestX = i;
                                bestY = j;
                            }
                        }
                    }
                }
            }
        }
    }
}
