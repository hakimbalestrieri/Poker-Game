package ch.heigvd.hbcg;

public class Player {

    private boolean actif = true;
    private Hand playerHand;

    public Player(){
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
