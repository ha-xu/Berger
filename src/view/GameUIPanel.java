package view;

import javax.swing.*;
import java.awt.*;

public class GameUIPanel extends JPanel {
    public GameUIPanel(){
        this.setPreferredSize(new Dimension(GameFrame.WIDTH-GameFrame.HEIGHT, GameFrame.HEIGHT));
        this.setLayout(null);
        this.setVisible(true);

        setBackground(Color.BLUE);
    }
}
