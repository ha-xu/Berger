package model.GameObjects;

import model.Character;
import model.Position;
import model.SoundPlayer;
import model.Threads.RancherMove;

import java.util.Iterator;

public class Rancher extends Character {
    //Largeur et hauteur d'image
    public static final int WIDTH = 80;
    public static final int HEIGHT = 80;
    //Rayon pour entre le rancher et la laine collecter de la laine
    public static final int COLLECT_WOOL_RANGE = 50;


    private final Ranch ranch;
    private final RancherMove rancherMove = new RancherMove(this);
    public void startMove(){
        rancherMove.start();
    }
    public void stopMove(){
        rancherMove.Pause();
    }

    // Constructeur du rancher avec une position et une référence au ranch
    public Rancher(Position position, Ranch ranch){
        super(position);
        this.ranch = ranch;
    }

    // Constructeur du rancher avec une position, une vitesse et une référence au ranch
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

                SoundPlayer.playPickSound();
                break;
            }
        }
    }

    //plant grass
    public void plantGrass(){
        ranch.getGrasses().add(new Grass(new Position(getPosition().getX(), getPosition().getY())));
    }

    // Méthode de déplacement du rancher avec contrainte pour rester dans le ranch
    @Override
    public void move() {
        stayInRanch(WIDTH, HEIGHT, ranch.WIDTH, ranch.HEIGHT);
        super.move();
    }


}
