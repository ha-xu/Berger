package model.GameObjects;

import model.Position;

public class Wool {

    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    private final Position position;

    public Wool(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
