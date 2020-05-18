package ch.heigvd.hbcg.model;

import java.beans.EventHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game {

    private List<Card> boardCard = new ArrayList<>();
    private List<PlayerInfo> pokerPlayers = new ArrayList<>();
    private List<PlayerInfo> currentPlayers = new ArrayList<>();
    private Dealer dealer;
    private PokerServer pokerServer;
    private static int i = 0;
    private STATE_GAME stageOfGame = STATE_GAME.DISTRIBUTION;
    private Timer timer;
    private double pot;

    public Game(List<PokerClientHandler> handlers, PokerServer server) throws IOException {
        dealer = new Dealer(); //initialise un Dealer et son deck
        pokerServer = server;

        for(int i = 0; i < handlers.size(); i++){
            pokerPlayers.add(i, handlers.get(i).getPlayerInfo());
            System.out.println(handlers.get(i).getPlayerInfo().getPseudoEmetteur());
            currentPlayers.add(handlers.get(i).getPlayerInfo());
        }
        // new Thread(this).start();
        startGame();
        //t2.start();
    }

    public void updateInfoOfPlayers(List<PlayerInfo> playerInfos){

        for(int i = 0; i < playerInfos.size(); i++){
          currentPlayers.set(i,playerInfos.get(i));
            //System.out.println(handlers.get(i).getPlayerInfo().getPseudoEmetteur());
        }

    }

    private void startGame() throws IOException {

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(pokerServer.getGameStarted()){
                    try {
                        switch (stageOfGame) {

                            case DISTRIBUTION:
                                dealer.distribue(pokerPlayers);
                                setActionAllPlayers(Actions.START_GAME);
                                stageOfGame = STATE_GAME.MISE_FLOP;
                                break;

                            case MISE_FLOP:
                                setActionAllPlayers(Actions.PHASE_MISE);
                                stageOfGame = STATE_GAME.FLOP;
                                break;

                            case FLOP:
                                checkIfSomeoneHasFold();
                             //   System.out.println("Mise P1 de : " + currentPlayers.get(0).getPseudoEmetteur() + " " + currentPlayers.get(0).getMise());
                               // System.out.println("Mise P2 : " + currentPlayers.get(1).getPseudoEmetteur() + " " + currentPlayers.get(1).getMise());
                                for (int i = 0; i < 3; i++) {
                                    drawBoardCardsAllPlayers(dealer.draw());
                                }
                                setActionAllPlayers(Actions.FLOP);
                                stageOfGame = STATE_GAME.TURN;
                                break;

                            case TURN:
                                drawBoardCardsAllPlayers(dealer.draw());
                                setActionAllPlayers(Actions.TURN);
                                stageOfGame = STATE_GAME.RIVER;
                                break;

                            case RIVER:
                                drawBoardCardsAllPlayers(dealer.draw());
                                setActionAllPlayers(Actions.RIVER);
                                stageOfGame = STATE_GAME.END;
                                break;

                        }

                        updatePlayers();

                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                else{
                    timer.cancel();
                }
            }
        },0,7000);


        //   if(!STATE_GAME.equals("END")) startGame();

    }

    private void drawBoardCardsAllPlayers(Card boardCard){

        this.boardCard.add(boardCard);
        for(PlayerInfo playerInfo : pokerPlayers){
            playerInfo.setBoardCards(boardCard);
        }

    }

    private void checkIfSomeoneHasFold() throws IOException {

        for (int i = 0; i < currentPlayers.size();i++){
            if(currentPlayers.get(i).getAction() == Actions.FOLD){
                currentPlayers.remove(i);
            }
        }
        if(currentPlayers.size() <= 1){
            //Game plus possible.
            setActionAllPlayers(Actions.END);
            updatePlayers();
        }
    }

    private void setActionAllPlayers(Actions action) throws IOException {

        for(PlayerInfo playerInfo : pokerPlayers){
            playerInfo.setAction(action);
        }

    }

    private void updatePlayers() throws IOException {

        for(PlayerInfo playerInfo : pokerPlayers){
            pokerServer.send(playerInfo);
        }

    }

    /*Thread t2 = new Thread(new Runnable() {

        @Override
        public void run() {
            for(int i = 0; i < 5; i++) {
                try {
                    startGame();
                    Thread.sleep(3000);
                } catch (InterruptedException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("Thread T2 : "+i);
            }
        }
    });*/

/*
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
    */

}
