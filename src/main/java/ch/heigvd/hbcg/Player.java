package ch.heigvd.hbcg;

public class Player {

    private boolean actif = true;
    private Hand playerHand;
    private int position;
    private boolean bigBlind = false;

    public Player(){
    }
    
    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isBigBlind() {
        return bigBlind;
    }

    public void setBigBlind(boolean bigBlind) {
        this.bigBlind = bigBlind;
    }

    public Player(Hand playerHand) {
        this.playerHand = playerHand;
    }

    public void setPlayerHand(Hand playerHand) {
        this.playerHand = playerHand;
    }

    public void receiveCard(Card card) {
        playerHand.add(card);
    }

    public Hand getPlayerHand() {
        return playerHand;
    }
}
