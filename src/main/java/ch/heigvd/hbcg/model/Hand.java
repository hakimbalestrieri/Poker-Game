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

    //private static final long serialVersionUID = 1L;
    private List<Card> twoCards;

    /**
     * Constructeur
     * @param twoCards_
     */
    public Hand(List<Card> twoCards_) {
        this.twoCards = twoCards_;
    }

    /**
     * Constructeur par défaut
     */
    public Hand(){
        twoCards = new ArrayList<>();
    }

    /**
     * Mise à jour de la force de notre main
     */
    public void updateRankingHand(){
        /*TODO il faut qu'on récupère la liste des cartes dévoilées du board car notre force dépend de celles-ci */
        //rankingCard = RankingCards.HIGH_CARD;
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
    public Card getCard(int i){
        return twoCards.get(i);
    }

    /**
     * Affichage de la première carte
     * @return String
     */
    public String getCard1(){
        System.out.println("size card " + twoCards.size());
        return twoCards.get(0).toString();
    }

    /**
     * Affichage de la première carte
     * @return String
     */
    public String getCard2(){
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
