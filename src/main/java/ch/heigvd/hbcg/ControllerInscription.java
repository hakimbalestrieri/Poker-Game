package ch.heigvd.hbcg;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;


public class ControllerInscription {


    public static int createInscription(String username, String password, String address, boolean termsConditions, Object day,
                                        Object month, Object year, String gender) throws IOException {

        Period period = Period.between(
                LocalDate.of(
                        Integer.valueOf(String.valueOf(year)), Integer.valueOf(String.valueOf(month)),
                        Integer.valueOf(String.valueOf(day))), LocalDate.now());
        if(termsConditions) {
            return -2;
        }

        if (period.getYears() < 18) {
            return -1; //mineur
        }
        if (checkIfUserExist(username)) {
            return 0; //utilisateur existe deja
        }

        StringBuilder toWrite = new StringBuilder();
        toWrite.append(username);
        toWrite.append(';');
        //toWrite.append()

        try {
            Files.write(Paths.get("users.txt"), "the text".getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
        return 1;
    }

    public static boolean checkIfUserExist(String name) throws IOException {

        /*final String FILE_NAME = "users.txt";
        String content = Files.readString(Paths.get(FILE_NAME), StandardCharsets.US_ASCII);

        if(content.toLowerCase().indexOf(name.toLowerCase()) != -1) {
            System.out.println("mot trouvé");
        }
        return content.contains(name); //user existe pas donc ok*/
        final String FILE_NAME = "users.txt";
        final String SPLITTER = ";";
        BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
        String ligne;

        while ((ligne = br.readLine()) != null) {
            String[] dataTab = ligne.split(SPLITTER);
            if (name.equals(dataTab[0])) {
                System.out.println("oui");
                return true; //erreur , utilisateur existe deja
            }
        }

        return false;

    }
}
