package ch.heigvd.hbcg.view;

import ch.heigvd.hbcg.controller.ControllerLogin;
import ch.heigvd.hbcg.model.PokerPlayer;
import ch.heigvd.hbcg.utils.UtilsFileReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 *
 * @author Hakim
 */
public class ConnexionFrame extends javax.swing.JFrame {

    private PokerPlayer pokerPlayer;

    public ConnexionFrame() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {

        //Définition de l'icone de l'application
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Large.PNG"));
        ImageIcon icon = new ImageIcon(image);
        setIconImage(icon.getImage());
        setResizable(false);


        username = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        b_inscription = new javax.swing.JLabel();
        l_username = new javax.swing.JLabel();
        b_connexion = new javax.swing.JLabel();
        l_password = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(415, 398));
        getContentPane().setLayout(null);
        getContentPane().add(username);
        username.setBounds(180, 170, 150, 22);
        username.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    passwordKeyPressed(e);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        getContentPane().add(password);
        password.setBounds(180, 220, 150, 22);
        password.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    passwordKeyPressed(e);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        b_inscription.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\registration.png")); 
        b_inscription.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                b_inscriptionMouseReleased(evt);
            }
        });
        getContentPane().add(b_inscription);
        b_inscription.setBounds(80, 280, 60, 50);

        l_username.setFont(new java.awt.Font("Yu Gothic Medium", 0, 12)); 
        l_username.setForeground(new java.awt.Color(255, 255, 255));
        l_username.setText("Utilisateur");
        l_username.setPreferredSize(new java.awt.Dimension(53, 22));
        getContentPane().add(l_username);
        l_username.setBounds(70, 180, 70, 16);

        b_connexion.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\login.png")); 
        getContentPane().add(b_connexion);
        b_connexion.setBounds(280, 280, 50, 50);
        b_connexion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    login(e);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        l_password.setFont(new java.awt.Font("Yu Gothic Medium", 0, 12)); 
        l_password.setForeground(new java.awt.Color(255, 255, 255));
        l_password.setText("Mot de passe");
        getContentPane().add(l_password);
        l_password.setBounds(70, 220, 90, 20);

        jLabel1.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\Logos\\final\\allSizes\\medium.png")); 
        getContentPane().add(jLabel1);
        jLabel1.setBounds(94, 20, 230, 140);

        background.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\background_fond.png")); 
        background.setText("jLabel1");
        getContentPane().add(background);
        background.setBounds(0, 0, 400, 360);

        pack();
    }


    private void passwordKeyPressed(KeyEvent evt) throws IOException {
        if (evt.getKeyCode()== KeyEvent.VK_ENTER){
            System.out.println("te");
            login(null);
        }
    }

    private void login(MouseEvent event) throws IOException {
        boolean result = ControllerLogin.logUser(username.getText(), password.getPassword());
        if(result) {
            JOptionPane.showMessageDialog(this, "Connexion réussie");
            dispose();
            //dans le futur cela ne vas pas directement ouvrir la table de jeu mais un espèce de "salon" dans lequel on pourra choisir la table

            //Mettre à jour les infos du playerInfo avec le fichier sauvegardé en local

            pokerPlayer = new PokerPlayer(UtilsFileReader.getPlayerInfo(username.getText()));
            new TableFrame(pokerPlayer);
      //      pokerPlayer.receive();

        }
        else {
            JOptionPane.showMessageDialog(this, "Identifiants incorrects", "Connexion", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void b_inscriptionMouseReleased(java.awt.event.MouseEvent evt) {
        InscriptionFrame f = new InscriptionFrame();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConnexionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConnexionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConnexionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConnexionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new ConnexionFrame().setVisible(true));
    }

    private javax.swing.JLabel b_connexion;
    private javax.swing.JLabel b_inscription;
    private javax.swing.JLabel background;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel l_password;
    private javax.swing.JLabel l_username;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField username;
}
