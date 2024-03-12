package view;

public class UIAnimation extends Thread{
    private final GameUIPanel gameUIPanel;

    private final int Interval = 30;

    private boolean isRunning = true;

    public UIAnimation(GameUIPanel gameUIPanel){
        this.gameUIPanel = gameUIPanel;
    }

    public void run(){
        while(isRunning){
            try{
                Thread.sleep(Interval);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            gameUIPanel.updateVariables();
        }
    }

    public void Pause(){
        isRunning = false;
    }
}
