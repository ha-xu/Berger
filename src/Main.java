import input.InputManager;
import model.Position;
import model.Rancher;
import model.RancherMove;
import view.*;

public class Main {

    private static final GameFrame frame = new GameFrame();

    private static void GameStart() {
        System.out.print("Rancher start!");

        Rancher rancher = new Rancher(new Position(0, 0));
        InputManager inputManager = new InputManager(rancher);
        Redessine redessine = new Redessine(frame);
        RancherMove rancherMove = new RancherMove(rancher);
        GamePanel panel = new GamePanel(rancher);

        redessine.start();
        rancherMove.start();

        frame.add(panel);
        frame.addKeyListener(inputManager);
        frame.pack();

    }

    public static void main(String[] args) {
        GameStart();
    }
}