package input;

import model.Direction;
import model.GameObjects.Rancher;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager implements KeyListener {
    private final Rancher rancher;


    public InputManager(Rancher rancher) {
        this.rancher = rancher;
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        System.out.println("keyTyped：" + KeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed：" + KeyEvent.getKeyText(e.getKeyCode()));
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: case KeyEvent.VK_W:
                rancher.SetMoveDirection(Direction.UP);
                break;
            case KeyEvent.VK_DOWN: case KeyEvent.VK_S:
                rancher.SetMoveDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT: case KeyEvent.VK_A:
                rancher.SetMoveDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT: case KeyEvent.VK_D:
                rancher.SetMoveDirection(Direction.RIGHT);
                break;

            case KeyEvent.VK_E:
                rancher.collectWool();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("keyReleased：" + KeyEvent.getKeyText(e.getKeyCode()));
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: case KeyEvent.VK_W:
                rancher.StopMoveDirection(Direction.UP);
                break;
            case KeyEvent.VK_DOWN: case KeyEvent.VK_S:
                rancher.StopMoveDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT: case KeyEvent.VK_A:
                rancher.StopMoveDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT: case KeyEvent.VK_D:
                rancher.StopMoveDirection(Direction.RIGHT);
                break;
        }
    }



}
