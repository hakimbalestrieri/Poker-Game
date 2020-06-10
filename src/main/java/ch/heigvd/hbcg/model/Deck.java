package ch.heigvd.hbcg.model;

import java.util.*;

/**
 * Classe Deck
 * Represente une liste de cartes
 * @authors Gomes & Balestrieri
 */
public class Deck {

    private List<Card> cards;

    /**
     * Constructeur
     */
    public Deck(){
        cards = new ArrayList<>();
        randomDeck();
    }

    /**
     * Création d'un deck aléatoire et mélange du deck
     */
    private void randomDeck() {

        for (Colors color : Colors.values()) {
            for (Numbers number : Numbers.values()) {
                cards.add(new Card(color, number));
            }
        }

        Collections.shuffle(cards);
        System.out.println("Random");
    }

    /**
     * Retire la première carte du deck
     * @return
     */
     public Card draw(){

        Card card;
        card = cards.get(0);
        cards.remove(0);
        return card;
    }

    /**
     * Retourne le nombre de cartes
     * @brief utilisé à des fins de test
     * @return
     */
    public int size(){
        return cards.size();
    }
}


