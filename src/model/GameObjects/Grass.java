package model.GameObjects;

import model.Position;

public class Grass {

    public static final int WIDTH = 70;
    public static final int HEIGHT = 70;
    private final Position position;

    private boolean isGrown = false;

    public Grass(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

}
