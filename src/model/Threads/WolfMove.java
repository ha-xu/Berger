package model.Threads;
import model.GameObjects.Rancher;
import model.GameObjects.Wolf;
import view.Threads.Redessine;

public class WolfMove extends Thread{
    private final Wolf wolf;
    private boolean isRunning = true;

    public WolfMove(Wolf wolf){
        this.wolf = wolf;
    }

    public void run(){
        while(isRunning){
            try{
                Thread.sleep(Redessine.REPAINT_INTERVAL);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            wolf.move();
        }
    }

    public void Pause(){
        isRunning = false;
    }
}