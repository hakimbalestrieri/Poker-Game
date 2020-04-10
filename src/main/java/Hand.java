package ch.heigvd.hbcg;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Hand {

    private Set<Card> twoCards = new HashSet<>(2);
    private RankingCards rankingCard;

    public Hand(Set<Card> twoCards) {
        this.twoCards = twoCards;
    }

    public void updateRankingHand(){
        //rankingCard = RankingCards.HIGH_CARD;
    }

    public RankingCards getRankingCard() {
        return rankingCard;
    }

    public void add(Card card){
        twoCards.add(card);
    }
}
