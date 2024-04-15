package model;

public class Position {
    private int x; // Coordonnée en abscisse
    private int y; // Coordonnée en ordonnée

    //Constructeur de la classe Position
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    //Obtient la coordonnée en abscisse.
    public int getX(){
        return x;
    }

    //Obtient la coordonnée en ordonnée.
    public int getY(){
        return y;
    }

    //Modifie la coordonnée en abscisse.
    public void setX(int x){
        this.x = x;
    }

    //Modifie la coordonnée en ordonnée.
    public void setY(int y){
        this.y = y;
    }

    //Calcule la distance entre la position actuelle et une autre position.
    public double distance(Position position){
        return Math.sqrt(Math.pow(x - position.getX(), 2) + Math.pow(y - position.getY(), 2));
    }
}
