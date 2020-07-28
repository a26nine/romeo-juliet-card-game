package cst4010.romeoandjuliet.gameplay.moves;

public class Swap {
    private int swapX, swapY;

    public Swap() {
        swapX = 0;
        swapY = 0;
    }

    public int checkSwap(int xPosition, int yPosition, int jokerX, int jokerY) {
        if ((xPosition == 1 && yPosition == 7) || (xPosition == 7 && yPosition == 1) ||
                (xPosition < 1 || xPosition > 7) || (yPosition < 1 || yPosition > 7)) {
            return -1;
        } else if (xPosition == jokerX || yPosition == jokerY) {
            return 1;
        } else {
            return -1;
        }
    }

    public void storeSwap(int swapX, int swapY) {
        this.swapX = swapX;
        this.swapY = swapY;
    }

    public int getSwapX() {
        return swapX;
    }

    public int getSwapY() {
        return swapY;
    }
}
