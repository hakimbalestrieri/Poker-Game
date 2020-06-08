package ch.heigvd.hbcg.utilsPoker;

import ch.heigvd.hbcg.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UtilsPokerTest {

    Card[] cardsTab = new Card[7];

    @Test
    public void higherRanking(){

        cardsTab[0] = new Card(Colors.coeur, Numbers.eight);
        cardsTab[1] = new Card(Colors.carreau, Numbers.two);
        //board
        cardsTab[2] = new Card(Colors.carreau, Numbers.nine);
        cardsTab[3] = new Card(Colors.coeur, Numbers.nine);
        cardsTab[4] = new Card(Colors.pique, Numbers.nine);
        cardsTab[5] = new Card(Colors.carreau, Numbers.four);
        cardsTab[6] = new Card(Colors.carreau, Numbers.k);

        assertEquals(RankingCards.THREE_OF_A_KIND, UtilsPoker.higherRanking(cardsTab));

    }

    @Test
    public void isOnePair(){
        //main
        cardsTab[0] = new Card(Colors.coeur, Numbers.eight);
        cardsTab[1] = new Card(Colors.carreau, Numbers.eight);
        //board
        cardsTab[2] = new Card(Colors.carreau, Numbers.six);
        cardsTab[3] = new Card(Colors.coeur, Numbers.three);
        cardsTab[4] = new Card(Colors.pique, Numbers.nine);
        cardsTab[5] = new Card(Colors.carreau, Numbers.four);
        cardsTab[6] = new Card(Colors.carreau, Numbers.k);

        assertEquals(RankingCards.PAIR, UtilsPoker.isOnePair(cardsTab));

    }

    @Test
    public void twoPair(){
        //main
        cardsTab[0] = new Card(Colors.coeur, Numbers.eight);
        cardsTab[1] = new Card(Colors.carreau, Numbers.eight);
        //board
        cardsTab[2] = new Card(Colors.carreau, Numbers.six);
        cardsTab[3] = new Card(Colors.coeur, Numbers.six);
        cardsTab[4] = new Card(Colors.pique, Numbers.nine);
        cardsTab[5] = new Card(Colors.carreau, Numbers.four);
        cardsTab[6] = new Card(Colors.carreau, Numbers.k);

        assertEquals(RankingCards.TWO_PAIR, UtilsPoker.isTwoPair(cardsTab));
    }

    @Test
    public void isthreeOfAKind(){
        //main
        cardsTab[0] = new Card(Colors.coeur, Numbers.eight);
        cardsTab[1] = new Card(Colors.carreau, Numbers.eight);
        //board
        cardsTab[2] = new Card(Colors.carreau, Numbers.eight);
        cardsTab[3] = new Card(Colors.coeur, Numbers.three);
        cardsTab[4] = new Card(Colors.pique, Numbers.nine);
        cardsTab[5] = new Card(Colors.carreau, Numbers.four);
        cardsTab[6] = new Card(Colors.carreau, Numbers.k);

        assertEquals(RankingCards.THREE_OF_A_KIND, UtilsPoker.isThreeOfAKind(cardsTab));
    }

    @Test
    public void isStraight(){
        //main
        cardsTab[0] = new Card(Colors.coeur, Numbers.eight);
        cardsTab[1] = new Card(Colors.carreau, Numbers.seven);
        //board
        cardsTab[2] = new Card(Colors.carreau, Numbers.six);
        cardsTab[3] = new Card(Colors.coeur, Numbers.five);
        cardsTab[4] = new Card(Colors.pique, Numbers.nine);
        cardsTab[5] = new Card(Colors.carreau, Numbers.four);
        cardsTab[6] = new Card(Colors.carreau, Numbers.k);

        assertEquals(RankingCards.STRAIGHT, UtilsPoker.isStraight(cardsTab));

        cardsTab[6] = new Card(Colors.carreau, Numbers.five);

        assertEquals(RankingCards.STRAIGHT, UtilsPoker.isStraight(cardsTab));

        cardsTab[2] = new Card(Colors.carreau, Numbers.three);
        cardsTab[3] = new Card(Colors.coeur, Numbers.two);
        cardsTab[4] = new Card(Colors.pique, Numbers.one);
        cardsTab[5] = new Card(Colors.carreau, Numbers.four);
        cardsTab[6] = new Card(Colors.carreau, Numbers.five);

        assertEquals(RankingCards.STRAIGHT, UtilsPoker.isStraight(cardsTab));
    }

    @Test
    public void flush() {

        //main
        cardsTab[0] = new Card(Colors.pique, Numbers.eight);
        cardsTab[1] = new Card(Colors.carreau, Numbers.eight);
        //board
        cardsTab[2] = new Card(Colors.carreau, Numbers.six);
        cardsTab[3] = new Card(Colors.coeur, Numbers.three);
        cardsTab[4] = new Card(Colors.carreau, Numbers.nine);
        cardsTab[5] = new Card(Colors.carreau, Numbers.four);
        cardsTab[6] = new Card(Colors.carreau, Numbers.k);

        UtilsPoker.sortByColor(cardsTab);

        assertEquals(RankingCards.FLUSH, UtilsPoker.isFlush(cardsTab));
    }

    @Test
    public void isFullHouse() {
        //main
        cardsTab[0] = new Card(Colors.carreau, Numbers.nine);
        cardsTab[1]= new Card(Colors.coeur, Numbers.nine);
        //board
        cardsTab[2] = new Card(Colors.carreau, Numbers.eight);
        cardsTab[3] = new Card(Colors.coeur, Numbers.eight);
        cardsTab[4] = new Card(Colors.pique, Numbers.nine);
        cardsTab[5] = new Card(Colors.carreau, Numbers.four);
        cardsTab[6] = new Card(Colors.carreau, Numbers.k);

        assertEquals(RankingCards.FULL_HOUSE, UtilsPoker.isFullHouse(cardsTab));
        //full house
    }

    @Test
    public void fourOfAKind() {
        //main
        cardsTab[0] = new Card(Colors.carreau, Numbers.nine);
        cardsTab[1]= new Card(Colors.coeur, Numbers.nine);
        //board
        cardsTab[2] = new Card(Colors.carreau, Numbers.nine);
        cardsTab[3] = new Card(Colors.coeur, Numbers.eight);
        cardsTab[4] = new Card(Colors.pique, Numbers.nine);
        cardsTab[5] = new Card(Colors.carreau, Numbers.four);
        cardsTab[6] = new Card(Colors.carreau, Numbers.k);

        assertEquals(RankingCards.FOUR_OF_A_KIND, UtilsPoker.isFour(cardsTab));
    }


    @Test
    public void isStraightFlush(){

        cardsTab[0] = new Card(Colors.carreau, Numbers.two);
        cardsTab[1]= new Card(Colors.carreau, Numbers.four);
        //board
        cardsTab[2] = new Card(Colors.carreau, Numbers.three);
        cardsTab[3] = new Card(Colors.coeur, Numbers.eight);
        cardsTab[4] = new Card(Colors.pique, Numbers.nine);
        cardsTab[5] = new Card(Colors.carreau, Numbers.six);
        cardsTab[6] = new Card(Colors.carreau, Numbers.five);

        assertEquals(RankingCards.STRAIGHT_FLUSH, UtilsPoker.isStraightFlush(cardsTab));

    }

    @Test
    public void isRoyalFlush() {

        //TODO : A voir si l'on l'implemente - Pose problème avec AS qui vaut 1 et la carte la plus forte
        //main
        cardsTab[0] = new Card(Colors.carreau, Numbers.one);
        cardsTab[1]= new Card(Colors.carreau, Numbers.k);
        //board
        cardsTab[2] = new Card(Colors.carreau, Numbers.q);
        cardsTab[3] = new Card(Colors.coeur, Numbers.eight);
        cardsTab[4] = new Card(Colors.pique, Numbers.nine);
        cardsTab[5] = new Card(Colors.carreau, Numbers.j);
        cardsTab[6] = new Card(Colors.carreau, Numbers.ten);

        assertEquals(RankingCards.ROYAL_FLUSH, UtilsPoker.isRoyalFlush(cardsTab));
    }
}