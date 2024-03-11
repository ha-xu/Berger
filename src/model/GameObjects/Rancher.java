package model.GameObjects;

import model.Character;
import model.Direction;
import model.Position;
import model.Threads.RancherMove;

public class Rancher extends Character {
    public static final int WIDTH = 70;
    public static final int HEIGHT = 70;
    public final int collectWoolRange = 80;


    private final Ranch ranch;

    private RancherMove rancherMove = new RancherMove(this);

    public void startMove(){
        rancherMove.start();
    }

    public void stopMove(){
        rancherMove.Pause();
    }
    public Rancher(Position position, Ranch ranch){
        super(position);
        this.ranch = ranch;
    }

    public Rancher(Position position, int speed, Ranch ranch){
        super(position, speed);
        this.ranch = ranch;
    }

    //collect wool
    public void collectWool(){
        for (Wool wool : ranch.getWools()) {
            if (distance(wool.getPosition()) < collectWoolRange) {
                ranch.getWools().remove(wool);
                ranch.addMoney(10);
                break;
            }
        }
    }

    //plant grass
    public void plantGrass(){
        ranch.getGrasses().add(new Grass(new Position(getPosition().getX(), getPosition().getY())));
    }

    @Override
    public void move() {
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
