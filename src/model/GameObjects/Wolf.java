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
    public void chaseSheep(){
        Sheep nearestSheep = nearestSheep();
        if (nearestSheep != null) {

            //run away from rancher if near
            if(distance(ranch.getRancher())<CLOSEST_DISTANCE_FROM_RANCHER){
                runAwayFromRancher();
                return;
            }

            super.StopAllMoveDirections();
            if (nearestSheep.getPosition().getX() > super.getPosition().getX()) {
                super.SetMoveDirection(model.Direction.RIGHT);
            } else {
                super.SetMoveDirection(model.Direction.LEFT);
            }
            if (nearestSheep.getPosition().getY() > super.getPosition().getY()) {
                super.SetMoveDirection(model.Direction.DOWN);
            } else {
                super.SetMoveDirection(model.Direction.UP);
            }

            if(distance(nearestSheep.getPosition()) < EAT_SHEEP_RANGE){
                eatSheep(nearestSheep);
            }
        }else {
            runAwayFromRancher();
        }

    }

    //eat sheep if near
    public void eatSheep(Sheep sheep){
        sheep.stopMove();
        ranch.getSheepFlock().remove(sheep);
    }

    //run away from rancher
    public void runAwayFromRancher(){
        Rancher rancher = ranch.getRancher();
        if (rancher != null) {
                super.StopAllMoveDirections();
                if (rancher.getPosition().getX() > super.getPosition().getX()) {
                    super.SetMoveDirection(model.Direction.LEFT);
                } else {
                    super.SetMoveDirection(model.Direction.RIGHT);
                }
                if (rancher.getPosition().getY() > super.getPosition().getY()) {
                    super.SetMoveDirection(model.Direction.UP);
                } else {
                    super.SetMoveDirection(model.Direction.DOWN);
                }
        }
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub -- To chase sheep
        chaseSheep();
        super.move();
    }
}
