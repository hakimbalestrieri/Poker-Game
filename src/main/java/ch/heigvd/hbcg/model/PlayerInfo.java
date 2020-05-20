package ch.heigvd.hbcg.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represente les informations d'un joueur
 * @authors Balestrieri & Gomes
 */
public class PlayerInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String pseudoEmetteur;
    private String message;
    private double credit;
    private boolean genre;
    private int position = 0;
    private Actions action;
    private double mise = 0;
    private Hand playerHand = new Hand();
    private boolean bigBlind = false;
    private boolean actif = true;
    private boolean showCard = true;
    private List<Card> boardCard = new ArrayList<>(5);

    public PlayerInfo(){}

    public PlayerInfo(PlayerInfo playerInfo) {

        this.pseudoEmetteur = playerInfo.getPseudoEmetteur();
        this.message = playerInfo.getMessage();
        this.position = playerInfo.getPosition();
        this.action = playerInfo.getAction();
        this.mise = playerInfo.getMise();
        this.showCard = playerInfo.getShowCard();


        if(playerInfo.getPlayerHand().size() != 0){
            for (int i = 0; i < playerInfo.getPlayerHand().size(); i++){
                playerHand.add(playerInfo.getPlayerHand().getCard(i));
            }
        }

        if(playerInfo.getBoardCard().size() != 0){
            for (int i = 0; i < playerInfo.getBoardCard().size(); i++){
                boardCard.add(playerInfo.getBoardCard().get(i));
            }
        }

        //this.pokerPlayer = player.getPokerPlayer();
    }

    public void setMise(double mise){
        this.mise = mise;
    }

    public List<Card> getBoardCard() {
        return boardCard;
    }

    public void setShowCard(boolean showCard) {
        this.showCard = showCard;
    }

    public Hand getPlayerHand() {
        return playerHand;
    }

    public String getMessage() {
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

    public void receiveCard(Card card) {
        playerHand.add(card);
    }


    public void setGenre(boolean valueOf1) {
        genre = valueOf1;
    }

    public double getCredit() {
        return credit;
    }

    public boolean getGenre(){
        return genre;
    }

    public boolean getShowCard() {
        return showCard;
    }

    public void setBoardCards(Card boardCard) {
        this.boardCard.add(boardCard);
    }

    public double getMise() {
        return mise;
    }
}
