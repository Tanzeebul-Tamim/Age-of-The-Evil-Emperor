package main;

import java.util.ArrayList;

public class Player extends Character {
    // Integers to store number of skills in each path
    public int combatCount, defensiveCount;
    public ArrayList<String> unlockedCombSkills = new ArrayList<>();
    public ArrayList<String> unlockedDefSkills = new ArrayList<>();

    // Arrays to store skill names
    public String[] combatSkills = {
            "Swift Strike",
            "Precision Shot",
            "Lunge and Parry",
            "Shield Slam",
            "Dual Blade Technique",
            "Fire Arrow Volley",
            "Frost Blade",
            "Thunderclap",
            "Berserker Charge",
            "Lightning Blade"
    };

    public String[] defensiveSkills = {
            "Evasion",
            "Counter-Parry",
            "Deflect",
            "Riposte",
            "Healing Poultice",
            "Shield Wall",
            "Stamina Regeneration",
            "Guard Stance",
            "Armored Resilience",
            "Ironclad Defense"
    };

    // Player specific constructor
    public Player(String name) {
        super(name, 100, 0);
        this.combatCount = 0;
        this.defensiveCount = 0;
    }

    // Player specific methods
    @Override
    public int attack() {
        return 0;
    }

    @Override
    public int defend() {
        return 0;
    }

    // Let the player choose a skill of either path
    public void chooseAbility(int lineWidth, String heading, String title, String folderName, String fileName) {
        Utilities.clearConsole();

        Utilities.printHeading(heading);
        Utilities.storyPrinter(lineWidth, heading, folderName, false, fileName);

        Utilities.printHeading(title);
        System.out.println("(1) " + combatSkills[combatCount]);
        System.out.println("(2) " + defensiveSkills[defensiveCount]);

        // Get the player's choice
        int input = Utilities.readInt("-> ", 2);

        // Handle both cases
        if (input == 1) {
            String skill = combatSkills[combatCount];
            unlockedCombSkills.add(skill);
            combatCount++;

            Utilities.printHeading("You unlocked " + skill + "!");
        } else {
            String skill = defensiveSkills[defensiveCount];
            unlockedDefSkills.add(skill);
            defensiveCount++;

            Utilities.printHeading("You unlocked " + skill + "!");
        }

        Utilities.pressEnter();
        Utilities.clearConsole();
    }
}
