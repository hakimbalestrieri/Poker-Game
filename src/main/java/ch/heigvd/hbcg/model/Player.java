package ch.heigvd.hbcg.model;

import ch.heigvd.hbcg.network.PokerClientHandler;
import ch.heigvd.hbcg.view.TableFrame;

/**
 * Classe Player
 *
 * @authors Hakim Balestrieri, Christian Gomes
 */
public class Player {

    private PlayerInfo playerInfo;

    /**
     * Retourne les informations d'un joueur
     * @return playerInfo
     */
    public PlayerInfo getPlayerInfo(){
        return playerInfo;
    }

    /**
     * Construction de l'objet playerInfo du joueur
     * @param s
     * @param s1
     * @param valueOf
     * @param valueOf1
     */
    public void setPlayerInfo(String s, String s1, double valueOf, boolean valueOf1) {
        playerInfo = new PlayerInfo();
        playerInfo.setPseudoEmetteur(s);
        playerInfo.setMessage(s1);
        playerInfo.setCredit(valueOf);
        playerInfo.setGenre(valueOf1);
    }

    /**
     * Liaison sur un playerInfo déjà défini
     * @param playerInfo
     */
    public void setPlayerInfo(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
    }

}
