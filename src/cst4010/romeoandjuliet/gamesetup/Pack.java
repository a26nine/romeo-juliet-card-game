package cst4010.romeoandjuliet.gamesetup;

import java.util.ArrayList;
import java.util.Collections;

public class Pack {
    private final ArrayList<Card> cards;

    public Pack() {
        cards = new ArrayList<>();

        for (Suit suit : Suit.values()) {
            for (Face face : Face.values()) {
                Card card = new Card(face, suit);
                cards.add(card);
            }
        }

        for (int i = 0; i < 3; i++) {
            Card card = new Card();
            cards.add(card);
        }

        Collections.shuffle(cards);
    }

    public ArrayList<Card> getPack() {
        return cards;
    }
}
