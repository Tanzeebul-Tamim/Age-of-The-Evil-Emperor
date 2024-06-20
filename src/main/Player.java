package main;

public class Player extends Character {
    // Integers to store number of skills in each path
    public int combatCount, defensiveCount;

    // Arrays to store skill names
    public String[] combatSkills = {
            "",
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
            "",
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
        chooseAbility();
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
        System.out.println("(1) " + combatSkills[combatCount + 1]);
        System.out.println("(2) " + defensiveSkills[defensiveCount + 1]);

        // Get the player's choice
        int input = Utilities.readInt("-> ", 2);
        Utilities.clearConsole();

        // Handle both cases
        if (input == 1) {
            Utilities.printHeading("You chose " + combatSkills[combatCount + 1] + "!");
            combatCount++;
        } else {
            Utilities.printHeading("You chose " + defensiveSkills[defensiveCount + 1] + "!");
            defensiveCount++;
        }

        Utilities.pressEnter();
    }
}
