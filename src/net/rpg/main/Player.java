package net.rpg.main;

public class Player extends Character {
    // Integers to store number of upgrades/skills in each path
    public int numAtkUpgrades, numDefUpgrades;

    // Arrays to store skill names
    public String[] atkUpgrades = {"Strength", "Power", "Might", "Godlike Strength"};
    public String[] defUpgrades = {"Heavy Bones", "Stone Skin", "Scale Armor", "Holy Aura"};
    
    // Player specific constructor
    public Player(String name) {
        super(name, 100, 0);
        this.numAtkUpgrades = 0;
        this.numDefUpgrades = 0;
        chooseTrait();
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

    // Let the player choose a trait of either skill path
    public void chooseTrait() {
        GameLogic.clearConsole();
        GameLogic.printHeading("Choose a trait:"); 
        System.out.println("(1) " + atkUpgrades[numAtkUpgrades]); 
        System.out.println("(2) " + defUpgrades[numDefUpgrades]); 

        // Get the player's choice
        int input = GameLogic.readInt("-> ", 2);
        GameLogic.clearConsole();

        // Handle both cases
        if (input == 1) {
            GameLogic.printHeading("You chose " + atkUpgrades[numAtkUpgrades] + "!");
            numAtkUpgrades++;
        } else {
            GameLogic.printHeading("You chose " + defUpgrades[numDefUpgrades] + "!");
            numDefUpgrades++;
        }

        GameLogic.anythingToContinue();
    }
}
