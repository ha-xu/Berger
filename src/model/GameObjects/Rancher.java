package model.GameObjects;

import model.Character;
import model.Position;
import model.Threads.RancherMove;

import java.util.Iterator;

public class Rancher extends Character {
    public static final int WIDTH = 80;
    public static final int HEIGHT = 80;
    public static final int COLLECT_WOOL_RANGE = 50;


    private final Ranch ranch;
    private final RancherMove rancherMove = new RancherMove(this);
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
        Iterator<Wool> woolIterator = ranch.getWools().iterator();
        while (woolIterator.hasNext()) {
            Wool wool = woolIterator.next();
            if (distance(wool.getPosition()) < COLLECT_WOOL_RANGE) {
                woolIterator.remove();
                ranch.addMoney(Ranch.WOOL_MONEY);
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
        stayInRanch(WIDTH, HEIGHT, ranch.WIDTH, ranch.HEIGHT);
        super.move();
    }


}
