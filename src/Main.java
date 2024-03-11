import input.InputManager;
import model.GameObjects.Ranch;
import model.GameObjects.Sheep;
import model.Threads.RancherMove;
import model.Threads.WolfMove;
import view.*;

import java.awt.*;

public class Main {

    private static final GameFrame frame = new GameFrame();

    private static void GameStart() {
        System.out.print("Rancher start!");

        Ranch ranch = new Ranch();

        InputManager inputManager = new InputManager(ranch.getRancher());
        Redessine redessine = new Redessine(frame);

        // Start the threads
        ranch.getRancher().startMove();
        ranch.getWolf().startMove();
        for (Sheep sheep : ranch.getSheepFlock()) {
            sheep.startMove();
        }

        GamePanel panel = new GamePanel(ranch);
        GameUIPanel uiPanel = new GameUIPanel();

        redessine.start();

        frame.setLayout(new FlowLayout()); // 1行2列

        frame.add(panel);
        frame.add(uiPanel);
        frame.addKeyListener(inputManager);
        frame.pack();

    }

    public static void main(String[] args) {
        GameStart();
    }
}