package model.Threads;
import model.GameObjects.Rancher;
import model.GameObjects.Wolf;

public class WolfMove extends Thread{
    private final Wolf wolf;
    private final int Interval = 10;
    private boolean isRunning = true;

    public WolfMove(Wolf wolf){
        this.wolf = wolf;
    }

    public void run(){
        while(isRunning){
            try{
                Thread.sleep(Interval);
               wolf.move();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void Pause(){
        isRunning = false;
    }
}

