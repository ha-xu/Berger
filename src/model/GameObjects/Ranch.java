package model.GameObjects;

import model.Position;

import java.util.ArrayList;

public class Ranch {

    private int money = 0;

    Rancher rancher;
    Wolf wolf;
    ArrayList<Sheep> sheepFlock = new ArrayList<>();

    ArrayList<Wool> wools = new ArrayList<>();

    ArrayList<Grass> grasses = new ArrayList<>();

    public Ranch() {
        rancher = new Rancher(new Position(0, 0), this);
        wolf = new Wolf(new Position(200, 200));
        sheepFlock.add(new Sheep(new Position(100, 300)));
        sheepFlock.add(new Sheep(new Position(300, 300)));
        sheepFlock.add(new Sheep(new Position(300, 500)));

        wools.add(new Wool(new Position(100, 100)));
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
