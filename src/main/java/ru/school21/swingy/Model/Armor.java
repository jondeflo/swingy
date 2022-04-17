package ru.school21.swingy.Model;

public class Armor extends Artifact {

    public Armor(String name, String type, int level, int attackIncrease, int defenceIncrease, int hitIncrease) throws Exception {
        super(name, type, level, 0, defenceIncrease, 0);
    }

    @Override
    public int getAttackIncrease() {
        return 0;
    }

    @Override
    public int getHitIncrease() {
        return 0;
    }
}
