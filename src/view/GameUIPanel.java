package view;

import model.GameObjects.Ranch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

public class GameUIPanel extends JPanel {

    private Ranch ranch;
    private int nbmoney = 0;
    private int nbsheep = 0;
    private int nbgrass = 0;



    public GameUIPanel(Ranch r){
        this.ranch = r;

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


        JButton button_grass = new JButton("BUY GRASS");
        button_grass.setBounds(0, 50, 150, 15);
        sous_panel.add(button_grass);



        JLabel label_coin = new JLabel("number of coins : "+ nbmoney);
        label_coin.setBounds(0, 0, GameFrame.WIDTH-GameFrame.HEIGHT, 15);
        label_coin.setForeground(Color.WHITE);
        this.add(label_coin);

        JLabel label_sheep = new JLabel("number of sheeps : "+ nbsheep);
        label_sheep.setBounds(0, 10, GameFrame.WIDTH-GameFrame.HEIGHT, 15);
        label_sheep.setForeground(Color.WHITE);
        this.add(label_sheep);

        JLabel label_grass = new JLabel("number of grasses : "+ nbgrass);
        label_grass.setBounds(0, 20, GameFrame.WIDTH-GameFrame.HEIGHT, 15);
        label_grass.setForeground(Color.WHITE);
        this.add(label_grass);

        button_sheep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                r.BuySheep();
            }
        });

        button_grass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                r.BuyGrass();
            }
        });


        setBackground(Color.BLUE);
    }

    public Ranch getRanch() {
        return ranch;
    }

    public void UIAnimation(){
        if (nbmoney < ranch.getMoney()){
            nbmoney++;
        }  else if (nbmoney > ranch.getMoney()){
            nbmoney--;
        }
        if (nbsheep < ranch.getSheepFlock().size()){
            nbsheep++;
        }  else if (nbsheep > ranch.getSheepFlock().size()){
            nbsheep--;
        }
        if (nbgrass < ranch.getGrasses().size()){
            nbgrass++;
        }  else if (nbgrass > ranch.getGrasses().size()){
            nbgrass--;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //clear the panel
        //update labels
        for (Component c : this.getComponents()){
            if (c instanceof JLabel){
                JLabel label = (JLabel) c;
                if (label.getText().contains("coins")){
                    label.setText("number of coins : "+ nbmoney);
                }
                if (label.getText().contains("sheeps")){
                    label.setText("number of sheeps : "+ nbsheep);
                }
                if (label.getText().contains("grasses")){
                    label.setText("number of grasses : "+ nbgrass);
                }
            }
        }

    }



}
