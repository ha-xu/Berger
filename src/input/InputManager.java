package input;

import model.Direction;
import model.Rancher;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager implements KeyListener {
    private final Rancher rancher;


    public InputManager(Rancher rancher) {
        this.rancher = rancher;
    }

    @Override
    public void keyTyped(KeyEvent e) {
       // System.out.println("keyTyped：" + KeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed：" + KeyEvent.getKeyText(e.getKeyCode()));
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                rancher.SetMoveDirection(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                rancher.SetMoveDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                rancher.SetMoveDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                rancher.SetMoveDirection(Direction.RIGHT);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("keyReleased：" + KeyEvent.getKeyText(e.getKeyCode()));
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                rancher.StopMoveDirection(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                rancher.StopMoveDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                rancher.StopMoveDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                rancher.StopMoveDirection(Direction.RIGHT);
                break;
        }
    }



}
