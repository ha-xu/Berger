package view;

import main.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GameFinishPanel extends JPanel {

    public GameFinishPanel(){
        JLabel label = new JLabel("Game Over");
        JButton restartButton = new JButton("Restart");
        JButton quitButton = new JButton("Quit");

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(GameFrame.WIDTH, GameFrame.HEIGHT));

        label.setFont(new Font("Consola", Font.BOLD, 25));
        label.setForeground(Color.ORANGE);
        label.setBorder(new EmptyBorder(0, 0, 10, 0));

        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createVerticalGlue());
        this.add(label);
        this.add(restartButton);
        this.add(quitButton);
        this.add(Box.createVerticalGlue());

        this.setVisible(true);
        restartButton.addActionListener(evt -> Main.GameStart());
        quitButton.addActionListener(evt -> System.exit(0));
    }
}
