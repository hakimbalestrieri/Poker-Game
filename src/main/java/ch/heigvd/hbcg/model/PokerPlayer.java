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
    private Player player;
    private Game game;

    public static void main(String[] args) {
       // PokerPlayer pokerPlayer = new PokerPlayer("PseudoNull");
        //new NewJFrame(pokerPlayer);
       // pokerPlayer.receive();
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public PokerPlayer(Player player){

        if(player != null){

            this.player = player;
         //   this.player.setPokerPlayer(this);

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
        // new NewJFrame(this);
        // this.receive();
        // receive();
    }

    public void receive() {
        Player player = null;
        try {
            System.out.println("Je tente de display");
            while ((player = (Player) in.readObject()) != null) {
                userInterface.display(player);
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

   /* public void send(String message) {
        try {
            out.writeObject(new PlayerInfo(playerInfo.getPseudoEmetteur(), message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

     public void send(Player player){
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
