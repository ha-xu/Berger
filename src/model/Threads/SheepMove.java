package model.Threads;

import model.GameObjects.Sheep;

public class SheepMove extends Thread{
    private final Sheep sheep;
    private final int Interval = 10;
    private boolean isRunning = true;

    public SheepMove(Sheep sheep){
        this.sheep = sheep;
    }
    public void run(){
        while(isRunning){
            try{
                Thread.sleep(Interval);
                sheep.move();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }


    public void Pause(){
        isRunning = false;
    }
}

