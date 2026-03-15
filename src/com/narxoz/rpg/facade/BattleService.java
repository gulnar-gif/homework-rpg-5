package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

import java.util.Random;

public class BattleService {
    private Random random = new Random(1L);

    public BattleService setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public AdventureResult battle(HeroProfile hero, BossEnemy boss, AttackAction action) {
        AdventureResult result = new AdventureResult();

        int rounds = 0;
        int maxRounds = 10;

        result.addLine("Battle starts: " + hero.getName() + " vs " + boss.getName());
        result.addLine("Hero action: " + action.getActionName());
        result.addLine("Effects: " + action.getEffectSummary());

        while (hero.isAlive() && boss.isAlive() && rounds < maxRounds) {
            rounds++;
            result.addLine("Round " + rounds + ":");

            int heroDamage = action.getDamage() + random.nextInt(4);
            boss.takeDamage(heroDamage);
            result.addLine(hero.getName() + " deals " + heroDamage + " damage to "
                    + boss.getName() + ". Boss HP = " + boss.getHealth());

            if (!boss.isAlive()) {
                break;
            }

            int bossDamage = boss.getAttackPower() + random.nextInt(3);
            hero.takeDamage(bossDamage);
            result.addLine(boss.getName() + " deals " + bossDamage + " damage to "
                    + hero.getName() + ". Hero HP = " + hero.getHealth());
        }

        result.setRounds(rounds);

        if (hero.isAlive() && !boss.isAlive()) {
            result.setWinner(hero.getName());

            if (rounds <= 2) {
                result.setReward("Legendary Chest + 500 gold");
            } else if (rounds <= 4) {
                result.setReward("Epic Chest + 250 gold");
            } else {
                result.setReward("Rare Chest + 100 gold");
            }

            result.addLine("Winner: " + hero.getName());
            result.addLine("Reward: " + result.getReward());

        } else if (!hero.isAlive() && boss.isAlive()) {
            result.setWinner(boss.getName());
            result.setReward("No reward");
            result.addLine("Winner: " + boss.getName());
            result.addLine("Reward: No reward");

        } else {
            result.setWinner("Draw");
            result.setReward("No reward");
            result.addLine("Winner: Draw");
            result.addLine("Reward: No reward");
        }

        return result;
    }
}
