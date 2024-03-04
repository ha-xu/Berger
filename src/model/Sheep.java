package model;

import java.util.Random;

public class Sheep extends Character{

    private boolean isAlive;

    public Sheep(Position position){
        super(position);
        this.isAlive = true;
    }

    public void Smove(){
        Random random = new Random();
        int direction = random.nextInt(4);
        switch (direction){
            case 0:
                SetMoveDirection(Direction.UP);
                break;
            case 1:
                SetMoveDirection(Direction.DOWN);
                break;
            case 2:
                SetMoveDirection(Direction.LEFT);
                break;
            case 3:
                SetMoveDirection(Direction.RIGHT);
                break;
        }
        System.out.println(direction);
        super.move();
    }


    public boolean isAlive(){
        return isAlive;
    }

    public void setAlive(boolean isAlive){
        this.isAlive = isAlive;
    }
    /**
     * Production du lait par les moutons, calculer les influences du
     taux de la production du lait par les différents mouvements.*/
}
