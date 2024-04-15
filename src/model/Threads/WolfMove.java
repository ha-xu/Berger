package model.Threads;
import model.GameObjects.Rancher;
import model.GameObjects.Wolf;
import view.Threads.Redessine;

public class WolfMove extends Thread{
    private final Wolf wolf;
    private boolean isRunning = true;

    public WolfMove(Wolf wolf){
        this.wolf = wolf;
    }

    // Méthode exécutée lors du démarrage du thread
    public void run(){
        while(isRunning){
            try{
                Thread.sleep(Redessine.REPAINT_INTERVAL);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            // Déplacement du loup à chaque itération de la boucle
            wolf.move();
        }
    }

    public void Pause(){
        isRunning = false;
    }
}