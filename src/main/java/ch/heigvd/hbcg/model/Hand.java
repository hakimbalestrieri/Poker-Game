package ch.heigvd.hbcg.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Hand implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Card> twoCards;
    private RankingCards rankingCard;

    public Hand(List<Card> twoCards_) {
        this.twoCards = twoCards_;
    }

    public Hand(){
        twoCards = new ArrayList<>();
    }

    public void updateRankingHand(){
        /*TODO il faut qu'on récupère la liste des cartes dévoilées du board car notre force dépend de celles-ci */
        //rankingCard = RankingCards.HIGH_CARD;
    }

    public int size(){
        return twoCards.size();
    }

    public RankingCards getRankingCard() {
        return rankingCard;
    }

    public void add(Card card){
        twoCards.add(card);
    }

    public Card getCard(int i){
        return twoCards.get(i);
    }


    public String getCard1(){
        System.out.println("size card " + twoCards.size());
        return twoCards.get(0).toString();
    }
    public String getCard2(){
        return twoCards.get(1).toString();
    }

    @Override
    public String toString() {
        return "Main = " + twoCards.get(0).toString() + " " + twoCards.get(1).toString() + "\n";
    }
}
