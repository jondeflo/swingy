package ru.school21.swingy.View;

import ru.school21.swingy.Model.GameUnit;
import ru.school21.swingy.Model.Hero;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import static ru.school21.swingy.Swingy.cnt;
import static ru.school21.swingy.Swingy.view;



public class ConsoleView extends GameView {

    static Scanner scan = new Scanner(System.in);

    @Override
    public Hero loadHero(ArrayList<Hero> heroList)
    {
        if (heroList != null && heroList.size() > 0) {
            view.writeOut(view.HERO_LIST);
            int i = 0;
            for (Hero h : heroList) {
                view.writeOut(view.COLOR_INPUT + (i + 1) + view.COLOR_CHOICE + ": " + heroList.get(i) + view.COLOR_RESET);
                i++;
            }

            while (true) {
                int num = -1;
                try {
                    num = Integer.parseInt(view.getUserInput());
                } catch (NumberFormatException e) {}
                if (num < 1 || num > heroList.size())
                    view.writeOut(view.COLOR_WARNING + "Wrong input. Enter correct hero number (from 1 to " + heroList.size() + ")" + view.COLOR_RESET);
                else {
                    return heroList.get(num - 1);
                }
            }
        }
        return null;
    }

    @Override
    public String getCreateOrLoadDecision()
    {
        writeOut(NEW_OR_LOAD);
        String input = "";
        while (true) {
            input = getUserInput().toLowerCase(Locale.ROOT);
            if (cnt.validateInput("name", input, "s", "y"))
                break;
        }
        return input;
    }

    @Override
    public String getArtifactAcception(String artInfo, String artName)
    {
        writeOut(COLOR_CHOICE + "Artifact '" + artName + "' found.\n" +
                artInfo +
                "Press " + COLOR_INPUT + "(y)" + COLOR_CHOICE + " to pick up new Artifact or " +
                COLOR_INPUT + "(n)" + COLOR_CHOICE + " to keep yours" + COLOR_RESET);
        String input = "";
        while (true) {
            input = getUserInput().toLowerCase(Locale.ROOT);
            if (cnt.validateInput("name", "2", "s", input))
                break;
        }
        return input;
    }

    @Override
    public String getEscapeOrFight()
    {
        writeOut(CELL_IS_OCCUPIED);
        String input = "";
        while (true) {
            input = getUserInput().toLowerCase(Locale.ROOT);
            if (cnt.validateInput("name", "1", "s", input))
                break;
        }
        return input;
    }

    @Override
    public String getContinueOrExit()
    {
        writeOut(CONTINUE_OR_EXIT);
        String input = "";
        while (true) {
            input = getUserInput().toLowerCase(Locale.ROOT);
            if (cnt.validateInput("name", "1", "s", input))
                break;
        }
        return input;
    }


    @Override
    public String getMovement()
    {
        writeOut(COLOR_STAT + getHeroStat() + COLOR_RESET);
        writeOut(IN_GAME_INFO_CON);
        String input;
        while (true) {
            input = getUserInput().toLowerCase(Locale.ROOT);
            if (cnt.validateInput("name", "1", input, "y"))
                break;
        }
        return input;
    }

    @Override
    public String getHeroName()
    {
        writeOut(HERO_NAME);
        String input = "";
        while (true) {
            input = getUserInput();
            if (cnt.validateInput(input, "2", "s", "y"))
                break;
        }
        return input;
    }

    @Override
    public String getHeroType()
    {
        writeOut(HERO_TYPES);
        String input = "";
        while (true) {
            input = getUserInput();
            if (cnt.validateInput("name", input, "s", "y"))
                break;
        }
        return input;
    }
    
    @Override
    public void displayWinGame()
    {
        writeOut(view.COLOR_CHOICE + "Hooray! You reach 8th level and win the game!" + view.COLOR_RESET);
        System.exit(0);
    }

    @Override
    public void displayWinMessage(String name)
    {
        writeOut(COLOR_FIGHT + "Battle outcome with " + name + ":\nCongrats! You have overcome " + name + COLOR_RESET);
    }

    @Override
    public void displayLoseMessage(String name)
    {
        writeOut(COLOR_FIGHT + "Battle outcome with " + name + ":\nOh no! " + name + " had completely bite you!" + COLOR_RESET);
    }

    @Override
    public void drawMessage(String msg)
    {
        writeOut(COLOR_INFO + msg + COLOR_RESET);
    }

    @Override
    public void displayEnemyStat(GameUnit unit) {
        writeOut(COLOR_FIGHT + "Your enemy: " + unit.toString() + COLOR_RESET);
    }

    @Override
    public void displayHeroStat()
    {
        writeOut(getHeroStat());
    }

    @Override
    public void redrawMap(GameUnit[] map)
    {
        
        int row = 0;
        for(GameUnit unit : map)
        {
            if (unit == null)
                System.out.print("\033[0;90m" + "#" + "\u001B[0m");
            else if (unit.getType().equals("evil")) {
                if (unit.getWeapon() != null || unit.getArmor() != null || unit.getHelm() != null)
                    System.out.print("\033[0;93m" + "E" + "\u001B[0m");
                else
                    System.out.print("\033[0;91m" + "E" + "\u001B[0m");
            }
            else if (unit.getType().equals("Greedy knight") || unit.getType().equals("Chubby priest")
                    || unit.getType().equals("Midget boxer"))
                System.out.print("\033[0;92m" + "X" + "\u001B[0m");
            else {
                writeOut("=");
            }
            row++;
            System.out.print(" ");
            if (row % Math.sqrt(map.length) == 0)
                writeOut("");

        }
        writeOut("");


        

    }

    public String getUserInput()
    {
        writeOut(COLOR_INPUT + "enter your choice --> " + COLOR_RESET);
        String res = "";
        try {
            if (scan.hasNextLine()) {
                res = scan.nextLine();
            } else {
                writeOut("Play the game, do not try to f*ckup my project!");
                System.exit(1);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        return res;
    }


    public void fiasco()
    {
        writeOut("- - - GAME OVER - - -");
    }

    @Override
    public void writeOut(String msg)
    {
        System.out.println(msg);
        System.out.flush();
    }

}
