package ru.school21.swingy.Model;

public abstract class GameUnit {

    private String name;
    private String type;
    private int level;
    private int attack;
    private int defence;
    private int hit;
    private Artifact weapon;
    private Artifact armor;
    private Artifact helm;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public void equipUnit(Artifact art)
    {
        if (art.getType().equals("Weapon"))
        {
            if (this.getWeapon() != null)
                this.setAttack(this.getAttack() - this.getWeapon().getAttackIncrease());
            this.weapon = art;
            this.setAttack(this.getAttack()+ art.getAttackIncrease());
        }
        if (art.getType().equals("Armor"))
        {
            if (this.getArmor() != null)
                this.setDefence(this.getDefence() - this.getArmor().getDefenceIncrease());
            this.armor = art;
            this.setDefence(this.getDefence()+ art.getDefenceIncrease());
        }
        if (art.getType().equals("Helm"))
        {
            if (this.getHelm() != null)
                this.setHit(this.getHit() - this.getHelm().getHitIncrease());
            this.helm = art;
            this.setHit(this.getHit() + art.getHitIncrease());
        }
    }

    public Artifact getWeapon() {
        return weapon;
    }

    public void setWeapon(Artifact weapon) {
        this.weapon = weapon;
    }

    public Artifact getArmor() {
        return armor;
    }

    public void setArmor(Artifact armor) {
        this.armor = armor;
    }

    public Artifact getHelm() {
        return helm;
    }

    public void setHelm(Artifact helm) {
        this.helm = helm;
    }

    public Artifact getArtifact() {
        if (this.getWeapon() != null)
            return this.getWeapon();
        if (this.getArmor() != null)
            return this.getArmor();
        if (this.getHelm() != null)
            return this.getHelm();
        return null;
    }
}
