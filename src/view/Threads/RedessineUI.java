package view.Threads;

import view.GameUIPanel;

public class RedessineUI extends Thread{
    private final GameUIPanel gameUIpanel;
    private boolean isRunning = true;

    // Constructeur prenant le panneau d'interface utilisateur en argument
    public RedessineUI(GameUIPanel g){
        this.gameUIpanel = g;
    }

    // Méthode principale du thread
    public void run(){
        while(isRunning){
            try{
                Thread.sleep(Redessine.REPAINT_INTERVAL);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            // Met à jour les variables du panneau d'interface utilisateur
            gameUIpanel.updateVariables();
            // Redessine le panneau d'interface utilisateur
            gameUIpanel.revalidate();
            gameUIpanel.repaint();

        }
    }

    // Méthode pour mettre en pause le thread
    public void Pause(){
        isRunning = false;
    }
}