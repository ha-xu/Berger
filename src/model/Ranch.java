package model;

import java.util.ArrayList;

public class Ranch {

    Rancher rancher;
    Wolf wolf;
    ArrayList<Sheep> sheeps = new ArrayList<Sheep>();

    ArrayList<Wool> wools = new ArrayList<Wool>();

    public Ranch() {
        rancher = new Rancher(new Position(0, 0));
        wolf = new Wolf(new Position(200, 200));
        sheeps.add(new Sheep(new Position(100, 300)));
        sheeps.add(new Sheep(new Position(300, 300)));
        sheeps.add(new Sheep(new Position(300, 500)));

        wools.add(new Wool(new Position(100, 100)));
    }

    public Rancher getRancher() {
        return rancher;
    }

    public Wolf getWolf() {
        return wolf;
    }

    public ArrayList<Sheep> getSheeps() {
        return sheeps;
    }

    public ArrayList<Wool> getWools() {
        return wools;
    }
}
