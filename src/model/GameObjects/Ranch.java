package model.GameObjects;

import model.Position;
import model.Probability;
import model.Threads.RanchMove;

import java.util.ArrayList;

public class Ranch {

    public final int WIDTH = 1000;
    public final int HEIGHT = 1000;

    //possibility of generate wolf
    private final double GENERATE_WOLF_POSSIBILITY = 0.005;
    public static final int GRASS_PRICE = 10; //the price of grass
    public static final int SHEEP_PRICE = 100; //the price of sheep
    public static final int WOOL_MONEY = 30;

    public static final int INITIAL_MONEY = 500;

    private int money = INITIAL_MONEY; //the money of the rancher


    Rancher rancher;
    ArrayList<Wolf> wolves = new ArrayList<>();
    ArrayList<Sheep> sheepFlock = new ArrayList<>();

    ArrayList<Wool> wools = new ArrayList<>();

    ArrayList<Grass> grasses = new ArrayList<>();

    RanchMove ranchMove = new RanchMove(this);

    public void startMove(){
        ranchMove.start();
    }

    public void stopMove(){
        ranchMove.Pause();
    }

    public Ranch() {
        rancher = new Rancher(new Position(50, 50), 5, this);
//        wolves.add(new Wolf(new Position(200, 60),6, this));
        //ajouter d'abord trois moutons
        sheepFlock.add(new Sheep(new Position(150, 160),3, this));
        sheepFlock.add(new Sheep(new Position(470, 155),3, this));
        sheepFlock.add(new Sheep(new Position(170, 380),3, this));
        //wools.add(new Wool(new Position(190, 310)));
    }

    public void start(){
        startMove();
        rancher.startMove();
        for (Wolf wolf : wolves) {
            wolf.startMove();
        }

        for (Sheep sheep : sheepFlock) {
            sheep.startMove();
        }
    }

    public void stop(){
        stopMove();
        rancher.stopMove();
        for (Wolf wolf : wolves) {
            wolf.stopMove();
        }
        for (Sheep sheep : sheepFlock) {
            sheep.stopMove();
        }
    }

    //ranch move
    public void move(){
        if(Probability.isTrue(GENERATE_WOLF_POSSIBILITY) && wolves.isEmpty()&& !sheepFlock.isEmpty()){
            AddWolf(1);
        }
    }

    //combination avec le magasin (Alizée) et le mouton (Xi)
    public void BuySheep(){
        if (money >= SHEEP_PRICE){
            money -= SHEEP_PRICE;
            Sheep newSheep = new Sheep(new Position(Probability.randomInt(0,500), Probability.randomInt(0,500)), this);
            sheepFlock.add(newSheep);
            newSheep.startMove();
        }
        else{
            System.out.println("You don't have enough money or you have reached the maximum number of sheep");
        }
    }

    //Combinaison avec le magasin (Alizée) et l'herbe (Xi)
    public void BuyGrass(){
        if (money >= GRASS_PRICE){
            money -= GRASS_PRICE;
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


    public ArrayList<Wolf> getWolves() {
        return wolves;
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


    private Position randomPositionOutsideRanch(int length){
        //generate random position outside ranch but within the frame
        //random X
        int x = Probability.randomInt(-length, WIDTH + length);
        //random Y
        if(x < 0 || x > WIDTH){
            return new Position(x, Probability.randomInt(-length, HEIGHT + length));
        }
        else{
            int y_up = Probability.randomInt(-length, 0);
            int y_down = Probability.randomInt(HEIGHT, HEIGHT + length);
            if(Probability.isTrue(0.5)){
                return new Position(x, y_up);
            }
            else{
                return new Position(x, y_down);
            }
        }
    }

    private void AddWolf(int nbWolf){
        for (int i = 0; i < nbWolf; i++) {
            Wolf wolf = new Wolf(randomPositionOutsideRanch(30), 6,this);
            wolves.add(wolf);
            wolf.startMove();
        }
    }
}
