package ch.heigvd.hbcg.model;

import java.io.Serializable;

/**
 * Enumeration RankingCards, représentant les combinaisons possibles
 *
 * @authors Hakim Balestrieri, Christian Gomes
 */

public enum RankingCards implements Serializable {

    //Force de la carte , déterminer par la position de l'enum.
    STRAIGHT_FLUSH,
    FOUR_OF_A_KIND,
    FULL_HOUSE,
    FLUSH,
    STRAIGHT,
    THREE_OF_A_KIND,
    TWO_PAIR,
    PAIR,
    HIGH_CARD

}
