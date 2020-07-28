package cst4010.romeoandjuliet.gameplay.rules;

public class ValidateMove {
    public boolean checkMove(int x, int y, int oppPlayerX, int oppPlayerY, int playerNumber) {
        if (x == oppPlayerX && y == oppPlayerY) {
            return false;
        } else if (playerNumber == 1) {
            return x != 1 || y != 7;
        } else if (playerNumber == 2) {
            return x != 7 || y != 1;
        } else {
            return true;
        }
    }
}
