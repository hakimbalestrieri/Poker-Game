package ch.heigvd.hbcg.view;

import ch.heigvd.hbcg.controller.ControllerInscription;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;

import static javax.swing.JOptionPane.ERROR_MESSAGE;

/**
 * @author Balestrieri & Gomes
 */
class InscriptionFrame extends JFrame {

    private Container c;
    private JLabel lTitle;
    private JLabel lUsername;
    private JTextField tUsername;
    private JLabel lPassword;
    private JTextField tPassword;
    private JLabel lGender;
    private JRadioButton tGender;
    private JRadioButton tFemale;
    private ButtonGroup gengp;
    private JLabel lBirthday;
    private JComboBox cDay;
    private JComboBox cMonth;
    private JComboBox cYear;
    private JLabel lAddress;
    private JTextArea tAddress;
    private JCheckBox cTermsAndConditions;
    private JButton bSubmit;
    private JButton reset;
    private JTextArea tout;
    private JLabel res;
    private JTextArea resadd;
    private JLabel background;

    /**
     * Constructeur
     */
    public InscriptionFrame() {
        setTitle("Registration Form");
        setBounds(300, 90, 450, 550);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);


        lTitle = new JLabel("Formulaire d'enregistrement");
        lTitle.setFont(new Font("Arial", Font.PLAIN, 24));
        lTitle.setForeground(new Color(255, 255, 255));
        lTitle.setSize(300, 30);
        lTitle.setLocation(80, 30);
        c.add(lTitle);

        lUsername = new JLabel("Nom d'utilisateur");
        lUsername.setFont(new Font("Arial", Font.PLAIN, 15));
        lUsername.setSize(150, 20);
        lUsername.setForeground(new Color(255, 255, 255));
        lUsername.setLocation(50, 100);
        c.add(lUsername);

        tUsername = new JTextField();
        tUsername.setFont(new Font("Arial", Font.PLAIN, 15));
        tUsername.setSize(200, 25);
        tUsername.setLocation(200, 100);
        c.add(tUsername);

        lPassword = new JLabel("Mot de passe");
        lPassword.setFont(new Font("Arial", Font.PLAIN, 15));
        lPassword.setSize(100, 20);
        lPassword.setForeground(new Color(255, 255, 255));
        lPassword.setLocation(50, 150);
        c.add(lPassword);

        tPassword = new JPasswordField();
        tPassword.setFont(new Font("Arial", Font.PLAIN, 15));
        tPassword.setSize(200, 25);
        tPassword.setLocation(200, 150);
        c.add(tPassword);

        lGender = new JLabel("Sexe");
        lGender.setFont(new Font("Arial", Font.PLAIN, 15));
        lGender.setSize(100, 20);
        lGender.setForeground(new Color(255, 255, 255));
        lGender.setLocation(50, 200);
        c.add(lGender);

        tGender = new JRadioButton("Homme");
        tGender.setFont(new Font("Arial", Font.PLAIN, 15));
        tGender.setSelected(true);
        tGender.setSize(75, 20);
        tGender.setForeground(new Color(255, 255, 255));
        tGender.setLocation(200, 200);
        c.add(tGender);

        tFemale = new JRadioButton("Femme");
        tFemale.setFont(new Font("Arial", Font.PLAIN, 15));
        tFemale.setSelected(false);
        tFemale.setForeground(new Color(255, 255, 255));
        tFemale.setSize(80, 20);
        tFemale.setLocation(275, 200);
        c.add(tFemale);

        gengp = new ButtonGroup();
        gengp.add(tGender);
        gengp.add(tFemale);
        System.out.println(gengp.getSelection().getActionCommand());


        lBirthday = new JLabel("Naissance");
        lBirthday.setForeground(new Color(255, 255, 255));
        lBirthday.setFont(new Font("Arial", Font.PLAIN, 15));
        lBirthday.setSize(100, 20);
        lBirthday.setLocation(50, 250);
        c.add(lBirthday);


        ArrayList<String> days_tmp = new ArrayList<String>();
        for (int day = 1; day < 31; day++) {
            if (day < 10) {
                days_tmp.add("0" + day);
            } else {
                days_tmp.add(String.valueOf(day));
            }
        }

