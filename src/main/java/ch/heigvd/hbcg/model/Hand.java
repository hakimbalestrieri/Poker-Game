package ch.heigvd.hbcg.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Hand
 *
 * @authors Hakim Balestrieri, Christian Gomes
 */
public class Hand implements Serializable {

    private List<Card> twoCards;

    /**
     * Constructeur par défaut
     */
    public Hand(){
        twoCards = new ArrayList<>();
    }

    /**
     * Retourne le nombre de cartes qu'un joueur possède
     * @return twoCards
     */
    public int size(){
        return twoCards.size();
    }

    /**
     * Ajout d'une carte dans une main
     * @param card
     */
    public void add(Card card){
        twoCards.add(card);
    }

    /**
     * Accès à une carte
     * @param i
     * @return card
     */
    synchronized public Card getCard(int i){
        return twoCards.get(i);
    }

    /**
     * Affichage de la première carte
     * @return String
     */
    synchronized public String getCard1(){
        System.out.println("size card " + twoCards.size());
        return twoCards.get(0).toString();
    }

    /**
     * Affichage de la première carte
     * @return String
     */
    synchronized public String getCard2(){
        return twoCards.get(1).toString();
    }

    /**
     * Spécialisation de la méthode ToString()
     * @return String
     */
    @Override
    public String toString() {
        return "Main = " + twoCards.get(0).toString() + " " + twoCards.get(1).toString() + "\n";
    }
}
