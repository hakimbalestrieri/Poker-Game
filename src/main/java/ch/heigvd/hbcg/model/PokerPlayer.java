package ch.heigvd.hbcg.model;

import ch.heigvd.hbcg.view.TableFrame;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

public class PokerPlayer implements Runnable {

    private UserInterface userInterface;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Player player;

    public static void main(String[] args) {
    }

    public PokerPlayer(Player player){

        if(player != null){

            this.player = player;

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

    }

    public void receive() {
        Player player_ = null;
        try {
            System.out.println("Je tente de display");
            while ((player_ = (Player) in.readObject()) != null) {
                System.out.println("receive[player] : " + player_.getAction());
                userInterface.display(player_);
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

     public void send(Player player){
        // Player _player = new Player(player);
        try {
            out.writeObject(new Player(player));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     public void display(Player player){
        userInterface.display(player);
    }

    public String getPseudo() {
        return player.getPseudoEmetteur();
    }

    @Override
    public void run() {
        this.receive();
    }

    public Player getPlayer(){
        return player;
    }

}
