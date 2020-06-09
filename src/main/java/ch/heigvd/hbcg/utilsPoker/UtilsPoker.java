package ch.heigvd.hbcg.utilsPoker;

import ch.heigvd.hbcg.model.*;

import java.util.*;

public class UtilsPoker {


    public static List<PlayerInfo> isWinner (List<PlayerInfo> currentPlayers, List<Card> boardCard){

        Card[] cardsToCheck = new Card[7];

        List<PlayerInfo> winners = new ArrayList<>();
        List<Integer> rankings = new ArrayList<>();

        for(int i = 2; i < 7; i++){
            cardsToCheck[i] = boardCard.get(i-2);
        }

        for (PlayerInfo playerInfo : currentPlayers) {

            cardsToCheck[0] = playerInfo.getPlayerHand().getCard(0);
            cardsToCheck[1] = playerInfo.getPlayerHand().getCard(1);

            Card[] temp = new Card[7];

            for (int j = 0; j < cardsToCheck.length; j++) {
                temp[j] = cardsToCheck[j];
            }


            playerInfo.setRankingCards(higherRanking(temp));
            rankings.add(higherRanking(temp).ordinal());
        }

        int minimum = Collections.min(rankings);

        for (PlayerInfo playerInfo : currentPlayers) {
            if (playerInfo.getRankingCards().ordinal() == minimum) {
                winners.add(playerInfo);
            }
        }

        return winners;

    }

    public static RankingCards higherRanking(Card[] cards){

        RankingCards rankingOfPlayer = null;

        int countRanking = 0;


        do{
            switch(countRanking){

                case 0:
                    rankingOfPlayer = isRoyalFlush(cards);
                    break;
                case 1:
                    rankingOfPlayer = isStraightFlush(cards);
                    break;
                case 2:
                    rankingOfPlayer = isFour(cards);
                    break;
                case 3:
                    rankingOfPlayer = isFullHouse(cards);
                    break;
                case 4:
                    rankingOfPlayer = isFlush(cards);
                    break;
                case 5:
                    rankingOfPlayer = isThreeOfAKind(cards);
                    break;
                case 6:
                    rankingOfPlayer = isTwoPair(cards);
                    break;
                case 7:
                    rankingOfPlayer = isOnePair(cards);
                    break;
                case 8:
                    rankingOfPlayer = RankingCards.HIGH_CARD;
                    break;
            }

            countRanking++;

        }while (rankingOfPlayer == null);

        return rankingOfPlayer;
    }


    public static RankingCards isFullHouse(Card[] h) {

        List<Integer> intList = new ArrayList<>();
        for(Card c : h) {
            intList.add(c.getNumber().ordinal() + 1);
        }
        Set<Integer> mySet = new HashSet<Integer>(intList);

        boolean threeOcc = false;
        boolean twoOcc = false;
        //il faut une fois une occurence de 3 et une fois une occurence de 2 pour un full house
        for(Integer integer: mySet){
            if(Collections.frequency(intList,integer) == 3) {
                threeOcc = true;
            }
            if(Collections.frequency(intList,integer) == 2) {
                twoOcc = true;
            }

        }

        if(threeOcc && twoOcc) {
            return  RankingCards.FULL_HOUSE;
        }
        return null;

    }

    public static RankingCards isOnePair(Card[] cards) {

        List<Integer> intList = new ArrayList<>();
        for(Card c : cards) {
            intList.add(c.getNumber().ordinal() + 1);
        }
        Set<Integer> mySet = new HashSet<Integer>(intList);

        for(Integer integer: mySet){
            if(Collections.frequency(intList,integer) == 2) {
                return RankingCards.PAIR;
            }
        }
        return null;
    }

    public static RankingCards isTwoPair(Card[] cards) {

        List<Integer> intList = new ArrayList<>();
        for(Card c : cards) {
            intList.add(c.getNumber().ordinal() + 1);
        }
        Set<Integer> mySet = new HashSet<Integer>(intList);
        int countPair = 0;
        for(Integer integer: mySet){
            if(Collections.frequency(intList,integer) == 2) {
                ++countPair;
            }
        }

        return countPair == 2 ? RankingCards.TWO_PAIR : null;

    }

    public static RankingCards isThreeOfAKind(Card[] cards) {
        List<Integer> intList = new ArrayList<>();
        for(Card c : cards) {
            intList.add(c.getNumber().ordinal() + 1);
        }
        Set<Integer> mySet = new HashSet<Integer>(intList);

        for(Integer integer: mySet){
            if(Collections.frequency(intList,integer) == 3) {
                return RankingCards.THREE_OF_A_KIND;
            }
        }
        return null;
    }

