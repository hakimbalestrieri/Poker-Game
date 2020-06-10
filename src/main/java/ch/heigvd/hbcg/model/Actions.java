package ch.heigvd.hbcg.model;

import java.io.Serializable;

/**
 * Enumeration des différentes actions possibles
 * @authors Hakim Balestrieri, Christian Gomes
 */
public enum Actions implements Serializable {
    MESSAGE,
    SIT_DOWN,
    CONNECTION,
    START_GAME,
    FLOP,
    TURN,
    RIVER,
    PHASE_MISE,
    FOLD,
    WINNER_IS,
    RESTART,
    FINISH
}
