package main;

import java.util.ArrayList;

//* Game Mechanics

public class EventManager {
    public static Player player;

    public static boolean isRunning;
    public static boolean isNewGame = false;

    // Method to start the game
    public static void launchGame() {
        // Get the player name
        String name = Actions.setName();

        // Print title screen
        Utils.clearConsole();

        Utils.printSeparator(40);
        Utils.printSeparator(30);

        System.out.println("AGE OF THE EVIL EMPEROR");
        System.out.println("TEXT RPG BY - TANZEEBUL TAMIM");

        Utils.printSeparator(30);
        Utils.printSeparator(40);

        Utils.pressEnter();

        // Create new player object with the name
        player = new Player(name);

        // Print Story Intro Part I and Part II
        Utils.storyPrinter(true, 100, "Prologue", "Intro", true, "para1.txt", "para2.txt");

        // Update location
        Actions.location++;

        // Print Story Intro Part III
        Utils.storyPrinter(true, 100, "Prologue", "Intro", true, "para3.txt");

        // Show initial character info
        Actions.printPlayerInfo();

        // Setting isRunning to true, so the game loop can continue
        isRunning = true;

        // Start main game loop
        gameLoop();
    }

    // Main game loop
    public static void gameLoop() {
        while (isRunning) {
            Actions.printMenu();
            int input = Utils.readPlayerInput("-> ", 3);

            if (input == 1) {
                // Resetting all the player stats for starting a new game
                if (isNewGame) {
                    Actions.location = Actions.location != 0 ? 0 : Actions.location;
                    player.xp = player.xp != 0 ? 0 : player.xp;
                    player.hp = player.hp != 100 ? 100 : player.hp;
                    player.healers = player.healers != 0 ? 0 : player.healers;
                    player.gold = player.gold != 0 ? 0 : player.gold;

                    if (player.combatCount != 0) {
                        player.combatCount = 0;
                        player.unlockedCombSkills = new ArrayList<>();
                    }

                    if (player.defensiveCount != 0) {
                        player.defensiveCount = 0;
                        player.unlockedDefSkills = new ArrayList<>();
                    }

                    if (player.weaponCount != 0) {
                        player.weaponCount = 0;
                        player.unlockedWeapons = new ArrayList<>();
                    }
                }

                continueJourney();
            } else if (input == 2)
                Actions.printPlayerInfo();
            else
                isRunning = false;
        }
    }

    // Method manage the events sequentially
    public static void continueJourney() {
        // Print Act-I Intro, Part-I
        Utils.storyPrinter(true, 100, "ACT I - INTRO", "firstActIntro", true, "para1.txt");

        // Print Act-I Intro, Part-II
        // (Get the first upgrade and show the character info)
        player.chooseAbility(100, "ACT I - INTRO", "Which skill do you want to learn?", "firstActIntro", "para2.txt");
        Actions.printPlayerInfo();

        // Print Act-I Intro, Part-III
        Utils.storyPrinter(true, 100, "ACT I - INTRO", "firstActIntro", true, "para3.txt");

        // Print Act-I Outro, Part-I
        Utils.storyPrinter(true, 100, "ACT I - OUTRO", "firstActOutro", true, "para1.txt", "para2.txt",
                "para3.txt");

        // Update location
        Actions.location++;

        // Print Act-I Outro, Part-II
        Utils.storyPrinter(true, 100, "ACT I - OUTRO", "firstActOutro", true, "para4.txt", "para5.txt");

        // First battle
        boolean[] battle1Result = Actions.randomBattle(100, "ACT I - OUTRO");

        if (battle1Result[0])
            return;

        // Print Act-I Outro, Part-III
        if (battle1Result[1])
            Utils.storyPrinter(true, 100, "ACT I - OUTRO", "firstActOutro", true, "para6_2.txt", "para7.txt");
        else
            Utils.storyPrinter(true, 100, "ACT I - OUTRO", "firstActOutro", true, "para6_1.txt", "para7.txt");

        //! This should always be at the bottom. It's for resetting all the player stats for starting a new game
        isNewGame = true;
    }
}
