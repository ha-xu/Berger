package main;

import input.InputManager;
import model.GameObjects.Ranch;
import view.GameMenuPanel;
import view.Threads.Redessine;
import view.GameFrame;
import view.GamePanel;
import view.GameUIPanel;

import java.awt.*;

public class Main {

    private static final GameFrame frame = new GameFrame();

    public static void ShowMenu() {
        frame.getContentPane().removeAll();
        frame.setLayout(new FlowLayout());
        frame.add(new GameMenuPanel());
        frame.pack();
    }

    public static void GameStart() {

        frame.getContentPane().removeAll();

        Ranch ranch = new Ranch();

        InputManager inputManager = new InputManager(ranch.getRancher());

        GamePanel panel = new GamePanel(ranch);
        GameUIPanel uiPanel = new GameUIPanel(ranch);

        //start the ui thread to repaint the ui panel for animation
        uiPanel.startRedessine();
        //ranch start means game start
        ranch.start();

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
        ShowMenu();
//        GameStart();
    }
}