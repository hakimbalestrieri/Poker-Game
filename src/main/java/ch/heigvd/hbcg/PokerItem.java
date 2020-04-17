package ch.heigvd.hbcg;

import java.io.Serializable;

public class PokerItem implements Serializable {

    private static final long serialVersionUID = 1L;
    private String pseudoEmetteur;
    private String message;
    private boolean genre;

    public PokerItem(String pseudoEmetteur, String message) {
        this.pseudoEmetteur = pseudoEmetteur;
        this.message = message;
      //  this.genre = genre;
    }

    public void setPseudoEmetteur(String pseudoEmetteur) {
        this.pseudoEmetteur = pseudoEmetteur;
    }

    @Override
    public String toString() {
        return  pseudoEmetteur + ": " + message;
    }
}
