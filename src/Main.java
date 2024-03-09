import input.InputManager;
import model.GameObjects.Ranch;
import model.Threads.RancherMove;
import model.Threads.UIAnimation;
import view.*;

import java.awt.*;

public class Main {

    private static final GameFrame frame = new GameFrame();

    private static void GameStart() {
        System.out.print("Rancher start!");

        Ranch ranch = new Ranch();

        InputManager inputManager = new InputManager(ranch.getRancher());
        Redessine redessine = new Redessine(frame);
        RancherMove rancherMove = new RancherMove(ranch.getRancher());


        GamePanel panel = new GamePanel(ranch);
        GameUIPanel uiPanel = new GameUIPanel(ranch);
        UIAnimation uiAnimation = new UIAnimation(uiPanel);

        redessine.start();
        rancherMove.start();
        uiAnimation.start();

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