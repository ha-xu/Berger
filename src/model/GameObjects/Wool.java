package model.GameObjects;

import model.Position;

public class Wool {

    public static final double WIDTH = 60;
    public static final double HEIGHT = 60;
    private final Position position;

    public Wool(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
