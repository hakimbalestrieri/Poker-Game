package ch.heigvd.hbcg;

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

    public PokerHandler(Socket socket, PokerServer pokerServer) throws IOException {

        this.pokerServer = pokerServer;
        this.socket = socket;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        new Thread(this).start();
    }

    public void run(){

        PokerItem pokerItem;

        try {
            while ((pokerItem = (PokerItem) in.readObject()) != null) {
                pokerServer.send(pokerItem);
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

    public void send(PokerItem pokerItem) throws IOException {
        out.writeObject(pokerItem);
    }
}