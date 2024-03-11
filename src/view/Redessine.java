package view;

public class Redessine extends Thread{

    private final GamePanel gamePanel;
    private final GameUIPanel gameUIPanel;


    private final int Interval = 30;
    private boolean isRunning = true;

    public Redessine(GamePanel gamePanel, GameUIPanel gameUIPanel){
        this.gamePanel = gamePanel;
        this.gameUIPanel = gameUIPanel;
    }
    public void run(){
        while(isRunning){

            try{
                Thread.sleep(Interval);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            gamePanel.repaint();
            gameUIPanel.paintComponent(gamePanel.getGraphics());
        }
    }

    public void Pause(){
        isRunning = false;
    }
}
