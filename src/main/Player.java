package main;

import java.util.ArrayList;

public class Player extends Character {
    // Integers to store number of skills in each path
    public int combatCount, defensiveCount;
    public ArrayList<String> unlockedCombSkills = new ArrayList<>();
    public ArrayList<String> unlockedDefSkills = new ArrayList<>();

    // Player specific constructor
    public Player(String name) {
        super(name, 100, 0);
        this.combatCount = 0;
        this.defensiveCount = 0;
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
        Utilities.clearConsole();

        Utilities.printHeading(heading);
        Utilities.storyPrinter(lineWidth, heading, folderName, false, fileName);

        Utilities.printHeading(title);
        System.out.println("(1) " + Names.combatSkills[combatCount]);
        System.out.println("(2) " + Names.defensiveSkills[defensiveCount]);

        // Get the player's choice
        int input = Utilities.readPlayerInput("-> ", 2);

        // Handle both cases
        if (input == 1) {
            String skill = Names.combatSkills[combatCount];
            unlockedCombSkills.add(skill);
            combatCount++;

            Utilities.printHeading("You unlocked " + skill + "!");
        } else {
            String skill = Names.defensiveSkills[defensiveCount];
            unlockedDefSkills.add(skill);
            defensiveCount++;

            Utilities.printHeading("You unlocked " + skill + "!");
        }

        Utilities.pressEnter();
        Utilities.clearConsole();
    }
}
