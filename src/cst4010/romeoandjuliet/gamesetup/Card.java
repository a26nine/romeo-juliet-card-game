package cst4010.romeoandjuliet.gamesetup;

public class Card {
    private Face face;
    private Suit suit;
    private int joker = 0;

    public Card() {
        joker = 1;
    }

    public Card(Face face, Suit suit) {
        this.face = face;
        this.suit = suit;
    }

    public String toString() {
        if (joker == 1) {
            return "JOKER";
        } else if (face.getFaceValue() == 13) {
            return "[K " + suit.getSuitUnicode() + "]";
        } else if (face.getFaceValue() == 11) {
            return "[J " + suit.getSuitUnicode() + "]";
        } else {
            return "[" + face.getFaceValue() + " " + suit.getSuitUnicode() + "]";
        }
    }
}
