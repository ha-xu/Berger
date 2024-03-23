package model.GameObjects;

import model.Character;
import model.Direction;
import model.Position;
import model.Probability;
import model.Threads.SheepMove;

import java.util.Iterator;
import java.util.Objects;
import java.util.Random;

import static model.Probability.isTrue;

public class Sheep extends Character {

    public static final int  WIDTH = 70;
    public static final int HEIGHT = 70;

    public static final int GRASS_EATING_DISTANCE = 40;


    private Ranch ranch;

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
//        boolean isMoving = isTrue(0.1);
//        //System.out.println(isMoving);
//        if (isMoving){
//            super.StopAllMoveDirections();
//            Random random = new Random();
//            int direction = random.nextInt(4);
//            switch (direction){
//                case 0:
//                    SetMoveDirection(Direction.UP);
//                    break;
//                case 1:
//                    SetMoveDirection(Direction.DOWN);
//                    break;
//                case 2:
//                    SetMoveDirection(Direction.LEFT);
//                    break;
//                case 3:
//                    SetMoveDirection(Direction.RIGHT);
//                    break;
//            }
//            //System.out.println(direction);
//        }

        if(Probability.isTrue(0.03)){
            Direction randomDirection = Probability.randomDirection();
            super.SetMoveDirection(Objects.requireNonNull(randomDirection));
        }

        if (Probability.isTrue(0.1)){
            super.StopAllMoveDirections();
        }
    }

    //Méthode de déplacement des moutons, avec limite de la zone de déplacement
    @Override
    public void move(){
//        this.EatGrass();
        //vérifier dans sheepFlock si le tableau est vide ou pas pour produire des poils

        this.produirePoil();

        if(!ranch.getGrasses().isEmpty()){
            Grass closestGrass = closestGrass();
            followGrass(closestGrass);
            if (this.distance(closestGrass.getPosition()) < GRASS_EATING_DISTANCE){
                EatGrass(closestGrass);
            }
        }else{
            randomMove();
        }

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
        stayInRanch(WIDTH, HEIGHT, ranch.WIDTH, ranch.HEIGHT);
        super.move();
    }

/**
    public boolean isAlive(){
        return isAlive;
    }

    public void setAlive(boolean isAlive){
        this.isAlive = isAlive;
    }
*/

    /**
     * Production aléatoire des poils par les moutons*/
    public void produirePoil(){
        boolean produitPoil = isTrue(0.001);
        if(produitPoil){
            ranch.getWools().add(new Wool(new Position(this.getPosition().getX(), this.getPosition().getY())));
        }
    }

    //closestGrass width iterator
    public Grass closestGrass(){
        Grass closestGrass = null;
        double minDistance = Double.MAX_VALUE;
        for (Grass grass : ranch.getGrasses()){
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

    //stay away from wolf
//    public void stayAwayFromWolf(){
//        stayAway(ranch.getWolf());
//    }

}
