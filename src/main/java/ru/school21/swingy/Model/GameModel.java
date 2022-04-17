package ru.school21.swingy.Model;

import java.util.ArrayList;
import java.util.Random;
import ru.school21.swingy.Controller.Controller;


public class GameModel {

    private boolean alive;
    private boolean win;
    private Hero hero;
    private ArrayList<Artifact> artList;
    private ArrayList<String> enemyList;
    private GameUnit[] map;
    private Controller cnt;

    public GameModel (ArrayList<Artifact> artList, ArrayList<String> enemyList, Controller cnt) {
        this.artList = artList;
        this.enemyList = enemyList;
        this.alive = true;
        this.win = false;
        this.cnt = cnt;
    }

    private void addHealth(Hero hero) {
        int lvl = hero.getLevel();
        if (lvl == 0)
            lvl++;
        if (hero.getHit() < (lvl * 70 + lvl * 15))
            hero.setHit(hero.getHit() + (lvl * 10));
    }

    public void movePlayer(String input) {

        int newPos = checkMove(input);

        if (newPos >= 0 && !win) {
            if (map[newPos] == null) {
                map[getHeroPosition()] = null;
                map[newPos] = hero;
                addHealth(hero);
            } else {
                if (!cnt.askEscapeOrFight()) { // fight section
                    GameUnit unit = map[newPos];
                    String enemyName = unit.getName();
                    cnt.forceDisplayEnemyStat(unit);

                    if (performCollision((Villain)map[newPos], hero) < 0) {
                        cnt.forceDisplayLoseMessage(enemyName);
                         alive = false;
                    } else {
                        cnt.forceDisplayWinMessage(enemyName);
                        if (new Random().nextBoolean()) { // if artifact dropped by enemy
                            artifactAcception(unit);
                        }
                        map[getHeroPosition()] = null;
                        map[newPos] = hero;
                    }
                }
            }
        }

        cnt.forceRedrawMap(map);

        if (win) {
            if (hero.getLevel() != 7)
                hero.setExp(hero.getExp() + (hero.getExp() / 5));
            addHealth(hero);

            if (cnt.askContinueOrExit()) {
                win = false;
                makeMap();
            } else {
                win = true;
            }
        }
    }

    private int checkMove(String in) {
        int pos = getHeroPosition();
        int size = (int)Math.sqrt(map.length);
        int row = pos / size;
        if (in.equals("w")) {
            if (pos - size < 0)
                win = true;
            else
                return pos - size;
        }
        if (in.equals("s")) {
            if ((pos + size) > (map.length - 1))
                win = true;
            else
                return pos + size;
        }
        if (in.equals("a")) {
            if ((pos - 1) == (row * size - 1))
                win = true;
            else
                return pos - 1;
        }
        if (in.equals("d")) {
            if ((pos + 1) == (row * size + size))
                win = true;
            else
                return pos + 1;
        }

        return -1;
    }

    private void artifactAcception(GameUnit unit) {
        Artifact art = unit.getArtifact();
        if (art != null) {
            if (cnt.askArtifactAcception(hero, art))
                hero.equipUnit(art);
        }
    }

    private int performCollision(Villain enemy, Hero hero) { // +1 if hero wins -1 if lose
        return new Fight().getWinner(enemy, hero);
    }

    private int getHeroPosition() {
        int pos = 0;
        for (GameUnit unit : map)
        {
            if (unit instanceof Hero)
                return pos;
            pos++;
        }
        return -1;
    }

    public String getHeroCoordinates() {
        int position = getHeroPosition() + 1;
        int size = (int)Math.sqrt(map.length);
        int rowCount = 0;
        int tmp = position;

        while (tmp > size) {
            tmp = tmp - size;
            rowCount++;
        }
        int row = rowCount + 1;
        int col = position - ((rowCount * size));

        return ("Position: row " + row + ", column " + col + "\n" + "Map size : " + size + " x " + size);
    }

    public void makeMap() {
        int level = this.hero.getLevel();
        int mapsize = (level - 1) * 5 + 10 - (level % 2);
        int vilAmount = 0;

        map = new GameUnit[mapsize * mapsize];

        while (vilAmount < (mapsize * 1.5 + hero.getLevel() * 10)) {
            Random rand = new Random();
            int randomNum = rand.nextInt(mapsize * mapsize);
            Random rand1 = new Random();
            int min = hero.getLevel() - 1;
            if (min < 0)
                min = 0;
            int max = hero.getLevel() + 1;
            int randomNum1 = rand1.nextInt((max - min) + 1) + min;

            if (randomNum == ((mapsize * mapsize) / 2) + 1)
                continue;

            if (map[randomNum] == null) {
                map[randomNum] = new Villain(enemyList.get(rand.nextInt(enemyList.size())), randomNum1, getRandomArtifact(randomNum1));
                vilAmount++;
            }
        }
        map[((mapsize * mapsize) / 2)] = hero;
        cnt.forceRedrawMap(map);
    }

    public boolean chanceToEscape() {
        Random rand = new Random();
        int randomNum = rand.nextInt(100);
        if (randomNum % 2 == 0)
            return true;
        return false;
    }

    private Artifact getRandomArtifact(int level) { // return random artifact according to level +-1

        level--;
        if (level > 7)
            level = 7;
        if (level <= 0)
            level = 1;

        ArrayList<Artifact> tmpList = new ArrayList<>();
        for (Artifact a : artList)
        {
            if (a.getLevel() == level || a.getLevel() == level - 1 || a.getLevel() == level + 1)
                tmpList.add(a);
        }
        Random rand = new Random();
        int randomNum = rand.nextInt(tmpList.size());
        return tmpList.get(randomNum);
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public void createHero(int type, String name) {
        this.setHero(Hero.createHero(type, name));
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isWin() {
        return win;
    }




}
