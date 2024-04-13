package model.GameObjects;

import model.*;
import model.Character;
import model.Threads.SheepMove;

import java.util.ArrayList;
import java.util.Objects;

import static model.Probability.isTrue;

public class Sheep extends Character {
    private int NbEatenGrass = 0;
    private double ProbaProduitPoil = 0.001;
    private int PoilsProduit = 0;
    public static final int WIDTH = 70;
    public static final int HEIGHT = 70;

    public static final int GRASS_EATING_DISTANCE = 40;
    public static final int CLOSEST_DISTANCE_FROM_WOLF = 200;
    public static final int CLOSEST_DISTANCE_FROM_SHEEP = 30;

    private final Ranch ranch;

    private final SheepMove sheepMove = new SheepMove(this);

    //startMove
    public void startMove(){
        sheepMove.start();
    }

    //stopMove
    public void stopMove(){
        sheepMove.Pause();
    }

    //Constructeur
    public Sheep(Position position, Ranch ranch){
        super(position);
        //this.isAlive = true;
        this.ranch = ranch;
    }

    //Constructeur
    public Sheep(Position position, int speed, Ranch ranch){
        super(position, speed);
        //this.isAlive = true;
        this.ranch = ranch;
    }

    //Méthode de déplacement aléatoire des moutons
    public void randomMove(){
        if(Probability.isTrue(0.03)){
            Direction randomDirection = Probability.randomDirection();
            super.SetMoveDirection(Objects.requireNonNull(randomDirection));
        }

        if (Probability.isTrue(0.1)){
            super.StopAllMoveDirections();
        }
    }

    //une function pour definir tous les activités des moutons
    public void sheepActions(){
        produirePoil();
        ProbaWool();
        if(stayAwayFromOthers()){
            if(!ranch.getGrasses().isEmpty()){
                Grass closestGrass = closestGrass();
                followGrass(closestGrass);
                if (closestGrass != null){
                    if (this.distance(closestGrass.getPosition()) < GRASS_EATING_DISTANCE){
                        EatGrass(closestGrass);
                    }
                    NbEatenGrass++;
                }

            }else{
                randomMove();
            }
        }
    }

    //Lors d'une rencontre d'un des loups, le mouton fuira. On évite que les moutons se superposent.
    public boolean stayAwayFromOthers(){
        boolean isAway = true;
        ArrayList<Wolf> wolves = new ArrayList<>(ranch.getWolves());
        ArrayList<Sheep> sheepFlock = new ArrayList<>(ranch.getSheepFlock());
        for (Wolf wolf : wolves){
            if(wolf != null){
                if (this.distance(wolf.getPosition()) < CLOSEST_DISTANCE_FROM_WOLF){
                    stayAway(wolf);
                    isAway = false;
                }
            }

        }
        for (Sheep sheep : sheepFlock){
            if (sheep != this){
                if (this.distance(sheep.getPosition()) < CLOSEST_DISTANCE_FROM_SHEEP){
                    stayAway(sheep);
                    isAway = false;
                }
            }
        }
        if(this.distance(ranch.getRancher().getPosition()) < 80){
            stayAwayFromRancher();
            isAway = false;
        }

        return isAway;
    }

    //si le mouton a mangé 2 herbes et produit moins de 5 poils, il produira plus souvent des poils, sinon il produira moins souvent.
    public void ProbaWool(){
        if(NbEatenGrass == 2 && PoilsProduit < 5){
            //System.out.println("NbEatenGrass: " + NbEatenGrass);
            //System.out.println("PoilsProduit: " + PoilsProduit);
            ProbaProduitPoil = 0.005;
        }else if(NbEatenGrass > 2 && PoilsProduit >= 5){
            ProbaProduitPoil = 0.001;
            NbEatenGrass = 0;
            PoilsProduit = 0;
        }
    }

    //Méthode de déplacement des moutons, avec limite de la zone de déplacement
    @Override
    public void move(){
//        this.EatGrass();
        //vérifier dans sheepFlock si le tableau est vide ou pas pour produire des poils

        sheepActions();
//        playSoundRandomly(0.001);

//        if (this.getPosition().getX() > ranch.WIDTH){
//            super.StopMoveDirection(Direction.RIGHT);
//        }
//        if(this.getPosition().getX() < 0 ){
//            super.StopMoveDirection(Direction.LEFT);
//        }
//        if(this.getPosition().getY() > ranch.HEIGHT){
//            super.StopMoveDirection(Direction.DOWN);
//        }
//        if(this.getPosition().getY() < 0){
//            super.StopMoveDirection(Direction.UP);
//        }
//        stayAwayFromOthers();
        stayInRanch(WIDTH, HEIGHT, ranch.WIDTH, ranch.HEIGHT);
        super.move();
    }

    public void produirePoil(){
        boolean produitPoil = isTrue(ProbaProduitPoil);
        if(produitPoil){
            ranch.getWools().add(new Wool(new Position(this.getPosition().getX(), this.getPosition().getY())));

            SoundPlayer.playSheepSound();

            PoilsProduit++;
        }
    }

    //closestGrass width iterator
    public Grass closestGrass(){
        Grass closestGrass = null;
        double minDistance = Double.MAX_VALUE;
        ArrayList<Grass> grasses = new ArrayList<>(ranch.getGrasses());
        for (Grass grass : grasses){
            double distance = this.distance(grass.getPosition());
            if (distance < minDistance){
                minDistance = distance;
                closestGrass = grass;
            }
        }
        return closestGrass;
    }


    public void followGrass(Grass grass) {
        if(grass != null) {
            follow(grass.getPosition());
        }
    }
    // Méthode de consommation de l'herbe par les moutons en cas de collision entre le sheep et le grass
    public void EatGrass(Grass grass){
        if(grass != null) {
            SoundPlayer.playSheepEatSound();
            ranch.getGrasses().remove(grass);
        }
    }

    //stay away from sheep
//    public void stayAwayFromSheep(){
//        for (Sheep sheep : ranch.getSheepFlock()) {
//            if (sheep != this){
//                stayAway(sheep);
//            }
//        }
//    }

    //stay away from rancher
    public void stayAwayFromRancher(){
        stayAway(ranch.getRancher());
    }

    public void playSoundRandomly(double probability)
    {
        if(Probability.isTrue(probability))
        {
            SoundPlayer.playSheepSound();
        }
    }

    //stay away from wolf
//    public void stayAwayFromWolf(){
//        stayAway(ranch.getWolf());
//    }

}
