package ch.heigvd.hbcg.network;
import ch.heigvd.hbcg.model.Player;
import ch.heigvd.hbcg.model.PlayerInfo;
import ch.heigvd.hbcg.model.UserInterface;

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

    public static void main(String[] args){}

    /**
     * Constructeur
     * @param player
     */
    public PokerClient(Player player){

        if(player != null){

            this.player = player;

            try {
                socket = new Socket("localhost", 6669);
                out = new ObjectOutputStream(socket.getOutputStream());
                out.flush();
                in = new ObjectInputStream(socket.getInputStream());
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        new Thread(this).start();

    }

    /**
     * Réception du playerInfo
     */
    public void clientRun() {

        PlayerInfo playerInfo;

        try {
             while ((playerInfo = (PlayerInfo) in.readObject()) != null) {
                if(!playerInfo.getPseudoEmetteur().equals(player.getPlayerInfo().getPseudoEmetteur())){
                    playerInfo.setShowCard(false);
                }else{
                    playerInfo.setShowCard(true);
                }
                userInterface.display(playerInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Ecriture du playerInfo
     * @param playerInfo
     */

    synchronized public void sendByClient(PlayerInfo playerInfo){

        try {

            out.writeObject(new PlayerInfo(playerInfo));
          //  out.flush();
            out.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lance le client
     */
    @Override
    public void run() {
        this.clientRun();
    }

    /**
     * Retourne le player actuel
     * @return player
     */
    public Player getPlayer(){
        return player;
    }

    /**
     * set l'interface utilisateur
     * @param userInterface
     */
    public void setUserInterface(UserInterface userInterface) {
        this.userInterface = userInterface;
    }
}
