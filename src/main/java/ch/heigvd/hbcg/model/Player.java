package ch.heigvd.hbcg.model;

import ch.heigvd.hbcg.network.PokerClientHandler;
import ch.heigvd.hbcg.view.TableFrame;

public class Player {

    private TableFrame tableFrame;
    private PlayerInfo playerInfo;
    private PokerClientHandler pokerClientHandler;

    /* public Player(PokerHandler pokerHandler) {
        this.pokerHandler = pokerHandler;
    }*/

    public PlayerInfo getPlayerInfo(){
        return playerInfo;
    }

    public void setJFrame(TableFrame tableFrame) {
        this.tableFrame = tableFrame;
    }

    public TableFrame getTableFrame(){
        return tableFrame;
    }

    public void setPlayerInfo(String s, String s1, double valueOf, boolean valueOf1) {
        playerInfo = new PlayerInfo();
        playerInfo.setPseudoEmetteur(s);
        playerInfo.setMessage(s1);
        playerInfo.setCredit(valueOf);
        playerInfo.setGenre(valueOf1);
    }

    public void setPlayerInfo(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
    }

}
