package model.GameObjects;

import model.Position;

public class Grass {
    //Largeur et hauteur d'image
    public static final int WIDTH = 60;
    public static final int HEIGHT = 60;
    private final Position position;

    //private boolean isGrown = false;

    // Constructeur de l'herbe avec position spécifiée
    public Grass(Position position) {
        this.position = position;
    }

    // Méthode pour obtenir la position de l'herbe
    public Position getPosition() {
        return position;
    }
}
