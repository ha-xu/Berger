package model;

import model.GameObjects.Fence;

public abstract class Character {

    // distance de mouvement à chaque pas
    private int speed = 2;
    private final Position position;

    // Indique si le personnage se déplace vers le haut, le bas, la gauche ou la droite
    private boolean isMovingUp = false;
    private boolean isMovingDown = false;
    private boolean isMovingLeft = false;
    private boolean isMovingRight = false;

    // Constructeur avec position
    public Character(Position position) {
        this.position = position;
    }

    // Constructeur avec position et vitesse
    public Character(Position position, int speed) {
        this.position = position;
        this.speed = speed;
    }
    public Position getPosition() {
        return position;
    }
    public void setSpeed(int speed){
        this.speed = speed;
    }


    // Méthode pour définir la direction de déplacement du personnage
    public void SetMoveDirection(Direction direction){
        switch (direction){
            case UP:
                isMovingUp = true;
                break;
            case DOWN:
                isMovingDown = true;
                break;
            case LEFT:
                isMovingLeft = true;
                break;
            case RIGHT:
                isMovingRight = true;
                break;
        }
    }

    // Méthode pour arrêter le déplacement dans une direction spécifique
    public void StopMoveDirection(Direction direction){
        switch (direction){
            case UP:
                isMovingUp = false;
                break;
            case DOWN:
                isMovingDown = false;
                break;
            case LEFT:
                isMovingLeft = false;
                break;
            case RIGHT:
                isMovingRight = false;
                break;
        }
    }

    // Méthode pour arrêter le déplacement dans toutes les directions
    public void StopAllMoveDirections(){
        isMovingUp = false;
        isMovingDown = false;
        isMovingLeft = false;
        isMovingRight = false;
    }

    // Méthode pour gérer le déplacement du personnage
    // (plusieurs acteurs, par exemple Sheep hérite Character et l'utilise pour déplacer)
    public void move(){
        if(isMovingUp){
            position.setY((int)(position.getY() - speed));
        }
        if(isMovingDown){
            position.setY((int)(position.getY() + speed));
        }
        if(isMovingLeft){
            position.setX((int)(position.getX() - speed));
        }
        if(isMovingRight){
            position.setX((int)(position.getX() + speed));
        }
    }


    //calculate the distance between two characters
    public double distance(Character character){
        return Math.sqrt(Math.pow(position.getX() - character.getPosition().getX(), 2) + Math.pow(position.getY() - character.getPosition().getY(), 2));
    }

    //calculate the distance between two positions
    public double distance(Position position){
        return Math.sqrt(Math.pow(this.position.getX() - position.getX(), 2) + Math.pow(this.position.getY() - position.getY(), 2));
    }

    //stay away from another character
    public void stayAway(Character character){
        StopAllMoveDirections();
        if(position.getX() < character.getPosition().getX()){
            SetMoveDirection(Direction.LEFT);
        }else if(position.getX() > character.getPosition().getX()){
            SetMoveDirection(Direction.RIGHT);
        }
        if(position.getY() < character.getPosition().getY()){
            SetMoveDirection(Direction.UP);
        }else if(position.getY() > character.getPosition().getY()){
            SetMoveDirection(Direction.DOWN);
        }
    }

    // Méthode pour rendre une clôture intouchable par le personnage
    public void FenceUntouchable(Fence fence){
        int disx = 0;
        int disy = 0;
        // Déterminer la distance minimale en fonction du type de clôture
        if(fence.getType() == Fence.TypeFence.HORIZONTALE ) {
            disx = 100;
            disy = 80;
        }else{
            disx = 80;
            disy = 100;
        }
        // Arrêter le déplacement si la distance entre le personnage et la clôture est inférieure à la distance minimale
        if(position.getX() < fence.getPosition().getX() && distance(fence.getPosition()) < disx){
            StopMoveDirection(Direction.LEFT);
            System.out.println(disx);
            System.out.println(fence.getType());
        }
        if(position.getX() > fence.getPosition().getX() && distance(fence.getPosition()) < disx){
            StopMoveDirection(Direction.RIGHT);
            System.out.println(disx);
        }
        if(position.getY() < fence.getPosition().getY() && distance(fence.getPosition()) < disy){
            StopMoveDirection(Direction.DOWN);
            System.out.println(disy);
        }
        if(position.getY() > fence.getPosition().getY() && distance(fence.getPosition()) < disy){
            StopMoveDirection(Direction.UP);
            System.out.println(disy);
        }
    }

    // Méthode pour suivre un autre personnage
    public void follow(Character character){
        StopAllMoveDirections();
        if(position.getX() < character.getPosition().getX()){
            SetMoveDirection(Direction.RIGHT);
        }else{
            SetMoveDirection(Direction.LEFT);
        }
        if(position.getY() < character.getPosition().getY()){
            SetMoveDirection(Direction.DOWN);
        }else{
            SetMoveDirection(Direction.UP);
        }
    }

    // Méthode pour suivre une position donnée
    public void follow(Position position){
        StopAllMoveDirections();
        if(this.position.getX() < position.getX()){
            SetMoveDirection(Direction.RIGHT);
        }else{
            SetMoveDirection(Direction.LEFT);
        }
        if(this.position.getY() < position.getY()){
            SetMoveDirection(Direction.DOWN);
        }else{
            SetMoveDirection(Direction.UP);
        }
    }

    //stay in the ranch
    public void stayInRanch(int characterWidth,int characterHeight, int ranchWidth, int ranchHeight){
        if(position.getX() < characterWidth/2){
            StopMoveDirection(Direction.LEFT);
        }
        if(position.getX() > ranchHeight - characterWidth/2){
            StopMoveDirection(Direction.RIGHT);
        }
        if(position.getY() < characterHeight/2){
            StopMoveDirection(Direction.UP);
        }
        if(position.getY() > ranchHeight - characterHeight/2){
            StopMoveDirection(Direction.DOWN);
        }
    }

    //boolean : is out of ranch
    public boolean isOutOfRanch(int characterWidth,int characterHeight, int ranchWidth, int ranchHeight){
        return position.getX() < -characterWidth/2 || position.getX() > ranchHeight + characterWidth/2 || position.getY() < -characterHeight/2 || position.getY() > ranchHeight + characterHeight/2;
    }
}
