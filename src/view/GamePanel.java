package view;
import model.GameObjects.Ranch;
import model.GameObjects.Rancher;
import model.GameObjects.Sheep;
import model.GameObjects.Wool;
import model.Position;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{
    private final ImageIcon rancherImageIcon;
    private final ImageIcon wolfImageIcon;
    private final ImageIcon sheepImageIcon;

    private final ImageIcon woolImageIcon;

    private final Ranch ranch;

    public GamePanel(Ranch ranch){
        this.ranch = ranch;

        //log
        System.out.println("GamePanel");
        //set size
//        this.setSize(GameFrame.HEIGHT, GameFrame.HEIGHT);
        this.setPreferredSize(new Dimension(GameFrame.HEIGHT, GameFrame.HEIGHT));
        this.setVisible(true);

        //set image
        rancherImageIcon = new ImageIcon("src/images/farmerImage.png");
        //set icon size
        rancherImageIcon.setImage(rancherImageIcon.getImage().getScaledInstance(RanchLengthToPanelLength(Rancher.WIDTH), RanchLengthToPanelLength(Rancher.HEIGHT), Image.SCALE_DEFAULT));


        sheepImageIcon = new ImageIcon("src/images/mouton.png");
        //TODO: set sheep icon size

        wolfImageIcon = new ImageIcon("src/images/wolfImage.png");
        //TODO: set wolf icon size

        woolImageIcon = new ImageIcon("src/images/woolImage.png");
        //TODO: set wool icon size

        //set background color
        float [] hsb = new float[3];
        Color.RGBtoHSB(167, 238, 145, hsb);
        this.setBackground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));

    }

    int RanchLengthToPanelLength(int length){
        return (int) (((double) length/(double)ranch.WIDTH)*(double)GameFrame.HEIGHT);
    }

    Position RanchPositionToPanelPosition(Position position){
        return new Position((int) (((double) position.getX()/(double)ranch.WIDTH)*(double)GameFrame.HEIGHT), (int) ( ((double) position.getY()/(double)ranch.HEIGHT)*(double)GameFrame.HEIGHT));
    }

    Position RanchPositionToPanelPosition_Centered(Position position,ImageIcon icon){
        return new Position((int) (((double) position.getX()/(double)ranch.WIDTH)*(double)GameFrame.HEIGHT) - icon.getIconWidth()/2, (int) ( ((double) position.getY()/(double)ranch.HEIGHT)*(double)GameFrame.HEIGHT) - icon.getIconHeight()/2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Position rancherPanelPosition = RanchPositionToPanelPosition_Centered(ranch.getRancher().getPosition(), rancherImageIcon);
        Position wolfPanelPosition = RanchPositionToPanelPosition_Centered(ranch.getWolf().getPosition(), wolfImageIcon);

        //draw rancher
        //g.drawImage(rancherImageIcon.getImage(), rancherPanelPosition.getX(), rancherPanelPosition.getY(), this);
        //draw wolf
        //g.drawImage(wolfImageIcon.getImage(), wolfPanelPosition.getX(), wolfPanelPosition.getY(), this);
        //draw sheep
        for (Sheep sheep : ranch.getSheepFlock()) {
            Position sheepPanelPosition = RanchPositionToPanelPosition_Centered(sheep.getPosition(), sheepImageIcon);
            g.drawImage(sheepImageIcon.getImage(), sheepPanelPosition.getX(), sheepPanelPosition.getY(), this);
        }
        //draw wool
        for (Wool wool : ranch.getWools()) {
            Position woolPanelPosition = RanchPositionToPanelPosition_Centered(wool.getPosition(), woolImageIcon);
            g.drawImage(woolImageIcon.getImage(), woolPanelPosition.getX(), woolPanelPosition.getY(), this);
        }

    }
}
