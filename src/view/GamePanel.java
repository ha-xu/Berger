package view;
import model.GameObjects.Ranch;
import model.GameObjects.Sheep;
import model.GameObjects.Wool;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{
    private final Image rancherImage;
    private final Image wolfImage;
    private final Image sheepImage;

    private final Image woolImage;

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
        ImageIcon rancherIcon = new ImageIcon("src/images/farmerImage.png");
        rancherImage = rancherIcon.getImage();

        ImageIcon sheepIcon = new ImageIcon("src/images/sheepImage.png");
        sheepImage = sheepIcon.getImage();

        ImageIcon wolfIcon = new ImageIcon("src/images/wolfImage.png");
        wolfImage = wolfIcon.getImage();

        ImageIcon woolIcon = new ImageIcon("src/images/woolImage.png");
        woolImage = woolIcon.getImage();

        //set background color
        float [] hsb = new float[3];
        Color.RGBtoHSB(167, 238, 145, hsb);
        this.setBackground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(rancherImage, ranch.getRancher().getPosition().getX(), ranch.getRancher().getPosition().getY(), this);
        g.drawImage(wolfImage, ranch.getWolf().getPosition().getX(), ranch.getWolf().getPosition().getY(), this);
        for (Sheep sheep : ranch.getSheepFlock()) {
            g.drawImage(sheepImage, sheep.getPosition().getX(), sheep.getPosition().getY(), this);
        }

        for (Wool wool : ranch.getWools()) {
            g.drawImage(woolImage, wool.getPosition().getX(), wool.getPosition().getY(), this);
        }

    }
}
