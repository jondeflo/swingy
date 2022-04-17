package ru.school21.swingy.Controller;

import java.util.ArrayList;
import ru.school21.swingy.Model.*;
import ru.school21.swingy.View.GameView;
import ru.school21.swingy.Swingy;

public class Controller {

    private GameModel model;
    private final GameView view = Swingy.view;
    private ArrayList<Hero> heroList;
    private ArrayList<Artifact> artList;
    private ArrayList<String> enemyList;
    private SaveHandler sh;

    public ArrayList<Hero> getHeroList() {
        return this.heroList;
    }

    public void gameInit() { // first fn to run
        sh = new SaveHandler();
        try {
            artList = sh.readArtifacts();
            heroList = sh.readSaves(artList);
            enemyList = sh.readEnemies();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error reading game data. Please reinstall");
            System.exit(1);
        }

        model = new GameModel(artList, enemyList,this);

        while (model.getHero() == null)
            askCreateOrLoadDecision();

        model.makeMap();

        if (gameLoop()) {
            if (model.isWin()) {
                sh.saveHero(model.getHero(), heroList);
                System.exit(0);
            }
        }
        else
            view.fiasco();
    }

    public GameModel getModel() {
        return this.model;
    }

    public boolean askArtifactAcception(Hero hero, Artifact art) {
        String input = view.getArtifactAcception(hero.artifactInfo(), art + "");
        return input.equals("y");
    }

    public boolean askContinueOrExit() {
        String input = view.getContinueOrExit();
        return input.equals("y");
    }

    public boolean askEscapeOrFight() {
        while (true) {
            String input = view.getEscapeOrFight();
            if (input.equals("y")) {
                forceMessage(view.BRAVE_CHOICE);
                return false;
            }
            if (input.equals("n")) {
                forceMessage(view.TRY_LUCK);
                if (model.chanceToEscape()) {
                    forceMessage(view.GOOD_LUCK);
                    return true;
                } else {
                    forceMessage(view.BAD_LUCK);
                    return false;
                }
            }
        }
    }

    public boolean gameLoop() {
        while (model.isAlive() || model.isWin()) {
            if (model.isWin())
                break;

            String input = view.getMovement();
            model.movePlayer(input);

            if (!model.isAlive())
                break;
        }
        return model.isAlive();
    }

    private void createNewHero() {
        String name = view.getHeroName();
        int type = Integer.parseInt(view.getHeroType());
        model.createHero(type, name);
    }

    private void loadHero() {
        model.setHero(sh.getHeroFromView(heroList, view));
        if (model.getHero() == null)
            view.writeOut(view.NO_SAVED_HEROES);
    }

    public boolean validateInput(String name, String type, String inGameInput, String yn) {
        InputValidator iv = new InputValidator(name, type, inGameInput, yn);
        return iv.getValidationResult(iv);
    }

    private void askCreateOrLoadDecision() {

        String input = view.getCreateOrLoadDecision();

        if (input.equals("1"))
            createNewHero();
        if (input.equals("2"))
            loadHero();
        if (input.equals("3"))
            System.exit(0);
    }

    public void forceDisplayWinMessage(String name) {
        view.displayWinMessage(name);
    }

    public void forceDisplayLoseMessage(String name) {
        view.displayLoseMessage(name);
    }

    public void forceDisplayEnemyStat(GameUnit unit) {
        view.displayEnemyStat(unit);
    }

    public void forceRedrawMap(GameUnit[] map) {
        view.redrawMap(map);
    }

    public void forceMessage(String msg) {
        view.drawMessage(msg);
    }

    public void forceGameWinMessage() {
        view.displayWinMessage("THE GREATEST BOSS OF ALL GAME");
        view.displayWinGame();
    }
}
