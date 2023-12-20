package game;

import constant.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class Initialize {
    private List<String> listColor = new ArrayList<>();

    public Initialize(boolean withRobot) {
        listColor.add(Color.PINK);
        listColor.add(Color.RED);
        listColor.add(Color.GREEN);
        listColor.add(Color.YELLOW);
        listColor.add(Color.ORANGE);
        listColor.add(Color.BLACK);
        listColor.add(Color.BLUE);

        card(8);
        Game.addPlayer(new Player(1, "joueur 1"));
        if(withRobot) {
            Player p = new Player(2, "robot");
            Game.addPlayer(p);

            Timer timer = new Timer();

            timer.scheduleAtFixedRate(p.robotPlay(), 0, 2000);
        } else {
            Game.addPlayer(new Player(2, "joueur 2"));
        }

        Game.setWithRobot(withRobot);

        this.giveCard();
    }

    private void card(int nbJoker) {
        listColor.forEach(color -> {
            listColor.forEach(color2 -> {
                if (!color2.equals(color)) {
                    Card c = new Card(color, color2, "images/card_" + color + "_" + color2 + ".png");
                    Game.addCard(c);
                }
            });
        });

        for (int i = 0; i < nbJoker; i++) {
            Card c = new Card(Color.JOKER, Color.JOKER, "images/card_" + Color.JOKER + ".png");
            Game.addCard(c);
        }

        Game.blendCard();
    }

    private void giveCard() {
        while (Game.getPlayers().size() < (Game.getListCard().size() - 1)) {
            Game.getPlayers().forEach(player -> {
                player.addCard(Game.getListCard().stream().findFirst().orElse(null));
                Game.getListCard().remove(0);
            });
        }

        Game.getHistoryCard().addAll(Game.getListCard());
    }
}
