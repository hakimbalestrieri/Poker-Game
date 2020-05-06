package ch.heigvd.hbcg.model;

import ch.heigvd.hbcg.view.TableFrame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game implements Runnable{

    private Set<Card> boardCard = new HashSet<>();
    private List<Player> pokerPlayers = new ArrayList<>();
    private Dealer dealer;
    private PokerServer pokerServer;
    private Thread thread;
    private static int i = 0;

    public void setServer(PokerServer pokerServer){
        this.pokerServer = pokerServer;
    }

    public Game(ArrayList<PokerHandler> handlers) {
        dealer = new Dealer(); //initialise un Dealer et son deck
        for(int i = 0; i < handlers.size(); i++){
            //System.out.println(handlers.get(i).getCurrentPlayer());
            pokerPlayers.add(i, handlers.get(i).getCurrentPlayer());

        }
    }

    public static void main(String[] args) {
        //TODO A implementer
        //new Game();

    }

    public int sizePlayers(){
        return pokerPlayers.size();
    }

    @Override
    public void run() {

        System.out.println("Je suis dans le run de game avec i = " + i);
        //thread.setPriority(Thread.MIN_PRIORITY);
        while(true){

            switch (i){
                case 0 :
                    if(checkAllSit_(pokerPlayers));
                    break;
                case 1:
                    System.out.println(pokerPlayers.size() + "SIZE AVANT DISTRUB : " + pokerPlayers.get(0).getPlayerHand().size());
                    dealer.distribue(pokerPlayers);
                    System.out.println(pokerPlayers.size() + "SIZE APRES DISTRUB : " + pokerPlayers.get(0).getPlayerHand().size());
                    System.out.println("1er Carte du 1er joueur : " + pokerPlayers.get(0).getPlayerHand().getCard1());
                    System.out.println("2eme Carte du 1er joueur : " + pokerPlayers.get(0).getPlayerHand().getCard2());
                    System.out.println("1er Carte du 2eme joueur : " + pokerPlayers.get(1).getPlayerHand().getCard1());
                    System.out.println("2eme Carte du 2eme joueur : " + pokerPlayers.get(1).getPlayerHand().getCard2());
                    System.out.println(i);

                    pokerPlayers.get(0).setAction(Actions.START_GAME);
                    pokerPlayers.get(1).setAction(Actions.START_GAME);

                    try {
                        System.out.println(pokerServer);
                        pokerServer.send(new Player(pokerPlayers.get(0)));

                    } catch (IOException  e) {
                        e.printStackTrace();
                    }

                    break;
                case 2:
                    try {
                        pokerServer.send(new Player(pokerPlayers.get(1)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i);
                    break;
                case 3:
                    System.out.println(i);
                    break;
                case 4:
                    System.out.println(i);
                    break;
            }
            i++;

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }

     public void start() throws IOException {

         thread = new Thread(this);
         thread.start();

         System.out.println("Je start le thread de game");


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


   /* public void addPlayers(ArrayList<PokerHandler> handlers) {
        for (PokerHandler handler : handlers)
        {
            if(!checkIfExistOnTable(handler.getCurrentPlayer())){
            pokerPlayers.add(handler.getCurrentPlayer());
            }

        }
    }*/


    public boolean checkIfExistOnTable(Player currentPlayer){

        for (Player pokerPlayer : pokerPlayers)
        {
            if(pokerPlayer.getPseudoEmetteur() == currentPlayer.getPseudoEmetteur()){
                return true;
            }
        }
        return false;
    }


    private boolean checkAllSit_(List<Player> players){

        System.out.println("Taille de players : " + players.size());
        for (Player player : players) {
            System.out.println("ACTION : " + player.getAction());
            if(player.getAction() != Actions.SIT_DOWN){
                return false;
            }

        }
        System.out.println("Check allSit is okay");
        return true;
    }

    public void addPlayers(Player pokerPlayer) {
        pokerPlayers.add(pokerPlayer);
    }
}
