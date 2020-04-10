package ch.heigvd.hbcg;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Enumeration;

class JFrameInscription extends JFrame {

    // Components of the Form 
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

    private String[] dates
            = {"1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30",
            "31"};
    private String[] months
            = {"01", "02", "03", "04",
            "05", "06", "07", "08",
            "09", "10", "11", "12"};
    private String[] years
            = {"1995", "1996", "1997", "1998",
            "1999", "2000", "2001", "2002",
            "2003", "2004", "2005", "2006",
            "2007", "2008", "2009", "2010",
            "2011", "2012", "2013", "2014",
            "2015", "2016", "2017", "2018",
            "2019", "2020"};

    // constructor, to initialize the components 
    // with default values. 
    public JFrameInscription()
    {
        setTitle("Registration Form");
        setBounds(300, 90, 450, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        lTitle = new JLabel("Registration Form");
        lTitle.setFont(new Font("Arial", Font.PLAIN, 30));
        lTitle.setSize(300, 30);
        lTitle.setLocation(105, 30);
        c.add(lTitle);

        lUsername = new JLabel("Username");
        lUsername.setFont(new Font("Arial", Font.PLAIN, 20));
        lUsername.setSize(100, 20);
        lUsername.setLocation(50, 100);
        c.add(lUsername);

        tUsername = new JTextField();
        tUsername.setFont(new Font("Arial", Font.PLAIN, 15));
        tUsername.setSize(190, 20);
        tUsername.setLocation(200, 100);
        c.add(tUsername);

        lPassword = new JLabel("Password");
        lPassword.setFont(new Font("Arial", Font.PLAIN, 20));
        lPassword.setSize(100, 20);
        lPassword.setLocation(50, 150);
        c.add(lPassword);

        tPassword = new JPasswordField();
        tPassword.setFont(new Font("Arial", Font.PLAIN, 15));
        tPassword.setSize(150, 20);
        tPassword.setLocation(200, 150);
        c.add(tPassword);

        lGender = new JLabel("Gender");
        lGender.setFont(new Font("Arial", Font.PLAIN, 20));
        lGender.setSize(100, 20);
        lGender.setLocation(50, 200);
        c.add(lGender);

        tGender = new JRadioButton("Male");
        tGender.setFont(new Font("Arial", Font.PLAIN, 15));
        tGender.setSelected(true);
        tGender.setSize(75, 20);
        tGender.setLocation(200, 200);
        c.add(tGender);

        tFemale = new JRadioButton("Female");
        tFemale.setFont(new Font("Arial", Font.PLAIN, 15));
        tFemale.setSelected(false);
        tFemale.setSize(80, 20);
        tFemale.setLocation(275, 200);
        c.add(tFemale);

        gengp = new ButtonGroup();
        gengp.add(tGender);
        gengp.add(tFemale);
        System.out.println(gengp.getSelection().getActionCommand());


        lBirthday = new JLabel("DOB");
        lBirthday.setFont(new Font("Arial", Font.PLAIN, 20));
        lBirthday.setSize(100, 20);
        lBirthday.setLocation(50, 250);
        c.add(lBirthday);

        cDay = new JComboBox(dates);
        cDay.setFont(new Font("Arial", Font.PLAIN, 15));
        cDay.setSize(50, 20);
        cDay.setLocation(200, 250);
        c.add(cDay);

        cMonth = new JComboBox(months);
        cMonth.setFont(new Font("Arial", Font.PLAIN, 15));
        cMonth.setSize(60, 20);
        cMonth.setLocation(250, 250);
        c.add(cMonth);

        cYear = new JComboBox(years);
        cYear.setFont(new Font("Arial", Font.PLAIN, 15));
        cYear.setSize(60, 20);
        cYear.setLocation(320, 250);
        c.add(cYear);

        lAddress = new JLabel("Address");
        lAddress.setFont(new Font("Arial", Font.PLAIN, 20));
        lAddress.setSize(100, 20);
        lAddress.setLocation(50, 300);
        c.add(lAddress);

        tAddress = new JTextArea();
        tAddress.setFont(new Font("Arial", Font.PLAIN, 15));
        tAddress.setSize(200, 75);
        tAddress.setLocation(200, 300);
        tAddress.setLineWrap(true);
        c.add(tAddress);

        cTermsAndConditions = new JCheckBox("Accept Terms And Conditions.");
        cTermsAndConditions.setFont(new Font("Arial", Font.PLAIN, 15));
        cTermsAndConditions.setSize(250, 20);
        cTermsAndConditions.setLocation(50, 400);
        c.add(cTermsAndConditions);

        bSubmit = new JButton("Submit");
        bSubmit.setFont(new Font("Arial", Font.PLAIN, 15));
        bSubmit.setSize(100, 20);
        bSubmit.setLocation(150, 450);
        bSubmit.addActionListener(e-> {
            //Lancer inscription
            //Si fail alors message d'erreur (donc méthode inscription renvoie un bool)
            //Si réussi alors ca ferme la fenetre d'inscription et lance la vue
            try {
                ControllerInscription.createInscription(tUsername.getText(), tAddress.getText(), tPassword.getText(),
                        cTermsAndConditions.isSelected(), cDay.getSelectedItem(),
                        cMonth.getSelectedItem(), cYear.getSelectedItem(), getSelectedButtonText(gengp));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        c.add(bSubmit);

        reset = new JButton("Reset");
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

        setVisible(true);
    }




    public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }

}

// Driver Code 
class Inscription {

    public static void main(String[] args) throws Exception
    {
        JFrameInscription f = new JFrameInscription();
    }
} 