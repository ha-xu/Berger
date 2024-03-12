package model.GameObjects;

import model.Character;
import model.Direction;
import model.Position;
import model.Probability;
import model.Threads.SheepMove;

import java.util.Objects;

public class Sheep extends Character {

    public static final int WIDTH = 70;
    public static final int HEIGHT = 70;

    public static final int SHEEP_AWAY_RANGE = 50; //the range of sheep away from each other
    public static final int WOLF_AWAY_RANGE = 120; //the range of sheep away from wolf
    public static final int RANCHER_AWAY_RANGE = 100; //the range of sheep away from rancher
    public static int SheepSpeed = 1;
    public static final int SHEEP_RUN_AWAY_SPEED = 3;

    private final Ranch ranch;

    //define the thread of sheep move
    private final SheepMove sheepMove = new SheepMove(this);

    public void startMove(){
        sheepMove.start();
    }
    public void stopMove(){
        sheepMove.Pause();
    }


    public Sheep(Position position, Ranch ranch){
        super(position);
        this.ranch = ranch;
        setSpeed(SheepSpeed);
    }

    public Sheep(Position position,int speed, Ranch ranch){
        super(position, speed);
        SheepSpeed = speed;
        this.ranch = ranch;
    }

    // Generate a random direction for the sheep to move
    // Sheep will stop moving  with a probability of 0.1
    public void RandomWalk(){
        if(Probability.isTrue(0.03)){
            Direction randomDirection = Probability.randomDirection();
            super.SetMoveDirection(Objects.requireNonNull(randomDirection));
        }

        if (Probability.isTrue(0.1)){
            super.StopAllMoveDirections();
        }
    }

    //Test if ranch has grass
    public boolean DetectGrassInRanch(){
        return !ranch.getGrasses().isEmpty();
    }


    public void GoEatGrass(){
        //get the nearest grass
        Grass nearestGrass = null;
        double minDistance = Double.MAX_VALUE;
        for (Grass grass : ranch.getGrasses()) {
            double distance = distance(grass.getPosition());
            if (distance < minDistance) {
                minDistance = distance;
                nearestGrass = grass;
            }
        }
        if (nearestGrass != null) {
            super.StopAllMoveDirections();
            if (nearestGrass.getPosition().getX() > super.getPosition().getX()) {
                super.SetMoveDirection(Direction.RIGHT);
            } else {
                super.SetMoveDirection(Direction.LEFT);
            }
            if (nearestGrass.getPosition().getY() > super.getPosition().getY()) {
                super.SetMoveDirection(Direction.DOWN);
            } else {
                super.SetMoveDirection(Direction.UP);
            }
            if(distance(nearestGrass.getPosition()) < 50){
                ranch.getGrasses().remove(nearestGrass);
            }
        }
    }

    public void DropWool(){
        if(Probability.isTrue(0.001)){
            ranch.getWools().add(new Wool(new Position(super.getPosition().getX(), super.getPosition().getY())));
        }
    }

    @Override
    public void move() {

        if(NoCharactersAround()){
            setSpeed(SheepSpeed);
            if(DetectGrassInRanch()){
                GoEatGrass();
            }else{
                RandomWalk();
            }
        }else{
            setSpeed(SHEEP_RUN_AWAY_SPEED);
            StayFromOthers();
            RandomWalk();
        }

        if(super.getPosition().getX()<WIDTH/2){
            super.StopMoveDirection(Direction.LEFT);
        }
        if(super.getPosition().getX()>ranch.WIDTH-WIDTH/2){
            super.StopMoveDirection(Direction.RIGHT);
        }
        if(super.getPosition().getY()<HEIGHT/2){
            super.StopMoveDirection(Direction.UP);
        }
        if(super.getPosition().getY()>ranch.HEIGHT-HEIGHT/2){
            super.StopMoveDirection(Direction.DOWN);
        }

        super.move();
    }

    //stay away from another character in ranch
    public void StayFromOthers() {
        for (Sheep sheep : ranch.getSheepFlock()) {
            if (sheep != this && distance(sheep) < SHEEP_AWAY_RANGE){
                stayAway(sheep);
            }
        }
        if(ranch.getRancher() != null && distance(ranch.getRancher()) < RANCHER_AWAY_RANGE)
            stayAway(ranch.getRancher());
        if(ranch.getWolf() != null && distance(ranch.getWolf()) < WOLF_AWAY_RANGE)
            stayAway(ranch.getWolf());
    }

    //Test no characters in ranch around
    public boolean NoCharactersAround(){
        for (Sheep sheep : ranch.getSheepFlock()) {
            if (sheep!=this && distance(sheep) < SHEEP_AWAY_RANGE){
                return false;
            }
        }
        if (ranch.getRancher() != null && distance(ranch.getRancher()) < RANCHER_AWAY_RANGE) return false;
        return ranch.getWolf() == null || !(distance(ranch.getWolf()) < WOLF_AWAY_RANGE);
    }

}
