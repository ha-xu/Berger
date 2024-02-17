import input.InputManager;
import view.*;

import javax.swing.*;

public class Main {

    private static final JFrame frame = new GameFrame();

    private static void GameStart() {
        System.out.print("Farmer start!");


        InputManager inputManager = new InputManager();
        GamePanel panel = new GamePanel();


        frame.add(panel);
        frame.addKeyListener(inputManager);
        frame.pack();

    }

    public static void main(String[] args) {
        GameStart();
    }
}