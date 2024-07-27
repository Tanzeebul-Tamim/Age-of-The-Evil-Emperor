package main;

import java.util.ArrayList;

public class Player extends GameCharacter {
    // Declaring variables for storing player stats
    public int combatSkillCount, defensiveSkillCount, combatWeaponCount, defensiveEquipmentCount, gold, healers,
            enemiesKilled;

    String location;

    public ArrayList<String> unlockedCombatSkills = new ArrayList<>();
    public ArrayList<String> unlockedDefensiveSkills = new ArrayList<>();

    public ArrayList<String> unlockedCombatWeapons = new ArrayList<>();
    public ArrayList<String> unlockedDefensiveEquipments = new ArrayList<>();

    // Player specific constructor for initializing player stats
    public Player(String name) {
        super(name, 100, 0);

        this.combatSkillCount = 0;
        this.defensiveSkillCount = 0;

        this.combatWeaponCount = 0;
        this.defensiveEquipmentCount = 0;

        this.gold = 10;
        this.healers = 0;

        // Set location (Lunaris village)
        this.location = Assets.locations[0];
        this.enemiesKilled = 0;
    }

    // Player specific methods
    @Override
    public int attack() {
        return (int) (Math.random() * (xp / 5 + combatSkillCount * 3 + combatWeaponCount + 2)
                + xp / 10 + combatSkillCount * 2 + defensiveSkillCount + defensiveEquipmentCount + 1);
    }

    @Override
    public int defend() {
        return (int) (Math.random() * (xp / 5 + defensiveSkillCount * 3 + defensiveEquipmentCount + 2)
                + xp / 10 + defensiveSkillCount * 2 + combatSkillCount + combatWeaponCount + 1);
    }

    // Let the player choose a weapon of either type
    public void chooseWeapon(
            int lineWidth,
            String[] titles,
            String intOut,
            String heading,
            String folderName,
            String fileName,
            boolean showStats) {
        // Get the player's choice
        int input = UIUtils.printChoicesWithHeading(lineWidth, titles, intOut, heading, folderName, fileName,
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

        if (showStats) {
            UIUtils.printPlayerInfo();
        }
    }

    // Let the player choose a skill of either path
    public void chooseSkill(
            int lineWidth,
            String[] titles,
            String intOut,
            String heading,
            String folderName,
            String fileName,
            boolean showStats) {
        // Get the player's choice
        int input = UIUtils.printChoicesWithHeading(lineWidth, titles, intOut, heading, folderName, fileName,
                Assets.combatSkills[combatSkillCount],
                Assets.defensiveSkills[defensiveSkillCount]);

        Utils.clearConsole();

        // Handle both cases
        String skill;
        if (input == 1) {
            skill = Assets.combatSkills[combatSkillCount];
            unlockedCombatSkills.add(skill);
            combatSkillCount++;

            Utils.printHeading(false, true, "You unlocked " + skill + "!");
        } else {
            skill = Assets.defensiveSkills[defensiveSkillCount];
            unlockedDefensiveSkills.add(skill);
            defensiveSkillCount++;

            Utils.printHeading(false, true, "You unlocked " + skill + "!");
        }

        Utils.pressEnter();
        Utils.clearConsole();

        if (showStats) {
            UIUtils.printPlayerInfo();
        }
    }

    // Let the player choose 5 skills as a bonus upgrade for the final mission
    public void chooseFinalSkills(
            int lineWidth,
            String[] titles,
            String intOut,
            String folderName,
            String fileName) {
        Utils.clearConsole();

        // Get all the skills
        int skillCount = Assets.combatSkills.length + Assets.defensiveSkills.length;
        int choiceCount = 5;

        while (choiceCount > 0) {
            int unlockedSkillCount = unlockedCombatSkills.size() + unlockedDefensiveSkills.size();
            int length = skillCount - unlockedSkillCount;

            String[] skills = new String[length];
            int currentIndex = 0;

            for (int i = 0; i < Assets.combatSkills.length; i++) {
                String combatSkill = Assets.combatSkills[i];
                String defensiveSkill = Assets.defensiveSkills[i];

                if (!unlockedCombatSkills.contains(combatSkill)) {
                    skills[currentIndex++] = combatSkill;
                }

                if (!unlockedDefensiveSkills.contains(defensiveSkill)) {
                    skills[currentIndex++] = defensiveSkill;
                }
            }

            String heading = choiceCount == 5 ? "Choose 5 Skills to Master"
                    : choiceCount == 1 ? "Last Choice, Choose Wisely" : choiceCount + " Choices Remaining";

            // Get the player's choice
            int input = UIUtils.printChoices(true, lineWidth, titles, intOut, heading, skills);

            Utils.clearConsole();
            String skill = skills[input - 1];

            if (Utils.checkArr(Assets.defensiveSkills, skill)) {
                unlockedDefensiveSkills.add(skill);
                defensiveSkillCount++;
            } else if (Utils.checkArr(Assets.combatSkills, skill)) {
                unlockedCombatSkills.add(skill);
                combatSkillCount++;
            }
            Utils.printHeading(false, true, "You unlocked " + skill + "!");

            choiceCount--;
            Utils.pressEnter();
            Utils.clearConsole();
        }
        UIUtils.printPlayerInfo();
    }
}
