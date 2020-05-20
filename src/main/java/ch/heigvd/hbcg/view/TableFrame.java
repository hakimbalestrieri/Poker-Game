package ch.heigvd.hbcg.view;

import ch.heigvd.hbcg.model.*;
import ch.heigvd.hbcg.network.PokerClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Hakim Balestrieri, Christian Gomes
 */
public class TableFrame extends JFrame implements UserInterface {


    private PokerClient pokerPlayer;
    private Set<Integer> positions = new HashSet();
    private boolean isMisedOrChecked = false;
    private boolean isCurrentPlayer = false;

    public TableFrame(PokerClient pokerPlayer) {

        if(pokerPlayer != null){
            initComponents();
            //game = PokerServer.getGame();
            // game.addPlayerToGame(pokerPlayer);
            this.pokerPlayer = pokerPlayer;
            pokerPlayer.setUserInterface(this);
            //   pokerPlayer.getPlayer().setJFrame(this);
            this.setVisible(true);
            System.out.println("New frame de " + pokerPlayer.getPlayer().getPlayerInfo().getPseudoEmetteur());
            setTitle(pokerPlayer.getPlayer().getPlayerInfo().getPseudoEmetteur());
        }

    }


    @Override
    public void display(PlayerInfo playerInfo){

        if(playerInfo == null) System.out.println("Tu es nul");

        if(pokerPlayer.getPlayer().getPlayerInfo().getPseudoEmetteur().equals(playerInfo.getPseudoEmetteur())){
            pokerPlayer.getPlayer().setPlayerInfo(playerInfo);
            isCurrentPlayer = true;
        }else{
            if(pokerPlayer.getPlayer().getPlayerInfo().getAction() != Actions.PHASE_MISE){
                isCurrentPlayer = false;
            }
        }

       //
        //if(isCurrentPlayer) messageArea.append("ACTION ACTUELLE : " + pokerPlayer.getPlayer().getPlayerInfo().getAction());
        System.out.println(playerInfo.getAction() + "display");


        //Attention - Action du joueur reçu en paramètre et non de celui à qui est la Jframe actuelle
        switch (playerInfo.getAction()){
            case CONNECTION:
                messageArea.append("Le joueur " + playerInfo.getPseudoEmetteur() + " rejoint la partie \n");
                break;
            case SIT_DOWN:
                //Synchro place
                System.out.println("Quelqu'n s'est assis");
                sitDown(playerInfo.getPosition(),true);
                // System.out.println("Quelqu'n s'est assis");
                break;
            case MESSAGE:
                //Affichage message
                System.out.println("J'ecris un message");
                messageArea.append(playerInfo.toString() + "\n");
                break;
            case START_GAME:
                showCards(playerInfo,true);
                break;
            case FLOP:
            case TURN:
            case RIVER:
                showBoardCard(playerInfo);
                break;
            case PHASE_MISE:
             //   messageArea.append("je recois une phase mise et moi je suis en " + pokerPlayer.getPlayer().getPlayerInfo().getAction());
                if(isCurrentPlayer)  messageArea.append("Vous pouvez miser");
                //pokerPlayer.getPlayer().getPlayerInfo().setAction(Actions.PHASE_MISE);
                //tempPlayerInfo = playerInfo;
                //miserCheck(playerInfo);
                break;
            case FOLD:
                showCards(playerInfo,false);
                break;
            case END:
                if(isCurrentPlayer){
                    messageArea.append("La partie est terminé");
                }
                break;
            default:
                System.out.println("Aucune action");
        }

    }

    private void miserCheck() {

        //System.out.println("VALEUR" + Double.valueOf(montant_mise.getText()));
        // String montant = StringUtils.substringBefore(valueSlider.getText().su, "CHF");
        //pokerPlayer.getPlayer().getPlayerInfo().setMise(666);
            pokerPlayer.getPlayer().getPlayerInfo().setMise(Double.parseDouble(valueSlider.getText()));
            messageArea.append("La mise est faite \n");
            pokerPlayer.sendByClient(pokerPlayer.getPlayer().getPlayerInfo());

    }

