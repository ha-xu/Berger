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
    public GameUIPanel(Ranch ranch){
        this.ranch = ranch;
        money = ranch.getMoney();
        nbSheep = ranch.getSheepFlock().size();

        //set box layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //add a panel in the top
        JPanel topPanel = new JPanel(new FlowLayout());
        //add label displays money
        moneyLabel = new JLabel("Money:34 " + money);
        //add label displays nomber of sheep
        sheepLabel = new JLabel("Sheep: " + nbSheep);

        topPanel.add(moneyLabel);
        topPanel.add(sheepLabel);
        this.add(topPanel);

        this.setPreferredSize(new Dimension(GameFrame.WIDTH-GameFrame.HEIGHT, GameFrame.HEIGHT));
        this.setVisible(true);
        System.out.println("GameUIPanel");

    }

    @Override
    public void repaint() {
        super.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //update money label
        money = ranch.getMoney();
        //update sheep label
        nbSheep = ranch.getSheepFlock().size();

        //update money label
        moneyLabel.setText("Money: " + money);
        //update sheep label
        sheepLabel.setText("Sheep: " + nbSheep);

        System.out.println("money");
    }


}
