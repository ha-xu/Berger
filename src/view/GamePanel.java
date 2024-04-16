package view;
import model.GameObjects.*;
import model.Position;
import model.SoundPlayer;
import view.Threads.Redessine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Panneau de jeu qui affiche les éléments du ranch.
 */
public class GamePanel extends JPanel{
    private final ImageIcon rancherImageIcon;
    private final ImageIcon wolfImageIcon;
    private final ImageIcon sheepImageIcon;
    private final ImageIcon grassImageIcon;
    private final ImageIcon woolImageIcon;

    private final ImageIcon fenceVIcon;
    private final ImageIcon fenceHIcon;


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
        sheepImageIcon.setImage(sheepImageIcon.getImage().getScaledInstance(RanchLengthToPanelLength(Sheep.WIDTH), RanchLengthToPanelLength(Sheep.HEIGHT), Image.SCALE_DEFAULT));

        wolfImageIcon = new ImageIcon("src/images/wolfImage.png");
        wolfImageIcon.setImage(wolfImageIcon.getImage().getScaledInstance(RanchLengthToPanelLength(Rancher.WIDTH), RanchLengthToPanelLength(Rancher.HEIGHT), Image.SCALE_DEFAULT));

        woolImageIcon = new ImageIcon("src/images/woolImage.png");
        woolImageIcon.setImage(woolImageIcon.getImage().getScaledInstance(RanchLengthToPanelLength(Wool.WIDTH), RanchLengthToPanelLength(Wool.HEIGHT), Image.SCALE_DEFAULT));

        grassImageIcon = new ImageIcon("src/images/grassImage.png");
        grassImageIcon.setImage(grassImageIcon.getImage().getScaledInstance(RanchLengthToPanelLength(Grass.WIDTH), RanchLengthToPanelLength(Grass.HEIGHT), Image.SCALE_DEFAULT));

        //set image fence (clôture) et sa taille
        fenceHIcon = new ImageIcon("src/images/fenceH.png");
        fenceHIcon.setImage(fenceHIcon.getImage().getScaledInstance(RanchLengthToPanelLength(Fence.WIDTH), RanchLengthToPanelLength(Fence.HEIGHT), Image.SCALE_DEFAULT));

        fenceVIcon = new ImageIcon("src/images/fenceV.png");
        fenceVIcon.setImage(fenceVIcon.getImage().getScaledInstance(RanchLengthToPanelLength(Fence.WIDTH), RanchLengthToPanelLength(Fence.HEIGHT), Image.SCALE_DEFAULT));

        //set background color
        float [] hsb = new float[3];
        Color.RGBtoHSB(167, 238, 145, hsb);
        this.setBackground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));


        //click mouse right event
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {
                    System.out.println("right click");
                    GameUIPanel.currentTypeFence = -1;
                }

                if(evt.getButton() == java.awt.event.MouseEvent.BUTTON1){
                    System.out.println("left click");
                    if(GameUIPanel.currentTypeFence != -1){
                        Position fencePosition = PanelPositionToRanchPosition(new Position(evt.getX(), evt.getY()));
                        ranch.BuyFence(fencePosition, GameUIPanel.currentTypeFence == 0 ? Fence.TypeFence.HORIZONTALE : Fence.TypeFence.VERTICALE);

                    }
                }
            }
        });
    }

    // Conversion des dimensions du ranch aux dimensions du panneau
    int RanchLengthToPanelLength(double length){
        return (int) (( length/ranch.WIDTH)*GameFrame.HEIGHT);
    }

    // Conversion de position du ranch à position du panneau
    Position RanchPositionToPanelPosition(Position position){
        return new Position((int) (((double) position.getX()/(double)ranch.WIDTH)*(double)GameFrame.HEIGHT), (int) ( ((double) position.getY()/(double)ranch.HEIGHT)*(double)GameFrame.HEIGHT));
    }

    // Conversion de position du panneau à position du ranch
    Position PanelPositionToRanchPosition(Position position){
        return new Position((int) (((double) position.getX()/(double)GameFrame.HEIGHT)*(double)ranch.WIDTH), (int) ( ((double) position.getY()/(double)GameFrame.HEIGHT)*(double)ranch.HEIGHT));
    }

    // Positionnement centré sur le panneau en fonction des dimensions de l'icône
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

        //draw fence
        for (Fence fence : ranch.getFences()) {
            if(fence != null){
                Position fencePanelPosition = RanchPositionToPanelPosition_Centered(fence.getPosition(), fence.getType() == Fence.TypeFence.HORIZONTALE ? fenceHIcon : fenceVIcon);
                g.drawImage(fence.getType() == Fence.TypeFence.HORIZONTALE ? fenceHIcon.getImage() : fenceVIcon.getImage(), fencePanelPosition.getX(), fencePanelPosition.getY(), this);
            }

        }


        /** Dessiner la zone sélectionnée par le curseur*/
        if(GameUIPanel.currentTypeFence != -1){
            // Récupérer la position de la souris dans le panneau
            Point mousePoint = getMousePosition();
            if (mousePoint != null) {
                if (GameUIPanel.currentTypeFence == 0) {
                    // Convertir la position de la souris en position de ranch
                    // Position ranchPosition = panelPositionToRanchPosition(new Position(mousePoint.x, mousePoint.y));
                    // Dessiner une indication visuelle autour de la position de la souris
                    // Partie horizontale
                    int fenceWidth = Fence.WIDTH/2; // Largeur de la clôture
                    int fenceHeight = Fence.HEIGHT/4; // Hauteur de la clôture
                    //int indicatorSize = Math.min(fenceWidth, fenceHeight); // Taille de l'indicateur (utilisez la plus petite dimension)
                    g.setColor(new Color(0, 255, 255, 100));
                    g.fillRect(mousePoint.x - fenceWidth / 2, mousePoint.y - fenceHeight / 2, fenceWidth, fenceHeight);
                } else if (GameUIPanel.currentTypeFence == 1) {
                    //Partie Verticale
                    // Position ranchPosition = panelPositionToRanchPosition(new Position(mousePoint.x, mousePoint.y));
                    int fenceWidth = Fence.HEIGHT/4;
                    int fenceHeight = Fence.WIDTH/2;
                    g.setColor(new Color(0, 255, 255, 100));
                    g.fillRect(mousePoint.x - fenceWidth / 2, mousePoint.y - fenceHeight / 2, fenceWidth, fenceHeight);
                }
            }

        }

    }
}
