package ru.school21.swingy.View;

import ru.school21.swingy.Model.GameUnit;
import ru.school21.swingy.Model.Hero;
import java.util.ArrayList;

import static ru.school21.swingy.Swingy.cnt;


public abstract class GameView {

    public static final String COLOR_RESET = "\u001B[0m";

    public static final String COLOR_WARNING = "\033[0;91m";    // RED
    public static final String COLOR_INFO = "\033[0;92m";  // GREEN
    public static final String COLOR_CHOICE = "\033[0;93m"; // YELLOW
    public static final String COLOR_STAT = "\033[0;95m"; // PURPLE
    public static final String COLOR_INPUT = "\033[0;96m";   // CYAN
    public static final String COLOR_FIGHT = "\033[0;97m";  // WHITE

    public final String NEW_OR_LOAD = COLOR_CHOICE + "Would you like to create new Hero or load an old one?\n" +
            "Press " + COLOR_INPUT + "1" + COLOR_CHOICE + " to create new Hero\n" +
            "Press " + COLOR_INPUT + "2" + COLOR_CHOICE + " to load saved Hero\n" +
            "Press " + COLOR_INPUT + "3" + COLOR_CHOICE + " to quit" + COLOR_RESET;

    public final String HERO_NAME = COLOR_CHOICE +
            "Please enter hero name (3-20 characters, letters and numbers allowed)" + COLOR_RESET;

    public final String HERO_TYPES = COLOR_CHOICE + "Please enter hero type (1-3)\n" +
            COLOR_INPUT + "1: " + COLOR_CHOICE + "Greedy knight  (strong attack, weak defence, normal health)\n" +
            COLOR_INPUT + "2: " + COLOR_CHOICE + "Chubby priest  (weak attack, strong defence, normal health)\n" +
            COLOR_INPUT + "3: " + COLOR_CHOICE + "Midget boxer   (strong attack, strong defence, medium health)" +
            COLOR_RESET;

    public final String NO_SAVED_HEROES = COLOR_WARNING +
            "No heroes was saved before. You may only create a new one" + COLOR_RESET;

    public final String HERO_LIST = COLOR_CHOICE + "Please enter Hero number"  + COLOR_RESET;

    public final String CONTINUE_OR_EXIT = COLOR_CHOICE + "You have reach the border of the map and complete current mission\n" +
            COLOR_CHOICE + "Would you like to continue missions " +
            COLOR_INPUT + "(y)" + COLOR_CHOICE + " or exit the game " +
            COLOR_INPUT + "(n)" + COLOR_CHOICE + "?" + COLOR_RESET;

    public final String CONTINUE_OR_EXIT_GUI = "You have reach the border of the map and complete current mission.\n" +
            "Would you like to continue missions or exit the game?";

    public final String CELL_IS_OCCUPIED = COLOR_CHOICE + "Cell is occupied by a villain. Will you fight " +
            COLOR_INPUT + "(y)" + COLOR_CHOICE + " or try to escape " +
            COLOR_INPUT + "(n)" + COLOR_CHOICE + "?" + COLOR_RESET;

    public final String CELL_IS_OCCUPIED_GUI = COLOR_CHOICE + "Cell is occupied by a villain.\n" +
            "Will you fight or try to escape?";

    public final String BRAVE_CHOICE = COLOR_CHOICE + "Brave choice! Let the fight begins!" + COLOR_RESET;

    public final String TRY_LUCK = COLOR_INFO + "Let's try your luck..." + COLOR_RESET;

    public final String GOOD_LUCK = COLOR_INFO + "Luck is on your side. Run for you life!" + COLOR_RESET;

    public final String BAD_LUCK =  COLOR_INFO + "Bad luck. You must fight!" + COLOR_RESET;

    public final String IN_GAME_INFO_CON = COLOR_INPUT +
            "Choose direction to move: North (w), South (s), West (a) or East (d)" + COLOR_RESET;

    public final String IN_GAME_INFO_GUI = "Choose direction to move: North, South, West or East";

    public abstract String getArtifactAcception(String artInfo, String artName);

    public abstract String getContinueOrExit();

    public abstract String getEscapeOrFight();

    public abstract String getMovement();

    public abstract String getCreateOrLoadDecision();

    public abstract Hero loadHero(ArrayList<Hero> heroList);

    public abstract String getHeroName();

    public abstract String getHeroType();

    public String getHeroStat() {
        if (cnt.getModel().isAlive())
            return cnt.getModel().getHero().getStat() + "\n" + cnt.getModel().getHeroCoordinates();
        else
            return "RIP, dear " + cnt.getModel().getHero().getName() + "!";
    }

    public abstract void displayWinGame();

    public abstract void displayWinMessage(String name);

    public abstract void displayLoseMessage(String name);

    public abstract void displayEnemyStat(GameUnit unit);

    public abstract void displayHeroStat();

    public abstract void drawMessage(String msg);

    public abstract void redrawMap(GameUnit[] map);

    public abstract void writeOut(String msg);

    public abstract void fiasco();

    public abstract String getUserInput();


}
