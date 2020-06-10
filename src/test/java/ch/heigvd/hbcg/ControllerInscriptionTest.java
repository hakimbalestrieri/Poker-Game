package ch.heigvd.hbcg;

import ch.heigvd.hbcg.controller.ControllerInscription;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.RandomAccessFile;

import static org.junit.jupiter.api.Assertions.*;

class ControllerInscriptionTest {

    @Test
    void createInscription() {
        try {
            //premiere inscription, l'utilisateur n'existe pas encore
            assertEquals(1, ControllerInscription.createInscription("testUser", "chemindutest", "password", true, 24,10,1993,"Homme"));
            //seconde inscription, l'utilisateur existe déjà et devrait renvoyer 0
            assertEquals(0, ControllerInscription.createInscription("testUser", "chemindutest", "password", true, 24,10,1993,"Homme"));
            //apres ce test il faut supprimer la dernière ligne du fichier pour pouvoir lancer ce test à nouveau dans de bonnes conditions.

            try{
                RandomAccessFile raf = new RandomAccessFile("users.txt", "rw");
                long length = raf.length();
                raf.setLength(length - 8);
                raf.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}