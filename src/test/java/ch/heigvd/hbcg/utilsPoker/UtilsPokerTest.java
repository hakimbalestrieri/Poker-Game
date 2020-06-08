package ch.heigvd.hbcg.utilsPoker;

import ch.heigvd.hbcg.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UtilsPokerTest {

    Card[] cardsTab = new Card[7];

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

        assertEquals(RankingCards.PAIR, UtilsPoker.isFullHouse(cardsTab));

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

        assertEquals(RankingCards.TWO_PAIR, UtilsPoker.isFullHouse(cardsTab));
    }

    @Test
    public void threeOfAKind(){
        //main
        cardsTab[0] = new Card(Colors.coeur, Numbers.eight);
        cardsTab[1] = new Card(Colors.carreau, Numbers.eight);
        //board
        cardsTab[2] = new Card(Colors.carreau, Numbers.eight);
        cardsTab[3] = new Card(Colors.coeur, Numbers.three);
        cardsTab[4] = new Card(Colors.pique, Numbers.nine);
        cardsTab[5] = new Card(Colors.carreau, Numbers.four);
        cardsTab[6] = new Card(Colors.carreau, Numbers.k);

        assertEquals(RankingCards.THREE_OF_A_KIND, UtilsPoker.isFullHouse(cardsTab));
    }

    @Test
    public void straight(){
        //main
        cardsTab[0] = new Card(Colors.coeur, Numbers.eight);
        cardsTab[1] = new Card(Colors.carreau, Numbers.seven);
        //board
        cardsTab[2] = new Card(Colors.carreau, Numbers.six);
        cardsTab[3] = new Card(Colors.coeur, Numbers.five);
        cardsTab[4] = new Card(Colors.pique, Numbers.nine);
        cardsTab[5] = new Card(Colors.carreau, Numbers.four);
        cardsTab[6] = new Card(Colors.carreau, Numbers.k);

        assertEquals(RankingCards.STRAIGHT, UtilsPoker.isFullHouse(cardsTab));
    }

    @Test
    public void flush() {
        //main
        cardsTab[0] = new Card(Colors.carreau, Numbers.eight);
        cardsTab[1] = new Card(Colors.carreau, Numbers.eight);
        //board
        cardsTab[2] = new Card(Colors.carreau, Numbers.six);
        cardsTab[3] = new Card(Colors.coeur, Numbers.three);
        cardsTab[4] = new Card(Colors.pique, Numbers.nine);
        cardsTab[5] = new Card(Colors.carreau, Numbers.four);
        cardsTab[6] = new Card(Colors.carreau, Numbers.k);

        assertEquals(RankingCards.FLUSH, UtilsPoker.isFullHouse(cardsTab));
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

        assertEquals(RankingCards.FOUR_OF_A_KIND, UtilsPoker.isFullHouse(cardsTab));
    }

    @Test
    public void straightFlush() {
        //main
        cardsTab[0] = new Card(Colors.carreau, Numbers.nine);
        cardsTab[1]= new Card(Colors.carreau, Numbers.eight);
        //board
        cardsTab[2] = new Card(Colors.carreau, Numbers.seven);
        cardsTab[3] = new Card(Colors.coeur, Numbers.eight);
        cardsTab[4] = new Card(Colors.pique, Numbers.nine);
        cardsTab[5] = new Card(Colors.carreau, Numbers.six);
        cardsTab[6] = new Card(Colors.carreau, Numbers.five);

        assertEquals(RankingCards.STRAIGHT_FLUSH, UtilsPoker.isFullHouse(cardsTab));
    }

    @Test
    public void royalFlush() {
        //main
        cardsTab[0] = new Card(Colors.carreau, Numbers.one);
        cardsTab[1]= new Card(Colors.carreau, Numbers.k);
        //board
        cardsTab[2] = new Card(Colors.carreau, Numbers.q);
        cardsTab[3] = new Card(Colors.coeur, Numbers.eight);
        cardsTab[4] = new Card(Colors.pique, Numbers.nine);
        cardsTab[5] = new Card(Colors.carreau, Numbers.j);
        cardsTab[6] = new Card(Colors.carreau, Numbers.ten);

        assertEquals(RankingCards.ROYAL_FLUSH, UtilsPoker.isFullHouse(cardsTab));
    }
}