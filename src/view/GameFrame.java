package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;

public class GameFrame extends JFrame{

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public GameFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setVisible(true);

        //set title
        this.setTitle("Farmer");
    }

}
