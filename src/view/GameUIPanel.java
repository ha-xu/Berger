package view;

import model.GameObjects.Ranch;
import model.SoundPlayer;
import view.Threads.RedessineUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameUIPanel extends JPanel {
    private Ranch ranch;
    private int money = 0;
    private int nbsheep = 0;
    private int nbgrass = 0;

    private JLabel label_date;
    private JLabel label_coin;
    private JLabel label_sheep;
    private JLabel label_grass;

    //threads
    private GameFrame frame;
    private final RedessineUI redessineUi = new RedessineUI(this);

    public void startRedessine(){
        redessineUi.start();
    }

    public void stopRedessine(){
        redessineUi.Pause();
    }

    public GameUIPanel(GameFrame frame, Ranch r){
        this.ranch = r;
        this.frame = frame;
        this.setPreferredSize(new Dimension(GameFrame.WIDTH-GameFrame.HEIGHT, GameFrame.HEIGHT));
        this.setLayout(null);
        this.setVisible(true);

        JPanel sous_panel = new JPanel();
        sous_panel.setBounds(0, 50, GameFrame.WIDTH-GameFrame.HEIGHT, GameFrame.HEIGHT/2);
        sous_panel.setBackground(Color.LIGHT_GRAY);
        this.add(sous_panel);

        JButton button_sheep = new JButton("BUY SHEEP");
        button_sheep.setBounds(0, 50, 150, 15);
        sous_panel.add(button_sheep);


        JButton button_grass = new JButton("BUY GRASS");
        button_grass.setBounds(0, 50, 150, 15);
        sous_panel.add(button_grass);

        label_date = new JLabel("date : "+ ranch.getDate());
        label_date.setBounds(0, 0, GameFrame.WIDTH-GameFrame.HEIGHT, 15);
        label_date.setForeground(Color.WHITE);
        this.add(label_date);

        label_coin = new JLabel("number of coins : "+ money);
        label_coin.setBounds(0, 10, GameFrame.WIDTH-GameFrame.HEIGHT, 15);
        label_coin.setForeground(Color.WHITE);
        this.add(label_coin);

        label_sheep = new JLabel("number of sheeps : "+ nbsheep);
        label_sheep.setBounds(0, 20, GameFrame.WIDTH-GameFrame.HEIGHT, 15);
        label_sheep.setForeground(Color.WHITE);
        this.add(label_sheep);

        label_grass = new JLabel("number of grasses : "+ nbgrass);
        label_grass.setBounds(0, 30, GameFrame.WIDTH-GameFrame.HEIGHT, 15);
        label_grass.setForeground(Color.WHITE);
        this.add(label_grass);

        button_sheep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ranch.BuySheep();
//                gamePanel.requestFocusInWindow();
                ranch.getRancher().StopAllMoveDirections();
                frame.requestFocus();

                SoundPlayer.playBuySound();
            }
        });

        button_grass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ranch.BuyGrass();
//                gamePanel.requestFocusInWindow();
                ranch.getRancher().StopAllMoveDirections();
                frame.requestFocus();
                SoundPlayer.playBuySound();

            }
        });


        setBackground(Color.BLUE);
    }

    public Ranch getRanch() {
        return ranch;
    }

    public void updateVariables(){
//        if (money < ranch.getMoney()){
//            money =  money+ (ranch.getMoney() - money)/10;
//        }  else if (money > ranch.getMoney()){
//            money = money - (money - ranch.getMoney())/10;
//        }
//        if (nbsheep < ranch.getSheepFlock().size()){
//            nbsheep++;
//        }  else if (nbsheep > ranch.getSheepFlock().size()){
//            nbsheep--;
//        }
//        if (nbgrass < ranch.getGrasses().size()){
//            nbgrass++;
//        }  else if (nbgrass > ranch.getGrasses().size()){
//            nbgrass--;
//        }

        label_date.setText("date : "+ ranch.getDate());
        money = ranch.getMoney();
        nbsheep = ranch.getSheepFlock().size();
        nbgrass = ranch.getGrasses().size();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //clear the panel

        label_coin.setText("number of coins : "+ money);
        label_sheep.setText("number of sheeps : "+ nbsheep);
        label_grass.setText("number of grasses : "+ nbgrass);

    }



}