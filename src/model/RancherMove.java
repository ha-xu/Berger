package model;

public class RancherMove extends Thread{
    private final Rancher rancher;
    private final int Interval = 10;
    private boolean isRunning = true;

    public RancherMove(Rancher rancher){
        this.rancher = rancher;
    }

    public void run(){
        while(isRunning){
            try{
                Thread.sleep(Interval);
                rancher.move();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void Pause(){
        isRunning = false;
    }
}
