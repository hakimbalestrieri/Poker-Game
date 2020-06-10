package ch.heigvd.hbcg.utilsPoker;

import ch.heigvd.hbcg.model.*;
import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class UtilsPoker {

    /**
     * Détermine le gagnant parmis une liste de joueur selon une liste de cartes données
     *
     * @param currentPlayers
     * @param boardCard
     * @return
     */
    public static List<PlayerInfo> isWinner(List<PlayerInfo> currentPlayers, List<Card> boardCard) {

        Card[] cardsToCheck = new Card[7];

        List<PlayerInfo> winners = new ArrayList<>();
        List<Integer> rankings = new ArrayList<>();

        for (int i = 2; i < 7; i++) {
            cardsToCheck[i] = boardCard.get(i - 2);
        }

        for (PlayerInfo playerInfo : currentPlayers) {

            cardsToCheck[0] = playerInfo.getPlayerHand().getCard(0);
            cardsToCheck[1] = playerInfo.getPlayerHand().getCard(1);

            Card[] temp = new Card[7];
            System.arraycopy(cardsToCheck, 0, temp, 0, cardsToCheck.length);
            System.out.println("Les cartes de : " + playerInfo.getPseudoEmetteur() + "");
            for (int i = 0; i < temp.length; i++) {
                System.out.print((temp[i].getNumber().ordinal() + 1) + " ");
            }
            System.out.println();


            playerInfo.setRankingCards(higherRanking(temp));
            System.out.println(playerInfo.getPseudoEmetteur() + " ::: " + playerInfo.getRankingCards());
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

    /**
     * Fonction utilitaire retournant le type de combinaison selon un tableau de carte
     *
     * @param cards
     * @return
     */
    public static RankingCards higherRanking(Card[] cards) {

        RankingCards rankingOfPlayer = null;

        int countRanking = 0;

        do {
            switch (countRanking) {

                case 0:
                    rankingOfPlayer = isStraightFlush(cards);
                    break;
                case 1:
                    rankingOfPlayer = isFour(cards);
                    break;
                case 2:
                    rankingOfPlayer = isFullHouse(cards);
                    break;
                case 3:
                    rankingOfPlayer = isFlush(cards);
                    break;
                case 4:
                    rankingOfPlayer = isThreeOfAKind(cards);
                    break;
                case 5:
                    rankingOfPlayer = isTwoPair(cards);
                    break;
                case 6:
                    rankingOfPlayer = isOnePair(cards);
                    break;
                case 7:
                    rankingOfPlayer = RankingCards.HIGH_CARD;
                    break;
            }

            countRanking++;

        } while (rankingOfPlayer == null);

        return rankingOfPlayer;
    }

    /**
     * Détecte un full house
     *
     * @param h
     * @return
     */
    public static RankingCards isFullHouse(Card[] h) {

        List<Integer> intList = new ArrayList<>();
        for (Card c : h) {
            intList.add(c.getNumber().ordinal() + 1);
        }
        Set<Integer> mySet = new HashSet<Integer>(intList);

        boolean threeOcc = false;
        boolean twoOcc = false;
        //il faut une fois une occurence de 3 et une fois une occurence de 2 pour un full house
        for (Integer integer : mySet) {
            if (Collections.frequency(intList, integer) == 3) {
                threeOcc = true;
            }
            if (Collections.frequency(intList, integer) == 2) {
                twoOcc = true;
            }

        }

        if (threeOcc && twoOcc) {
            return RankingCards.FULL_HOUSE;
        }
        return null;

    }

    /**
     * Détecte une paire
     *
     * @param cards
     * @return
     */
    public static RankingCards isOnePair(Card[] cards) {

        List<Integer> intList = new ArrayList<>();
        for (Card c : cards) {
            intList.add(c.getNumber().ordinal() + 1);
        }
        Set<Integer> mySet = new HashSet<Integer>(intList);

        for (Integer integer : mySet) {
            if (Collections.frequency(intList, integer) == 2) {
                return RankingCards.PAIR;
            }
        }
        return null;
    }

    /**
     * Détecte une double paire
     *
     * @param cards
     * @return
     */
    public static RankingCards isTwoPair(Card[] cards) {

        List<Integer> intList = new ArrayList<>();
        for (Card c : cards) {
            intList.add(c.getNumber().ordinal() + 1);
        }
        Set<Integer> mySet = new HashSet<>(intList);
        int countPair = 0;
        for (Integer integer : mySet) {
            if (Collections.frequency(intList, integer) == 2) {
                ++countPair;
            }
        }
        return countPair == 2 ? RankingCards.TWO_PAIR : null;

    }

    /**
     * Détecte un brelan
     *
     * @param cards
     * @return
     */
    public static RankingCards isThreeOfAKind(Card[] cards) {
        List<Integer> intList = new ArrayList<>();
        for (Card c : cards) {
            intList.add(c.getNumber().ordinal() + 1);
        }
        Set<Integer> mySet = new HashSet<Integer>(intList);

        for (Integer integer : mySet) {
            if (Collections.frequency(intList, integer) == 3) {
                return RankingCards.THREE_OF_A_KIND;
            }
        }
        return null;
    }

    /**
     * Détecte un carré
     *
     * @param cards
     * @return
     */
    public static RankingCards isFour(Card[] cards) {

        List<Integer> intList = new ArrayList<>();
        for (Card c : cards) {
            intList.add(c.getNumber().ordinal() + 1);
        }

        for (Integer integer : intList) {
            if (Collections.frequency(intList, integer) == 4) {
                return RankingCards.FOUR_OF_A_KIND;
            }
        }

        return null;
    }

    /**
     * Détecte une couleur
     *
     * @param cards
     * @return
     */
    public static RankingCards isFlush(Card[] cards) {

        sortByColor(cards);

        if (cards[0].getColor().ordinal() == cards[4].getColor().ordinal() || cards[1].getColor().ordinal() == cards[5].getColor().ordinal()
                || cards[2].getColor().ordinal() == cards[6].getColor().ordinal()) {
            return RankingCards.FLUSH;
        } else {
            return null;
        }

    }

    /**
     * Détecte une suite
     *
     * @param cards
     * @return
     */
    public static RankingCards isStraight(Card[] cards) {

        sortByRank(cards);
        int valueTest = 0;
        int isStraight = 0;

        Set<Integer> cardInt = new HashSet<>();

        for (Card card : cards) {
            cardInt.add(card.getNumber().ordinal());
        }

        System.out.println("TAILLE SUITE : " + cardInt.size());
        if (cardInt.size() < 5) {
            return null;
        } else if (cardInt.size() == 5) {
            valueTest = 1;
        } else if (cardInt.size() == 6) {
            valueTest = 2;
        } else if (cardInt.size() == 7) {
            valueTest = 3;
        }

        List<Integer> mainCards = new ArrayList<Integer>();
        mainCards.addAll(cardInt);


        for (int j = 0; j < valueTest; j++) {

            int testRank = mainCards.get(j) + 1;

            for (int i = 1; i < 5; i++) {
                if (mainCards.get(i) == testRank) {
                    if (++isStraight == 4)
                        return RankingCards.STRAIGHT;
                }
                testRank++;
            }

            isStraight = 0;
        }

        if (isStraight != 5) return null;

        return RankingCards.STRAIGHT;
    }

    /**
     * Détecte une quinte flush
     *
     * @param cards
     * @return
     */
    public static RankingCards isStraightFlush(Card[] cards) {

        if (isFlush(cards) == RankingCards.FLUSH && isStraight(cards) == RankingCards.STRAIGHT)
            return RankingCards.STRAIGHT_FLUSH;

        return null;

    }

    /**
     * Trie les cartes par valeur
     *
     * @param h
     */
    public static void sortByRank(Card[] h) {
        int i, j, min_j;


        for (i = 0; i < h.length; i++) {

            min_j = i;

            for (j = i + 1; j < h.length; j++) {
                if (h[j].getNumber().ordinal() < h[min_j].getNumber().ordinal()) {
                    min_j = j;
                }
            }

            Card help = h[i];
            h[i] = h[min_j];
            h[min_j] = help;
        }
    }

    /**
     * Trie les cartes par type
     *
     * @param cards
     */
    public static void sortByColor(Card[] cards) {
        int i, j, min_j;

        for (i = 0; i < cards.length; i++) {

            min_j = i;

            for (j = i + 1; j < cards.length; j++) {
                if (cards[j].getColor().ordinal() < cards[min_j].getColor().ordinal()) {
                    min_j = j;
                }
            }

            Card help = cards[i];
            cards[i] = cards[min_j];
            cards[min_j] = help;
        }
    }
}