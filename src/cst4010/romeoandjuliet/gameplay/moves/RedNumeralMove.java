package cst4010.romeoandjuliet.gameplay.moves;

public class RedNumeralMove {
    public int getXPositionCount(int x, int moveCount, String ud) {
        if (ud.charAt(0) == 'D' || ud.charAt(0) == 'd') {
            int position = x + moveCount;
            if (position > 7) {
                do {
                    position -= 7;
                } while (position > 7);
            }
            return position;
        }
        if (ud.charAt(0) == 'U' || ud.charAt(0) == 'u') {
            int position = x - moveCount;
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
