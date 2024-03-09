package model.GameObjects;

import model.Position;

public class Grass {
    private final Position position;

    private boolean isGrown = false;

    public Grass(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
