package main;

import java.util.ArrayList;

public class Player extends GameCharacter {
    // Integers to store number of skills in each path
    public int combatCount, defensiveCount, combatWeaponCount, defensiveEquipmentCount, gold, healers, enemiesKilled;

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

        this.gold = 100; // Todo
        this.healers = 0;

        this.enemiesKilled = 0;
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
        // Get the player's choice
        int input = UIUtils.choose(lineWidth, heading, title, folderName, fileName, Assets.combatSkills[combatCount],
                Assets.defensiveSkills[defensiveCount]);

        Utils.clearConsole();

        // Handle both cases
        if (input == 1) {
            String skill = Assets.combatSkills[combatCount];
            unlockedCombatSkills.add(skill);
            combatCount++;

            Utils.printHeading(false, true, "You unlocked " + skill + "!");
        } else {
            String skill = Assets.defensiveSkills[defensiveCount];
            unlockedDefensiveSkills.add(skill);
            defensiveCount++;

            Utils.printHeading(false, true, "You unlocked " + skill + "!");
        }

        Utils.pressEnter();
        Utils.clearConsole();
    }

    // Let the player choose a weapon of either type
    public void chooseWeapon(int lineWidth, String heading, String title, String folderName, String fileName) {
        // Get the player's choice
        int input = UIUtils.choose(lineWidth, heading, title, folderName, fileName,
                Assets.combatWeapons[combatWeaponCount],
                Assets.defensiveEquipment[defensiveEquipmentCount]);

        Utils.clearConsole();

        // Handle both cases
        if (input == 1) {
            String weapon = Assets.combatWeapons[combatWeaponCount];
            unlockedCombatWeapons.add(weapon);
            combatWeaponCount++;

            Utils.printHeading(false, true, "You unlocked " + weapon + "!");
        } else {
            String weapon = Assets.defensiveEquipment[defensiveEquipmentCount];
            unlockedDefensiveEquipments.add(weapon);
            defensiveEquipmentCount++;

            Utils.printHeading(false, true, "You unlocked " + weapon + "!");
        }

        Utils.pressEnter();
        Utils.clearConsole();
    }
}
