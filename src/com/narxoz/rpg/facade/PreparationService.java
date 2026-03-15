package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

public class PreparationService {

    public String prepare(HeroProfile hero, BossEnemy boss, AttackAction action) {

        if (hero == null || boss == null || action == null) {
            return "Preparation failed: invalid hero, boss, or action.";
        }

        return "Preparation complete: Hero " + hero.getName() + " enters the dungeon with action " + action.getActionName()
                + " against boss " + boss.getName() + ". Effects: " + action.getEffectSummary();
    }
}
