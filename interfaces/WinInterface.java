package interfaces;

import game.Game;

import javax.swing.*;

public class WinInterface extends JFrame {
    public void build(String win) {
        // Configuration de la fenêtre principale
        setTitle("Résultat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(250, 120);

        JPanel panel = new JPanel();
        JLabel text = new JLabel((win.equals("Egalité")) ? "Egalité !" : "La gagnant est " + win);

        panel.add(text);

        add(panel);

        setVisible(true);
    }
}
