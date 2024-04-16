package view;

import main.Main;
import model.GameObjects.Ranch;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GameFinishPanel extends JPanel {


    // Constructeur de la classe
    public GameFinishPanel(boolean win, Ranch ranch,String message){
        // Détermine le titre en fonction du résultat (victoire ou défaite)
        String title = win ? "You Win" : "Game Over";
        // Récupère l'argent du ranch
        int money = ranch.getMoney();
        // Récupère le nombre de moutons dans le ranch
        int sheepNb = ranch.getSheepFlock().size();

        // Création des composants Swing
        JLabel titleLabel = new JLabel(title);
        JLabel messageLabel = new JLabel(message);
        JLabel moneyLabel = new JLabel("Money: " + money);
        JLabel sheepLabel = new JLabel("Sheep: " + sheepNb);
        JButton restartButton = new JButton("Restart");
        JButton quitButton = new JButton("Quit");

        // Configuration de la disposition et de la taille du panneau
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(GameFrame.WIDTH, GameFrame.HEIGHT));

        // Configuration des labels de titre, message, argent et moutons
        titleLabel.setFont(new Font("Consola", Font.BOLD, 32));
        titleLabel.setForeground(Color.ORANGE);
        titleLabel.setBorder(new EmptyBorder(0, 0, 10, 0));

        messageLabel.setFont(new Font("Consola", Font.BOLD, 18));
        messageLabel.setForeground(Color.gray);
        messageLabel.setBorder(new EmptyBorder(0, 0, 10, 0));

        moneyLabel.setFont(new Font("Consola", Font.BOLD, 18));
        moneyLabel.setForeground(Color.gray);
        moneyLabel.setBorder(new EmptyBorder(0, 0, 10, 0));

        sheepLabel.setFont(new Font("Consola", Font.BOLD, 18));
        sheepLabel.setForeground(Color.gray);
        sheepLabel.setBorder(new EmptyBorder(0, 0, 10, 0));

        // Configuration de l'alignement des composants
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        moneyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        sheepLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Ajout des composants au panneau avec de l'espace vertical entre eux
        this.add(Box.createVerticalGlue());
        this.add(titleLabel);
        this.add(messageLabel);
        this.add(moneyLabel);
        this.add(sheepLabel);
        this.add(restartButton);
        this.add(quitButton);
        this.add(Box.createVerticalGlue());

        // Affiche le panneau
        this.setVisible(true);
        // Ajoute les écouteurs d'événements pour les boutons
        restartButton.addActionListener(evt -> Main.GameStart());
        quitButton.addActionListener(evt -> System.exit(0));
    }
}
