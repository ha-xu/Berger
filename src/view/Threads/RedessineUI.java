package view.Threads;

import view.GameUIPanel;

public class RedessineUI extends Thread{
    private final GameUIPanel gameUIpanel;
    private boolean isRunning = true;

    public RedessineUI(GameUIPanel g){
        this.gameUIpanel = g;
    }

    public void run(){
        while(isRunning){
            try{
                Thread.sleep(Redessine.REPAINT_INTERVAL);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            gameUIpanel.updateVariables();
            gameUIpanel.revalidate();
            gameUIpanel.repaint();

        }
    }

    public void Pause(){
        isRunning = false;
    }
}