    private void showBoardCard(PlayerInfo playerInfo) {

        ImageIcon fold1, fold2, fold3, turn, river;
        String file = "src\\main\\resources\\resizedEtArrondie\\final\\";

        switch (playerInfo.getAction()){
            case FLOP :
                fold1 = new javax.swing.ImageIcon(file + playerInfo.getBoardCard().get(0).toString() + ".png");
                fold2 = new javax.swing.ImageIcon(file + playerInfo.getBoardCard().get(1).toString() + ".png");
                fold3 = new javax.swing.ImageIcon(file + playerInfo.getBoardCard().get(2).toString() + ".png");
                carte1.setIcon(fold1);
                carte2.setIcon(fold2);
                carte3.setIcon(fold3);
                carte1.setVisible(true);
                carte2.setVisible(true);
                carte3.setVisible(true);
                break;
            case TURN :
                turn = new javax.swing.ImageIcon(file + playerInfo.getBoardCard().get(3).toString() + ".png");
                carte4.setIcon(turn);
                carte4.setVisible(true);
                break;

            case RIVER:
                river = new javax.swing.ImageIcon(file + playerInfo.getBoardCard().get(4).toString() + ".png");
                carte5.setIcon(river);
                carte5.setVisible(true);
                break;
        }
    }

    // public void showCards(){
    public void showCards(PlayerInfo playerInfo, boolean fold){

        //int position = this.pokerPlayer.getPlayer().getPosition();
        String file = "src\\main\\resources\\resizedEtArrondie\\final\\";
        String file2 = "src\\main\\resources\\resizedEtArrondie\\output-onlinepngtools";
        ImageIcon image1;
        ImageIcon image2;

        if(playerInfo.getShowCard()){
            image1 = new javax.swing.ImageIcon(file + playerInfo.getPlayerHand().getCard1() + ".png");
            image2 = new javax.swing.ImageIcon(file + playerInfo.getPlayerHand().getCard2() + ".png");
        }else{
            image1 = new javax.swing.ImageIcon(file2 + ".png");
            image2 = new javax.swing.ImageIcon(file2 + ".png");
        }

        switch (playerInfo.getPosition()){
            case 1:

                jLabel10.setIcon(image1);
                jLabel11.setIcon(image2);
                jLabel10.setVisible(fold);
                jLabel11.setVisible(fold);

                break;
            case 2:
                jLabel8.setIcon(image1);
                jLabel9.setIcon(image2);
                jLabel8.setVisible(fold);
                jLabel9.setVisible(fold);
                break;
            case 3:
                jLabel6.setIcon(image1);
                jLabel7.setIcon(image2);
                jLabel6.setVisible(fold);
                jLabel7.setVisible(fold);
                break;
            case 4:
                jLabel4.setIcon(image1);
                jLabel5.setIcon(image2);
                jLabel4.setVisible(fold);
                jLabel5.setVisible(fold);
                break;
            case 5:
                jLabel2.setIcon(image1);
                jLabel3.setIcon(image2);
                jLabel2.setVisible(fold);
                jLabel3.setVisible(fold);
                break;
            case 6:
                jLabel12.setIcon(image1);
                jLabel13.setIcon(image2);
                jLabel12.setVisible(fold);
                jLabel13.setVisible(fold);
                break;
            case 7:
                jLabel14.setIcon(image1);
                jLabel15.setIcon(image2);
                jLabel14.setVisible(fold);
                jLabel15.setVisible(fold);
                break;
            case 8:
                jLabel16.setIcon(image1);
                jLabel17.setIcon(image2);
                jLabel16.setVisible(fold);
                jLabel17.setVisible(fold);
                break;
            case 9:
                jLabel18.setIcon(image1);
                jLabel19.setIcon(image2);
                jLabel18.setVisible(fold);
                jLabel19.setVisible(fold);
                break;
            case 10:
                jLabel20.setIcon(image1);
                jLabel21.setIcon(image2);
                jLabel20.setVisible(fold);
                jLabel21.setVisible(fold);
                break;
            default:
        }

    }


