package cst4010.romeoandjuliet.gameplay.moves;

public class KnightMove {
    public int checkMove(int xPosition, int yPosition, int newXPosition, int newYPosition) {
        if (newXPosition != xPosition || newYPosition != yPosition) {
            if (newXPosition < 8 && newXPosition > 0 && newYPosition < 8 && newYPosition > 0 &&
                    ((newXPosition == xPosition + 1 && newYPosition == yPosition - 2) ||
                            (newXPosition == xPosition + 1 && newYPosition == yPosition - 2) ||
                            (newXPosition == xPosition + 1 && newYPosition == yPosition + 2) ||
                            (newXPosition == xPosition + 2 && newYPosition == yPosition - 1) ||
                            (newXPosition == xPosition + 2 && newYPosition == yPosition + 1) ||
                            (newXPosition == xPosition - 1 && newYPosition == yPosition + 2) ||
                            (newXPosition == xPosition - 1 && newYPosition == yPosition - 2) ||
                            (newXPosition == xPosition - 2 && newYPosition == yPosition + 1) ||
                            (newXPosition == xPosition - 2 && newYPosition == yPosition - 1))) {
                return 1;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }
}
