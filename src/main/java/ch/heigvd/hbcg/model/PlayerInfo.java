package ch.heigvd.hbcg.model;

import java.io.Serializable;

public class PlayerInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String pseudoEmetteur;
    private String message;
    private double credit;
    private boolean genre;
    private int position = 0;
    private Actions action;

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

    public PlayerInfo(PlayerInfo playerInfo) {
        this.pseudoEmetteur = playerInfo.getPseudoEmetteur();
        this.message = playerInfo.getMessage();
        this.position = playerInfo.getPosition();
        this.action = playerInfo.getAction();
    }

    private String getMessage() {
        return message;
    }


    public void setPseudoEmetteur(String pseudoEmetteur) {
        this.pseudoEmetteur = pseudoEmetteur;
    }

    public void setCredit(double credit){
        this.credit = credit;
    }

    public void setAction(Actions action){
        this.action = action;
    }

    @Override
    public String toString() {
        return  pseudoEmetteur + ": " + message;
    }

    public String getPseudoEmetteur() {
        return pseudoEmetteur;
    }

    public int getPosition(){
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Actions getAction() {
        return this.action;
    }
}
