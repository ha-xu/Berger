package model.Threads;

import model.GameObjects.Sheep;
import view.Threads.Redessine;

public class SheepMove extends Thread{
    private final Sheep sheep;
    private boolean isRunning = true;

    public SheepMove(Sheep sheep){
        this.sheep = sheep;
    }

    // Méthode exécutée lors du démarrage du thread
    public void run(){
        while(isRunning){
            try{
                Thread.sleep(Redessine.REPAINT_INTERVAL);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            // Déplacement du mouton à chaque itération de la boucle
            sheep.move();
        }
    }

    public void Pause(){
        isRunning = false;
    }
}
