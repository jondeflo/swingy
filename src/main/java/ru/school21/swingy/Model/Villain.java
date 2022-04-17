package ru.school21.swingy.Model;

import java.util.Random;

public class Villain extends GameUnit {

    public Villain(String name, int level, Artifact artifact) {

        Random rand = new Random();

        this.setName(name);
        this.setLevel(level);

        double multi = level;
        if (multi == 0)
            multi = 0.5;

        this.setAttack((int)(multi * (25 + rand.nextInt(5) + 1)));
        this.setDefence((int)(multi * (25 + rand.nextInt(5) + 1)));
        this.setHit((int)(multi * (20 + rand.nextInt(5) + 1)));
        if (rand.nextInt(20) % 2 == 0)
            this.equipUnit(artifact);
        this.setType("evil");
    }

    @Override
    public String toString() {
        String enemyInfo =  "\033[0;95m" + "Level " + this.getLevel() + " " + this.getName() + ": " +
                this.getAttack() + " attack, " + this.getDefence() +
                " defence, " + this.getHit() + " hit points";
        return enemyInfo + "\u001B[0m";
    }
}
