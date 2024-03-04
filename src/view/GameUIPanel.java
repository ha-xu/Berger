package view;

import javax.swing.*;
import java.awt.*;

public class GameUIPanel extends JPanel {
    public GameUIPanel(){
        this.setPreferredSize(new Dimension(GameFrame.WIDTH-GameFrame.HEIGHT, GameFrame.HEIGHT));
        this.setLayout(null);
        this.setVisible(true);

        JPanel sous_panel = new JPanel();
        sous_panel.setBounds(0, 50, GameFrame.WIDTH-GameFrame.HEIGHT, GameFrame.HEIGHT/2);
        sous_panel.setBackground(Color.LIGHT_GRAY);
        this.add(sous_panel);

        JButton button_sheep = new JButton("BUY SHEEP");
        button_sheep.setBounds(0, 50, 150, 15);
        sous_panel.add(button_sheep);

        JLabel label_coin = new JLabel("number of coins");
        label_coin.setBounds(GameFrame.WIDTH/2, 0, 150, 15);
        this.add(label_coin);






        setBackground(Color.BLUE);
    }
}
