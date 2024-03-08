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
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("keyReleased：" + KeyEvent.getKeyText(e.getKeyCode()));
    }



}
