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
            Utilities.printHeading("Your name is " + name + ".", "Do you want to keep this name?");
            System.out.println("(1) Yes!");
            System.out.println("(2) No, I want to change my name.");
            int input = Utilities.readInt("-> ", 2);
            System.out.println();

            if (input == 1)
                nameSet = true;
        } while (!nameSet);

        // Create new player object with the name
        player = new Player(name);
        characterInfo();

        // Setting isRunning to true, so the game loop can continue
        isRunning = true;

        // Start main game loop
        gameLoop();
    }

    // Method to continue the journey
    public static void continueJourney() {
        // Print Story Intro
        Utilities.storyPrinter(100, "Story", "Intro", "para1.txt", "para2.txt", "para3.txt");

        // Get the first upgrade and show the character info
        player.chooseAbility();
        characterInfo();

        Utilities.storyPrinter(100, "ACT I - INTRO", "firstActIntro", "para1.txt", "para2.txt", "para3.txt");
    }

    // Printing out important info of the player character
    public static void characterInfo() {
        Utilities.clearConsole();
        Utilities.printHeading("CHARACTER INFO");

        System.out.println("NAME: " + player.name);
        System.out.println("HP: " + player.hp + "/" + player.maxHp);
        System.out.println("XP: " + player.xp);

        System.out.println();

        // Printing the unlocked skills
        Utilities.printHeading("Unlocked Skills");


        System.out.print("Combat Skills: ");
        if (player.unlockedCombSkills.size() == 0) {
            System.out.print("None\n");
        } else {
            for (int i = 0; i < player.unlockedCombSkills.size(); i++) { 
                String skill = player.unlockedCombSkills.get(i);

                if (i == player.unlockedCombSkills.size() - 1) {
                    System.out.print(skill + "\n");
                } else {
                    System.out.print(skill + ", ");
                }
            }
        }

        System.out.print("Defensive Skills: ");
        if (player.unlockedDefSkills.size() == 0) {
            System.out.print("None\n");
        } else {
            for (int i = 0; i < player.unlockedDefSkills.size(); i++) {
                String skill = player.unlockedDefSkills.get(i);

                if (i == player.unlockedCombSkills.size() - 1) {
                    System.out.print(skill + "\n");
                } else {
                    System.out.print(skill + ", ");
                }
            }
        }

        System.out.println();

        Utilities.pressEnter();
    }

    // Printing the main menu
    public static void printMenu() {
        Utilities.clearConsole();
        Utilities.printHeading("Menu");
        System.out.println("Choose an action:\n");

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