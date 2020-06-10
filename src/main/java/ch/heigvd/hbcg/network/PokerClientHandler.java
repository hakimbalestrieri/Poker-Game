package ch.heigvd.hbcg.network;

import ch.heigvd.hbcg.model.PlayerInfo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Represente le thread d'un joueur
 * @authors Balestrieri & Gomes
 */
public class PokerClientHandler extends Thread {

    private Socket socket;
    private PokerServer server;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private PlayerInfo playerInfo;

    /**
     * Constructeur
     * @param socket
     * @param server
     * @throws IOException
     */
    public PokerClientHandler(Socket socket,PokerServer server) throws IOException {
        this.socket = socket;
        this.server = server;
        out = new ObjectOutputStream(socket.getOutputStream());
        out.flush();
        in = new ObjectInputStream(socket.getInputStream());
    }

    public void resetGame(){
        playerInfo.resetInfo();
    }

    /**
     * Thread de réception du handler
     */
    public void run() {
        try {

            PlayerInfo playerInfo;

            while ((playerInfo = (PlayerInfo) in.readObject()) != null){
                System.out.println("[HANDLERCLIENT] : Lecture des infos de " + playerInfo.getPseudoEmetteur());
                this.playerInfo = playerInfo;
                server.send(playerInfo);
            }
            socket.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
        try {
            socket.close();
            server.remove(this);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Envoi du playerInfo
     * @param playerInfo
     * @throws IOException
     */
    synchronized public void sendOnHandler(PlayerInfo playerInfo) throws IOException {
        System.out.println("SEND[HANDLER] : " + playerInfo.getPseudoEmetteur() + " " + playerInfo.getAction());

        //Update info joueur si handler concerné
        if(playerInfo.getPseudoEmetteur().equals(this.playerInfo.getPseudoEmetteur())) this.playerInfo = playerInfo;

        out.writeObject(new PlayerInfo(playerInfo));
        out.flush();

    }

    synchronized public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

}
