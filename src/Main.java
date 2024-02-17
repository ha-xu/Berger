import input.InputManager;
import view.*;

public class Main {

    public static void main(String[] args) {
        //TIP 当文本光标位于高亮显示的文本处时按 <shortcut actionId="ShowIntentionActions"/>
        // 查看 IntelliJ IDEA 建议如何修复。
        System.out.print("Farmer start!");

        InputManager inputManager = new InputManager();


        // Create a new JFrame
        GameFrame frame = new GameFrame();
        GamePanel panel = new GamePanel();
        frame.add(panel);
        frame.addKeyListener(inputManager);
    }
}