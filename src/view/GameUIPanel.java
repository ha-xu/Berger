package view;

import model.GameObjects.Ranch;

import javax.swing.*;
import java.awt.*;

public class GameUIPanel extends JPanel {

    int money;
    int nbSheep;

    private final Ranch ranch;
    private final JLabel moneyLabel;
    private final JLabel sheepLabel;

    private final UIAnimation uiAnimation = new UIAnimation(this);
    public GameUIPanel(Ranch ranch){
        this.ranch = ranch;
        money = ranch.getMoney();
        nbSheep = ranch.getSheepFlock().size();

        //set box layout
        this.setLayout(new GridLayout(0,2));
        //add a panel in the top
        //JPanel topPanel = new JPanel(new FlowLayout());
        //add label displays money
        moneyLabel = new JLabel("Money: " + money);
        //add label displays nomber of sheep
        sheepLabel = new JLabel("Sheep: " + nbSheep);

        //buysheep button
        JButton buySheepButton = new JButton("Buy Sheep");
        buySheepButton.addActionListener(e -> {
            if(ranch.getMoney() >= 30){
                ranch.addRandomSheep();
                ranch.setMoney(ranch.getMoney()-30);
            }
        });



        this.add(moneyLabel);
        this.add(sheepLabel);
        this.add(buySheepButton);
//        this.add(topPanel);

        this.setPreferredSize(new Dimension(GameFrame.WIDTH-GameFrame.HEIGHT, GameFrame.HEIGHT));
        this.setVisible(true);
        System.out.println("GameUIPanel");


        uiAnimation.start();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        //update money label
        moneyLabel.setText("Money: " + money);
        //update sheep label
        sheepLabel.setText("Sheep: " + nbSheep);

    }


    //add up vars
    public void updateVariables(){
        if(money < ranch.getMoney()){
            money++;
        }else if(money > ranch.getMoney()){
            money--;
        }

        if(nbSheep < ranch.getSheepFlock().size()){
            nbSheep++;
        }else if(nbSheep > ranch.getSheepFlock().size()){
            nbSheep--;
        }


    }

}
