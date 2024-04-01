package view;
import model.GameObjects.*;
import model.Position;
import view.Threads.Redessine;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GamePanel extends JPanel{
    private final ImageIcon rancherImageIcon;
    private final ImageIcon wolfImageIcon;
    private final ImageIcon sheepImageIcon;
    private final ImageIcon grassImageIcon;
    private final ImageIcon woolImageIcon;
    private final ImageIcon fenceImageIcon;
    private final ImageIcon fenceVerticaleIcon;

    private final Ranch ranch;

    private Redessine redessine = new Redessine(this);

    //start the thread
    public void startRedessine(){
        redessine.start();
    }

    //stop the thread
    public void stopRedessine(){
        redessine.Pause();
    }

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

        //set image fence (clôture) et sa taille
        fenceImageIcon = new ImageIcon("src/images/fence.png");
        fenceImageIcon.setImage(fenceImageIcon.getImage().getScaledInstance(RanchLengthToPanelLength(Fence.WIDTH), RanchLengthToPanelLength(Fence.HEIGHT), Image.SCALE_DEFAULT));
        // Fence Verticale
        // Créer une nouvelle BufferedImage pour l'image de la clôture
        int width = RanchLengthToPanelLength(Fence.WIDTH);
        int height = RanchLengthToPanelLength(Fence.HEIGHT);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        // Rotation de l'image de la clôture de 90 degrés
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(90), width / 2, height / 2);
        g2d.setTransform(transform);
        g2d.drawImage(fenceImageIcon.getImage(), 0, 0, null);
        g2d.dispose();
        fenceVerticaleIcon = new ImageIcon(bufferedImage);


        sheepImageIcon = new ImageIcon("src/images/sheepImage.png");
        sheepImageIcon.setImage(sheepImageIcon.getImage().getScaledInstance(RanchLengthToPanelLength(Sheep.WIDTH), RanchLengthToPanelLength(Sheep.HEIGHT), Image.SCALE_DEFAULT));

        wolfImageIcon = new ImageIcon("src/images/wolfImage.png");
        wolfImageIcon.setImage(wolfImageIcon.getImage().getScaledInstance(RanchLengthToPanelLength(Rancher.WIDTH), RanchLengthToPanelLength(Rancher.HEIGHT), Image.SCALE_DEFAULT));

        woolImageIcon = new ImageIcon("src/images/woolImage.png");
        woolImageIcon.setImage(woolImageIcon.getImage().getScaledInstance(RanchLengthToPanelLength(Wool.WIDTH), RanchLengthToPanelLength(Wool.HEIGHT), Image.SCALE_DEFAULT));

        grassImageIcon = new ImageIcon("src/images/grassImage.png");
        grassImageIcon.setImage(grassImageIcon.getImage().getScaledInstance(RanchLengthToPanelLength(Grass.WIDTH), RanchLengthToPanelLength(Grass.HEIGHT), Image.SCALE_DEFAULT));


        //set background color
        float [] hsb = new float[3];
        Color.RGBtoHSB(167, 238, 145, hsb);
        this.setBackground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));

    }

    int RanchLengthToPanelLength(double length){
        return (int) (( length/ranch.WIDTH)*GameFrame.HEIGHT);
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
//        Position wolfPanelPosition = RanchPositionToPanelPosition_Centered(ranch.getWolf().getPosition(), wolfImageIcon);

        //make copys
        List<Fence> Fences = new ArrayList<>(ranch.getFences());
        List<Sheep> sheepFlock =  new ArrayList<>(ranch.getSheepFlock());
        List<Wolf> wolves = new ArrayList<>(ranch.getWolves());
        List<Wool> wools = new ArrayList<>(ranch.getWools());
        List<Grass> grasses = new ArrayList<>(ranch.getGrasses());


        //draw rancher
        g.drawImage(rancherImageIcon.getImage(), rancherPanelPosition.getX(), rancherPanelPosition.getY(), this);
        //draw wolf
//        g.drawImage(wolfImageIcon.getImage(), wolfPanelPosition.getX(), wolfPanelPosition.getY(), this);

        //draw wolf
        for (Wolf wolf : wolves) {
            Position wolfPanelPosition = RanchPositionToPanelPosition_Centered(wolf.getPosition(), wolfImageIcon);
            g.drawImage(wolfImageIcon.getImage(), wolfPanelPosition.getX(), wolfPanelPosition.getY(), this);
        }
        //draw sheep
        for (Sheep sheep : sheepFlock) {
            Position sheepPanelPosition = RanchPositionToPanelPosition_Centered(sheep.getPosition(), sheepImageIcon);
            g.drawImage(sheepImageIcon.getImage(), sheepPanelPosition.getX(), sheepPanelPosition.getY(), this);
        }
        //draw wool
//        for (Wool wool : ranch.getWools()) {
//            Position woolPanelPosition = RanchPositionToPanelPosition_Centered(wool.getPosition(), woolImageIcon);
//            g.drawImage(woolImageIcon.getImage(), woolPanelPosition.getX(), woolPanelPosition.getY(), this);
//        }
//
        for (Wool wool : wools) {
            Position woolPanelPosition = RanchPositionToPanelPosition_Centered(wool.getPosition(), woolImageIcon);
            g.drawImage(woolImageIcon.getImage(), woolPanelPosition.getX(), woolPanelPosition.getY(), this);
        }

        //draw fences
        for(Fence fence : Fences){
            Position fencePanelPosition = RanchPositionToPanelPosition_Centered(fence.getPosition(), fenceImageIcon);
            g.drawImage(fenceImageIcon.getImage(), fencePanelPosition.getX(), fencePanelPosition.getY(), this);
        }

        //change to iterator
        for (Grass grass : grasses) {
            Position grassPanelPosition = RanchPositionToPanelPosition_Centered(grass.getPosition(), grassImageIcon);
            g.drawImage(grassImageIcon.getImage(), grassPanelPosition.getX(), grassPanelPosition.getY(), this);
        }
//        //draw grass
//        for (Grass grass : ranch.getGrasses()) {
//            Position grassPanelPosition = RanchPositionToPanelPosition_Centered(grass.getPosition(), grassImageIcon);
//            g.drawImage(grassImageIcon.getImage(), grassPanelPosition.getX(), grassPanelPosition.getY(), this);
//        }

    }
}
