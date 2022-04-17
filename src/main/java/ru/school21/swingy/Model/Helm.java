package ru.school21.swingy.Model;

public class Helm extends Artifact {

    public Helm(String name, String type, int level,  int attackIncrease, int defenceIncrease, int hitIncrease) throws Exception {
        super(name, type, level, 0, 0, hitIncrease);
    }

    @Override
    public int getAttackIncrease() {
        return 0;
    }

    @Override
    public int getDefenceIncrease() {
        return 0;
    }

}
