package view.Threads;

import view.GameFrame;

public class Redessine extends Thread{

    private final GameFrame gameFrame;

    public static final int REPAINT_INTERVAL = 30;
    private boolean isRunning = true;

    public Redessine(GameFrame gameFrame){
        this.gameFrame = gameFrame;
    }

    public void run(){
        while(isRunning){
            try{
                Thread.sleep(REPAINT_INTERVAL);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            gameFrame.repaint();
        }
    }

    public void Pause(){
        isRunning = false;
    }
}
