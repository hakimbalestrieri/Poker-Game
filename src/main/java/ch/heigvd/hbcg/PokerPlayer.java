package ch.heigvd.hbcg;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class PokerPlayer {

    private UserInterface userInterface;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String pseudo;

    public static void main(String[] args) {
        PokerPlayer pokerPlayer = new PokerPlayer();
        new NewJFrame(pokerPlayer);
        //pokerPlayer.receive();
    }

    public PokerPlayer(String pseudo){

        if(pseudo != null){

            this.pseudo = pseudo;

            try {
                socket = new Socket("localhost", 6669);
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       // receive();
    }

    public PokerPlayer() {

        System.out.println("APPEL POKERPLAYER VIDE ()");
       /* try {
            socket = new Socket("localhost", 6669);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void receive() {
        PokerItem pokerItem = null;
        try {
            System.out.println("Je tente de display");
            while ((pokerItem = (PokerItem) in.readObject()) != null) {
                userInterface.display(pokerItem);
                System.out.println("DISPLAY un truc");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setUserInterface(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public void send(String message) {
        try {
            out.writeObject(new PokerItem(pseudo, message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void display(PokerItem pokerItem){
        userInterface.display(pokerItem);
    }

    public String getPseudo() {
        return pseudo;
    }
}
