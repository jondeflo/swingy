package ru.school21.swingy;

import ru.school21.swingy.Controller.Controller;
import ru.school21.swingy.View.GameView;
import ru.school21.swingy.View.ConsoleView;
import ru.school21.swingy.View.GuiView;

public class Swingy {

    public static GameView view;
    public static Controller cnt;

    public static void main(String[] args) throws Exception {

        if (args.length != 1) {
            System.out.println("You must specify one argument to run the program.");
            System.exit(1);
        }
        if (args[0].equals("console")) {
            view = new ConsoleView();
            System.out.println("starting console view");
        } else if (args[0].equals("gui")) {
            view = new GuiView();
            System.out.println("starting gui view");
        } else {
            System.out.println("Only 'console' or 'gui' options are available. Please relaunch program.");
            System.exit(1);
        }
        cnt = new Controller();
        cnt.gameInit();
    }
}
