package main;

import java.util.ArrayList;

public class Player extends Character {
    // Integers to store number of skills in each path
    public int combatCount, defensiveCount, weaponCount, gold, healers;
    public ArrayList<String> unlockedCombSkills = new ArrayList<>();
    public ArrayList<String> unlockedDefSkills = new ArrayList<>();
    public ArrayList<String> unlockedWeapons = new ArrayList<>();

    // Player specific constructor
    public Player(String name) {
        super(name, 100, 0);
        this.combatCount = 0;
        this.defensiveCount = 0;
        this.weaponCount = 0;
        this.gold = 5;
        this.healers = 0;
    }

    // Player specific methods
    @Override
    public int attack() {
        return (int) (Math.random() * (xp / 4 + combatCount * 3 + 3) + xp / 10 + combatCount * 2 + defensiveCount + 1);
    }

    @Override
    public int defend() {
        return (int) (Math.random() * (xp / 4 + defensiveCount * 3 + 3) + xp / 10 + defensiveCount * 2 + combatCount
                + 1);
    }

    // Let the player choose a skill of either path
    public void chooseAbility(int lineWidth, String heading, String title, String folderName, String fileName) {
        Utils.clearConsole();

        Utils.printHeading(true, heading);
        Utils.storyPrinter(false, lineWidth, heading, folderName, false, fileName);

        Utils.printHeading(true, title);
        System.out.println("(1) " + Assets.combatSkills[combatCount]);
        System.out.println("(2) " + Assets.defensiveSkills[defensiveCount]);

        // Get the player's choice
        int input = Utils.readPlayerInput("-> ", 2);

        Utils.clearConsole();

        // Handle both cases
        if (input == 1) {
            String skill = Assets.combatSkills[combatCount];
            unlockedCombSkills.add(skill);
            combatCount++;

            Utils.printHeading(true, "You unlocked " + skill + "!");
        } else {
            String skill = Assets.defensiveSkills[defensiveCount];
            unlockedDefSkills.add(skill);
            defensiveCount++;

            Utils.printHeading(true, "You unlocked " + skill + "!");
        }

        Utils.pressEnter();
        Utils.clearConsole();
    }
}
