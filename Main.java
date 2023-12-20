import constant.State;
import game.Game;
import game.Initialize;
import interfaces.MenuInterface;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        MenuInterface menuInterface = new MenuInterface();
        menuInterface.build();
    }
}