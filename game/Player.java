package game;

import com.sun.tools.javac.Main;
import interfaces.GameVersusInterface;
import interfaces.WinInterface;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

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

    public TimerTask robotPlay() {
        TimerTask tache = new TimerTask() {
            @Override
            public void run() {
                boolean modif = false;
                List<Card> main = Game.getPlayers().get(0).getMain();
                Iterator<Card> iterator = main.iterator();

                if(!main.isEmpty()) {
                    while(!modif) {
                        Card c = iterator.next();
                        if(Game.playCard(Game.getPlayers().get(1), c)) {
                            Game.getGameVersusInterface().setVisible(false);
                            Game.setGameVersusInterface((new GameVersusInterface()).build());
                            modif = true;
                        }
                    }
                }
            }
        };

        return tache;
    }

    public List<Card> getMain() {
        return main;
    }

    public boolean canPLayCard(Card c) {
        for (Card card : main) {
            for (String string : c.getAcceptedColor()) {
                if(card.verifyColor(string) || card.verifyColor("JOKER") || string.equals("JOKER")) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean verifyWin() {
        if(pick.isEmpty() && main.isEmpty()) {
            (new WinInterface()).build(pseudo);
            Game.getGameVersusInterface().setVisible(false);
            return true;
        }
        return false;
    }

    public boolean piocher() {
        if(!pick.isEmpty()) {
            main.add(pick.getLast());
            pick.remove(pick.getLast());
            return true;
        } else {
            return false;
        }
    }

    public boolean pickCanPlay() {
        for(Card c : pick) {
            for(String str : c.getAcceptedColor()) {
                if (Game.getHistoryCard().getLast().verifyColor(str)) {
                    // Optimisation
                    pick.remove(c);
                    pick.addFirst(c);
                    return true;
                }
            }
        }
        return false;
    }

    public int getNbCardInPick() {
        return pick.size();
    }

    public List<Card> getPick() {
        return pick;
    }

    public String getPseudo() {
        return pseudo;
    }
}
