package ch.heigvd.hbcg.model;

import java.util.ArrayList;
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

    public void distribue(List<PlayerInfo> playerInfos){

        //Distribue deux cartes à chaque joueurs
        //TODO : Penser à factory la distribution
        for (PlayerInfo playerInfo: playerInfos){
            System.out.println("Phase 1 - Je distribue une carte à " + playerInfo.getPseudoEmetteur());
            playerInfo.receiveCard(deck.draw());
        }
        for (PlayerInfo playerInfo: playerInfos){
            System.out.println("Phase 2 - Je distribue une carte à " + playerInfo.getPseudoEmetteur());
            playerInfo.receiveCard(deck.draw());
        }

    }

    public Card draw(){

       /* List<Card> board = new ArrayList<>(5);

        if(count == 0){
            //Flop
            deck.draw();
            System.out.println("Distrubution du flop");
            for(int i= 0; i < 3; i++){
                board.add(deck.draw());
                count++;
            }
        }else{
            //Turn/River
            System.out.println("Distrubution de la turn/river");
            count++;
            deck.draw();
            board.add(deck.draw());
        }

        return board;*/

        return deck.draw();

    }

}
