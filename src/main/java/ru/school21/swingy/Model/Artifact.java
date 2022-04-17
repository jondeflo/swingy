package ru.school21.swingy.Model;

import java.util.Arrays;

public abstract class Artifact {

    private static final String[] types = {"Weapon", "Armor", "Helm"};

    private final String name;
    private final String type;
    private final int level;
    private final int attackIncrease;
    private final int defenceIncrease;
    private final int hitIncrease;

    public Artifact(String name, String type, int level, int attackIncrease, int defenceIncrease, int hitIncrease) throws Exception {
        if (!Arrays.asList(types).contains(type))
            throw new Exception("Wrong artifact type requested: " + type);

        this.name = name;
        this.type = type;
        this.level = level;
        this.attackIncrease = attackIncrease;
        this.defenceIncrease = defenceIncrease;
        this.hitIncrease = hitIncrease;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public int getLevel() {
        return this.level;
    }

    public int getAttackIncrease() {
        return this.attackIncrease;
    }

    public int getDefenceIncrease()
    {
        return this.defenceIncrease;
    }

    public int getHitIncrease() {
        return this.hitIncrease;
    }

    @Override
    public String toString()
    {
        String info =  "lvl " + getLevel() + " " + getType() + " '" + getName() + "': ";
        if (this.getAttackIncrease() > 0)
            info += "+" + this.getAttackIncrease() + " Attack";
        if (this.getDefenceIncrease() > 0)
            info += "+" + this.getDefenceIncrease() + " Defence";
        if (this.getHitIncrease() > 0)
            info += "+" + this.getHitIncrease() + " Hit points";
        return info;
    }

}
