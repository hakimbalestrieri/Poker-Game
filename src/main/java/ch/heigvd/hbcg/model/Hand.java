package ch.heigvd.hbcg.model;

import java.util.HashSet;
import java.util.Set;

public class Hand {

    private Set<Card> twoCards = new HashSet<>(2);
    private RankingCards rankingCard;

    public Hand(Set<Card> twoCards) {
        this.twoCards = twoCards;
    }

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
}
