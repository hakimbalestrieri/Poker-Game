package ch.heigvd.hbcg.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe PlayerInfo
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
    private boolean showCard = true;
    private List<Card> boardCard = new ArrayList<>(5);

    private boolean isWinner = false;
    private RankingCards rankingCards = null;
    /**
     * Constructeur par défaut
     */
    public PlayerInfo(){}

    /**
     * Constructeur
     * @param playerInfo
     */
     public PlayerInfo(PlayerInfo playerInfo) {


        this.pseudoEmetteur = playerInfo.getPseudoEmetteur();
        this.message = playerInfo.getMessage();
        this.position = playerInfo.getPosition();
        this.action = playerInfo.getAction();
        this.mise = playerInfo.getMise();
        this.showCard = playerInfo.getShowCard();
        this.isWinner = playerInfo.getWinner();
        this.rankingCards = playerInfo.getRankingCards();


            if(playerInfo.getPlayerHand().size() != 0){
                System.out.println("size du joueur à ajouter" +  playerInfo.getPlayerHand().size());
                for (int i = 0; i < playerInfo.getPlayerHand().size(); i++){
                    playerHand.add(playerInfo.getPlayerHand().getCard(i));
                }
            }

            if(playerInfo.getBoardCard().size() != 0){
                for (int i = 0; i < playerInfo.getBoardCard().size(); i++){
                    boardCard.add(playerInfo.getBoardCard().get(i));
                }
            }


    }

    /**
     * réinitialisation des attributs
     */
    public void resetInfo(){
        isWinner = false;
        playerHand = new Hand();
        showCard = true;
        rankingCards = null;
        boardCard = new ArrayList<>(5);
        mise = 0;
    }

    /**
     * Définit le gagnant de la partie
     * @param win
     */
    public void setWinner(boolean win){ this.isWinner = win;}

    /**
     * Retourne le gagnant de la partie
     * @return
     */
    public boolean getWinner(){
        return isWinner;
    }

    /**
     * affectation de la mise
     * @param mise
     */
    public void setMise(double mise){
        this.mise = mise;
    }

    /**
     * Retourne les cartes du board
     * @return boardCard
     */
    public List<Card> getBoardCard() {
        return boardCard;
    }

    /**
     * affectation du showCard
     * @param showCard
     */
    public void setShowCard(boolean showCard) {
        this.showCard = showCard;
    }

    /**
     * Retourne la main courante du joueur
     * @return playerHand
     */
    synchronized public Hand getPlayerHand() {
        return playerHand;
    }

    /**
     * Retourne le message écrit par le joueur
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Affectation du pseudo au joueur
     * @param pseudoEmetteur
     */
    public void setPseudoEmetteur(String pseudoEmetteur) {
        this.pseudoEmetteur = pseudoEmetteur;
    }

    /**
     * Affectation du crédit au joueur
     * @param credit
     */
    public void setCredit(double credit){
        this.credit = credit;
    }

    /**
     * Affecation d'une action au joueur
     * @param action
     */
    public void setAction(Actions action){
        this.action = action;
    }

    /**
     * Spécialisation de la méthode toString()
     * @return String
     */
    @Override
    public String toString() {
        return pseudoEmetteur + ": " + message;
    }

    /**
     * Retourne le pseudo du joueur
     * @return pseudoEmetteur
     */
    public String getPseudoEmetteur() {
        return pseudoEmetteur;
    }

    /**
     * Retourne la position courante du joueur
     * @return position
     */
    public int getPosition(){
        return position;
    }

    /**
     * Affectation de la position du joueur
     * @param position
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Affectation d'un message à un joueur
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Retourne l'action d'un joueur
     * @return
     */
    public Actions getAction() {
        return this.action;
    }

    /**
     * Réception d'une carte
     * @param card
     */
    public void receiveCard(Card card) {
        playerHand.add(card);
    }

    /**
     * Affectation du genre du joueur
     * @param genre
     */
    public void setGenre(boolean genre) {
        this.genre = genre;
    }

    /**
     * Retourne un booleen indiquant si on doit montrer nos cartes ou pas
     * @return showCard
     */
    public boolean getShowCard() {
        return showCard;
    }

    /**
     * Liaison des cartes du board
     * @param boardCard
     */
    public void setBoardCards(Card boardCard) {
        this.boardCard.add(boardCard);
    }

    /**
     * Retourne la mise d'un joueur
     * @return mise
     */
    public double getMise() {
        return mise;
    }

    /**
     * Retourne la combinaison du joueur
     * @return
     */
    public RankingCards getRankingCards() {
        return rankingCards;
    }

    /**
     * Initialise la combinaison du joueur
     * @param rankingCards
     */
    public void setRankingCards(RankingCards rankingCards) {
        this.rankingCards = rankingCards;
    }
}
