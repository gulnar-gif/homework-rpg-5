package com.narxoz.rpg.facade;

public class RewardService {

    public String determineReward(AdventureResult battleResult) {
        if (battleResult == null) {
            return "No reward";
        }

        String winner = battleResult.getWinner();

        if ("Draw".equals(winner)) {
            return "No reward";
        }

        if (winner != null && !winner.isBlank()) {
            int rounds = battleResult.getRounds();

            if (rounds <= 2) {
                return "Legendary Chest + 500 gold";
            } else if (rounds <= 4) {
                return "Epic Chest + 250 gold";
            } else {
                return "Rare Chest + 100 gold";
            }
        }

        return "No reward";
    }
}