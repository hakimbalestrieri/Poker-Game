package ch.heigvd.hbcg.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PokerServer {

    private Set<Player> listPlayers = new HashSet<>();
    private ArrayList<PokerHandler> handlers;
    private PokerHandler pokerHandler;
    private Game game;
    private boolean started = false;

    public static void main(String[] args) throws IOException {
        new PokerServer().receive();
    }

    public PokerServer(){
        handlers = new ArrayList<>();
        game = new Game(this);
    }

    private void receive() throws IOException {

        ServerSocket serverSocket = new ServerSocket(6669);

        while(true){

            Socket socket = serverSocket.accept();
            System.out.println("Client connecté");
            pokerHandler = new PokerHandler(socket,this);
           //Player newPlayer = new Player(pokerHandler);
           //listOfPlayers.add(newPlayer);
            handlers.add(pokerHandler);
            //game.getPokerPlayers().add(pokerHandler.getPlayer());


           //game.getPokerPlayers().add(//joueurs)
          /*  if(!started && handlers.size() == 2){
                System.out.println("2 joueurs");
                //new Game();
                //game.start();
                new Game(listOfPlayers).start();
                started = true;
            }*/

        }
    }

    public void send(Player player) throws IOException {

       // currentPlayer = player;
      //  game.getPokerPlayers().add(player);
        //System.out.println(game.sizePlayers());
       /* if(player.getAction() == Actions.SIT_DOWN){
            listOfPlayers.add(player);
        }**/
        System.out.println(player.getPlayerHand().getCard1());


        for(PokerHandler handler : handlers){
            handler.send(player);
        }

        if(!started && handlers.size() == 2 && checkAllSit(handlers)){
            //game.addPlayers(handlers);
            game.addPlayers(handlers);
            started = true;
            game.start();
        }

       /*if(player.getAction() == Actions.SIT_DOWN){
           player.addGame(game);
           listPlayers.add(player);

       }/*
        if(handlers.size() == 2){
            game.start(listPlayers);
        }
        /*if(started) {
            started = false;
            game.start();
        }*/
    }

    private boolean checkAllSit(ArrayList<PokerHandler> handlers){
        for (PokerHandler handler :handlers) {
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
