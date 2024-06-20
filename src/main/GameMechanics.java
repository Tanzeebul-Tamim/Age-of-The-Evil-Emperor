package main;

import java.util.Scanner;

//* Game Mechanics

public class GameMechanics {
    static Scanner scanner = new Scanner(System.in);
    static Player player;

    public static boolean isRunning;

    // Method to start the game
    public static void startGame() {
        boolean nameSet = false;
        String name;

        // Print title screen
        Utilities.clearConsole();
        Utilities.printSeparator(40);
        Utilities.printSeparator(30);
        System.out.println("AGE OF THE EVIL EMPEROR");
        System.out.println("TEXT RPG BY - TANZEEBUL TAMIM");
        Utilities.printSeparator(30);
        Utilities.printSeparator(40);

        Utilities.pressEnter();

        // Getting the player name
        do {
            Utilities.clearConsole();
            Utilities.printHeading("What's your name?");
            name = scanner.nextLine().trim();

            // Check if name is empty
            if (name.isEmpty()) {
                Utilities.isEmptyInput("Name");
                continue;
            }

            // Asking the player if he wants to correct his choice
            Utilities.clearConsole();
            Utilities.printHeading("Your name is " + name + "\nDo you want to keep this name?");
            System.out.println("(1) Yes!");
            System.out.println("(2) No, I want to change my name.");
            int input = Utilities.readInt("-> ", 2);
            System.out.println();

            if (input == 1)
                nameSet = true;
        } while (!nameSet);

        // Create new player object with the name
        player = new Player(name);

        // Setting isRunning to true, so the game loop can continue
        isRunning = true;

        // Start main game loop
        gameLoop();
    }

    // Method to continue the journey
    public static void continueJourney() {
        Story.printIntro();
    }

    // Printing out important info of the player character
    public static void characterInfo() {
        System.out.println("DEBUG: Entered characterInfo method.");
        Utilities.clearConsole();
        Utilities.printHeading("CHARACTER INFO");

        System.out.println("NAME: " + player.name);
        System.out.println("HP: " + player.hp + "/" + player.maxHp + "\tXP: " + player.xp);

        System.out.println();

        // Printing the chosen skills
        if (player.combatCount > 0) {
            System.out.println("Combat Skill: " + player.combatSkills[player.combatCount]);
        }

        if (player.defensiveCount > 0) {
            System.out.println("Defensive Skill: " + player.defensiveSkills[player.defensiveCount]);
        }

        Utilities.pressEnter();
    }

    // Printing the main menu
    public static void printMenu() {
        Utilities.clearConsole();
        Utilities.printHeading("MENU");

        System.out.println("Choose an action:");
        Utilities.printSeparator(20);

        System.out.println("(1) Continue on your journey");
        System.out.println("(2) Character Info");
        System.out.println("(3) Exit Game");
    }

    // Main game loop
    public static void gameLoop() {
        while (isRunning) {
            printMenu();
            int input = Utilities.readInt("-> ", 3);

            if (input == 1)
                continueJourney();
            else if (input == 2)
                characterInfo();
            else
                isRunning = false;
        }
    }
}
