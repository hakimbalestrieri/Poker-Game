package ch.heigvd.hbcg;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dealer {

    private Deck deck;
   // private Set<Player> playerList = new HashSet<>(10);

    private static int count = 0;

    public Dealer(){
        new Deck();
    }

    public void distribue(){
        Set<Player> playerList = Game.getPlayers();
        //Distribue deux cartes à chaque joueurs
        //TODO : Penser à factory la distribution
        for (Player player: playerList){
            player.receiveCard(deck.draw());
        }
        for (Player player: playerList){
            player.receiveCard(deck.draw());
        }

    }

    public Set<Card> draw(){

        Set<Card> tempCard = new HashSet<>(5);

        if(count == 0){
            //Flop
            deck.draw();
            for(int i= 0; i < 3; i++){
                tempCard.add(deck.draw());
                count++;
            }
        }else{
            //Turn/River
            count++;
            deck.draw();
            tempCard.add(deck.draw());
        }

        return tempCard;
    }

}
