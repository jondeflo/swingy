package ru.school21.swingy.Model;

import static ru.school21.swingy.Swingy.cnt;

public class Hero extends GameUnit {

    private int exp;

    public static Hero createHero(int type, String name) {
        if (type == 1)
            return new Hero(name, "Greedy knight", 0, 0, 25,15, 100);
        if (type == 2)
            return new Hero(name, "Chubby priest", 0, 0, 15,25, 100);
        if (type == 3)
            return new Hero(name, "Midget boxer", 0, 0, 25,25, 80);
        return null;
    }

    public Hero(String name, String type, int level, int exp, int attack, int defence, int hit) {
        this.setName(name);
        this.setType(type);
        this.setLevel(level);
        this.exp = exp;
        this.setAttack(attack);
        this.setDefence(defence);
        this.setHit(hit);
    }

    public void checkXP() {
        int nextLevelXP = (this.getLevel() + 1) * 1000 + ((this.getLevel()) * (this.getLevel())) * 450;

        if (this.exp >= nextLevelXP) {
                this.setLevel(this.getLevel() + 1);
                this.setAttack(this.getAttack() + 20);
                this.setDefence(this.getDefence() + 20);
                this.setHit(this.getHit() + 20);
        }
        if (this.getLevel() == 8) {
            cnt.forceGameWinMessage();
        }
    }

    public int getExp() {
        return this.exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
        this.checkXP();
    }

    public String artifactInfo() {
        String info = "";
        if (this.getWeapon() != null || this.getArmor() != null || this.getHelm() != null)
        {
            info += "Now you have: ";
            if (this.getWeapon() != null)
                info += this.getWeapon() + "\n";
            if (this.getArmor() != null)
                info += this.getArmor() + "\n";
            if (this.getHelm() != null)
                info += this.getHelm() + "\n";
            return info;
        }
        return info;
    }

    public void unequip() {

        if (this.getWeapon() != null) {
            this.setAttack(this.getAttack() - this.getWeapon().getAttackIncrease());
            this.setWeapon(null);
        }
        if (this.getArmor() != null) {
            this.setDefence(this.getDefence() - this.getArmor().getDefenceIncrease());
            this.setArmor(null);
        }
        if (this.getHelm() != null) {
            this.setHit(this.getHit() - this.getHelm().getHitIncrease());
            if (this.getHit() < 0)
                this.setHit(0);
            this.setHelm(null);
        }
    }

    public String getStat() {
        String heroInfo =  this.getType() + " " + this.getName() +
                "\nLevel: " + this.getLevel() +
                "\nExperience: " + this.exp + "\nAttack: " + this.getAttack() +
                "\nDefence: " + this.getDefence() + "\nHit points: " + this.getHit() + "\n";

        if (this.getWeapon() != null || this.getArmor() != null || this.getHelm() != null)
            heroInfo += artifactInfo();

        return heroInfo;
    }

    @Override
    public String toString() {
        String heroInfo =  "lvl " + this.getLevel() + " " + this.getType() + " " + this.getName()  + ": " +
            this.getExp() + " XP, " + this.getAttack() + " attack, " + this.getDefence() +
            " defence, " + this.getHit() + " hit points";
        if (this.getWeapon() != null || this.getArmor() != null || this.getHelm() != null)
        {
            heroInfo += "\n" + this.getName() + " equipped with:\n";
            if (this.getWeapon() != null)
                heroInfo += this.getWeapon() + "\n";
            if (this.getArmor() != null)
                heroInfo += this.getArmor() + "\n";
            if (this.getHelm() != null)
                heroInfo += this.getHelm() + "\n";
        }
        return heroInfo;
    }
}





