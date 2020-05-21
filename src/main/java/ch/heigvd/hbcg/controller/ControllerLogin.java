package ch.heigvd.hbcg.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ControllerLogin {

    /**
     * Connexion d'un joueur
     * @param username
     * @param password
     * @return
     * @throws IOException
     */
    public static boolean logUser(String username, char[] password) throws IOException {

        final String FILE_NAME = "users.txt";
        final String SPLITTER = ";";
        BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
        String ligne;

        while ((ligne = br.readLine()) != null) {
            String[] dataTab = ligne.split(SPLITTER);
            if (username.equals(dataTab[0]) && String.valueOf(password).equals(dataTab[1])) {
                System.out.println("login correct");
                return true;
            }
        }
        return false;
    }
}
