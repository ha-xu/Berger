package model;

public abstract class Character {
    private int speed = 1;
    private final Position position;

    private boolean isMovingUp = false;
    private boolean isMovingDown = false;
    private boolean isMovingLeft = false;
    private boolean isMovingRight = false;

    public Character(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
    public void setSpeed(int speed){
        this.speed = speed;
    }



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

    public void StopAllMoveDirections(){
        isMovingUp = false;
        isMovingDown = false;
        isMovingLeft = false;
        isMovingRight = false;
    }

    public void move(){
        if(isMovingUp){
            position.setY(position.getY() - speed);
        }
        if(isMovingDown){
            position.setY(position.getY() + speed);
        }
        if(isMovingLeft){
            position.setX(position.getX() - speed);
        }
        if(isMovingRight){
            position.setX(position.getX() + speed);
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
}
