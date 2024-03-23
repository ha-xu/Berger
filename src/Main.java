import input.InputManager;
import model.GameObjects.Ranch;
import view.Threads.Redessine;
import view.Threads.RedessineUI;
import view.*;

import java.awt.*;

public class Main {

    private static final GameFrame frame = new GameFrame();

    private static void GameStart() {

        Ranch ranch = new Ranch();

        InputManager inputManager = new InputManager(ranch.getRancher());

        GamePanel panel = new GamePanel(ranch);
        GameUIPanel uiPanel = new GameUIPanel(ranch);

        //start the ui thread to repaint the ui panel for animation
        uiPanel.startRedessine();
        //ranch start means game start
        ranch.startMove();

        frame.startRedessine(); //start the thread to repaint the frame
        frame.setLayout(new FlowLayout()); // 1行2列
        frame.add(panel);
        frame.add(uiPanel);
        frame.addKeyListener(inputManager);
        frame.pack();

        //a thread to keep the frame focused
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(Redessine.REPAINT_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                frame.requestFocus();

            }
        }).start();
    }

    public static void main(String[] args) {
        GameStart();
    }
}