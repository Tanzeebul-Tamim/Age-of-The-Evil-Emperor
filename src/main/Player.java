package main;

import java.util.ArrayList;

public class Player extends Character {
    // Integers to store number of skills in each path
    public int combatCount, defensiveCount;
    public ArrayList<String> unlockedCombSkills = new ArrayList<>();
    public ArrayList<String> unlockedDefSkills = new ArrayList<>();

    // Arrays to store skill names
    public String[] combatSkills = {
            "Rapid Shot",
            "Arrow Barrage",
            "Magic Missile",
            "Sword Slash",
            "Flaming Fist",
            "Fireball",
            "Ice Spear",
            "Thunder Strike",
            "Assault Charge",
            "Lightning Bolt"
    };

    public String[] defensiveSkills = {
            "Dodge",
            "Counterattack",
            "Evasion",
            "Reflective Shield",
            "Healing Potion",
            "Barrier",
            "Regeneration",
            "Shield Block",
            "Stone Skin",
            "Iron Defense"
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
    public void chooseAbility() {
        Utilities.clearConsole();
        Utilities.printHeading("Choose an ability:");
        System.out.println("(1) " + combatSkills[combatCount]);
        System.out.println("(2) " + defensiveSkills[defensiveCount]);

        // Get the player's choice
        int input = Utilities.readInt("-> ", 2);
        Utilities.clearConsole();

        // Handle both cases
        if (input == 1) {
            String skill = combatSkills[combatCount];
            unlockedCombSkills.add(skill);
            combatCount++;

            Utilities.printHeading("You chose " + skill + "!");
        } else {
            String skill = defensiveSkills[defensiveCount];
            unlockedDefSkills.add(skill);
            defensiveCount++;

            Utilities.printHeading("You chose " + skill + "!");
        }

        Utilities.pressEnter();
    }
}
