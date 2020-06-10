package ch.heigvd.hbcg.model;

/**
 * Enumeration STATE_GAME, représentant les états possibles
 *
 * @authors Hakim Balestrieri, Christian Gomes
 */

public enum STATE_GAME {
    DISTRIBUTION,
    FLOP,
    MISE_FLOP,
    TURN,
    MISE_TURN,
    RIVER,
    MISE_RIVER,
    WINNER_IS,
    FINISH,
    RESTART
}
