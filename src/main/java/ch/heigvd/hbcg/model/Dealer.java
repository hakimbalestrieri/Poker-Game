package ch.heigvd.hbcg.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dealer {

    private Deck deck;
   // private Set<Player> playerList = new HashSet<>(10);

    private static int count = 0;

    public Dealer(){
        deck = new Deck();
    }

    public void distribue(List<Player> playerInfo){

        //Distribue deux cartes à chaque joueurs
        //TODO : Penser à factory la distribution
        for (Player player: playerInfo){
            System.out.println("Phase 1 - Je distribue une carte à " + player.getPseudoEmetteur());
            player.receiveCard(deck.draw());
        }
        for (Player player: playerInfo){
            System.out.println("Phase 2 - Je distribue une carte à " + player.getPseudoEmetteur());
            player.receiveCard(deck.draw());
        }

    }

    public Set<Card> draw(){

        Set<Card> board = new HashSet<>(5);

        if(count == 0){
            //Flop
            deck.draw();
            for(int i= 0; i < 3; i++){
                board.add(deck.draw());
                count++;
            }
        }else{
            //Turn/River
            count++;
            deck.draw();
            board.add(deck.draw());
        }

        return board;
    }

}
