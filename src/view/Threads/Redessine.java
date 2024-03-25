package view.Threads;

import view.GameFrame;
import view.GamePanel;

public class Redessine extends Thread{

    private final GamePanel gamePanel;

    public static final int REPAINT_INTERVAL = 30;
    private boolean isRunning = true;

    public Redessine(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void run(){
        while(isRunning){
            try{
                Thread.sleep(REPAINT_INTERVAL);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            gamePanel.revalidate();
            gamePanel.repaint();
        }
    }

    public void Pause(){
        isRunning = false;
    }
}
