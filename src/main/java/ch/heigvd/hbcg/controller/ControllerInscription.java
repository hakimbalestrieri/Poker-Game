package ch.heigvd.hbcg.controller;
import java.io.*;
import java.time.LocalDate;
import java.time.Period;


public class ControllerInscription {

    /**
     * Réalisation de l'inscription d'un joueur, inscription des données dans le fichier texte gérant les utilisateurs.
     * @param username
     * @param address
     * @param password
     * @param termsConditions
     * @param day
     * @param month
     * @param year
     * @param gender
     * @return
     * @throws IOException
     */
    public static int createInscription(String username, String address, String password, boolean termsConditions, Object day,
                                        Object month, Object year, String gender) throws IOException {

        //Vérifier que tous les champs sont remplis
        if(username.equals("") || password.equals("") || address.equals(""))
            return -3;
        //Vérification de l'age
        Period period = Period.between(
                LocalDate.of(
                        Integer.valueOf(String.valueOf(year)), Integer.valueOf(String.valueOf(month)),
                        Integer.valueOf(String.valueOf(day))), LocalDate.now());
        if(!termsConditions) {
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
        toWrite.append(password);
        toWrite.append(';');
        toWrite.append(day);
        toWrite.append(';');
        toWrite.append(month);
        toWrite.append(';');
        toWrite.append(year);
        toWrite.append(';');
        toWrite.append(address);
        toWrite.append(';');
        toWrite.append(gender.equals("Homme") ? 1 : 0 );
        toWrite.append(';');
        toWrite.append("1000" + "\n");

        OutputStream os = null;
        try {
            os = new FileOutputStream(new File("users.txt"), true);
            os.write(String.valueOf(toWrite).getBytes(), 0, toWrite.length());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return 1;
    }

    /**
     * Vérifie si l'utilisateur existe
     * @param name
     * @return
     * @throws IOException
     */
    public static boolean checkIfUserExist(String name) throws IOException {

        final String FILE_NAME = "users.txt";
        final String SPLITTER = ";";
        BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
        String ligne;

        while ((ligne = br.readLine()) != null) {
            String[] dataTab = ligne.split(SPLITTER);
            if (name.equals(dataTab[0])) {
                return true; //erreur , utilisateur existe deja
            }
        }

        return false;

    }
}
