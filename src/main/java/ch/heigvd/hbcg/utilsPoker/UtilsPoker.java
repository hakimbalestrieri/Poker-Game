package ch.heigvd.hbcg.utilsPoker;

import ch.heigvd.hbcg.model.Card;
import ch.heigvd.hbcg.model.RankingCards;

import java.util.*;

public class UtilsPoker {

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

    public static boolean isOnePair(Card[] cards) {

        List<Integer> intList = new ArrayList<>();
        for(Card c : cards) {
            intList.add(c.getNumber().ordinal() + 1);
        }
        Set<Integer> mySet = new HashSet<Integer>(intList);

        for(Integer integer: mySet){
            if(Collections.frequency(intList,integer) == 2) {
                return true;
            }
        }
        return false;
    }

    public static boolean isTwoPair(Card[] cards) {

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
        return countPair == 2;
    }

    public static boolean threeOfAKind(Card[] cards) {
        List<Integer> intList = new ArrayList<>();
        for(Card c : cards) {
            intList.add(c.getNumber().ordinal() + 1);
        }
        Set<Integer> mySet = new HashSet<Integer>(intList);

        for(Integer integer: mySet){
            if(Collections.frequency(intList,integer) == 3) {
                return true;
            }
        }
        return false;
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
}