        cDay = new JComboBox(days_tmp.toArray());
        cDay.setFont(new Font("Arial", Font.PLAIN, 15));
        cDay.setSize(50, 20);
        cDay.setLocation(200, 250);
        c.add(cDay);


        ArrayList<String> months_tmp = new ArrayList<String>();
        for (int month = 1; month < 13; month++) {
            if (month < 10) {
                months_tmp.add("0" + month);
            } else {
                months_tmp.add(String.valueOf(month));
            }

        }

        cMonth = new JComboBox(months_tmp.toArray());
        cMonth.setFont(new Font("Arial", Font.PLAIN, 15));
        cMonth.setSize(60, 20);
        cMonth.setLocation(250, 250);
        c.add(cMonth);


        ArrayList<String> years_tmp = new ArrayList<String>();
        for (int years = Calendar.getInstance().get(Calendar.YEAR); years >= 1980; years--) {
            years_tmp.add(years + "");
        }
        cYear = new JComboBox(years_tmp.toArray());

        cYear.setFont(new Font("Arial", Font.PLAIN, 15));
        cYear.setSize(80, 20);
        cYear.setLocation(320, 250);
        c.add(cYear);

        lAddress = new JLabel("Adresse");
        lAddress.setForeground(new Color(255, 255, 255));
        lAddress.setFont(new Font("Arial", Font.PLAIN, 15));
        lAddress.setSize(100, 20);
        lAddress.setLocation(50, 300);
        c.add(lAddress);

        tAddress = new JTextArea();
        tAddress.setFont(new Font("Arial", Font.PLAIN, 15));
        tAddress.setSize(200, 75);
        tAddress.setLocation(200, 300);
        tAddress.setLineWrap(true);
        c.add(tAddress);

        cTermsAndConditions = new JCheckBox("Accepter les conditions générales d'utilisation");
        cTermsAndConditions.setFont(new Font("Arial", Font.PLAIN, 15));
        cTermsAndConditions.setForeground(new Color(255, 255, 255));
        cTermsAndConditions.setSize(350, 20);
        cTermsAndConditions.setLocation(50, 400);
        c.add(cTermsAndConditions);

        bSubmit = new JButton("Envoyer");
        bSubmit.setFont(new Font("Arial", Font.PLAIN, 15));
        bSubmit.setSize(100, 20);
        bSubmit.setLocation(150, 450);
        bSubmit.addActionListener(e -> {
            int returnValue;
            try {
                returnValue = ControllerInscription.createInscription(tUsername.getText(), tAddress.getText(), tPassword.getText(),
                        cTermsAndConditions.isSelected(), cDay.getSelectedItem(),
                        cMonth.getSelectedItem(), cYear.getSelectedItem(), getSelectedButtonText(gengp));

                switch (returnValue) {
                    case -3: JOptionPane.showMessageDialog(this,"Veuillez saisir tous les champs","Champs incomplets" , ERROR_MESSAGE);
                    break;
                    case -2: JOptionPane.showMessageDialog(this, "Vous devez cocher les conditions générales d'utilisations", "CGV", ERROR_MESSAGE);
                        break;
                    case -1: JOptionPane.showMessageDialog(this, "Vous devez être majeur afin de vous inscrire", "Age", ERROR_MESSAGE);
                        break;
                    case 0: JOptionPane.showMessageDialog(this, "Cet utilisateur existe déjà", "Utilisateur déjà existant", ERROR_MESSAGE);
                        break;
                    case 1: JOptionPane.showMessageDialog(this, "Inscription saisie avec succès");
                            dispose();
                        break;
                    default:
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        c.add(bSubmit);

        reset = new JButton("Effacer");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 20);
        reset.setLocation(270, 450);
        reset.addActionListener(e -> {
            String def = "";
            tUsername.setText(def);
            tAddress.setText(def);
            tPassword.setText(def);
            res.setText(def);
            cTermsAndConditions.setSelected(false);
            cDay.setSelectedIndex(0);
            cMonth.setSelectedIndex(0);
            cYear.setSelectedIndex(0);
        });
        c.add(reset);


        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(100, 500);
        c.add(res);


        background = new JLabel("");
        background.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\background_fond.png"));
        background.setBounds(0, 0, 1000, 800);

        c.add(background);


        setVisible(true);
    }

    /**
     * Retourne le texte du bouton sélectionné
     * @param buttonGroup
     * @return
     */
    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }

}