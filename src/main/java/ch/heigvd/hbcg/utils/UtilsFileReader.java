package ch.heigvd.hbcg.utils;

import ch.heigvd.hbcg.model.Player;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UtilsFileReader {

    public static Player getPlayerInfo(String username) throws IOException {

        //Recuperation des informations d'un joueur
        final String FILE_NAME = "users.txt";
        final String SPLITTER = ";";
        BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
        String ligne;

        while ((ligne = br.readLine()) != null) {
            String[] dataTab = ligne.split(SPLITTER);
            if (username.equals(dataTab[0])) {
                Player player = new Player(dataTab[0],"",Double.valueOf(dataTab[7]),Boolean.valueOf(dataTab[6]));
                return player;
            }
        }
        return null;
    }

}
