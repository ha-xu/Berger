import input.InputManager;
import model.*;
import view.*;

import java.awt.*;

public class Main {

    private static final GameFrame frame = new GameFrame();

    private static void GameStart() {
        System.out.print("Rancher start!");

        Rancher rancher = new Rancher(new Position(0, 0));
        /**pour sheep*/
        Sheep sheep = new Sheep(new Position(100, 100));
        SheepMove sheepMove = new SheepMove(sheep);

        InputManager inputManager = new InputManager(rancher);
        Redessine redessine = new Redessine(frame);
        RancherMove rancherMove = new RancherMove(rancher);


        GamePanel panel = new GamePanel(rancher, sheep);
        GameUIPanel uiPanel = new GameUIPanel();

        redessine.start();
        rancherMove.start();
        /**sheep*/
        sheepMove.start();

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