    public static RankingCards isFour(Card[] cards) {

        List<Integer> intList = new ArrayList<>();
        for(Card c : cards) {
            intList.add(c.getNumber().ordinal() + 1);
        }

        for(Integer integer: intList){
            if(Collections.frequency(intList,integer) == 4) {
                return RankingCards.FOUR_OF_A_KIND;
            }
        }

        return null;
    }


    public static RankingCards isFlush(Card[] cards){

        sortByColor(cards);

        if(cards[0].getColor().ordinal() == cards[4].getColor().ordinal() || cards[1].getColor().ordinal() == cards[5].getColor().ordinal()
                || cards[2].getColor().ordinal() == cards[6].getColor().ordinal()){
            return RankingCards.FLUSH;
        }
        else{
            return null;
        }

    }

    public static RankingCards isStraight(Card[] cards){


         /* ===========================================
            General case: check for increasing values
            =========================================== */
         sortByRank(cards);
         int valueTest = 0;
         int isStraight = 0;

         Set<Integer> cardInt = new HashSet<>();

         for(Card card : cards){
            cardInt.add(card.getNumber().ordinal());
         }

        System.out.println("TAILLE SUITE : " + cardInt.size());
         if(cardInt.size() < 5) {
             return null;
         }else if(cardInt.size() == 5){
             valueTest = 1;
         }else if(cardInt.size() == 6){
             valueTest = 2;
         }else if(cardInt.size() == 7){
             valueTest = 3;
         }

        List<Integer> mainCards = new ArrayList<Integer>();
        mainCards.addAll(cardInt);


         for(int j = 0 ; j < valueTest; j++){

             int testRank = mainCards.get(j) + 1;

             for ( int i = 1; i < 5; i++ )
             {
                 if (mainCards.get(i) != testRank ){
                     //return null;
                 }else{
                     if(++isStraight == 4) return RankingCards.STRAIGHT;
                 }

                 testRank++;   // Next card in hand
             }

             isStraight = 0;
         }

         if(isStraight != 5) return null;

        return RankingCards.STRAIGHT;        // Straight found !
    }

    public static RankingCards isStraightFlush(Card[] cards){

        if(isFlush(cards) == RankingCards.FLUSH && isStraight(cards) == RankingCards.STRAIGHT) return RankingCards.STRAIGHT_FLUSH;

        return null;

    }

    public static RankingCards isRoyalFlush (Card[] cards){

  /*      if(isFlush(cards) == RankingCards.FLUSH && isStraight(cards) == RankingCards.STRAIGHT) {

            sortByRank(cards);
            if (cards[6].getNumber() == Numbers.one && cards[2].getNumber() == Numbers.ten) return RankingCards.ROYAL_FLUSH;
        }
*/
            return null;

    }

    public static void sortByRank(Card[] h) {
        int i, j, min_j;

      /* ---------------------------------------------------
         The selection sort algorithm
         --------------------------------------------------- */
        for (i = 0; i < h.length; i++) {
         /* ---------------------------------------------------
            Find array element with min. value among
            h[i], h[i+1], ..., h[n-1]
            --------------------------------------------------- */
            min_j = i;   // Assume elem i (h[i]) is the minimum

            for (j = i + 1; j < h.length; j++) {
                if (h[j].getNumber().ordinal() < h[min_j].getNumber().ordinal()) {
                    min_j = j;    // We found a smaller minimum, update min_j
                }
            }

         /* ---------------------------------------------------
            Swap a[i] and a[min_j]
            --------------------------------------------------- */
            Card help = h[i];
            h[i] = h[min_j];
            h[min_j] = help;
        }
    }

    public static void sortByColor(Card[] cards){
        int i, j, min_j;

      /* ---------------------------------------------------
         The selection sort algorithm
         --------------------------------------------------- */
        for ( i = 0 ; i < cards.length ; i ++ )
        {
         /* ---------------------------------------------------
            Find array element with min. value among
            h[i], h[i+1], ..., h[n-1]
            --------------------------------------------------- */
            min_j = i;   // Assume elem i (h[i]) is the minimum

            for ( j = i+1 ; j < cards.length ; j++ )
            {
                if ( cards[j].getColor().ordinal() < cards[min_j].getColor().ordinal() )
                {
                    min_j = j;    // We found a smaller suit value, update min_j
                }
            }

         /* ---------------------------------------------------
            Swap a[i] and a[min_j]
            --------------------------------------------------- */
            Card help = cards[i];
            cards[i] = cards[min_j];
            cards[min_j] = help;
        }
    }


}