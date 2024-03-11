package model.GameObjects;

import model.Character;
import model.Direction;
import model.Position;

public class Rancher extends Character {
    public static final int WIDTH = 40;
    public static final int HEIGHT = 40;


    private final Ranch ranch;
    public Rancher(Position position, Ranch ranch){
        super(position);
        this.ranch = ranch;
    }


}