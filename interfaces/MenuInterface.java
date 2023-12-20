package interfaces;

import game.Game;
import game.Initialize;

import javax.swing.*;
import java.awt.*;

public class MenuInterface  extends JFrame {
    public void build() {
// Configuration de la fenêtre principale
        setTitle("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Création d'un panneau
        JPanel panel = new JPanel();
        Image image = Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("images/Logo.png"));
        image = image.getScaledInstance(384, 188, Image.SCALE_DEFAULT);

        JLabel img = new JLabel(new ImageIcon(image));
        panel.setLayout(new BorderLayout());

        JButton bouton = new JButton("Jouer Contre robot");
        bouton.setSize(256, 128);

        JButton bouton2 = new JButton("Jouer 1v1");
        bouton2.setSize(256, 128);

        // Ajout d'un listener pour chaque bouton
        bouton.addActionListener(e -> {
            new Initialize(true);

            GameVersusInterface gameInterface = new GameVersusInterface();
            Game.setGameVersusInterface(gameInterface.build());

            this.setVisible(false);
        });

        bouton2.addActionListener(e -> {
            new Initialize(false);

            GameVersusInterface gameInterface = new GameVersusInterface();
            Game.setGameVersusInterface(gameInterface.build());

            this.setVisible(false);
        });
        panel.add(img, BorderLayout.NORTH);
        panel.add(bouton, BorderLayout.CENTER);
        panel.add(bouton2, BorderLayout.SOUTH);

        // Ajout du panneau à la fenêtre principale
        add(panel);

        // Rendre la fenêtre visible
        setVisible(true);
    }
}
