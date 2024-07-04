package main;

import java.util.ArrayList;

public class Player extends GameCharacter {
    // Integers to store number of skills in each path
    public int combatSkillCount, defensiveSkillCount, combatWeaponCount, defensiveEquipmentCount, gold, healers,
            enemiesKilled;

    String location;

    public ArrayList<String> unlockedCombatSkills = new ArrayList<>();
    public ArrayList<String> unlockedDefensiveSkills = new ArrayList<>();

    public ArrayList<String> unlockedCombatWeapons = new ArrayList<>();
    public ArrayList<String> unlockedDefensiveEquipments = new ArrayList<>();

    // Player specific constructor
    public Player(String name) {
        super(name, 100, 0);

        this.combatSkillCount = 0;
        this.defensiveSkillCount = 0;

        this.combatWeaponCount = 0;
        this.defensiveEquipmentCount = 0;

        this.gold = 10;
        this.healers = 0;

        this.enemiesKilled = 0;
        this.location = "Village";
    }

    // Player specific methods
    @Override
    public int attack() {
        return (int) (Math.random() * (xp / 4 + combatSkillCount * 3 + combatWeaponCount * 2 + 3)
                + xp / 10 + combatSkillCount * 2 + defensiveSkillCount + defensiveEquipmentCount + 1);
    }

    @Override
    public int defend() {
        return (int) (Math.random() * (xp / 4 + defensiveSkillCount * 3 + defensiveEquipmentCount * 2 + 3)
                + xp / 10 + defensiveSkillCount * 2 + combatSkillCount + combatWeaponCount + 1);
    }

    // Let the player choose a weapon of either type
    public void chooseWeapon(int lineWidth, String heading, String title, String folderName, String fileName,
            boolean showStats) {
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

        if (showStats) {
            UIUtils.printPlayerInfo();
        }
    }

    // Let the player choose a skill of either path
    public void chooseSkill(int lineWidth, String heading, String title, String folderName, String fileName,
            boolean showStats) {
        // Get the player's choice
        int input = UIUtils.choose(lineWidth, heading, title, folderName, fileName,
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

    // Let the player choose skills for the final mission
    public void chooseFinalSkills(int lineWidth, String heading, String folderName, String fileName,
            boolean showStats) {
        int choiceCount = 5;

        while (choiceCount > 0) {
            // Get all the skills
            int skillCount = Assets.combatSkills.length + Assets.defensiveSkills.length;
            int unlockedSkillCount = unlockedCombatSkills.size() + unlockedDefensiveSkills.size();
            int length = skillCount - unlockedSkillCount;

            String[] skills = new String[length];
            int currentIndex = 0;

            for (int i = 0; i < Assets.combatSkills.length; i++) {
                String skill = Assets.combatSkills[i];

                if (!unlockedCombatSkills.contains(skill)) {
                    skills[currentIndex++] = skill;
                }
            }

            for (int i = 0; i < Assets.defensiveSkills.length; i++) {
                String skill = Assets.defensiveSkills[i];

                if (!unlockedDefensiveSkills.contains(skill)) {
                    skills[currentIndex++] = skill;
                }
            }

            String title = choiceCount == 5 ? "Choose 5 Skills to Master."
                    : choiceCount == 1 ? "Last Choice, Choose Wisely" : choiceCount + " Choices Remaining.";

            // Get the player's choice
            int input = UIUtils.choose(lineWidth, heading, title, folderName, fileName, skills);

            Utils.clearConsole();
            int index;
            String skill;
            if (input % 2 == 0) {
                index = (input - 2) / 2;
                skill = Assets.defensiveSkills[index];
                unlockedDefensiveEquipments.add(skill);
                defensiveSkillCount++;

                Utils.printHeading(false, true, "You unlocked " + skill + "!");
            } else {
                index = (input - 1) / 2;
                skill = Assets.combatSkills[index];
                unlockedCombatSkills.add(skill);
                combatSkillCount++;

                Utils.printHeading(false, true, "You unlocked " + skill + "!");
            }

            choiceCount--;
            Utils.pressEnter();
            Utils.clearConsole();

            if (showStats) {
                UIUtils.printPlayerInfo();
            }
        }
    }
}
