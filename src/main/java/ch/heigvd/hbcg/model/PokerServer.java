package ch.heigvd.hbcg.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class PokerServer {

    private PlayerInfo playerInfo = new PlayerInfo("TEST", "TEST");
    private ArrayList<PokerHandler> handlers;
    private PokerHandler pokerHandler;
    private PokerHandler currentPlayer;

    public static void main(String[] args) throws IOException {
        new PokerServer().receive();
    }

    public PokerServer(){
        handlers = new ArrayList<>();
    }


    private void receive() throws IOException {
        ServerSocket serverSocket = new ServerSocket(6669);

        while(true){

            Socket socket = serverSocket.accept();
            System.out.println("Client connecté");
            pokerHandler = new PokerHandler(socket,this);
            handlers.add(pokerHandler);
            /*  if(handlers.size() == 1){
                currentPlayer = pokerHandler;
                currentPlayer.send(pokerItem);
            }*/

        }
    }

    public void send(PlayerInfo playerInfo) throws IOException {
       // currentPlayer.send(pokerItem);

        for(PokerHandler handler : handlers){
            handler.send(playerInfo);
        }
    }

    public void remove(PokerHandler pokerHandler){
        handlers.remove(pokerHandler);
    }

}
