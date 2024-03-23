package model.Threads;

import model.GameObjects.Ranch;
import view.Threads.Redessine;

public class RanchMove extends Thread{
    private final Ranch ranch;
    private boolean isRunning = true;

    public RanchMove(Ranch ranch){
        this.ranch = ranch;
    }

    public void run(){
        while(isRunning){
            try{
                Thread.sleep(Redessine.REPAINT_INTERVAL);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            ranch.move();
        }
    }

    public void Pause(){
        isRunning = false;
    }
}
