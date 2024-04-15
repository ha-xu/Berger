package model.GameObjects;

import model.Position;

public class Wool {
    //Largeur et hauteur d'image
    public static final double WIDTH = 60;
    public static final double HEIGHT = 60;

    // Position de la laine
    private final Position position;

    // Constructeur de la laine prenant une position
    public Wool(Position position) {
        this.position = position;
    }

    // Méthode pour obtenir la position de la laine
    public Position getPosition() {
        return position;
    }
}
