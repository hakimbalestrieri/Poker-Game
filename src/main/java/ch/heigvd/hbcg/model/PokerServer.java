package ch.heigvd.hbcg.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class PokerServer {

    private List<PokerPlayer> listClients = new ArrayList<>();
    private ArrayList<PokerHandler> handlers;
    private PokerHandler currentPlayer;
    private Game game;
    private boolean started = false;

    public static void main(String[] args) throws IOException {
        new PokerServer().receive();
    }

    public PokerServer(){
        handlers = new ArrayList<>();
        //game = new Game();
    }

    public ArrayList<PokerHandler> getHandlers() {
        return handlers;
    }

    private void receive() throws IOException {

        ServerSocket serverSocket = new ServerSocket(6669);

        while(true){

            Socket socket = serverSocket.accept();
            System.out.println("Client connecté");
            currentPlayer = new PokerHandler(socket,this);
            //Player newPlayer = new Player(pokerHandler);
            //listOfPlayers.add(pokerHandler);
            handlers.add(currentPlayer);

        }
    }

    public void send(Player player) throws IOException {

        for(PokerHandler handler : handlers){

            System.out.println("pokerPlayer sur serveur : " + player.getAction());
            handler.send(player);
        }

        //if(!started && handlers.size() == 2 && checkAllSit(listPlayers)){
        System.out.println(checkAllSit(handlers));
        if(!started && handlers.size() == 2 && checkAllSit(handlers)){
            //game.addPlayers(handlers);
            System.out.println("Tout le monde est assis");
            game = new Game(handlers);
            game.setServer(this);
            started = true;
            game.start();
        }

    }

     private boolean checkAllSit(List<PokerHandler> handlers){

        System.out.println("Taille : " + handlers.size());

        for (PokerHandler handler : handlers) {
            if(handler.getCurrentPlayer().getAction() != Actions.SIT_DOWN){
                return false;
            }
        }

        return true;
    }

    public void remove(PokerHandler pokerHandler){
        handlers.remove(pokerHandler);
    }

}
