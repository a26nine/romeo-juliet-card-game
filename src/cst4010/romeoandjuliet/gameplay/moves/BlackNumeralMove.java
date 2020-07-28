package cst4010.romeoandjuliet.gameplay.moves;

public class BlackNumeralMove {
    public int getYPositionCount(int y, int moveCount, String rl) {
        if (rl.charAt(0) == 'R' || rl.charAt(0) == 'r') {
            int position = y + moveCount;
            if (position > 7) {
                do {
                    position -= 7;
                } while (position > 7);
            }
            return position;
        }
        if (rl.charAt(0) == 'L' || rl.charAt(0) == 'l') {
            int position = y - moveCount;
            if (position < 1) {
                do {
                    position += 7;
                } while (position < 1);
            }
            return position;
        } else {
            return 0;
        }
    }
}