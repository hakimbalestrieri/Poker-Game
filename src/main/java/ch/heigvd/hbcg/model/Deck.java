package ch.heigvd.hbcg.model;

import java.util.*;

/**
 * Classe Deck
 * Represente une liste de cartes
 * @authors Gomes & Balestrieri
 */
public class Deck {

    private Set<Card> cards;

    public Deck(){
        randomDeck();
    }

    private void randomDeck() {

        cards = new HashSet<>();
        for (Colors color : Colors.values()) {
            for (Numbers number : Numbers.values()) {
                cards.add(new Card(color, number));
            }
        }

    }

    public Card draw(){

        Card card = null;
        Iterator it = cards.iterator();
        if(it.hasNext()) {
            card = (Card) it.next();
            it.remove();
        }
        return card;
    }

    public int size(){
        return cards.size();
    }
}


