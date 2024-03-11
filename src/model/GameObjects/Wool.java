package model.GameObjects;

import model.Position;

public class Wool {

    public static final int WIDTH = 70;
    public static final int HEIGHT = 70;
    private final Position position;

    public Wool(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
