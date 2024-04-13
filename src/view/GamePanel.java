package view;
import model.GameObjects.*;
import model.Position;
import model.SoundPlayer;
import view.Threads.Redessine;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GamePanel extends JPanel{
    private final ImageIcon rancherImageIcon;
    private final ImageIcon wolfImageIcon;
    private final ImageIcon sheepImageIcon;
    private final ImageIcon grassImageIcon;
    private final ImageIcon woolImageIcon;

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


        sheepImageIcon = new ImageIcon("src/images/sheepImage.png");
        //TODO: set sheep icon size
        sheepImageIcon.setImage(sheepImageIcon.getImage().getScaledInstance(RanchLengthToPanelLength(Sheep.WIDTH), RanchLengthToPanelLength(Sheep.HEIGHT), Image.SCALE_DEFAULT));

        wolfImageIcon = new ImageIcon("src/images/wolfImage.png");
        //TODO: wolf sheep icon size
        wolfImageIcon.setImage(wolfImageIcon.getImage().getScaledInstance(RanchLengthToPanelLength(Rancher.WIDTH), RanchLengthToPanelLength(Rancher.HEIGHT), Image.SCALE_DEFAULT));

        woolImageIcon = new ImageIcon("src/images/woolImage.png");
        //TODO: set wool icon size
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
            if(wolf != null){
                Position wolfPanelPosition = RanchPositionToPanelPosition_Centered(wolf.getPosition(), wolfImageIcon);
                g.drawImage(wolfImageIcon.getImage(), wolfPanelPosition.getX(), wolfPanelPosition.getY(), this);
            }

        }
        //draw sheep
        for (Sheep sheep : sheepFlock) {
            if (sheep != null){
                Position sheepPanelPosition = RanchPositionToPanelPosition_Centered(sheep.getPosition(), sheepImageIcon);
                g.drawImage(sheepImageIcon.getImage(), sheepPanelPosition.getX(), sheepPanelPosition.getY(), this);
            }

        }
        //draw wool
//        for (Wool wool : ranch.getWools()) {
//            Position woolPanelPosition = RanchPositionToPanelPosition_Centered(wool.getPosition(), woolImageIcon);
//            g.drawImage(woolImageIcon.getImage(), woolPanelPosition.getX(), woolPanelPosition.getY(), this);
//        }
//
        for (Wool wool : wools) {
            if(wool != null){
                Position woolPanelPosition = RanchPositionToPanelPosition_Centered(wool.getPosition(), woolImageIcon);
                g.drawImage(woolImageIcon.getImage(), woolPanelPosition.getX(), woolPanelPosition.getY(), this);
            }

        }

        //change to iterator
        for (Grass grass : grasses) {
            if(grass != null){
                Position grassPanelPosition = RanchPositionToPanelPosition_Centered(grass.getPosition(), grassImageIcon);
                g.drawImage(grassImageIcon.getImage(), grassPanelPosition.getX(), grassPanelPosition.getY(), this);
            }
        }
//        //draw grass
//        for (Grass grass : ranch.getGrasses()) {
//            Position grassPanelPosition = RanchPositionToPanelPosition_Centered(grass.getPosition(), grassImageIcon);
//            g.drawImage(grassImageIcon.getImage(), grassPanelPosition.getX(), grassPanelPosition.getY(), this);
//        }

    }
}
