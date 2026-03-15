package com.narxoz.rpg;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.decorator.BasicAttack;
import com.narxoz.rpg.decorator.CriticalFocusDecorator;
import com.narxoz.rpg.decorator.FireRuneDecorator;
import com.narxoz.rpg.decorator.PoisonCoatingDecorator;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.facade.AdventureResult;
import com.narxoz.rpg.facade.DungeonFacade;
import com.narxoz.rpg.hero.HeroProfile;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Homework 5 Demo: Decorator + Facade ===\n");


        HeroProfile hero = new HeroProfile("Arlan", 100);
        BossEnemy boss = new BossEnemy("Shadow Dragon", 90, 14);
        AttackAction basic = new BasicAttack("Sword Strike", 12);
        AttackAction fireOnly = new FireRuneDecorator(basic);

        AttackAction poisonThenCritical = new CriticalFocusDecorator(
                new PoisonCoatingDecorator(
                        new BasicAttack("Sword Strike", 12)
                )
        );

        AttackAction fullyEnhanced = new FireRuneDecorator(
                new PoisonCoatingDecorator(
                        new CriticalFocusDecorator(
                                new BasicAttack("Sword Strike", 12)
                        )
                )
        );

        System.out.println("--- Decorator Preview ---");

        System.out.println("1) Base action");
        System.out.println("Name: " + basic.getActionName());
        System.out.println("Damage: " + basic.getDamage());
        System.out.println("Effects: " + basic.getEffectSummary());
        System.out.println();

        System.out.println("2) Fire-enhanced action");
        System.out.println("Name: " + fireOnly.getActionName());
        System.out.println("Damage: " + fireOnly.getDamage());
        System.out.println("Effects: " + fireOnly.getEffectSummary());
        System.out.println();

        System.out.println("3) Poison + Critical action");
        System.out.println("Name: " + poisonThenCritical.getActionName());
        System.out.println("Damage: " + poisonThenCritical.getDamage());
        System.out.println("Effects: " + poisonThenCritical.getEffectSummary());
        System.out.println();

        System.out.println("4) Fire + Poison + Critical action");
        System.out.println("Name: " + fullyEnhanced.getActionName());
        System.out.println("Damage: " + fullyEnhanced.getDamage());
        System.out.println("Effects: " + fullyEnhanced.getEffectSummary());

        System.out.println("\nThis proves that attacks can be extended at runtime");
        System.out.println("without creating separate subclasses for every combination.");

        System.out.println("\n--- Facade Preview ---");

        DungeonFacade facade = new DungeonFacade().setRandomSeed(42L);
        AdventureResult result = facade.runAdventure(hero, boss, fullyEnhanced);

        System.out.println("Final Summary:");
        System.out.println("Winner: " + result.getWinner());
        System.out.println("Rounds: " + result.getRounds());
        System.out.println("Reward: " + result.getReward());
        System.out.println();

        System.out.println("Adventure Log:");
        for (String line : result.getLog()) {
            System.out.println(line);
        }

        System.out.println("\n=== Demo Complete ===");
    }
}
