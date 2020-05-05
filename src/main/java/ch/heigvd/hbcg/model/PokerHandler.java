package ch.heigvd.hbcg.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class PokerHandler implements Runnable {

    private ObjectOutputStream out;
    private ObjectInputStream in;
    private PokerServer pokerServer;
    private Socket socket;
    private Player currentPlayer;

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public PokerHandler(Socket socket, PokerServer pokerServer) throws IOException {

        this.pokerServer = pokerServer;
        this.socket = socket;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        new Thread(this).start();
    }

    public void run(){

        Player player;

        try {
            while ((player = (Player) in.readObject()) != null) {
                currentPlayer = player;
                pokerServer.send(player);
            }
            System.out.println("fin");
        } catch (SocketException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
            pokerServer.remove(this);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    public void send(Player player) throws IOException {
        out.writeObject(player);
    }
}
