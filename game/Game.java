package game;

import constant.State;
import interfaces.ShuffleInterface;
import interfaces.WinInterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private static final List<Player> players = new ArrayList<>();
    private static boolean withRobot = false;
    private static Player playerTurn = null;
    private static Component gameVersusInterface = null;
    private static final List<Card> listCard = new ArrayList<>();
    private static final List<Card> historyCard = new ArrayList<>();

    public static boolean playCard(Player p, Card card) {
        Card c = card;

        boolean cardAccepted = false;
        for (String string : c.getAcceptedColor()) {
            if (!cardAccepted) cardAccepted = historyCard.getLast().verifyColor(string);
        }

        if (cardAccepted) {
            System.out.println("La carte jouer est " + card.getColorsString());
            historyCard.add(card);
            p.getMain().remove(card);

            if(p.verifyWin()) {
                return false;
            }

            p.piocher();

            if(!verifyCanPlay()) {
                (new ShuffleInterface()).build();
                gameVersusInterface.setVisible(false);
                return false;
            }
            return true;
        } else {
            System.out.println("La carte jouer est " + card.getColorsString() + " est incorrecte!");
            return false;
        }
    }

    private static boolean verifyCanPlay() {
        boolean canPLay = false;
        for(Player p : players) {
            for(Card c : p.getMain()) {
                for (String string : c.getAcceptedColor()) {
                    if (!canPLay) canPLay = historyCard.getLast().verifyColor(string);
                }
            }
        }
        return canPLay;
    }

    public static void shuffleMain() {
        for(Player p : players) {
            if(p.pickCanPlay()) {
                p.getPick().addAll(p.getMain());
                p.getMain().clear();
                Collections.shuffle(p.getPick());
                for (int i = 0; i < 3; i++) {
                    p.getMain().add(p.getPick().getFirst());
                    p.getPick().remove(0);
                }
            }
        }

        if(!verifyCanPlay()) {
            WinInterface winInterface = new WinInterface();
            winInterface.build("Egalité");
        }
    }

    public static void addCard(Card card) {
        listCard.add(card);
    }

    public static void addPlayer(Player p) {
        players.add(p);
    }

    public static List<Card> getHistoryCard() {
        return historyCard;
    }

    public static List<Card> getListCard() {
        return listCard;
    }

    public static List<Player> getPlayers() {
        return players;
    }

    public static void blendCard() {
        Collections.shuffle(listCard);
    }

    public static void setPlayerTurn(Player playerTurn) {
        Game.playerTurn = playerTurn;
    }

    public static void setWithRobot(boolean robot) {
        Game.withRobot = robot;
    }

    public static Component getGameVersusInterface() {
        return gameVersusInterface;
    }

    public static void setGameVersusInterface(Component gameVersusInterface) {
        Game.gameVersusInterface = gameVersusInterface;
    }
}
