package model.GameObjects;

import model.Character;
import model.Direction;
import model.Position;

import java.util.Random;

import static model.Probability.isTrue;

public class Sheep extends Character {

    //private boolean isAlive;
    private Ranch ranch;

    //Constructeur
    public Sheep(Position position, Ranch ranch){
        super(position);
        //this.isAlive = true;
        this.ranch = ranch;
    }
    //Méthode de déplacement aléatoire des moutons
    public void Smove(){
        boolean isMoving = isTrue(0.1);
        //System.out.println(isMoving);
        if (isMoving){
            super.StopAllMoveDirections();
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
            //System.out.println(direction);
        }
    }

    //Méthode de déplacement des moutons, avec limite de la zone de déplacement
    @Override
    public void move(){
        this.produirePoil();
        Smove();
        if (this.getPosition().getX() > ranch.WIDTH){
            super.StopMoveDirection(Direction.RIGHT);
        }
        if(this.getPosition().getX() < 0 ){
            super.StopMoveDirection(Direction.LEFT);
        }
        if(this.getPosition().getY() > ranch.HEIGHT){
            super.StopMoveDirection(Direction.DOWN);
        }
        if(this.getPosition().getY() < 0){
            super.StopMoveDirection(Direction.UP);
        }
        super.move();
    }

/**
 public boolean isAlive(){
 return isAlive;
 }

 public void setAlive(boolean isAlive){
 this.isAlive = isAlive;
 }
 */

    /**
     * Production aléatoire des poils par les moutons*/
    public void produirePoil(){
        boolean produitPoil = isTrue(0.001);
        if(produitPoil){
            ranch.getWools().add(new Wool(new Position(this.getPosition().getX(), this.getPosition().getY())));
        }
    }

    // Méthode de consommation de l'herbe par les moutons
    public void EatGrass(){
        for (Grass grass : ranch.getGrasses()){
            if (grass.getPosition().getX() == this.getPosition().getX() && grass.getPosition().getY() == this.getPosition().getY()){
                ranch.getGrasses().remove(grass);
                break;
            }
        }
    }

}
