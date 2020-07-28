package cst4010.romeoandjuliet.gamesetup;

public class Player {
    private final String playerName;
    private int xPosition, yPosition;

    public Player(String playerName, int playerNumber) {
        this.playerName = playerName;

        if (playerNumber == 1) {
            this.xPosition = 1;
            this.yPosition = 7;
        }

        if (playerNumber == 2) {
            this.xPosition = 7;
            this.yPosition = 1;
        }
    }

    public void setPosition(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public String getPlayerName() {
        return playerName;
    }
}