package main;

import input.InputManager;
import model.GameObjects.Ranch;
import view.*;
import view.Threads.Redessine;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
        GameUIPanel uiPanel = new GameUIPanel(frame, ranch);

        //start the ui thread to repaint the ui panel for animation
        panel.startRedessine(); //start the thread to repaint the frame
        uiPanel.startRedessine();
        //ranch start means game start
        ranch.start();

        frame.setLayout(new FlowLayout()); // 1行2列
        frame.add(panel);
        frame.add(uiPanel);
        frame.addKeyListener(inputManager);
        frame.pack();
        frame.requestFocus();
    }

    public static void GameStop(boolean isWin, Ranch ranch) {
        frame.getContentPane().removeAll();
        frame.setLayout(new FlowLayout());
        frame.add(new GameFinishPanel(isWin, ranch));
        frame.pack();
    }

    public static void main(String[] args) {
        ShowMenu();
         //GameStart();
//        GameStop(true, new Ranch());
    }
}