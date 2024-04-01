package model.GameObjects;

import model.Position;

public class Fence {
    public final static int WIDTH = 100;
    public final static int HEIGHT = 100;
    private final Position position;
    private TypeFence type;
    public enum TypeFence {
        HORIZONTALE,
        VERTICALE
    }

    public Fence(Position position, TypeFence type){
        this.position = position;
        this.type = type;
    }

    public TypeFence getType() {
        return type;
    }

    public void setType(TypeFence type) {
        this.type = type;
    }

    public Position getPosition() {
        return position;
    }

}
