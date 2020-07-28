package cst4010.romeoandjuliet.gameplay.rules;

public class WinningMove {
    private int gameFlag = 0;

    public int checkMove(int xPosition, int yPosition) {
        if ((xPosition == 7 && yPosition == 1) ||
                (xPosition == 1 && yPosition == 7)) {
            gameFlag = 1;
        }
        return gameFlag;
    }
}