package ch.heigvd.hbcg;

import java.util.*;

public class Deck {

    private List<Card> cards;

    public Deck(){
        randomDeck();
    }

    private void randomDeck() {

        cards = new ArrayList<>();
        for (Colors color : Colors.values()) {
            for (Numbers number : Numbers.values()) {
                cards.add(new Card(color, number));
            }
        }

        Collections.shuffle(cards);

    }

    public Card draw(){
        return cards.remove(cards.size() - 1);
    }

}


