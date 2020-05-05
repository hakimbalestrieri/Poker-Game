package ch.heigvd.hbcg.model;

import java.io.Serializable;

public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
    private String pseudoEmetteur;
    private String message;
    private double credit;
    private boolean genre;
    private int position = 0;
    private Actions action;
    private PokerPlayer pokerPlayer;
    private Hand playerHand = new Hand();
    private boolean bigBlind = false;
    private boolean actif = true;
    private Game game;

    private PokerHandler pokerHandler;

    public Hand getPlayerHand() {
        return playerHand;
    }

    public void addGame(Game game){

        if(this.game == null || this.game != game ){
            this.game = game;
        }
    }

    public Player(String pseudoEmetteur, String message, double credit, boolean genre) {
        this.pseudoEmetteur = pseudoEmetteur;
        this.message = message;
        this.credit = credit;
        this.genre = genre;
    }

    public Player(String pseudoEmetteur, String message) {
        this.pseudoEmetteur = pseudoEmetteur;
        this.message = message;
    }

    public Player(Player player) {
        this.pseudoEmetteur = player.getPseudoEmetteur();
        this.message = player.getMessage();
        this.position = player.getPosition();
        this.action = player.getAction();
    }

    public Player(PokerHandler pokerHandler) {
        this.pokerHandler = pokerHandler;
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

    public void setPokerPlayer(PokerPlayer pokerPlayer){
        this.pokerPlayer = pokerPlayer;
    }

    public void receiveCard(Card card) {
        playerHand.add(card);
    }

    public PokerPlayer getPokerPlayer() {
        return pokerPlayer;
    }
}
