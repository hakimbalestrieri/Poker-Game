package ch.heigvd.hbcg.model;

import java.util.HashSet;
import java.util.Set;

public class Game {

    private static Set<Card> boardCard = new HashSet<>(5);
    private static Set<Player> players = new HashSet<>(10);
    private Dealer dealer;

    public static Set<Player> getPlayers() {
        return players;
    }

    public Game(){
        dealer = new Dealer(); //initialise un Dealer et son deck
    }

    public static void main(String[] args) {
        //TODO A implementer
        new Game();
    }

    private void start(){
        //Distribution des cartes aux joueurs présents
        dealer.distribue();
    }

}
