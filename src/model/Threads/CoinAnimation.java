package model.Threads;

import view.GameUIPanel;

public class CoinAnimation extends Thread{
    private GameUIPanel gameUIpanel;
    private final int Interval = 10;
    private boolean isRunning = true;

    public CoinAnimation(GameUIPanel g){
        this.gameUIpanel = g;
    }

    public void run(){
        while(isRunning){
            try{
                Thread.sleep(Interval);
                gameUIpanel.UIAnimation();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void Pause(){
        isRunning = false;
    }
}
