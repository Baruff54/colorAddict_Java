package game;

import constant.State;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game {
    private State state = State.STOP;
    private static List<Card> listCard = new ArrayList<>();

    public static void addCard(Card card) {
        listCard.add(card);
    }
    public static void blendCard() {
        Collections.shuffle(listCard);
    }
}
