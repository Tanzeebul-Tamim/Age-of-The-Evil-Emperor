package main;

import java.util.ArrayList;

//* Game Mechanics

public class EventManager {
    public static Player player;

    public static boolean isRunning;
    public static boolean isNewGame = false;

    // Method to start the game
    public static void launchGame() {
        // Print title screen
        UIUtils.printOpening();

        // Get the player name
        String name = UIUtils.setName();

        // Create new player object with the name
        player = new Player(name);

        // Print Story Intro Part I and Part II
        Utils.storyPrinter(true, 100, "Prologue", "Intro", true, "para1.txt", "para2.txt");

        // Update location
        UIUtils.location++;

        // Print Story Intro Part III
        Utils.storyPrinter(true, 100, "Prologue", "Intro", true, "para3.txt");

        // Show initial character info
        UIUtils.printPlayerInfo();

        // Setting isRunning to true, so the game loop can continue
        isRunning = true;

        // Start main game loop
        gameLoop();
    }

    // Main game loop
    public static void gameLoop() {
        while (isRunning) {
            UIUtils.printMenu();
            int input = Utils.readPlayerInput("-> ", 3);

            if (input == 1) {
                // Resetting all the player stats for starting a new game
                if (isNewGame) {
                    UIUtils.location = UIUtils.location != 1 ? 1 : UIUtils.location;
                    player.hp = player.hp != 100 ? 100 : player.hp;
                    player.xp = player.xp != 0 ? 0 : player.xp;
                    player.gold = player.gold != 0 ? 0 : player.gold;
                    player.healers = player.healers != 0 ? 0 : player.healers;

                    if (player.combatCount != 0) {
                        player.combatCount = 0;
                        player.unlockedCombatSkills = new ArrayList<>();
                    }

                    if (player.defensiveCount != 0) {
                        player.defensiveCount = 0;
                        player.unlockedDefensiveSkills = new ArrayList<>();
                    }

                    if (player.combatWeaponCount != 0) {
                        player.combatWeaponCount = 0;
                        player.unlockedCombatWeapons = new ArrayList<>();
                    }

                    if (player.defensiveEquipmentCount != 0) {
                        player.defensiveEquipmentCount = 0;
                        player.unlockedDefensiveEquipments = new ArrayList<>();
                    }
                }

                continueJourney();
            } else if (input == 2)
                UIUtils.printPlayerInfo();
            else
                isRunning = false;
        }
    }

    // Method manage the events sequentially
    public static void continueJourney() {
        // Print Act-I Intro, Part-I
        Utils.storyPrinter(true, 100, "ACT I - INTRO", "firstActIntro", true, "para1.txt", "para2_1.txt");

        // Print Act-I Intro, Part-II
        // (Get the first upgrade and show the character info)
        player.chooseSkill(100, "ACT I - INTRO", "Which skill do you want to learn?", "firstActIntro", "para2_2.txt");
        UIUtils.printPlayerInfo();

        // Print Act-I Intro, Part-III
        Utils.storyPrinter(true, 100, "ACT I - INTRO", "firstActIntro", true, "para3.txt");

        // Print Act-I Outro, Part-I
        Utils.storyPrinter(true, 100, "ACT I - OUTRO", "firstActOutro", true, "para1.txt", "para2.txt", "para3.txt");

        // Update location
        UIUtils.location++;

        // Print Act-I Outro, Part-II
        Utils.storyPrinter(true, 100, "ACT I - OUTRO", "firstActOutro", true, "para4.txt", "para5.txt", "para6_1.txt");

        // Print Act-I Outro, Part-III
        // (Get the first weapon and show the character info)
        player.chooseWeapon(100, "ACT I - OUTRO", "Which Weapon Will You Choose for the Journey?", "firstActOutro",
                "para6_2.txt");
        UIUtils.printPlayerInfo();

        // Print Act-I Outro, Part-IV
        Utils.storyPrinter(true, 100, "ACT I - OUTRO", "firstActOutro", true, "para7.txt");

        // First battle
        boolean[] battle1Result = Actions.randomBattle(100, "ACT I - OUTRO");

        if (battle1Result[0])
            return;

        // Print Act-I Outro, Part-V
        if (!battle1Result[1])
            Utils.storyPrinter(true, 100, "ACT I - OUTRO", "firstActOutro", true, "para8_1.txt", "para9.txt");
        else
            Utils.storyPrinter(true, 100, "ACT I - OUTRO", "firstActOutro", true, "para8_2.txt", "para9.txt");

        // ! This should always be at the bottom. It's for resetting all the player
        // stats for starting a new game
        isNewGame = true;
    }
}
