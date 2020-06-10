package ch.heigvd.hbcg.model;

import ch.heigvd.hbcg.network.PokerClientHandler;
import ch.heigvd.hbcg.network.PokerServer;
import ch.heigvd.hbcg.utilsPoker.UtilsPoker;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Classe Game
 *
 * @authors Hakim Balestrieri, Christian Gomes
 */
public class Game {

    private List<Card> boardCard = new ArrayList<>();
    private List<String> winnersGame = new ArrayList<>();
    private List<PlayerInfo> currentPlayers = new ArrayList<>();
    private List<PlayerInfo> winners = new ArrayList<>();
    private Dealer dealer;
    private PokerServer pokerServer;
    private static int i = 0;
    private STATE_GAME stageOfGame = STATE_GAME.DISTRIBUTION;

    private Timer timer;
    private double pot;

    /**
     * Constructeur
     *
     * @param handlers
     * @param server
     * @throws IOException
     */
    public Game(List<PokerClientHandler> handlers, PokerServer server) throws IOException {
        dealer = new Dealer(); //initialise un Dealer et son deck
        pokerServer = server;
        for (int i = 0; i < handlers.size(); i++) {
            System.out.println(handlers.get(i).getPlayerInfo().getPseudoEmetteur());
            currentPlayers.add(handlers.get(i).getPlayerInfo());
        }
        startGame();
    }

    /**
     * Création d'un nouveau dealer et lancement du début de partie
     *
     * @param handlers
     * @throws IOException
     */
    public void resetGame(List<PokerClientHandler> handlers) throws IOException {
        dealer = new Dealer(); //initialise un Dealer et son deck
        winnersGame.clear();
        winners.clear();
        currentPlayers.clear();
        for (int i = 0; i < handlers.size(); i++) {
            System.out.println(handlers.get(i).getPlayerInfo().getPseudoEmetteur());
            currentPlayers.add(handlers.get(i).getPlayerInfo());
        }
        stageOfGame = STATE_GAME.DISTRIBUTION;
        startGame();
    }

    /**
     * Mise à jour des playerInfos
     *
     * @param playerInfos
     */
    public void updateInfoOfPlayers(List<PlayerInfo> playerInfos) {
        for (int i = 0; i < currentPlayers.size(); i++) {
            currentPlayers.set(i, playerInfos.get(i));
        }
    }

    /**
     * Lancement de la partie
     *
     */
    synchronized private void startGame() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (pokerServer.getGameStarted()) {
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
                                stageOfGame = STATE_GAME.WINNER_IS;
                                break;

                            case WINNER_IS:
                                System.out.println("le pot est de " + pot);
                                setActionAllPlayers(Actions.WINNER_IS);
                                winners = UtilsPoker.isWinner(currentPlayers,boardCard);
                                System.out.println(winners.size());

                                for (PlayerInfo playerInfo : winners){

                                    winnersGame.add(playerInfo.getPseudoEmetteur());
                                    System.out.println("Winner : " + playerInfo.getPseudoEmetteur());
                                }
                                stageOfGame = STATE_GAME.FINISH;
                                break;

                            case FINISH:
                                setActionAllPlayers(Actions.FINISH);
                                stageOfGame = STATE_GAME.RESTART;
                                break;

                            case RESTART:
                                setActionAllPlayers(Actions.RESTART);
                                timer.cancel();
                                stageOfGame = STATE_GAME.RESTART;
                                break;

                        }

                        updatePlayers();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    timer.cancel();
                }
            }
        }, 0, 6000);

    }

    /**
     * Affichage du board pour tous les joueurs
     *
     * @param boardCard
     */
    private void drawBoardCardsAllPlayers(Card boardCard) {

        this.boardCard.add(boardCard);
        for (PlayerInfo playerInfo : currentPlayers) {
            playerInfo.setBoardCards(boardCard);
        }
    }

    /**
     * Vérification des personnes encore présentes dans la partie
     *
     * @throws IOException
     */
    private void checkIfSomeoneHasFold() throws IOException {

        for (int i = 0; i < currentPlayers.size(); i++) {
            if (currentPlayers.get(i).getAction() == Actions.FOLD) {
                currentPlayers.remove(i);
            } else {
                pot += currentPlayers.get(i).getMise();
            }
        }
        if (currentPlayers.size() <= 1) {
            //Game plus possible.
            setActionAllPlayers(Actions.RESTART);
            stageOfGame = STATE_GAME.RESTART;
            pot = 0;
            updatePlayers();
        }
    }

    /**
     * Affectation d'une action donnée aux joueurs
     *
     * @param action
     */
    private void setActionAllPlayers(Actions action) {

        for (PlayerInfo playerInfo : currentPlayers) {

            playerInfo.setAction(action);

            if(action == Actions.FINISH){

                for(PlayerInfo winner : winners){
                    if(winner.getPseudoEmetteur().equals(playerInfo.getPseudoEmetteur())){
                        playerInfo.setWinner(true);
                    }
                }

            }
        }
    }

    /**
     * Mise à jour des joueurs
     * @throws IOException
     */
    private void updatePlayers() throws IOException {

        //Création d'une copie car problème de modification concurrente
        List<PlayerInfo> myList = new CopyOnWriteArrayList<>(currentPlayers);

        Iterator<PlayerInfo> iterator = myList.iterator();

        while (iterator.hasNext()) {
            pokerServer.send(iterator.next());
        }
    }

}
