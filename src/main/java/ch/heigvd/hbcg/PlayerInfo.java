package ch.heigvd.hbcg;

import java.io.Serializable;

public class PlayerInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String pseudoEmetteur;
    private String message;
    private double credit;
    private boolean genre;

    public PlayerInfo(String pseudoEmetteur, String message, double credit, boolean genre) {
        this.pseudoEmetteur = pseudoEmetteur;
        this.message = message;
        this.credit = credit;
        this.genre = genre;
    }

    public PlayerInfo(String pseudoEmetteur, String message) {
        this.pseudoEmetteur = pseudoEmetteur;
        this.message = message;
    }

    public void setPseudoEmetteur(String pseudoEmetteur) {
        this.pseudoEmetteur = pseudoEmetteur;
    }

    public void setCredit(double credit){
        this.credit = credit;
    }

    @Override
    public String toString() {
        return  pseudoEmetteur + ": " + message;
    }

    public String getPseudoEmetteur() {
        return pseudoEmetteur;
    }
}
