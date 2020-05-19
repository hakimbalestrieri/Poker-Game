package ch.heigvd.hbcg.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PokerServer {

    public static final int PORT = 6669;
    private Game game;
    private List<PokerClientHandler> listClients = new ArrayList<>();
    private List<PlayerInfo> currentPlayers = new ArrayList<>();
    private boolean started = false;
    private boolean updatePlayers = false;
    private double pot = 0;

    public static void main(String[] args) {
        new PokerServer().runServer();
    }

    private void runServer(){

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            System.out.println("Server is listening on port " + PORT);

            while (true) {

                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                PokerClientHandler pokerClientHandler = new PokerClientHandler(socket,this);
                listClients.add(pokerClientHandler);
                currentPlayers.add(pokerClientHandler.getPlayerInfo());
                pokerClientHandler.start();
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void send(PlayerInfo playerInfo) throws IOException {

        //Est-ce que les infos sont à jour dans le handler ?
     /*   for(PokerClientHandler handler : listClients){
            handler.sendOnHandler(playerInfo);
           //addUpdatePlayer(playerInfo);
        }*/

        for(int i=0 ; i< listClients.size(); i++){
            listClients.get(i).sendOnHandler(playerInfo);
            System.out.println("for " + i + " " + listClients.get(i).getPlayerInfo());
            currentPlayers.set(i,listClients.get(i).getPlayerInfo());
        }


        if(updatePlayers){
            game.updateInfoOfPlayers(currentPlayers);
            //currentPlayers.clear();
        }

        if(!started && listClients.size() == 3 && checkPlayerAreSit(listClients)){
            started = true;
            System.out.println("GAME IS STARTED");
            startGame();
            //addUpdatePlayer(playerInfo);
           // currentPlayers.clear();
            updatePlayers = true;
        }
    }

    private void startGame() throws IOException {
        game = new Game(listClients,this);
    }

    public void remove(PokerClientHandler handler){
        listClients.remove(handler);
    }

    private void addUpdatePlayer(PlayerInfo playerInfo){

     /*  if(currentPlayers.size() != 0){
            for(int i = 0; i < listClients.size() ; i++) {
                if(currentPlayers.get(i).getPseudoEmetteur().equals(playerInfo.getPseudoEmetteur())){
                    currentPlayers.set(i,playerInfo);
                }else{
                    currentPlayers.add(playerInfo);
                }
            }
        }else{
            currentPlayers.add(playerInfo);
        }*/

       /* if(currentPlayers.size() != 0){

            for(int i = 0; i < currentPlayers.size() ; i++){
                if(currentPlayers.get(i).getPseudoEmetteur().equals(playerInfo.getPseudoEmetteur())){
                    currentPlayers.set(i,playerInfo);
                }else{
                    currentPlayers.add(playerInfo);
                }
            }
        }else{
            currentPlayers.add(playerInfo);
        }*/
       // currentPlayers.clear();

      //  currentPlayers.add(playerInfo);

    }


    private boolean checkPlayerAreSit(List<PokerClientHandler> handlers){

        for(PokerClientHandler handler : handlers){
            if(handler.getPlayerInfo().getAction() != Actions.SIT_DOWN){
                return false;
            }
        }
        return true;
    }

    public boolean getGameStarted(){
        return started;
    }
}