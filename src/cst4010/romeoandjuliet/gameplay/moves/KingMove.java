package cst4010.romeoandjuliet.gameplay.moves;

public class KingMove {
    public int checkMove(int xPosition, int yPosition, int newXPosition, int newYPosition) {
        if (newXPosition != xPosition || newYPosition != yPosition) {
            if (newXPosition < 8 && newXPosition > 0 && newYPosition < 8 && newYPosition > 0 &&
                    (newXPosition == (1 + xPosition) || newXPosition == (xPosition - 1) || newXPosition == xPosition) &&
                    (newYPosition == (1 + yPosition) || newYPosition == (yPosition - 1) || newYPosition == yPosition)) {
                return 1;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }
}
