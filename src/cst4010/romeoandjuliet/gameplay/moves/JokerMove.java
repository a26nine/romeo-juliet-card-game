package cst4010.romeoandjuliet.gameplay.moves;

public class JokerMove {
    public int checkMove(int xPosition, int yPosition, int newXPosition, int newYPosition) {
        if ((newXPosition == xPosition && newYPosition == yPosition) || (newXPosition == 7 && newYPosition == 1) ||
                (newXPosition == 1 && newYPosition == 7) || (newXPosition > 7 || newXPosition < 1) ||
                (newYPosition > 7 || newYPosition < 1)) {
            return -1;
        } else {
            return 1;
        }
    }
}
