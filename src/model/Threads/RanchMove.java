package model.Threads;

import model.GameObjects.Ranch;
import view.Threads.Redessine;

public class RanchMove extends Thread{
    private final Ranch ranch;
    private boolean isRunning = true;

    public RanchMove(Ranch ranch){
        this.ranch = ranch;
    }

    // Méthode exécutée lors du démarrage du thread
    public void run(){
        while(isRunning){
            try{
                Thread.sleep(Redessine.REPAINT_INTERVAL);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            // Déplacement du ranch à chaque itération de la boucle
            ranch.move();
            // Exécution de la méthode dateRun du ranch pour mettre à jour la date du jeu
            ranch.dateRun(Redessine.REPAINT_INTERVAL);
        }
    }

    public void Pause(){
        isRunning = false;
    }
}
