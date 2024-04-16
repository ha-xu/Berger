package input;

import model.Direction;
import model.GameObjects.Rancher;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager implements KeyListener {
    private final Rancher rancher;

    // Constructeur prenant le rancher en argument
    public InputManager(Rancher rancher) {
        this.rancher = rancher;
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        System.out.println("keyTyped：" + KeyEvent.getKeyText(e.getKeyCode()));
    }

    // Cette méthode est appelée lorsqu'une touche est enfoncée.
    // Elle déclenche des actions en fonction de la touche enfoncée.
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed：" + KeyEvent.getKeyText(e.getKeyCode()));
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: case KeyEvent.VK_W:
                rancher.SetMoveDirection(Direction.UP); // Définit la direction de déplacement du rancher vers le haut
                break;
            case KeyEvent.VK_DOWN: case KeyEvent.VK_S:
                rancher.SetMoveDirection(Direction.DOWN); // vers le bas
                break;
            case KeyEvent.VK_LEFT: case KeyEvent.VK_A:
                rancher.SetMoveDirection(Direction.LEFT); // vers la gauche
                break;
            case KeyEvent.VK_RIGHT: case KeyEvent.VK_D:
                rancher.SetMoveDirection(Direction.RIGHT); // vers la droite
                break;

            case KeyEvent.VK_E:
                rancher.collectWool(); // Le rancher collecte la laine
                break;
        }
    }

    // Cette méthode est appelée lorsque la touche enfoncée est relâchée.
    // Elle arrête le mouvement du rancher dans la direction correspondante.
    @Override
    public void keyReleased(KeyEvent e) {
        //System.out.println("keyReleased：" + KeyEvent.getKeyText(e.getKeyCode()));
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: case KeyEvent.VK_W:
                rancher.StopMoveDirection(Direction.UP); // Arrête le mouvement vers le haut
                break;
            case KeyEvent.VK_DOWN: case KeyEvent.VK_S:
                rancher.StopMoveDirection(Direction.DOWN); // Arrête le mouvement vers le bas
                break;
            case KeyEvent.VK_LEFT: case KeyEvent.VK_A:
                rancher.StopMoveDirection(Direction.LEFT); // Arrête le mouvement vers la gauche
                break;
            case KeyEvent.VK_RIGHT: case KeyEvent.VK_D:
                rancher.StopMoveDirection(Direction.RIGHT); // Arrête le mouvement vers la droite
                break;
        }
    }

}
