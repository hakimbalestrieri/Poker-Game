package ch.heigvd.hbcg.model;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class PokerClient implements Runnable {

    private UserInterface userInterface;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Player player;

    public static void main(String[] args){};

    public PokerClient(Player player){

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

    public void clientRun() {

        PlayerInfo playerInfo = null;

        try {

            System.out.println("Je tente de display");
            while ((playerInfo = (PlayerInfo) in.readObject()) != null) {
                System.out.println("receive[player] : " + playerInfo.getAction());
                // System.out.println("son message est : " + p);

                if(!playerInfo.getPseudoEmetteur().equals(player.getPlayerInfo().getPseudoEmetteur())){
                    playerInfo.setShowCard(false);
                }else{
                    playerInfo.setShowCard(true);
                }

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

    public void sendByClient(PlayerInfo playerInfo){

        try {
            out.writeObject(new PlayerInfo(playerInfo));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void display(PlayerInfo playerinfo){

        userInterface.display(playerinfo);
    }

    //public String getPseudo() {
    //    return player.getPseudoEmetteur();
    // }

    @Override
    public void run() {
        this.clientRun();
    }

    public Player getPlayer(){
        return player;
    }

}
