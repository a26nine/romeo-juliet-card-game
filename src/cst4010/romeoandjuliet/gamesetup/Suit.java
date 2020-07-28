package cst4010.romeoandjuliet.gamesetup;

public enum Suit {
    SPADES("\u2660"),
    HEART("\u2661"),
    CLUB("\u2663"),
    DIAMOND("\u2662");

    private final String suitUnicode;

    Suit(String suitUnicode) {
        this.suitUnicode = suitUnicode;
    }

    public String getSuitUnicode() {
        return suitUnicode;
    }
}
