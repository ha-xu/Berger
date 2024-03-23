package model.GameObjects;

import model.Position;
import model.Probability;
import model.Threads.SheepMove;

import java.util.ArrayList;

public class Ranch {

    public final double WIDTH = 1000;
    public final double HEIGHT = 1000;

    private int money = 550; //the money of the rancher
    private final int grassPrice = 10; //the price of grass
    private final int sheepPrice = 100; //the price of sheep
    private double maxSheep = 30; //the maximum number of sheep
    private int maxWolf = 1; //the maximum number of wolf

    Rancher rancher;
    Wolf wolf;
    ArrayList<Sheep> sheepFlock = new ArrayList<>();

    ArrayList<Wool> wools = new ArrayList<>();

    ArrayList<Grass> grasses = new ArrayList<>();

    public Ranch() {
        rancher = new Rancher(new Position(50, 50), 5, this);
        wolf = new Wolf(new Position(200, 60),6, this);
        //ajouter d'abord trois moutons
        sheepFlock.add(new Sheep(new Position(150, 160),3, this));
        sheepFlock.add(new Sheep(new Position(470, 155),3, this));
        sheepFlock.add(new Sheep(new Position(170, 380),3, this));

        wools.add(new Wool(new Position(190, 310)));
    }

    public void startMove(){
        rancher.startMove();
        wolf.startMove();
        for (Sheep sheep : sheepFlock) {
            sheep.startMove();
        }
    }

    public void stopMove(){
        rancher.stopMove();
        wolf.stopMove();
        for (Sheep sheep : sheepFlock) {
            sheep.stopMove();
        }
    }

    //combination avec le magasin (Alizée) et le mouton (Xi)
    public void BuySheep(){
        if (money >= 100 && sheepFlock.size() < maxSheep){
            money -= sheepPrice;
            Sheep newSheep = new Sheep(new Position(Probability.randomInt(0,500), Probability.randomInt(0,500)), this);
            sheepFlock.add(newSheep);
            //pour le mouton acheté, lui ajouter un thread pour le déplacer
            SheepMove newSheepMove = new SheepMove(newSheep);
            newSheepMove.start();
        }
        else{
            System.out.println("You don't have enough money or you have reached the maximum number of sheep");
        }
    }

    //Combinaison avec le magasin (Alizée) et l'herbe (Xi)
    public void BuyGrass(){
        if (money >= 10){
            money -= grassPrice;
            grasses.add(new Grass(new Position(Probability.randomInt(0,500), Probability.randomInt(0,500))));
        }
        else{
            System.out.println("You don't have enough money");
        }
    }

    public Rancher getRancher() {
        return rancher;
    }

    public int getMoney() {
        return money;
    }

    public Wolf getWolf() {
        return wolf;
    }

    public ArrayList<Sheep> getSheepFlock() {
        return sheepFlock;
    }

    public ArrayList<Wool> getWools() {
        return wools;
    }

    public ArrayList<Grass> getGrasses() {
        return grasses;
    }

    //add wool money
    public void addMoney(int money){
        this.money += money;
    }

}
