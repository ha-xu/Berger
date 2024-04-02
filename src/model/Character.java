package model;

import model.GameObjects.Fence;

import java.util.ArrayList;

public abstract class Character {


    private int speed = 2;
    private final Position position;

    private boolean isMovingUp = false;
    private boolean isMovingDown = false;
    private boolean isMovingLeft = false;
    private boolean isMovingRight = false;

    public Character(Position position) {
        this.position = position;
    }
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

    //Clôture untouchable par les autres character.
    public void FenceUntouchable(Fence fence){
        int disx = 0;
        int disy = 0;
        if(fence.getType() == Fence.TypeFence.HORIZONTALE ) {
            disx = 100;
            disy = 80;
        }else{
            disx = 80;
            disy = 100;
        }
        if(position.getX() < fence.getPosition().getX() && distance(fence.getPosition()) < disx){
            SetMoveDirection(Direction.LEFT);
            System.out.println(disx);
            System.out.println(fence.getType());
        }
        if(position.getX() > fence.getPosition().getX() && distance(fence.getPosition()) < disx){
            SetMoveDirection(Direction.RIGHT);
            System.out.println(disx);
        }
        if(position.getY() < fence.getPosition().getY() && distance(fence.getPosition()) < disy){
            SetMoveDirection(Direction.UP);
            System.out.println(disy);
        }
        if(position.getY() > fence.getPosition().getY() && distance(fence.getPosition()) < disy){
            SetMoveDirection(Direction.DOWN);
            System.out.println(disy);
        }
    }

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

    //is out of ranch
    public boolean isOutOfRanch(int characterWidth,int characterHeight, int ranchWidth, int ranchHeight){
        return position.getX() < -characterWidth/2 || position.getX() > ranchHeight + characterWidth/2 || position.getY() < -characterHeight/2 || position.getY() > ranchHeight + characterHeight/2;
    }
}
