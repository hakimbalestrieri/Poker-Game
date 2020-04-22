package ch.heigvd.hbcg.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class PokerPlayer implements Runnable {

    private UserInterface userInterface;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String pseudo;
    private PlayerInfo playerInfo;

    public static void main(String[] args) {
       // PokerPlayer pokerPlayer = new PokerPlayer("PseudoNull");
        //new NewJFrame(pokerPlayer);
       // pokerPlayer.receive();
    }


    public PokerPlayer(PlayerInfo playerInfo){

        if(playerInfo != null){

            this.playerInfo = playerInfo;

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

        new Thread(this).start();
       //new NewJFrame(this);
     //  this.receive();
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
        PlayerInfo playerInfo = null;
        try {
            System.out.println("Je tente de display");
            while ((playerInfo = (PlayerInfo) in.readObject()) != null) {
                userInterface.display(playerInfo);
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
            out.writeObject(new PlayerInfo(playerInfo.getPseudoEmetteur(), message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void display(PlayerInfo playerInfo){
        userInterface.display(playerInfo);
    }

    public String getPseudo() {
        return playerInfo.getPseudoEmetteur();
    }

    @Override
    public void run() {
        this.receive();
    }
}
