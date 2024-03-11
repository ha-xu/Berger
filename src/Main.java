import input.InputManager;
import model.GameObjects.Ranch;
import model.GameObjects.Sheep;
import model.Threads.RancherMove;
import model.Threads.SheepMove;
import view.*;

import java.awt.*;
import java.util.ArrayList;

public class Main {

    private static final GameFrame frame = new GameFrame();

    private static void GameStart() {
        System.out.print("Rancher start!");

        Ranch ranch = new Ranch();

        InputManager inputManager = new InputManager(ranch.getRancher());
        Redessine redessine = new Redessine(frame);
        RancherMove rancherMove = new RancherMove(ranch.getRancher());

        //reprendre les moutons et les déplacements des moutons
        ArrayList<Sheep> sheepFlock = ranch.getSheepFlock();
        ArrayList<SheepMove> sheepMove = new ArrayList<>();
        for (Sheep sheep : sheepFlock) {
            sheepMove.add(new SheepMove(sheep));
        }

        GamePanel panel = new GamePanel(ranch);
        GameUIPanel uiPanel = new GameUIPanel();

        redessine.start();
        rancherMove.start();
        //pour déplacer les moutons
        for (SheepMove SM : sheepMove) {
            SM.start();
        }

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