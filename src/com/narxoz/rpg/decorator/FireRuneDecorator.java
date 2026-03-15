package com.narxoz.rpg.decorator;

public class FireRuneDecorator extends ActionDecorator {
    public FireRuneDecorator(AttackAction wrappedAction) {
        super(wrappedAction);
    }

    @Override
    public String getActionName() {
        return super.getActionName() + " [Fire]";
    }

    @Override
    public int getDamage() {
        return super.getDamage() + 8;
    }

    @Override
    public String getEffectSummary() {
        return super.getEffectSummary() + " | Fire rune: +8 fire damage";
    }
}