package model.GameObjects;

import model.Position;

public class Fence {
    //Largeur et hauteur d'image
    public final static int WIDTH = 100;
    public final static int HEIGHT = 100;
    private final Position position;
    private TypeFence type;

    private int startDate; // Date de début de la clôture

    // Enumération des types de clôture
    public enum TypeFence {
        HORIZONTALE,
        VERTICALE,
        NONE
    }

    // Constructeur de la clôture
    public Fence(Position position, TypeFence type,int startDate){
        this.position = position;
        this.type = type;
        this.startDate = startDate;
    }

    // Méthode pour obtenir le type de la clôture
    public TypeFence getType() {
        return type;
    }

    // Méthode pour obtenir la date de début de la clôture
    public int getStartDate() {
        return startDate;
    }

    /**public void setType(TypeFence type) {
        this.type = type;
    }*/

    // Méthode pour obtenir la position de la clôture
    public Position getPosition() {
        return position;
    }

}
