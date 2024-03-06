package model.GameObjects;

import model.Character;
import model.Position;

public class Rancher extends Character {

    public final int collectWoolRange = 10;
    private final Ranch ranch;
    public Rancher(Position position, Ranch ranch){
        super(position);
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

}
