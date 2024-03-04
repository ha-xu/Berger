package model;

public class SheepMove extends Thread{
    private final Sheep sheep;
    private final int Interval = 1000;
    private boolean isRunning = true;

    public SheepMove(Sheep sheep){
        this.sheep = sheep;
    }

    public void run(){
        while(isRunning){
            try{
                Thread.sleep(Interval);
                sheep.Smove();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void Pause(){
        isRunning = false;
    }
}
