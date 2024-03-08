package model.GameObjects;

import model.Character;
import model.Direction;
import model.Position;
import model.Probability;

import java.util.Objects;

public class Sheep extends Character {

    public static final int WIDTH = 70;
    public static final int HEIGHT = 70;

    private final Ranch ranch;

    public Sheep(Position position, Ranch ranch){
        super(position);
        this.ranch = ranch;
    }

    public Sheep(Position position,int speed, Ranch ranch){
        super(position, speed);
        this.ranch = ranch;
    }

    // TODO: Implement the auto-select direction method
    public void autoSelectDirection(){
        // TODO Auto-generated method stub
        if(Probability.isTrue(0.03)){
            Direction randomDirection = Probability.randomDirection();
            super.SetMoveDirection(Objects.requireNonNull(randomDirection));
        }

        if (Probability.isTrue(0.1)){
            super.StopAllMoveDirections();
        }

    }

    @Override
    public void move() {
        // TODO Auto-generated method stub
        autoSelectDirection();

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
}
