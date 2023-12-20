package game;

import constant.Color;

import java.util.ArrayList;
import java.util.List;

public class Card {
    //
    private List<String> accepted = new ArrayList<>();
    private String colorString;
    private String link;

    public Card(String color, String color2, String URL) {
        accepted.add(color);
        accepted.add(color2);
        colorString = color + " / " + color2;
        this.link = URL;
    }

    public boolean verifyColor(String c) {
        return (this.accepted.contains(c) || c.equals("JOKER") ||this.accepted.contains("JOKER"));
    }

    public List<String> getAcceptedColor() {
        return accepted;
    }

    public String getColorsString() {
        return this.colorString;
    }

    public String getLink() {
        return link;
    }
}
