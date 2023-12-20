package interfaces;

import game.Card;
import game.Game;
import game.Player;

import javax.accessibility.AccessibleContext;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameVersusInterface extends JFrame {
    public JPanel build() {
        // Configuration de la fenêtre principale
        setTitle("Jeux 1v1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 700);

        // Création d'un panneau
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        List<JPanel> jPanels = new ArrayList<>();

        List<Integer> nbPioche = new ArrayList<>();

        for (Player player : Game.getPlayers()) {
            JPanel cardsPanel = new JPanel(new GridLayout(1, 3, 5, 5));
            nbPioche.add(player.getNbCardInPick());
            for (Card card : player.getMain()) {
                Image image = Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource(card.getLink()));
                image = image.getScaledInstance(125, 200, Image.SCALE_DEFAULT);
                JButton button = new JButton(new ImageIcon(image));
                button.setBorder(BorderFactory.createEmptyBorder());
                button.setContentAreaFilled(false);

                button.addActionListener(e -> {
                    if(Game.playCard(player, card)) {
                        setVisible(false);
                        Game.setGameVersusInterface((new GameVersusInterface()).build());
                    }
                });

                cardsPanel.add(button);
            }

            // Ajouter des boutons vides pour remplir les emplacements manquants
            int cardsToAdd = 3 - player.getMain().size();
            for (int i = 0; i < cardsToAdd; i++) {
                JButton emptyButton = new JButton(); // Créez un bouton vide ou un élément graphique fictif
                emptyButton.setBorder(BorderFactory.createEmptyBorder());
                emptyButton.setContentAreaFilled(false);
                cardsPanel.add(emptyButton);
            }

            jPanels.add(cardsPanel);
        }

        Image image = Toolkit.getDefaultToolkit().getImage(
                this.getClass().getClassLoader().getResource(Game.getHistoryCard().getLast().getLink()));
        image = image.getScaledInstance(125, 200, Image.SCALE_DEFAULT);

        panel.add(jPanels.get(0));

        JPanel infoPanel = new JPanel(new GridLayout(1, 3, 5, 5));

        JLabel nbPick = new JLabel("Nombre restant de carte du joueur 1 : " + nbPioche.get(0) + " cartes");
        nbPick.setHorizontalAlignment(JLabel.CENTER);
        infoPanel.add(nbPick);

        JLabel picLabel = new JLabel(new ImageIcon(image));
        picLabel.setBorder(BorderFactory.createEmptyBorder());
        infoPanel.add(picLabel);

        JLabel nbPick2 = new JLabel("Nombre restant de carte du joueur 2 : " + nbPioche.get(1) + " cartes");
        nbPick2.setHorizontalAlignment(JLabel.CENTER);
        infoPanel.add(nbPick2);

        panel.add(infoPanel);

        panel.add(jPanels.get(1));

        add(panel);

        setVisible(true);

        return panel;
    }
}
