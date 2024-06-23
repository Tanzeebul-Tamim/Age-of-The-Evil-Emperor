package main;

import java.util.ArrayList;

public class Player extends Character {
    // Integers to store number of skills in each path
    public int combatCount, defensiveCount, combatWeaponCount, defensiveEquipmentCount, gold, healers;

    public ArrayList<String> unlockedCombatSkills = new ArrayList<>();
    public ArrayList<String> unlockedDefensiveSkills = new ArrayList<>();

    public ArrayList<String> unlockedCombatWeapons = new ArrayList<>();
    public ArrayList<String> unlockedDefensiveEquipments = new ArrayList<>();

    // Player specific constructor
    public Player(String name) {
        super(name, 100, 0);

        this.combatCount = 0;
        this.defensiveCount = 0;

        this.combatWeaponCount = 0;
        this.defensiveEquipmentCount = 0;

        this.gold = 5;
        this.healers = 0;
    }

    // Player specific methods
    @Override
    public int attack() {
        return (int) (Math.random() * (xp / 4 + combatCount * 3 + combatWeaponCount * 2 + 3)
                + xp / 10 + combatCount * 2 + defensiveCount + defensiveEquipmentCount + 1);
    }

    @Override
    public int defend() {
        return (int) (Math.random() * (xp / 4 + defensiveCount * 3 + defensiveEquipmentCount * 2 + 3)
                + xp / 10 + defensiveCount * 2 + combatCount + combatWeaponCount + 1);
    }

    // Let the player choose a skill of either path
    public void chooseSkill(int lineWidth, String heading, String title, String folderName, String fileName) {
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
            unlockedCombatSkills.add(skill);
            combatCount++;

            Utils.printHeading(true, "You unlocked " + skill + "!");
        } else {
            String skill = Assets.defensiveSkills[defensiveCount];
            unlockedDefensiveSkills.add(skill);
            defensiveCount++;

            Utils.printHeading(true, "You unlocked " + skill + "!");
        }

        Utils.pressEnter();
        Utils.clearConsole();
    }

    // Let the player choose a weapon of either type
    public void chooseWeapon(int lineWidth, String heading, String title, String folderName, String fileName) {
        Utils.clearConsole();

        Utils.printHeading(true, heading);
        Utils.storyPrinter(false, lineWidth, heading, folderName, false, fileName);

        Utils.printHeading(true, title);
        System.out.println("(1) " + Assets.combatWeapons[combatWeaponCount]);
        System.out.println("(2) " + Assets.defensiveEquipment[defensiveEquipmentCount]);

        // Get the player's choice
        int input = Utils.readPlayerInput("-> ", 2);

        Utils.clearConsole();

        // Handle both cases
        if (input == 1) {
            String weapon = Assets.combatWeapons[combatWeaponCount];
            unlockedCombatWeapons.add(weapon);
            combatWeaponCount++;

            Utils.printHeading(true, "You unlocked the " + weapon + "!");
        } else {
            String weapon = Assets.defensiveEquipment[defensiveEquipmentCount];
            unlockedDefensiveEquipments.add(weapon);
            defensiveEquipmentCount++;

            Utils.printHeading(true, "You unlocked the " + weapon + "!");
        }

        Utils.pressEnter();
        Utils.clearConsole();
    }
}
