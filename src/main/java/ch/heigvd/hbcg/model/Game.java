package ch.heigvd.hbcg.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game {

    private Set<Card> boardCard = new HashSet<>(5);
    private List<Player> pokerPlayers = new ArrayList<>(10);
    //private Set<Player> players = new HashSet<>(10);
    private Dealer dealer;
    private PokerServer pokerServer;

    public Game(PokerServer pokerServer){
        this.pokerServer = pokerServer;
        dealer = new Dealer(); //initialise un Dealer et son deck
    }

    public Game(Set<Player> listOfPlayers) {
       // pokerPlayers = listOfPlayers; boucle for
    }

    public static void main(String[] args) {
        //TODO A implementer
        //new Game();

    }

    public int sizePlayers(){
        return pokerPlayers.size();
    }

    public void start(Set<Player> listPlayers){
        System.out.println(listPlayers.size());
        //Distribution des cartes aux joueurs présents
        //dealer.distribue(pokerPlayers);
        //System.out.println(pokerPlayers.size());
     }

     public void start() throws IOException {
         System.out.println(pokerPlayers.size());
         dealer.distribue(pokerPlayers);
         System.out.println(pokerPlayers.get(0).getPlayerHand());
         System.out.println(pokerPlayers.get(1).getPlayerHand());
         setActionAllPlayers(Actions.START_GAME);
         System.out.println(pokerPlayers.get(0).getAction() + "start");

         System.out.println(pokerPlayers.get(0).getPlayerHand().getCard1() + " card avant start");
         pokerServer.send(pokerPlayers.get(0));
         //   pokerPlayers.get(0).getPokerPlayer().send(pokerPlayers.get(0));
         //pokerPlayers.get(0).getPokerPlayer().send(pokerPlayers.get(0));
        // pokerPlayer.send(pokerPlayer.getPlayer());
    }

    private void setActionAllPlayers(Actions action){

        for (Player pokerPlayer : pokerPlayers)
        {
            pokerPlayer.setAction(action);
        }
    }



    public List<Player> getPokerPlayers() {
        return pokerPlayers;
    }


    public void addPlayers(ArrayList<PokerHandler> handlers) {
        for (PokerHandler handler : handlers)
        {
            if(!checkIfExistOnTable(handler.getCurrentPlayer())){
            pokerPlayers.add(handler.getCurrentPlayer());
            }

        }
    }


    public boolean checkIfExistOnTable(Player currentPlayer){

        for (Player pokerPlayer : pokerPlayers)
        {
            if(pokerPlayer.getPseudoEmetteur() == currentPlayer.getPseudoEmetteur()){
                return true;
            }
        }
        return false;
    }
}
