package model.GameObjects;

import model.Position;

import java.util.ArrayList;

public class Ranch {

    public final int WIDTH = 1000;
    public final int HEIGHT = 1000;

    private int money = 0; //the money of the rancher
    private int maxSheep = 3; //the maximum number of sheep
    private int maxWolf = 1; //the maximum number of wolf

    Rancher rancher;
    Wolf wolf;
    ArrayList<Sheep> sheepFlock = new ArrayList<>();

    ArrayList<Wool> wools = new ArrayList<>();

    ArrayList<Grass> grasses = new ArrayList<>();

    public Ranch() {
        rancher = new Rancher(new Position(50, 50), 3, this);
        wolf = new Wolf(new Position(200, 60), this);
        sheepFlock.add(new Sheep(new Position(150, 160), 1, this));
        sheepFlock.add(new Sheep(new Position(470, 155),1, this));
        sheepFlock.add(new Sheep(new Position(170, 380),1, this));

        wools.add(new Wool(new Position(190, 310)));
    }

    public Rancher getRancher() {
        return rancher;
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
