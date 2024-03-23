package model.GameObjects;

import model.Character;
import model.Position;
import model.Threads.WolfMove;

public class Wolf extends Character {
    public static final int WIDTH = 70;
    public static final int HEIGHT = 70;

    public static final int EAT_SHEEP_RANGE = 10;
    public static final int CLOSEST_DISTANCE_FROM_RANCHER = 200;

    private final Ranch ranch;

    private boolean runningAway = false;

    private WolfMove wolfMove = new WolfMove(this);

    public void startMove(){
        wolfMove.start();
    }

    public void stopMove(){
        wolfMove.Pause();
    }

    public Wolf(Position position, Ranch ranch){
        super(position);
        this.ranch = ranch;
    }

    public Wolf(Position position, int speed, Ranch ranch){
        super(position, speed);
        this.ranch = ranch;
    }

    //Nearest sheep
    public Sheep nearestSheep(){
        Sheep nearestSheep = null;
        double minDistance = Double.MAX_VALUE;
        for (Sheep sheep : ranch.getSheepFlock()) {
            double distance = distance(sheep.getPosition());
            if (distance < minDistance) {
                minDistance = distance;
                nearestSheep = sheep;
            }
        }
        return nearestSheep;
    }

    //Chase sheep
    public void chaseSheep(Sheep sheep){
        if (sheep != null) {
            follow(sheep);
        }
    }

    //eat sheep if near
    public void eatSheep(Sheep sheep){
        System.out.println("Wolf ate a sheep");
        sheep.stopMove();
        ranch.getSheepFlock().remove(sheep);
    }

    //run away from rancher
    public void runAwayFromRancher(){
        stayAway(ranch.getRancher());
        //if out of ranch
        if(isOutOfRanch(WIDTH,HEIGHT, ranch.WIDTH, ranch.HEIGHT)){
            this.stopMove();
            ranch.getWolves().remove(this);
            System.out.println("Wolf removed from ranch");
        }
    }

//    public void runAwayFromRancher(int safeDistance){
//        if(distance(ranch.getRancher())<safeDistance){
//            stayAway(ranch.getRancher());
//        }
//    }

    public void WolfActions(){
        if(runningAway){
            runAwayFromRancher();
        }else
        if(distance(ranch.getRancher())<CLOSEST_DISTANCE_FROM_RANCHER){
            runningAway = true;
        }else if(!ranch.getSheepFlock().isEmpty()){
            Sheep nearestSheep = nearestSheep();
            chaseSheep(nearestSheep);
            if(distance(nearestSheep.getPosition()) < EAT_SHEEP_RANGE){
                eatSheep(nearestSheep);
            }
        }else{
            runAwayFromRancher();
        }
    }

    @Override
    public void move() {
        WolfActions();
        super.move();
    }
}