package ru.school21.swingy.Model;

public class Weapon extends Artifact {

    public Weapon(String name, String type, int level, int attackIncrease, int defenceIncrease, int hitIncrease) throws Exception {
        super(name, type, level, attackIncrease, 0, 0);
    }

    @Override
    public int getDefenceIncrease() {
        return 0;
    }

    @Override
    public int getHitIncrease() {
        return 0;
    }
}
