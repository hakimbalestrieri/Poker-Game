package ch.heigvd.hbcg.network;

import ch.heigvd.hbcg.model.Actions;
import ch.heigvd.hbcg.model.Game;
import ch.heigvd.hbcg.model.PlayerInfo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;



/**
 * Represente le serveur pour le jeu de Poker
 * @authors Balestrieri & Gomes
 */
public class PokerServer {

    public static final int PORT = 6669;
    private Game game;
    private List<PokerClientHandler> listHandlers = new ArrayList<>();
    private List<PlayerInfo> currentPlayers = new ArrayList<>();
    private boolean started = false;
    private boolean isUpdatable = false;

    public static void main(String[] args) {
        new PokerServer().runServer();
    }

    /**
     * Lancement du serveur avec l'ouverture d'un socket
     */
    private void runServer(){

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            System.out.println("Lancement du serveur sur " + PORT);

            while (true) {

                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                PokerClientHandler pokerClientHandler = new PokerClientHandler(socket,this);
                listHandlers.add(pokerClientHandler);
                currentPlayers.add(pokerClientHandler.getPlayerInfo());
                pokerClientHandler.start();
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Permet l'envoi des informations des joueurs aux différentes joueurs
     * @param playerInfo informations du joueur
     * @throws IOException
     */
    public void send(PlayerInfo playerInfo) throws IOException {


        for(int i=0 ; i< listHandlers.size(); i++){
            listHandlers.get(i).sendOnHandler(playerInfo);
            currentPlayers.set(i,listHandlers.get(i).getPlayerInfo());
        }

        if(checkActionPlayer(Actions.RESTART)){
            started = false;
            isUpdatable = false;
            game.resetGame(listHandlers);
            isUpdatable = true;
            started = true;
        }

        //Update des joueurs dès que la partie est lancée
        if(isUpdatable){
            game.updateInfoOfPlayers(currentPlayers);
        }

        //Lancement de la partie à partir de 3 joueurs
        //TODO : Ajouter un moyen de démarrage au lieu d'un nombre fixe de joueurs
        if(!started && listHandlers.size() == 3 && checkActionPlayer(Actions.SIT_DOWN)){
            started = true;
            startGame();
            isUpdatable = true;
        }

    }


    /**
     * Permet le lancement de la partie avec les handlers
     * @throws IOException
     */
    private void startGame() throws IOException {
        game = new Game(listHandlers,this);
    }

    /**
     * Enlève un joueur de la liste des handlers
     * @param handler
     */
    public void remove(PokerClientHandler handler){
        listHandlers.remove(handler);
    }


    /**
     * Permet de savoir si tous les joueurs connectés sont sur une action précise
     * @param action action à verifier
     * @return vrai/faux
     */
    private boolean checkActionPlayer(Actions action){

        for(PokerClientHandler handler : listHandlers){
            if(handler.getPlayerInfo().getAction() != action){
                return false;
            }
        }
        return true;
    }

    /**
     * Permet de savoir si la partie a été lancée
     * @return vrai/faux
     */
    public boolean getGameStarted(){
        return started;
    }
}