package interfaces;

import game.Game;
import game.Initialize;

import javax.swing.*;

public class ShuffleInterface extends JFrame {
    public void build() {
        // Configuration de la fenêtre principale
        setTitle("Shuffle");
        setSize(350, 120);

        JPanel panel = new JPanel();

        JLabel text = new JLabel("Vous ne pouvez plus jouer, mélange des cartes!");

        panel.add(text);

        JButton bouton = new JButton("Mélanger");

        // Ajout d'un écouteur d'événements pour chaque bouton
        bouton.addActionListener(e -> {
            Game.shuffleMain();

            GameVersusInterface gameInterface = new GameVersusInterface();
            Game.getGameVersusInterface().setVisible(false);
            Game.setGameVersusInterface(gameInterface.build());

            this.setVisible(false);
        });

        panel.add(bouton);

        add(panel);

        setVisible(true);
    }
}
