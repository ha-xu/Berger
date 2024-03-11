import input.InputManager;
import model.GameObjects.Ranch;
import model.GameObjects.Sheep;
import view.GameFrame;
import view.GamePanel;
import view.GameUIPanel;
import view.Redessine;

import java.awt.*;

public class Main {

    private static final GameFrame frame = new GameFrame();

    private static void GameStart() {
        System.out.print("Rancher start!");

        Ranch ranch = new Ranch();

        InputManager inputManager = new InputManager(ranch.getRancher());

        // Start the threads--------------------------------------------------------------
        ranch.getRancher().startMove();
        //ranch.getWolf().startMove();
        for (Sheep sheep : ranch.getSheepFlock()) {
            sheep.startMove();
        }
        //--------------------------------------------------------------------------------

        GamePanel panel = new GamePanel(ranch);
        GameUIPanel uiPanel = new GameUIPanel(ranch);
        Redessine redessine = new Redessine(panel, uiPanel);

        redessine.start();

        frame.setLayout(new FlowLayout()); // 1行2列

        frame.add(panel);
        frame.add(uiPanel);
        frame.addKeyListener(inputManager);
        frame.pack();
        if (uiPanel.isDisplayable()) {
            System.out.println("GameUIPanel is correctly added to a visible window.");
        } else {
            System.out.println("GameUIPanel is not correctly added to a visible window.");
        }
        uiPanel.repaint();
    }

    public static void main(String[] args) {
        GameStart();
    }
}