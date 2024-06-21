package main;

import java.util.ArrayList;
import java.util.Scanner;

//* Game Mechanics

public class GameEngine {
    static Scanner scanner = new Scanner(System.in);
    public static Player player;

    public static boolean isRunning;
    public static boolean isNewGame = false;

    // Method to start the game
    public static void setName() {
        boolean nameSet = false;
        String name;

        // Print title screen
        UtilityMethods.clearConsole();

        UtilityMethods.printSeparator(40);
        UtilityMethods.printSeparator(30);

        System.out.println("AGE OF THE EVIL EMPEROR");
        System.out.println("TEXT RPG BY - TANZEEBUL TAMIM");

        UtilityMethods.printSeparator(30);
        UtilityMethods.printSeparator(40);

        UtilityMethods.pressEnter();

        // Getting the player name
        do {
            UtilityMethods.clearConsole();
            UtilityMethods.printHeading(true, "What's your name?");
            name = scanner.nextLine().trim();

            // Check if name is empty
            if (name.isEmpty()) {
                UtilityMethods.isEmptyInput("Name");
                continue;
            }

            // Asking the player if he wants to correct his choice
            UtilityMethods.clearConsole();
            UtilityMethods.printHeading(true, "Your name is " + name + ".", "Do you want to keep this name?");
            System.out.println("(1) Yes!");
            System.out.println("(2) No, I want to change my name.");
            int input = UtilityMethods.readPlayerInput("-> ", 2);
            System.out.println();

            if (input == 1)
                nameSet = true;
        } while (!nameSet);

        // Create new player object with the name
        player = new Player(name);

        // Print Story Intro Part I and Part II
        UtilityMethods.storyPrinter(true, 100, "Prologue", "Intro", true, "para1.txt", "para2.txt");

        // Update location
        GameLogic.location++;

        // Print Story Intro Part III
        UtilityMethods.storyPrinter(true, 100, "Prologue", "Intro", true, "para3.txt");

        // Show initial character info
        GameLogic.showPlayerInfo();

        // Setting isRunning to true, so the game loop can continue
        isRunning = true;

        // Start main game loop
        gameLoop();
    }

    // Method to continue the journey
    public static void continueJourney() {
        // Print Act-I Intro, Part-I
        UtilityMethods.storyPrinter(true, 100, "ACT I - INTRO", "firstActIntro", true, "para1.txt");

        // Print Act-I Intro, Part-II
        // (Get the first upgrade and show the character info)
        player.chooseAbility(100, "ACT I - INTRO", "Which skill do you want to learn?", "firstActIntro", "para2.txt");
        GameLogic.showPlayerInfo();

        // Print Act-I Intro, Part-III
        UtilityMethods.storyPrinter(true, 100, "ACT I - INTRO", "firstActIntro", true, "para3.txt");

        // Print Act-I Outro, Part-I
        UtilityMethods.storyPrinter(true, 100, "ACT I - OUTRO", "firstActOutro", true, "para1.txt", "para2.txt",
                "para3.txt");

        // Update location
        GameLogic.location++;

        // Print Act-I Outro, Part-II
        UtilityMethods.storyPrinter(true, 100, "ACT I - OUTRO", "firstActOutro", true, "para4.txt", "para5.txt");

        // First battle
        boolean[] battle1Result = GameLogic.randomBattle(100, "ACT I - OUTRO");

        if (battle1Result[0])
            return;

        // Print Act-I Outro, Part-III
        if (battle1Result[1])
            UtilityMethods.storyPrinter(true, 100, "ACT I - OUTRO", "firstActOutro", true, "para6_2.txt", "para7.txt");
        else
            UtilityMethods.storyPrinter(true, 100, "ACT I - OUTRO", "firstActOutro", true, "para6_1.txt", "para7.txt");

        // Resetting all the player stats for starting a new game
        isNewGame = true;
    }

    // Main game loop
    public static void gameLoop() {
        while (isRunning) {
            GameLogic.printMenu();
            int input = UtilityMethods.readPlayerInput("-> ", 3);

            if (input == 1) {
                // Resetting all the player stats for starting a new game
                if (isNewGame) {
                    GameLogic.location = GameLogic.location != 0 ? 0 : GameLogic.location;
                    player.xp = player.xp != 0 ? 0 : player.xp;
                    player.hp = player.hp != 100 ? 100 : player.hp;

                    if (player.combatCount != 0) {
                        player.combatCount = 0;
                        player.unlockedCombSkills = new ArrayList<>();
                    }

                    if (player.defensiveCount != 0) {
                        player.defensiveCount = 0;
                        player.unlockedDefSkills = new ArrayList<>();
                    }
                }

                continueJourney();
            } else if (input == 2)
                GameLogic.showPlayerInfo();
            else
                isRunning = false;
        }
    }
}
