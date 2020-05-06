package ch.heigvd.hbcg.model;

import java.util.*;

/**
 * Classe Deck
 * Represente une liste de cartes
 * @authors Gomes & Balestrieri
 */
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

        /*Card card = null;
        Iterator it = cards.iterator();
        if(it.hasNext()) {
            card = (Card) it.next();
            it.remove();
        }

        */
        Card card = null;
        card = cards.get(0);
        System.out.println(card);
        cards.remove(0);
        return card;
    }

    public int size(){
        return cards.size();
    }
}


