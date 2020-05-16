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
    //private ExecutorService pool;
    private List<PokerClientHandler> listClients = new ArrayList<>();
    private boolean started = false;
    private double pot = 0;
    // private List<Player> listPlayers = new ArrayList<>();

    public static void main(String[] args) {
        new PokerServer().runServer();
    }

    private void runServer(){

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {

            //   pool = Executors.newFixedThreadPool(10);
            System.out.println("Server is listening on port " + PORT);

            while (true) {

                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
                PokerClientHandler pokerClientHandler = new PokerClientHandler(socket,this);
                listClients.add(pokerClientHandler);
                pokerClientHandler.start();
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }


    public void send(PlayerInfo playerInfo) throws IOException {

        //Est-ce que les infos sont à jour dans le handler ?
        for(PokerClientHandler handler : listClients){
            handler.sendOnHandler(playerInfo);
        }

        if(!started && listClients.size() == 2 && checkPlayerAreSit(listClients)){
            started = true;
            System.out.println("GAME IS STARTED");
            startGame();
        }

    }

    private void startGame() throws IOException {

        game = new Game(listClients,this);
    }

    public void remove(PokerClientHandler handler){
        listClients.remove(handler);
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