package game;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int id;
    private String pseudo;
    private List<Card> main = new ArrayList<>();
    private List<Card> pick = new ArrayList<>();
    public Player(int place, String pseudo) {
        this.id = place;
        this.pseudo = pseudo;
    }

    public void addCard(Card card) {
        if(main.size() < 3) {
            main.add(card);
        } else {
            pick.add(card);
        }
    }
}
