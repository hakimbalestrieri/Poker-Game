package ch.heigvd.hbcg.model;

import java.io.Serializable;

public enum Actions implements Serializable {
    MESSAGE,
    SIT_DOWN,
    CONNECTION,
    START_GAME,
    FLOP,
    TURN,
    RIVER,
    PHASE_MISE,
    CALL,
    CHECK,
    FOLD,
    END
}
