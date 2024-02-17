package view;

public class Redessine extends Thread{

    private final GameFrame gameFrame;

    private final int Interval = 30;
    private boolean isRunning = true;

    public Redessine(GameFrame gameFrame){
        this.gameFrame = gameFrame;
    }

    public void run(){
        while(isRunning){
            try{
                Thread.sleep(Interval);
                gameFrame.repaint();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void Pause(){
        isRunning = false;
    }
}
