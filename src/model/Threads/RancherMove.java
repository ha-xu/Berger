package model.Threads;

import model.GameObjects.Rancher;
import view.Threads.Redessine;

public class RancherMove extends Thread{
    private final Rancher rancher;
    private boolean isRunning = true; //si le thread est en cours d'exécution

    public RancherMove(Rancher rancher){
        this.rancher = rancher;
    }

    // Méthode exécutée lors du démarrage du thread
    public void run(){
        while(isRunning){
            try{
                // Pause de l'exécution
                Thread.sleep(Redessine.REPAINT_INTERVAL);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            // Déplacement du rancher à chaque itération de la boucle
            rancher.move();
        }
    }

    // Méthode pour mettre en pause l'exécution du thread
    public void Pause(){
        isRunning = false;
    }
}
