package cst4010.romeoandjuliet.gamesetup;

public enum Face {
    ACE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(11),
    KING(13);

    private final int faceValue;

    Face(int faceValue) {
        this.faceValue = faceValue;
    }

    public int getFaceValue() {
        return faceValue;
    }
}
