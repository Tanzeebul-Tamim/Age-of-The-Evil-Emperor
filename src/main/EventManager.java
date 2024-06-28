package main;

import java.util.ArrayList;

//* Game Mechanics

public class EventManager {
    public static Player player;

    public static boolean isRunning;
    static int paraSeparator = 100;
    public static boolean isNewGame = false;

    // Method to start the game
    public static void launchGame(boolean firstTime) {
        if (firstTime) {
            // Print title screen
            UIUtils.printOpening();

            // Get the player name
            String name = UIUtils.setName();

            // Create new player object with the name
            player = new Player(name);
        }

        // ! Intro
        // Print Story Intro Part I and Part II
        Utils.storyPrinter(true, paraSeparator, "Prologue", "Intro", true, "para1.txt", "para2.txt");

        // Update location (Hidden Cavern) 1
        UIUtils.location++;

        // Print Story Intro Part III
        Utils.storyPrinter(true, paraSeparator, "Prologue", "Intro", true, "para3.txt");

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
            // Print the game menu
            UIUtils.printMenu();
            int input = Utils.readPlayerInput("-> ", 3);

            if (input == 1) {
                // Resetting all the player stats for starting a new game
                if (isNewGame) {
                    UIUtils.location = 0;
                    player.hp = player.maxHp;
                    player.xp = 0;
                    player.gold = 5;
                    player.healers = 0;
                    player.enemiesKilled = 0;

                    player.combatCount = 0;
                    player.unlockedCombatSkills = new ArrayList<>();

                    player.defensiveCount = 0;
                    player.unlockedDefensiveSkills = new ArrayList<>();

                    player.combatWeaponCount = 0;
                    player.unlockedCombatWeapons = new ArrayList<>();

                    player.defensiveEquipmentCount = 0;
                    player.unlockedDefensiveEquipments = new ArrayList<>();

                    isNewGame = false;
                    launchGame(false);
                } else {
                    continueJourney();
                }
            } else if (input == 2)
                UIUtils.printPlayerInfo();
            else
                isRunning = false;
        }
    }

    // Method manage the events sequentially
    public static void continueJourney() {
        // ! Act-I Intro
        // Print Act-I Intro, Part-I
        Utils.storyPrinter(true, paraSeparator, "ACT I - INTRO", "firstActIntro", true, "para1.txt", "para2_1.txt");

        // Print Act-I Intro, Part-II
        // (Get the first upgrade and show the character info)
        player.chooseSkill(paraSeparator, "ACT I - INTRO", "Which skill do you want to learn?", "firstActIntro",
                "para2_2.txt");

        UIUtils.printPlayerInfo();

        // Print Act-I Intro, Part-III
        Utils.storyPrinter(true, paraSeparator, "ACT I - INTRO", "firstActIntro", true, "para3.txt");

        // Update location (Hideout Cavern) 2
        UIUtils.location++;

        // ! Act-I Outro
        // Print Act-I Outro, Part-I
        Utils.storyPrinter(true, paraSeparator, "ACT I - OUTRO", "firstActOutro", true, "para1.txt", "para2.txt",
                "para3.txt");

        // Update location (Nearby village) 3
        UIUtils.location++;

        // Print Act-I Outro, Part-II
        Utils.storyPrinter(true, paraSeparator, "ACT I - OUTRO", "firstActOutro", true, "para4.txt", "para5.txt",
                "para6_1.txt");

        // Print Act-I Outro, Part-III
        // (Get the first weapon and show the character info)
        player.chooseWeapon(paraSeparator, "ACT I - OUTRO", "Which Weapon Will You Choose for the Journey?",
                "firstActOutro",
                "para6_2.txt");
        UIUtils.printPlayerInfo();

        // Print Act-I Outro, Part-IV
        Utils.storyPrinter(true, paraSeparator, "ACT I - OUTRO", "firstActOutro", true, "para7.txt");

        // * First Battle
        boolean[] battle1Result = Actions.randomBattle(paraSeparator, "ACT I - OUTRO - BATTLE");

        if (battle1Result[0])
            return;

        // Print Act-I Outro, Part-V
        if (!battle1Result[1]) // If the player win the battle
            Utils.storyPrinter(true, paraSeparator, "ACT I - OUTRO", "firstActOutro", true, "para8_1.txt",
                    "para9.txt");
        else // If the player has been fled from the battle
            Utils.storyPrinter(true, paraSeparator, "ACT I - OUTRO", "firstActOutro", true, "para8_2.txt",
                    "para9.txt");

        // Update location (Hideout Cavern) 4
        UIUtils.location++;

        // ! Act-II Intro
        if (!battle1Result[1]) // If the player win the battle
            Utils.storyPrinter(true, paraSeparator, "ACT II - INTRO", "secondActIntro/if_won", true, "para1.txt",
                    "para2.txt", "para3.txt", "para4.txt", "para5.txt", "para6.txt", "para7.txt", "para8.txt");
        else // If the player has been fled from the battle
            Utils.storyPrinter(true, paraSeparator, "ACT II - INTRO", "secondActIntro/if_fled", true, "para1.txt",
                    "para2.txt", "para3.txt", "para4.txt", "para5.txt", "para6.txt", "para7.txt", "para8.txt");

        // Choosing strategy
        String choice1 = "Lead the covert mission through the tunnels.";
        String choice2 = "Rally support from the villages.";
        int choice = UIUtils.choose(paraSeparator, "ACT II - INTRO", "Decide wisely!", "secondActIntro", "para1.txt",
                choice1, choice2);

        // ! Act-II Outro
        if (choice == 1) { // Player chose the tunnel mission
            // Update location (Secret Tunnels) 5
            UIUtils.location++;

            // Print Act-II Outro, Part-I
            Utils.storyPrinter(true, paraSeparator, "ACT II - OUTRO", "secondActOutro/tunnel", true, "para1.txt",
                    "para2.txt", "para3.txt", "para4.txt", "para5.txt", "para6.txt");

            // * Second Battle
            boolean[] battle2Result = Actions.battle(paraSeparator, "ACT II - OUTRO - BATTLE", "Cave Bandit");

            if (battle2Result[0])
                return;

            // Print Act-II Outro, Part-II
            if (!battle2Result[1]) {// If the player win the battle
                Utils.storyPrinter(true, paraSeparator, "ACT II - OUTRO", "secondActOutro/tunnel/if_won", true,
                        "para1.txt", "para2.txt", "para3.txt", "para4.txt");

                // Update location (The Evil Emperor's Fortress) 6
                UIUtils.location++;

                Utils.storyPrinter(true, paraSeparator, "ACT II - OUTRO", "secondActOutro/tunnel/if_won", true,
                        "para5.txt");

                // Update location (Hideout Cavern) 7
                UIUtils.location++;

                Utils.storyPrinter(true, paraSeparator, "ACT II - OUTRO", "secondActOutro/tunnel/if_won", true,
                        "para6.txt");
            } else { // If the player has been fled from the battle
                Utils.storyPrinter(true, paraSeparator, "ACT II - OUTRO", "secondActOutro/tunnel/if_fled", true,
                        "para1.txt", "para2.txt", "para3.txt", "para4.txt", "para5.txt");

                Utils.storyPrinter(false, paraSeparator, "ACT II - OUTRO", "secondActOutro/tunnel/if_fled", true,
                        "para6.txt");
            }
        } else { // player chose the village mission
            // Update location (Enchanted Woods) 8
            UIUtils.location += 4;

            Utils.storyPrinter(true, paraSeparator, "ACT II - OUTRO", "secondActOutro/village", true,
                    "para1.txt");

            Actions.randomEncounter("ACT II - OUTRO", "secondActOutro/village", "para6_2.txt", "para6_1.txt");

            // Update location (Nearby Village) 9
            UIUtils.location++;

            Utils.storyPrinter(true, paraSeparator, "ACT II - OUTRO", "secondActOutro/village", true,
                    "para2.txt", "para3.txt");

            // Update location (Enchanted Woods) 10
            UIUtils.location++;

            // * Second Battle
            boolean[] battle2Result = Actions.battle(paraSeparator, "ACT II - OUTRO - BATTLE", "Evil Emperor's Guard");

            if (battle2Result[0])
                return;

            // Update location (Hideout Cavern) 11
            UIUtils.location++;

            // Print Act-II Outro, Part-II
            if (!battle2Result[1]) {// If the player win the battle
                Utils.storyPrinter(true, paraSeparator, "ACT II - OUTRO", "secondActOutro/village/if_won", true,
                        "para1.txt", "para2.txt", "para3.txt");
            } else { // If the player has been fled from the battle
                Utils.storyPrinter(true, paraSeparator, "ACT II - OUTRO", "secondActOutro/village/if_fled", true,
                        "para1.txt", "para2.txt", "para3.txt", "para4.txt", "para5.txt");
            }
        }

        // Todo Print make final battle
        // Actions.finalBattle(paraSeparator, "end");

        // Todo Print Outro After final battle
        // Utils.storyPrinter(false, paraSeparator, "Epilogue", "outro", true,
                // "para1.txt", "para2.txt", "para3.txt", "para4.txt", "para5.txt");

        // Todo Print ending screen
        // UIUtils.printCompletionMessage();

        // ! This should always be at the bottom. It's for resetting all the player
        // stats for starting a new game
        isNewGame = true;
    }
}
