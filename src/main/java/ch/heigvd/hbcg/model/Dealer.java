package ch.heigvd.hbcg.model;

import java.util.List;

/**
 * Classe Dealer, représentant la "personne" distribuant les cartes.
 *
 * @authors Hakim Balestrieri, Christian Gomes
 */
public class Dealer {

    private Deck deck;
    private static int count = 0;

    /**
     * Constructeur
     */
    public Dealer() {
        deck = new Deck();
    }

    /**
     * Distribution des cartes
     *
     * @param playerInfos
     */
    public void distribue(List<PlayerInfo> playerInfos) {

        //Distribue deux cartes à chaque joueurs
        for (PlayerInfo playerInfo : playerInfos) {
            System.out.println("Phase 1 - Je distribue une carte à " + playerInfo.getPseudoEmetteur());
            playerInfo.receiveCard(deck.draw());
        }
        for (PlayerInfo playerInfo : playerInfos) {
            System.out.println("Phase 2 - Je distribue une carte à " + playerInfo.getPseudoEmetteur());
            playerInfo.receiveCard(deck.draw());
        }

    }

    /**
     * Retire la première carte du deck
     * @return
     */
    public Card draw() {
        return deck.draw();
    }

}
