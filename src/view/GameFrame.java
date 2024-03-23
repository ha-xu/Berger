package view;
import view.Threads.Redessine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;

public class GameFrame extends JFrame{

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private Redessine redessine = new Redessine(this);

    //start the thread
    public void startRedessine(){
        redessine.start();
    }

    //stop the thread
    public void stopRedessine(){
        redessine.Pause();
    }


    public GameFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
//        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setResizable(false);
        this.setVisible(true);

        //set title
        this.setTitle("Rancher");
    }

}
