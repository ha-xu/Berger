package model.Threads;

import model.GameObjects.Rancher;
import view.Threads.Redessine;

public class RancherMove extends Thread{
    private final Rancher rancher;
    private boolean isRunning = true;

    public RancherMove(Rancher rancher){
        this.rancher = rancher;
    }

    public void run(){
        while(isRunning){
            try{
                Thread.sleep(Redessine.REPAINT_INTERVAL);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            rancher.move();
        }
    }

    public void Pause(){
        isRunning = false;
    }
}
