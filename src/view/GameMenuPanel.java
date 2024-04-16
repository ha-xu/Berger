package view;

import main.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Panneau de menu principal du jeu.
 */
public class GameMenuPanel extends JPanel {

    public GameMenuPanel() {
        //set size

        JLabel label = new JLabel("RANCHER");
        JButton startButton = new JButton("Start");
        JButton quitButton = new JButton("Quit");

        // Configuration du layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(GameFrame.WIDTH, GameFrame.HEIGHT));

        //Set the properties of the label
        label.setFont(new Font("Consola", Font.BOLD, 35));
        label.setForeground(Color.ORANGE);
        label.setBorder(new EmptyBorder(0, 0, 10, 0));  //set the padding of the label

        //Set the alignements of the label and the button
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createVerticalGlue());
        this.add(label);
        this.add(startButton);
        this.add(quitButton);
        this.add(Box.createVerticalGlue());
        // Rendre le panneau visible
        this.setVisible(true);
        //add a listener to the button
        startButton.addActionListener(evt -> Main.GameStart());
        quitButton.addActionListener(evt -> System.exit(0));

    }

}