    @SuppressWarnings("unchecked")
    private void initComponents() {

        carte1 = new javax.swing.JLabel();
        carte2 = new javax.swing.JLabel();
        carte3 = new javax.swing.JLabel();
        carte4 = new javax.swing.JLabel();
        carte5 = new javax.swing.JLabel();
        pos1 = new javax.swing.JLabel();
        pos2 = new javax.swing.JLabel();
        pos3 = new javax.swing.JLabel();
        pos4 = new javax.swing.JLabel();
        pos5 = new javax.swing.JLabel();
        pos6 = new javax.swing.JLabel();
        pos7 = new javax.swing.JLabel();
        pos8 = new javax.swing.JLabel();
        pos9 = new javax.swing.JLabel();
        pos10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        croupier = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        jScrollBar1 = new javax.swing.JScrollBar();
        background_table = new javax.swing.JLabel();
        b_sendMessage = new javax.swing.JLabel();
        messageToSend = new javax.swing.JTextField();
        messageToDisplay = new javax.swing.JScrollPane();
        messageArea = new javax.swing.JTextArea();
        b_suivre = new javax.swing.JButton();
        b_seCoucher = new javax.swing.JButton();
        b_miser = new javax.swing.JButton();
        valueSlider = new javax.swing.JTextField();
        slider_miser = new javax.swing.JSlider();
        montant_mise = new javax.swing.JLabel();
        background_frame = new javax.swing.JLabel();

        setResizable(false);
        setMaximumSize(new Dimension(1500,1500));
        setPreferredSize(new Dimension(1003,1000));
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);


        carte1.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\resizedEtArrondie\\final\\one_trefle.png"));
        carte1.setMaximumSize(new java.awt.Dimension(100, 70));
        carte1.setMinimumSize(new java.awt.Dimension(100, 70));
        carte1.setPreferredSize(new java.awt.Dimension(100, 70));
        getContentPane().add(carte1);
        carte1.setBounds(360, 350, 70, 90);
        carte1.setVisible(false);

        carte2.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\resizedEtArrondie\\final\\two_trefle.png"));
        carte2.setText("jLabel2");
        getContentPane().add(carte2);
        carte2.setBounds(420, 350, 70, 90);
        carte2.setVisible(false);

        carte3.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\resizedEtArrondie\\final\\three_trefle.png"));
        getContentPane().add(carte3);
        carte3.setBounds(470, 350, 70, 90);
        carte3.setVisible(false);

        carte4.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\resizedEtArrondie\\final\\four_trefle.png"));
        getContentPane().add(carte4);
        carte4.setBounds(530, 350, 70, 90);
        carte4.setVisible(false);

        carte5.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\resizedEtArrondie\\final\\five_trefle.png"));
        getContentPane().add(carte5);
        carte5.setBounds(580, 350, 70, 90);
        carte5.setVisible(false);

