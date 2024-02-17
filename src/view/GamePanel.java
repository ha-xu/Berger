package view;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{
    private final Image rancherImage;
    private Image wolfImage;
    private Image sheepImage;


    public GamePanel(){
        //log
        System.out.println("GamePanel");
        //set size
        this.setSize(GameFrame.WIDTH, GameFrame.HEIGHT);
        this.setPreferredSize(new Dimension(GameFrame.WIDTH, GameFrame.HEIGHT));
        this.setVisible(true);

        //set image
        ImageIcon icon = new ImageIcon("src/images/farmerImage.png");
        rancherImage = icon.getImage();


        //set background color
        float [] hsb = new float[3];
        Color.RGBtoHSB(167, 238, 145, hsb);
        this.setBackground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(rancherImage, 12, 12, this);
        //log
        System.out.println("paintComponent");
    }
}
