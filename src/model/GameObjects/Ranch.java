package model.GameObjects;

import main.Main;
import model.Position;
import model.Probability;
import model.SoundPlayer;
import model.Threads.RanchMove;

import java.util.ArrayList;

public class Ranch {

    public final int WIDTH = 1000;
    public final int HEIGHT = 1000;

    //possibility of generate wolf
    private final double GENERATE_WOLF_POSSIBILITY = 0.005;
    public static final int GRASS_PRICE = 10; //the price of grass
    public static final int SHEEP_PRICE = 100; //the price of sheep

    public static final int FENCE_PRICE = 80; //the price of fence
    public static final int WOOL_MONEY = 30;

    public static final int INITIAL_MONEY = 500;

    public static final int DATE_TIME = 10000;//time interval of a day in milliseconds
    public static final int FENCE_LIFE = 5;
    public static final int WIN_DAYS = 30;

    public static final int WIN_SHEEP_COUNT = 10;

    private int money = INITIAL_MONEY; //the money of the rancher
    private int date = 1; //the date of the game

    private int wolfCount = 0;
    Rancher rancher;
    ArrayList<Wolf> wolves = new ArrayList<>();
    ArrayList<Sheep> sheepFlock = new ArrayList<>();

    ArrayList<Wool> wools = new ArrayList<>();

    ArrayList<Grass> grasses = new ArrayList<>();

    ArrayList<Fence> fences = new ArrayList<>();


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

    boolean daychanged = false;
    //ranch move
    public void move(){
        //when all the sheep are eaten, the player loses
        if(this.sheepFlock.isEmpty()){
            GameLose("All the sheep are eaten by the wolves!");
        }

        //when the date is 30, the player wins
        if(date >= WIN_DAYS && sheepFlock.size() >= WIN_SHEEP_COUNT){
            GameWin("You have survived for " + WIN_DAYS + " days and have more than " + WIN_SHEEP_COUNT + " sheep");
        }

        if(date >= WIN_DAYS && sheepFlock.size() < WIN_SHEEP_COUNT){
            GameLose("You have survived for " + WIN_DAYS + " days but have less than " + WIN_SHEEP_COUNT + " sheep");
        }
    }

    private void GameWin(String winReason) {
        stopMove();
        Main.GameStop(true,this,winReason);
    }

    private void GameLose(String loseReason) {
        stopMove();
        Main.GameStop(false,this,loseReason);
    }

    private int datetimecount = DATE_TIME;

    public int getDate() {
        return date;
    }

    //get the fences
    public ArrayList<Fence> getFences() {
        return fences;
    }

    public void dateRun(int updateInterval){
        datetimecount -= updateInterval;
        if(datetimecount <= 0){
            date++;
            datetimecount = DATE_TIME;

            if(date % 2 == 0){
                AddWolf(date / 2);
            }

            //remove the fences that have been placed for more than 2 days
            fences.removeIf(fence -> date - fence.getStartDate() > FENCE_LIFE);
        }
    }

    //combination avec le magasin (Alizée) et le mouton (Xi)
    public void BuySheep(){
        if (money >= SHEEP_PRICE){
            money -= SHEEP_PRICE;
            Sheep newSheep = new Sheep(new Position(Probability.randomInt(0,500), Probability.randomInt(0,500)), this);
            sheepFlock.add(newSheep);
            newSheep.startMove();
            SoundPlayer.playBuySound();
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
            SoundPlayer.playBuySound();

        }
        else{
            System.out.println("You don't have enough money");
        }
    }

    //buy fence
    public void BuyFence(Position position, Fence.TypeFence type){
        if (money >= FENCE_PRICE){
            money -= FENCE_PRICE;
            addFence(position, type);
            SoundPlayer.playBuySound();

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

    private void AddWolf(int wolfCount){
        SoundPlayer.playWolfSound();
        for (int i = 0; i < wolfCount; i++) {
            Wolf wolf = new Wolf(randomPositionOutsideRanch(30), 6, this);
            wolves.add(wolf);
            wolf.startMove();
        }
    }

    //ajouter la clôture (position est choisi par le curseur par
    // la fonction définie dans GamePanel).
    public void addFence(Position position, Fence.TypeFence type){
        Fence newFence = new Fence(position, type, date);
        fences.add(newFence);
    }
}