        pos1.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\oval.png"));
        pos1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pos1MouseReleased(evt);
            }
        });
        getContentPane().add(pos1);
        pos1.setBounds(830, 260, 90, 80);

        pos2.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\oval.png"));
        pos2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pos2MouseReleased(evt);
            }
        });
        getContentPane().add(pos2);
        pos2.setBounds(860, 370, 90, 80);

        pos3.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\oval.png"));
        pos3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pos3MouseReleased(evt);
            }
        });
        getContentPane().add(pos3);
        pos3.setBounds(830, 480, 90, 80);

        pos4.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\oval.png"));
        pos4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pos4MouseReleased(evt);
            }
        });
        getContentPane().add(pos4);
        pos4.setBounds(720, 570, 90, 80);

        pos5.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\oval.png"));
        pos5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pos5MouseReleased(evt);
            }
        });
        getContentPane().add(pos5);
        pos5.setBounds(580, 570, 90, 80);

        pos6.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\oval.png"));
        pos6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pos6MouseReleased(evt);
            }
        });
        getContentPane().add(pos6);
        pos6.setBounds(400, 570, 90, 80);

        pos7.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\oval.png"));
        pos7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pos7MouseReleased(evt);
            }
        });
        getContentPane().add(pos7);
        pos7.setBounds(230, 560, 90, 80);

        pos8.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\oval.png"));
        pos8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pos8MouseReleased(evt);
            }
        });
        getContentPane().add(pos8);
        pos8.setBounds(110, 500, 90, 80);

        pos9.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\oval.png"));
        pos9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pos9MouseReleased(evt);
            }
        });
        getContentPane().add(pos9);
        pos9.setBounds(60, 380, 90, 80);

        pos10.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\oval.png"));
        pos10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pos10MouseReleased(evt);
            }
        });
        getContentPane().add(pos10);
        pos10.setBounds(80, 260, 90, 80);

        jLabel4.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\resizedEtArrondie\\output-onlinepngtools (1).png"));
        getContentPane().add(jLabel4);
        jLabel4.setBounds(710, 530, 70, 70);
        jLabel4.setVisible(false);

        jLabel5.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\resizedEtArrondie\\output-onlinepngtools (1).png"));
        getContentPane().add(jLabel5);
        jLabel5.setBounds(690, 520, 70, 70);
        jLabel5.setVisible(false);

        jLabel6.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\resizedEtArrondie\\output-onlinepngtools (1).png"));
        getContentPane().add(jLabel6);
        jLabel6.setBounds(810, 450, 70, 70);
        jLabel6.setVisible(false);

        jLabel7.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\resizedEtArrondie\\output-onlinepngtools (1).png"));
        getContentPane().add(jLabel7);
        jLabel7.setBounds(790, 440, 70, 70);
        jLabel7.setVisible(false);

        jLabel8.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\resizedEtArrondie\\output-onlinepngtools (1).png"));
        getContentPane().add(jLabel8);
        jLabel8.setBounds(830, 350, 70, 70);
        jLabel8.setVisible(false);

        jLabel9.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\resizedEtArrondie\\output-onlinepngtools (1).png"));
        getContentPane().add(jLabel9);
        jLabel9.setBounds(810, 340, 70, 70);
        jLabel9.setVisible(false);

        jLabel10.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\resizedEtArrondie\\output-onlinepngtools (1).png"));
        getContentPane().add(jLabel10);
        jLabel10.setBounds(810, 220, 70, 70);
        jLabel10.setVisible(false);

        jLabel11.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\resizedEtArrondie\\output-onlinepngtools (1).png"));
        getContentPane().add(jLabel11);
        jLabel11.setBounds(790, 210, 70, 70);
        jLabel11.setVisible(false);

        jLabel12.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\resizedEtArrondie\\output-onlinepngtools (1).png"));
        getContentPane().add(jLabel12);
        jLabel12.setBounds(390, 530, 70, 70);
        jLabel12.setVisible(false);

        jLabel13.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\resizedEtArrondie\\output-onlinepngtools (1).png"));
        getContentPane().add(jLabel13);
        jLabel13.setBounds(370, 520, 70, 70);
        jLabel13.setVisible(false);

        jLabel14.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\resizedEtArrondie\\output-onlinepngtools (1).png"));
        getContentPane().add(jLabel14);
        jLabel14.setBounds(220, 520, 70, 70);
        jLabel14.setVisible(false);

        jLabel15.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\resizedEtArrondie\\output-onlinepngtools (1).png"));
        getContentPane().add(jLabel15);
        jLabel15.setBounds(200, 510, 70, 70);
        jLabel15.setVisible(false);

        jLabel16.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\resizedEtArrondie\\output-onlinepngtools (1).png"));
        getContentPane().add(jLabel16);
        jLabel16.setBounds(100, 470, 70, 70);
        jLabel16.setVisible(false);

        jLabel17.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\resizedEtArrondie\\output-onlinepngtools (1).png"));
        getContentPane().add(jLabel17);
        jLabel17.setBounds(80, 460, 70, 70);
        jLabel17.setVisible(false);

        jLabel18.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\resizedEtArrondie\\output-onlinepngtools (1).png"));
        getContentPane().add(jLabel18);
        jLabel18.setBounds(50, 350, 70, 70);
        jLabel18.setVisible(false);

        jLabel19.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\resizedEtArrondie\\output-onlinepngtools (1).png"));
        getContentPane().add(jLabel19);
        jLabel19.setBounds(30, 340, 70, 70);
        jLabel19.setVisible(false);

        jLabel20.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\resizedEtArrondie\\output-onlinepngtools (1).png"));
        getContentPane().add(jLabel20);
        jLabel20.setBounds(70, 220, 70, 70);
        jLabel20.setVisible(false);

        jLabel21.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\resizedEtArrondie\\output-onlinepngtools (1).png"));
        getContentPane().add(jLabel21);
        jLabel21.setBounds(50, 210, 70, 70);
        jLabel21.setVisible(false);

        jLabel2.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\resizedEtArrondie\\output-onlinepngtools (1).png"));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(570, 540, 70, 70);
        jLabel2.setVisible(false);

        jLabel3.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\resizedEtArrondie\\output-onlinepngtools (1).png"));
        getContentPane().add(jLabel3);
        jLabel3.setBounds(550, 530, 70, 70);
        jLabel3.setVisible(false);

        croupier.setForeground(new java.awt.Color(255, 255, 255));
        croupier.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\croupier.png"));
        getContentPane().add(croupier);
        croupier.setBounds(460, 170, 70, 80);

        logo.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\Logos\\final\\allSizes\\medium.png"));
        getContentPane().add(logo);
        logo.setBounds(400, 30, 200, 162);
        getContentPane().add(jScrollBar1);
        jScrollBar1.setBounds(480, 710, 20, 200);

        background_table.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\poker_table.png"));
        getContentPane().add(background_table);
        background_table.setBounds(130, 80, 970, 620);
        background_table.getAccessibleContext().setAccessibleName("background");

        b_sendMessage.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\send.png"));
        b_sendMessage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                b_sendMessageMouseClicked(evt);
            }
        });
        getContentPane().add(b_sendMessage);
        b_sendMessage.setBounds(440, 915, 30, 40);

        messageToSend.setText("Saisissez votre texte ici...");
        messageToSend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                messageToSendMouseReleased(evt);
            }
        });
        messageToSend.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                messageToSendKeyPressed(evt);
            }
        });
        getContentPane().add(messageToSend);
        messageToSend.setBounds(0, 910, 500, 50);

        messageArea.setEditable(false);
        messageArea.setColumns(20);
        messageArea.setRows(5);
        messageArea.setFocusable(false);
        messageArea.setHighlighter(null);
        messageToDisplay.setViewportView(messageArea);

        getContentPane().add(messageToDisplay);
        messageToDisplay.setBounds(0, 710, 500, 200);

        b_suivre.setText("Suivre");
        b_suivre.setMaximumSize(new java.awt.Dimension(85, 23));
        b_suivre.setMinimumSize(new java.awt.Dimension(85, 23));
        b_suivre.setPreferredSize(new java.awt.Dimension(85, 23));
        getContentPane().add(b_suivre);
        b_suivre.setBounds(830, 770, 110, 23);

        b_seCoucher.setText("Se coucher");
        b_seCoucher.addActionListener(evt -> b_seCoucherActionPerformed(evt));
        getContentPane().add(b_seCoucher);
        b_seCoucher.setBounds(590, 770, 130, 22);

        b_miser.setText("Miser");
        b_miser.setMaximumSize(new java.awt.Dimension(85, 23));
        b_miser.setMinimumSize(new java.awt.Dimension(85, 23));
        b_miser.setPreferredSize(new java.awt.Dimension(85, 23));
        b_miser.addActionListener(evt -> b_miserActionPerformed(evt));
        getContentPane().add(b_miser);
        b_miser.setBounds(720, 770, 110, 23);

        valueSlider.setMaximumSize(new java.awt.Dimension(85, 23));
        valueSlider.setMinimumSize(new java.awt.Dimension(85, 23));
        valueSlider.setPreferredSize(new java.awt.Dimension(85, 23));
        valueSlider.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                valueSliderFocusLost(evt);
            }
        });
        valueSlider.addActionListener(evt -> valueSliderActionPerformed(evt));
        valueSlider.addPropertyChangeListener(evt -> valueSliderPropertyChange(evt));
        getContentPane().add(valueSlider);
        valueSlider.setBounds(720, 830, 100, 30);

        slider_miser.setMaximumSize(new java.awt.Dimension(255, 26));
        slider_miser.setMinimumSize(new java.awt.Dimension(255, 26));
        slider_miser.setPreferredSize(new java.awt.Dimension(255, 26));
        slider_miser.addChangeListener(evt -> slider_miserStateChanged(evt));
        getContentPane().add(slider_miser);
        slider_miser.setBounds(590, 790, 350, 26);

        montant_mise.setForeground(new java.awt.Color(255, 255, 255));
        montant_mise.setText("CHF");
        getContentPane().add(montant_mise);
        montant_mise.setBounds(840, 840, 90, 16);

        background_frame.setIcon(new javax.swing.ImageIcon("src\\main\\resources\\background_fond.png"));
        background_frame.setText("jLabel6");
        background_frame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                background_frameMouseReleased(evt);
            }
        });
        getContentPane().add(background_frame);
        background_frame.setBounds(-5, -6, 1000, 970);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //@todo remove player
                System.out.println("on ferme l'appli");
                super.windowClosing(e);
            }
        });

        pack();
    }



    private void slider_miserStateChanged(javax.swing.event.ChangeEvent evt) {
        //@TODO on est obligé de miser au moins 1 fois la big blind, il faut vérifier cela sur le Slider
        valueSlider.setText(String.valueOf(slider_miser.getValue()));
        if(slider_miser.getValue() > 0)
            b_miser.setEnabled(true);
        else
            b_miser.setEnabled(false);

    }

    private void pos1MouseReleased(java.awt.event.MouseEvent evt) {
        sitDown(1,false);
    }

    private void pos2MouseReleased(java.awt.event.MouseEvent evt) {
        sitDown(2,false);
    }

    private void pos3MouseReleased(java.awt.event.MouseEvent evt) {
        sitDown(3,false);
    }

    private void pos4MouseReleased(java.awt.event.MouseEvent evt) {
        sitDown(4,false);
    }

    private void pos5MouseReleased(java.awt.event.MouseEvent evt) {
        sitDown(5,false);
    }

    private void pos6MouseReleased(java.awt.event.MouseEvent evt) {
        sitDown(6,false);
    }

    private void pos7MouseReleased(java.awt.event.MouseEvent evt) {
        sitDown(7,false);
    }

    private void pos8MouseReleased(java.awt.event.MouseEvent evt) {
        sitDown(8,false);
    }

    private void pos9MouseReleased(java.awt.event.MouseEvent evt) {
        sitDown(9,false);
    }

    private void pos10MouseReleased(java.awt.event.MouseEvent evt) {
        sitDown(10,false);
    }

    private void b_seCoucherActionPerformed(java.awt.event.ActionEvent evt) {
        //System.out.print("Je me couche par terre!");
        if(isCurrentPlayer){
            if(pokerPlayer.getPlayer().getPlayerInfo().getAction() == Actions.PHASE_MISE) {
                pokerPlayer.getPlayer().getPlayerInfo().setAction(Actions.FOLD);
                pokerPlayer.sendByClient(pokerPlayer.getPlayer().getPlayerInfo());
            }
        }
    }

    private void b_miserActionPerformed(java.awt.event.ActionEvent evt) {
        messageArea.setText(messageArea.getText() + "Croupier : Mise de " + valueSlider.getText() + " de l'utilisateur : " + pokerPlayer.getPlayer().getPlayerInfo().getPseudoEmetteur() + " \n");
      //  System.out.println("Bouton press " + Double.parseDouble(valueSlider.getText()));
        //System.out.println(pokerPlayer.getPlayer().getPlayerInfo().getPseudoEmetteur() + " J'ai appuyé sur miser ");
        if(isCurrentPlayer && pokerPlayer.getPlayer().getPlayerInfo().getAction() == Actions.PHASE_MISE){
            miserCheck();
        }else{
            messageArea.append("Le joueur n'est pas en Action miser\n");
        }

    }

    private void formWindowOpened(java.awt.event.WindowEvent evt) {
        jScrollBar1.setValue(100);
        messageArea.setText("Croupier : Bienvenue à la table !\n");
        valueSlider.setText("0");
        slider_miser.setValue(0);
        slider_miser.setMaximum(10000); //@TODO definir le maximum de mise pour chaque table
        b_miser.setEnabled(false);
    }

    private void messageToSendMouseReleased(java.awt.event.MouseEvent evt) {
        if(messageToSend.getText().equals("Saisissez votre texte ici..."))
            messageToSend.setText("");
    }

    private void background_frameMouseReleased(java.awt.event.MouseEvent evt) {
        background_frame.requestFocusInWindow();
    }

    private void valueSliderPropertyChange(java.beans.PropertyChangeEvent evt) {

    }

    private void valueSliderActionPerformed(java.awt.event.ActionEvent evt) {
        if(!valueSlider.getText().equals("")) {
            slider_miser.setValue(Integer.valueOf(valueSlider.getText()));
            if(Integer.valueOf(valueSlider.getText()) > 0)
                b_miser.setEnabled(true);
            else
                b_miser.setEnabled(false);
            System.out.print(Integer.valueOf(valueSlider.getText()));

        }    }

    private void valueSliderFocusLost(java.awt.event.FocusEvent evt) {
        if(!valueSlider.getText().equals("")) {
            slider_miser.setValue(Integer.valueOf(valueSlider.getText()));

            System.out.print(Integer.valueOf(valueSlider.getText()));

        }
    }

    private void messageToSendKeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode()== KeyEvent.VK_ENTER){
            submitMessage();
        }
    }

    private void b_sendMessageMouseClicked(java.awt.event.MouseEvent evt) {
        submitMessage();
    }

    public void submitMessage() {
        String message = messageToSend.getText();
        if(!message.isEmpty()) {
            messageToSend.setText("");
            pokerPlayer.getPlayer().getPlayerInfo().setAction(Actions.MESSAGE);
            pokerPlayer.getPlayer().getPlayerInfo().setMessage(message);
            pokerPlayer.sendByClient(pokerPlayer.getPlayer().getPlayerInfo());

        }
    }

    public void sitDown(int position, boolean placeAdversaire) {

        final String filenameIcon = "src\\main\\resources\\user_male.png";

        if(placeAdversaire|| !positions.contains(position) && pokerPlayer.getPlayer().getPlayerInfo().getPosition() == 0) {
            positions.add(position);
            pokerPlayer.getPlayer().getPlayerInfo().setAction(Actions.SIT_DOWN);
            if(!placeAdversaire){
                pokerPlayer.getPlayer().getPlayerInfo().setPosition(position); //si joueur quitte partie (remove position)
                //pokerPlayer.getPlayer().setAction(Actions.SIT_DOWN);
                pokerPlayer.sendByClient(pokerPlayer.getPlayer().getPlayerInfo());
            }
            switch (position) {
                case 1:
                    pos1.setIcon(new javax.swing.ImageIcon(filenameIcon));
                    break;
                case 2:
                    pos2.setIcon(new javax.swing.ImageIcon(filenameIcon));
                    break;
                case 3:
                    pos3.setIcon(new javax.swing.ImageIcon(filenameIcon));
                    break;
                case 4:
                    pos4.setIcon(new javax.swing.ImageIcon(filenameIcon));
                    break;
                case 5:
                    pos5.setIcon(new javax.swing.ImageIcon(filenameIcon));
                    break;
                case 6:
                    pos6.setIcon(new javax.swing.ImageIcon(filenameIcon));
                    break;
                case 7:
                    pos7.setIcon(new javax.swing.ImageIcon(filenameIcon));
                    break;
                case 8:
                    pos8.setIcon(new javax.swing.ImageIcon(filenameIcon));
                    break;
                case 9:
                    pos9.setIcon(new javax.swing.ImageIcon(filenameIcon));
                    break;
                case 10:
                    pos10.setIcon(new javax.swing.ImageIcon(filenameIcon));
                    break;
            }

        }

        //System.out.print(position);
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
            java.util.logging.Logger.getLogger(TableFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TableFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TableFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TableFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    private javax.swing.JButton b_miser;
    private javax.swing.JButton b_seCoucher;
    private javax.swing.JLabel b_sendMessage;
    private javax.swing.JButton b_suivre;
    private javax.swing.JLabel background_frame;
    private javax.swing.JLabel background_table;
    private javax.swing.JLabel carte1;
    private javax.swing.JLabel carte2;
    private javax.swing.JLabel carte3;
    private javax.swing.JLabel carte4;
    private javax.swing.JLabel carte5;
    private javax.swing.JLabel croupier;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JLabel logo;
    private javax.swing.JTextArea messageArea;
    private javax.swing.JScrollPane messageToDisplay;
    private javax.swing.JTextField messageToSend;
    private javax.swing.JLabel montant_mise;
    private javax.swing.JLabel pos1;
    private javax.swing.JLabel pos10;
    private javax.swing.JLabel pos2;
    private javax.swing.JLabel pos3;
    private javax.swing.JLabel pos4;
    private javax.swing.JLabel pos5;
    private javax.swing.JLabel pos6;
    private javax.swing.JLabel pos7;
    private javax.swing.JLabel pos8;
    private javax.swing.JLabel pos9;
    private javax.swing.JSlider slider_miser;
    private javax.swing.JTextField valueSlider;

}
