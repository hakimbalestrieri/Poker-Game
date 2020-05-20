package ch.heigvd.hbcg.model;

import ch.heigvd.hbcg.network.PokerClientHandler;
import ch.heigvd.hbcg.network.PokerServer;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game {

    private List<Card> boardCard = new ArrayList<>();
    //private List<PlayerInfo> pokerPlayers = new ArrayList<>();
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
        //t2.start();
        for(int i = 0; i < handlers.size(); i++){
            //pokerPlayers.add(i, handlers.get(i).getPlayerInfo());
            System.out.println(handlers.get(i).getPlayerInfo().getPseudoEmetteur());
            currentPlayers.add(handlers.get(i).getPlayerInfo());
        }
        // new Thread(this).start();
        startGame();
    }

    public void resetGame(List<PokerClientHandler> handlers) throws IOException {
        dealer = new Dealer(); //initialise un Dealer et son deck
        currentPlayers.clear();
        for(int i = 0; i < handlers.size(); i++){
            //pokerPlayers.add(i, handlers.get(i).getPlayerInfo());
            System.out.println(handlers.get(i).getPlayerInfo().getPseudoEmetteur());
            currentPlayers.add(handlers.get(i).getPlayerInfo());
        }
        // new Thread(this).start();
        stageOfGame = STATE_GAME.DISTRIBUTION;
        startGame();
    }

    public void updateInfoOfPlayers(List<PlayerInfo> playerInfos){

        for(int i = 0; i < currentPlayers.size(); i++){
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
                                dealer.distribue(currentPlayers);
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
                                stageOfGame = STATE_GAME.MISE_TURN;
                                break;

                            case MISE_TURN:
                                setActionAllPlayers(Actions.PHASE_MISE);
                                stageOfGame = STATE_GAME.TURN;
                                break;

                            case TURN:
                                checkIfSomeoneHasFold();
                                drawBoardCardsAllPlayers(dealer.draw());
                                setActionAllPlayers(Actions.TURN);
                                stageOfGame = STATE_GAME.MISE_RIVER;
                                break;

                            case MISE_RIVER:
                                setActionAllPlayers(Actions.PHASE_MISE);
                                stageOfGame = STATE_GAME.RIVER;
                                break;

                            case RIVER:
                                checkIfSomeoneHasFold();
                                drawBoardCardsAllPlayers(dealer.draw());
                                setActionAllPlayers(Actions.RIVER);
                                stageOfGame = STATE_GAME.END;
                                break;

                            case END:
                                System.out.println("le pot est de " + pot);
                                setActionAllPlayers(Actions.END);
                                //TODO : Prevoir gagnant partie ici avec affichage de son pot gagnant
                                stageOfGame = STATE_GAME.END;
                                break;

                            case RESTART:
                                setActionAllPlayers(Actions.RESTART);
                                stageOfGame = STATE_GAME.FINISH;
                                break;

                        }

                        updatePlayers();
                        if(stageOfGame == STATE_GAME.FINISH) {
                            timer.purge();
                            timer.cancel();
                        }


                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                else{
                    timer.cancel();
                }
            }
        },0,5000);

        //   if(!STATE_GAME.equals("END")) startGame();

    }

    private void drawBoardCardsAllPlayers(Card boardCard){

        this.boardCard.add(boardCard);
        for(PlayerInfo playerInfo : currentPlayers){
            playerInfo.setBoardCards(boardCard);
        }

    }

    private void checkIfSomeoneHasFold() throws IOException {

        for (int i = 0; i < currentPlayers.size();i++){
            if(currentPlayers.get(i).getAction() == Actions.FOLD){
                currentPlayers.remove(i);
            }else{
                pot += currentPlayers.get(i).getMise();
            }
        }
        if(currentPlayers.size() <= 1){
            //Game plus possible.
            setActionAllPlayers(Actions.END);
            stageOfGame = STATE_GAME.END;
            pot = 0;
            updatePlayers();
        }
    }

    private void setActionAllPlayers(Actions action) throws IOException {

        for(PlayerInfo playerInfo : currentPlayers){
            playerInfo.setAction(action);
        }

    }

    private void updatePlayers() throws IOException {




            //Création d'une copie car problème de modification concurrente
            List<PlayerInfo> myList = new CopyOnWriteArrayList<>(currentPlayers);


            Iterator<PlayerInfo> iterator = myList.iterator();

            while(iterator.hasNext()){
                pokerServer.send(iterator.next());
            }
    }

}
