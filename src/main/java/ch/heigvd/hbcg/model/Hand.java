package ch.heigvd.hbcg.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Hand implements Serializable {

    private List<Card> twoCards = new ArrayList<>(2);
    private RankingCards rankingCard;

    public Hand(List<Card> twoCards) {
        this.twoCards = twoCards;
    }

    public Hand(){}
    public void updateRankingHand(){
        /*TODO il faut qu'on récupère la liste des cartes dévoilées du board car notre force dépend de celles-ci */
        //rankingCard = RankingCards.HIGH_CARD;
    }

    public RankingCards getRankingCard() {
        return rankingCard;
    }

    public void add(Card card){
        twoCards.add(card);
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
