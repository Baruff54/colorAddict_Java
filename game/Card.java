package game;

import constant.Color;

import java.util.ArrayList;
import java.util.List;

public class Card {
    private List<String> accepted = new ArrayList<>();
    private String link;

    public Card(String color, String color2, String URL) {
        accepted.add(color);
        accepted.add(color2);
        this.link = URL;
    }
}
