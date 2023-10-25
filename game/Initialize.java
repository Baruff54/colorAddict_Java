package game;

import constant.Color;
import sun.rmi.server.Activation$ActivationSystemImpl_Stub;

import java.util.ArrayList;
import java.util.List;

public class Initialize {
    private List<String> listColor = new ArrayList<>();
    public Initialize() {
        listColor.add(Color.PINK);
        listColor.add(Color.RED);
        listColor.add(Color.GREEN);
        listColor.add(Color.YELLOW);
        listColor.add(Color.ORANGE);
        listColor.add(Color.BLACK);
        listColor.add(Color.BLUE);
    }
    public void card(int nbJoker) {
        listColor.forEach(color -> {
            listColor.forEach(color2 -> {
                Card c = new Card(color, color2, "card_" + color + "_" + color2);
                Game.addCard(c);
            });
        });

        for (int i = 0; i < nbJoker; i++) {
            Card c = new Card(Color.JOKER, Color.JOKER, "card_" + Color.JOKER);
            Game.addCard(c);
        }

        Game.blendCard();

    }
}
