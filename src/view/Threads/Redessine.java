package view.Threads;

import view.GameFrame;
import view.GamePanel;

public class Redessine extends Thread{

    private final GamePanel gamePanel;

    // Intervalle de redessin du panneau (en millisecondes)
    public static final int REPAINT_INTERVAL = 30;
    private boolean isRunning = true;

    // Constructeur prenant le panneau de jeu en argument
    public Redessine(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    // Méthode principale du thread
    public void run(){
        while(isRunning){
            try{
                Thread.sleep(REPAINT_INTERVAL);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            // Demande au panneau de jeu de se redessiner
            gamePanel.revalidate();
            gamePanel.repaint();
        }
    }

    // Méthode pour mettre en pause le thread
    public void Pause(){
        isRunning = false;
    }
}
