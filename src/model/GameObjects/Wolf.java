package model.GameObjects;

import model.Character;
import model.Direction;
import model.Position;

import java.util.ArrayList;

public class Wolf extends Character {

    Ranch ranch;
    public Wolf(Position position,Ranch ranch){
        super(position);
        this.ranch = ranch;
    }

    @Override
    public void move() {
        // TODO Auto-generated method stub -- To chase sheep
        if (ranch.sheepFlock.isEmpty()) {
            return;
        }

        moveToSheep(ranch.getSheepFlock().get(0));
        super.move();
    }
    public void moveToSheep(Sheep sheep) {

        Position wolfPosition = this.getPosition();


        Position sheepPosition = sheep.getPosition();
        StopAllMoveDirections();


            if (wolfPosition.getX() < sheepPosition.getX()) {
                SetMoveDirection(Direction.RIGHT);
            }
            if (wolfPosition.getX() > sheepPosition.getX()) {
                SetMoveDirection(Direction.LEFT);
            }

            if (wolfPosition.getY() < sheepPosition.getY()) {
                SetMoveDirection(Direction.DOWN);
            }

            if (wolfPosition.getY() > sheepPosition.getY()) {
                SetMoveDirection(Direction.UP);
            }




        ArrayList<Sheep> sheepFlock = ranch.getSheepFlock();
        if(this.distance(sheepPosition) < 20)
            sheepFlock.remove(sheep);
    }
    public void ranAwayFromRancher(){
        Position wolfPosition = this.getPosition();
        Position rancherPosition =ranch.getRancher().getPosition();
        int deltaX = wolfPosition.getX() - rancherPosition.getX();
        int deltaY = wolfPosition.getY() - rancherPosition.getY();
        StopAllMoveDirections();

        if (deltaX < -20) {
            SetMoveDirection(Direction.RIGHT);
        }

        else if (deltaX > 20) {
            SetMoveDirection(Direction.LEFT);
        }


        if (deltaY < -20) {
            SetMoveDirection(Direction.DOWN);
        }

        else if (deltaY > 20) {
            SetMoveDirection(Direction.UP);
        }
    }
}
