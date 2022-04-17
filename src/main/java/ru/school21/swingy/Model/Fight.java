package ru.school21.swingy.Model;

import java.util.Random;

public class Fight {

    private Random rand;

    public Fight() {
        this.rand = new Random();
    }

    public int getWinner(Villain enemy, Hero hero) {

        int initEnemyHp = enemy.getHit();

        while (enemy.getHit() > 0 && hero.getHit() > 0)
        {
            if (rand.nextBoolean()) { // hero attacks first

                int damageToEnemy = getDamage(hero.getAttack(), enemy.getDefence());
                int damageToHero = getDamage(enemy.getAttack(), hero.getDefence());

                enemy.setHit(enemy.getHit() - damageToEnemy);
                if (enemy.getHit() <= 0)
                    break;
                hero.setHit(hero.getHit() - damageToHero);

            } else { // enemy attacks first

                int damageToEnemy = getDamage(hero.getAttack(), enemy.getDefence());
                int damageToHero = getDamage(enemy.getAttack(), hero.getDefence());

                hero.setHit(hero.getHit() - damageToHero);
                if (hero.getHit() <= 0)
                    break;
                enemy.setHit(enemy.getHit() - damageToEnemy);
            }
        }

        if (hero.getHit() <= 0)
            return -1;
        if (enemy.getHit() <= 0) {
            if (enemy.getLevel() == 0)
                hero.setExp(hero.getExp() + initEnemyHp);
            else
                hero.setExp(hero.getExp() + initEnemyHp * enemy.getLevel() * 2);
            return 1;
        }

        return 0;
    }

    private int getDamage(int attack, int defence) {

        int totalAttack = 0;
        int totalDefence = 0;

        for (int i = 0; i < 6; i++) {
            totalAttack += attack * (rand.nextInt(6) + 1);

            if (rand.nextBoolean()) // if luck == true give some extra attack points
                totalAttack += attack;

            totalDefence += defence * (rand.nextInt(6) + 1);
        }

        int damage = totalAttack - totalDefence;
        if (damage < 1)
            return 0;
        else {
            while (damage > 20)
                damage = damage / (rand.nextInt(3) + 1);
            return damage;
        }
    }
}
