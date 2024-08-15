package main;

import java.util.ArrayList;

//* Game Mechanics

public class EventManager {
        static Player player;

        static boolean isRunning;
        static int paraSeparator = 100;
        static boolean isNewGame = false;
        static String[] locations = Assets.locations;

        // Story titles for each chapters of the game
        static String[][] actsAndTitles = Assets.actsAndTitles;

        // Storing Titles of each Acts of the game
        static String prologue = actsAndTitles[0][0];
        static String[] actI = actsAndTitles[1];
        static String[] actII = actsAndTitles[2];
        static String[] actIII = actsAndTitles[3];
        static String[] actIV = actsAndTitles[4];
        static String epilogue = actsAndTitles[5][0];

        // Variable for storing battle outcomes
        static boolean[] battleResult;

        // Method to start the game
        public static void launchGame(boolean firstTime) {
                if (firstTime) {
                        // Print title screen
                        UIUtils.printOpening();

                        // Get the player name
                        String name = UIUtils.setName();

                        // Create new player object with the name
                        player = new Player(name);
                        // player = new Player("Tamim");

                        // Manage the final battle
                        // battleResult = Actions.finalBattle(
                        //                 paraSeparator,
                        //                 actIV,
                        //                 "i");

                        // if (battleResult[0])
                        //         return;

                        // // Print Outro After the final battle
                        // Utils.storyPrinter(
                        //                 false,
                        //                 paraSeparator,
                        //                 epilogue,
                        //                 "outro",
                        //                 true,
                        //                 "1", "2", "3", "4", "5");

                        // // Print ending screen
                        // UIUtils.printCompletionMessage();
                }

                // ! ---------Intro---------
                // Print Story Intro Part I and Part II
                Utils.storyPrinter(
                                true,
                                paraSeparator,
                                prologue,
                                "intro",
                                true,
                                "1", "2");

                // Update location (hidden cave)
                player.location = locations[1];

                // Print Story Intro Part III
                Utils.storyPrinter(
                                true,
                                paraSeparator,
                                prologue,
                                "intro",
                                true,
                                "3");

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
                                        // Reset location (Lunaris village)
                                        player.location = locations[0];
                                        player.hp = player.maxHp;
                                        player.xp = 0;
                                        player.gold = 5;
                                        player.healers = 0;
                                        player.enemiesKilled = 0;

                                        player.combatSkillCount = 0;
                                        player.unlockedCombatSkills = new ArrayList<>();

                                        player.defensiveSkillCount = 0;
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
                // ! ---------Act-I Intro---------
                // Print Act-I Intro, Part-I
                Utils.storyPrinter(
                                true,
                                paraSeparator,
                                actI,
                                "i",
                                "firstActIntro",
                                true,
                                "1", "2_1");

                // Print Act-I Intro, Part-II
                // (Get the first skill upgrade)
                player.chooseSkill(
                                paraSeparator,
                                actI,
                                "i",
                                "Which skill do you want to learn?",
                                "firstActIntro",
                                "2_2",
                                true);

                // Print Act-I Intro, Part-III
                Utils.storyPrinter(
                                true,
                                paraSeparator,
                                actI,
                                "i",
                                "firstActIntro",
                                true,
                                "3");

                // Update location (hideout cavern)
                player.location = locations[2];

                // ! ---------Act-I Outro---------
                // Print Act-I Outro, Part-I
                Utils.storyPrinter(
                                true,
                                paraSeparator,
                                actI,
                                "o",
                                "firstActOutro",
                                true,
                                "1", "2", "3");

                // Update location (Thornwood village)
                player.location = locations[3];

                // Print Act-I Outro, Part-II
                Utils.storyPrinter(
                                true,
                                paraSeparator,
                                actI,
                                "o",
                                "firstActOutro",
                                true,
                                "4", "5", "6_1");

                // Print Act-I Outro, Part-III
                // (Get the first weapon upgrade)
                player.chooseWeapon(
                                paraSeparator,
                                actI,
                                "o",
                                "Which Weapon Will You Choose for the Journey?",
                                "firstActOutro",
                                "6_2",
                                true);

                // Print Act-I Outro, Part-IV
                Utils.storyPrinter(
                                true,
                                paraSeparator,
                                actI,
                                "o",
                                "firstActOutro",
                                true,
                                "7");

                // * First Battle
                battleResult = Actions.randomBattle(paraSeparator, actI, "o");

                if (battleResult[0])
                        return;

                // Print Act-I Outro, Part-V
                if (!battleResult[1]) // If the player win the battle
                        Utils.storyPrinter(
                                        true,
                                        paraSeparator,
                                        actI,
                                        "o",
                                        "firstActOutro",
                                        true,
                                        "8_1", "9");
                else // If the player has been fled from the battle
                        Utils.storyPrinter(
                                        true,
                                        paraSeparator,
                                        actI,
                                        "o",
                                        "firstActOutro",
                                        true,
                                        "8_2", "9");

                // Update location (hideout cavern)
                player.location = locations[2];

                // ! ---------Act-II Intro---------
                if (!battleResult[1]) // If the player win the battle
                        Utils.storyPrinter(
                                        true,
                                        paraSeparator,
                                        actII,
                                        "i",

                                        "secondActIntro/if_won",
                                        true,
                                        "1", "2", "3", "4", "5", "6", "7", "8");
                else // If the player has been fled from the battle
                        Utils.storyPrinter(
                                        true,
                                        paraSeparator,
                                        actII,
                                        "i",
                                        "secondActIntro/if_fled",
                                        true,
                                        "1", "2", "3", "4", "5", "6", "7", "8");

                // Choosing strategy
                String choice1 = "Lead the covert mission through the tunnels.";
                String choice2 = "Rally support from the Willowdale village.";
                int choice = UIUtils.printChoicesWithHeading(
                                paraSeparator,
                                actII,
                                "i", "Decide wisely!",
                                "secondActIntro",
                                "1",
                                choice1,
                                choice2);

                // (Get the second skill upgrade)
                player.chooseSkill(
                                paraSeparator,
                                actII,
                                "i",
                                "Which skill do you want to learn this time?",
                                "secondActIntro",
                                "2",
                                false);

                // (Get the second weapon upgrade)
                player.chooseWeapon(
                                paraSeparator,
                                actII,
                                "i",
                                "Which Weapon Will You Choose for the Journey?",
                                "secondActIntro",
                                "3",
                                true);

                if (choice == 1) { // Player chose the tunnel mission
                        // Update location (secret tunnel)
                        player.location = locations[4];

                        // Print Act-II Outro, Part-I
                        Utils.storyPrinter(
                                        true,
                                        paraSeparator,
                                        actII,
                                        "i",
                                        "secondActOutro/tunnel",
                                        true,
                                        "1", "2", "3", "4", "5", "6");

                        // * Second Battle
                        battleResult = Actions.battle(
                                        paraSeparator,
                                        actII,
                                        "i",
                                        "Cave Bandit");

                        Utils.clearConsole();

                        battleResult = Actions.battle(
                                        paraSeparator,
                                        actII,
                                        "i",
                                        "Cave Bandit Leader");

                        if (battleResult[0])
                                return;

                        // ! ---------Act-II Outro---------

                        // Print Act-II Outro, Part-II
                        if (!battleResult[1]) {// If the player win the battle
                                Utils.storyPrinter(
                                                true,
                                                paraSeparator,
                                                actII,
                                                "i",
                                                "secondActOutro/tunnel/if_won",
                                                true,
                                                "1", "2", "3", "4");

                                // Update location (The Evil Emperor's Fortress)
                                player.location = locations[5];

                                Utils.storyPrinter(
                                                true,
                                                paraSeparator,
                                                actII,
                                                "i",
                                                "secondActOutro/tunnel/if_won",
                                                true,
                                                "5");

                                // Update location (hideout cavern)
                                player.location = locations[2];

                                Utils.storyPrinter(
                                                true,
                                                paraSeparator,
                                                actII,
                                                "o",
                                                "secondActOutro/tunnel/if_won",
                                                true,
                                                "6");

                        } else { // If the player has been fled from the battle
                                Utils.storyPrinter(
                                                true,
                                                paraSeparator,
                                                actII,
                                                "i",
                                                "secondActOutro/tunnel/if_fled",
                                                true,
                                                "1", "2", "3", "4", "5");

                                Utils.storyPrinter(
                                                false,
                                                paraSeparator,
                                                actII,
                                                "o",
                                                "secondActOutro/tunnel/if_fled",
                                                true,
                                                "6");

                                // Update location (hideout cavern)
                                player.location = locations[2];
                        }
                } else { // player chose the village mission
                         // Update location (enchanted Woods)
                        player.location = locations[6];

                        Utils.storyPrinter(
                                        true,
                                        paraSeparator,
                                        actII,
                                        "i",
                                        "secondActOutro/village",
                                        true,
                                        "1");

                        Actions.randomEncounter(
                                        actII,
                                        "i",
                                        "secondActOutro/village",
                                        "4_2",
                                        "4_1");

                        // Update location (Willowdale village)
                        player.location = locations[7];

                        Utils.storyPrinter(
                                        true,
                                        paraSeparator,
                                        actII,
                                        "i",
                                        "secondActOutro/village",
                                        true,
                                        "2");

                        // Update location (enchanted Woods)
                        player.location = locations[6];

                        Utils.storyPrinter(
                                        true,
                                        paraSeparator,
                                        actII,
                                        "i",
                                        "secondActOutro/village",
                                        true,
                                        "3");

                        // * Second Battle
                        battleResult = Actions.battle(
                                        paraSeparator,
                                        actII,
                                        "i",
                                        "Evil Emperor's Guard");

                        if (battleResult[0])
                                return;

                        Utils.clearConsole();

                        battleResult = Actions.battle(
                                        paraSeparator,
                                        actII,
                                        "i",
                                        "Evil Emperor's Guard Commander");

                        if (battleResult[0])
                                return;

                        // ! ---------Act-II Outro---------

                        // Update location (hideout cavern)
                        player.location = locations[2];

                        // Print Act-II Outro, Part-II
                        if (!battleResult[1]) {// If the player win the battle
                                Utils.storyPrinter(
                                                true,
                                                paraSeparator,
                                                actII,
                                                "o",
                                                "secondActOutro/village/if_won",
                                                true,
                                                "1", "2", "3");
                        } else { // If the player has been fled from the battle
                                Utils.storyPrinter(
                                                true,
                                                paraSeparator,
                                                actII,
                                                "o",
                                                "secondActOutro/village/if_fled",
                                                true,
                                                "1", "2", "3", "4", "5");
                        }
                }

                // ! ---------Act-III Intro---------
                // Print Act-III Intro
                Utils.storyPrinter(
                                true,
                                paraSeparator,
                                actIII,
                                "i",
                                "thirdActIntro",
                                true,
                                "1");

                if (choice == 1) { // Player chose the tunnel mission first, will now play the village mission
                        if (!battleResult[1]) {// If the player had won the previous mission battle
                                Utils.storyPrinter(
                                                true,
                                                paraSeparator,
                                                actIII,
                                                "i",
                                                "thirdActIntro/tunnel/if_won",
                                                true,
                                                "1");
                        } else { // If the player had fled from the previous mission battle
                                Utils.storyPrinter(
                                                true,
                                                paraSeparator,
                                                actIII,
                                                "i",
                                                "thirdActIntro/tunnel/if_fled",
                                                true,
                                                "1");
                        }

                        // Village mission starts

                        // Update location (enchanted Woods)
                        player.location = locations[6];

                        Utils.storyPrinter(
                                        true,
                                        paraSeparator,
                                        actIII,
                                        "i",
                                        "secondActOutro/village",
                                        true,
                                        "1");

                        Actions.randomEncounter(
                                        actIII,
                                        "i",
                                        "secondActOutro/village",
                                        "4_2",
                                        "4_1");

                        // Update location (Willowdale village)
                        player.location = locations[7];

                        Utils.storyPrinter(
                                        true,
                                        paraSeparator,
                                        actIII,
                                        "i",
                                        "secondActOutro/village",
                                        true,
                                        "2");

                        // Update location (enchanted Woods)
                        player.location = locations[6];

                        Utils.storyPrinter(
                                        true,
                                        paraSeparator,
                                        actIII,
                                        "i",
                                        "secondActOutro/village",
                                        true,
                                        "3");

                        // * Second Battle
                        battleResult = Actions.battle(
                                        paraSeparator,
                                        actIII,
                                        "i",
                                        "Evil Emperor's Guard");

                        if (battleResult[0])
                                return;

                        Utils.clearConsole();

                        battleResult = Actions.battle(
                                        paraSeparator,
                                        actIII,
                                        "i",
                                        "Evil Emperor's Guard Commander");

                        if (battleResult[0])
                                return;

                        // ! ---------Act-III Outro---------

                        // Update location (hideout cavern)
                        player.location = locations[2];

                        // Print Act-III Outro
                        if (!battleResult[1]) {// If the player win the battle
                                Utils.storyPrinter(
                                                true,
                                                paraSeparator,
                                                actIII,
                                                "o",
                                                "secondActOutro/village/if_won",
                                                true,
                                                "1", "2", "3");
                        } else { // If the player has been fled from the battle
                                Utils.storyPrinter(
                                                true,
                                                paraSeparator,
                                                actIII,
                                                "o",
                                                "secondActOutro/village/if_fled",
                                                true,
                                                "1", "2", "3", "4", "5");
                        }
                } else { // Player chose the village mission first, will now play the tunnel mission
                        if (!battleResult[1]) {// If the player had won the previous mission battle
                                Utils.storyPrinter(
                                                true,
                                                paraSeparator,
                                                actIII,
                                                "i",
                                                "thirdActIntro/village/if_won",
                                                true,
                                                "1");
                        } else { // If the player had fled from the previous mission battle
                                Utils.storyPrinter(
                                                true,
                                                paraSeparator,
                                                actIII,
                                                "i",
                                                "thirdActIntro/village/if_fled",
                                                true,
                                                "1", "2");
                        }

                        // Tunnel mission starts

                        // Update location (secret tunnel)
                        player.location = locations[4];

                        // Print Act-II Outro, Part-I
                        Utils.storyPrinter(
                                        true,
                                        paraSeparator,
                                        actIII,
                                        "i",
                                        "secondActOutro/tunnel",
                                        true,
                                        "1", "2", "3", "4", "5", "6");

                        // * Second Battle
                        battleResult = Actions.battle(
                                        paraSeparator,
                                        actIII,
                                        "i",
                                        "Cave Bandit");

                        Utils.clearConsole();

                        battleResult = Actions.battle(
                                        paraSeparator,
                                        actIII,
                                        "i",
                                        "Cave Bandit Leader");

                        if (battleResult[0])
                                return;

                        // ! ---------Act-III Outro---------

                        // Print Act-III Outro
                        if (!battleResult[1]) {// If the player win the battle
                                Utils.storyPrinter(
                                                true,
                                                paraSeparator,
                                                actIII,
                                                "i",
                                                "secondActOutro/tunnel/if_won",
                                                true,
                                                "1", "2", "3", "4");

                                // Update location (The Evil Emperor's Fortress)
                                player.location = locations[5];

                                Utils.storyPrinter(
                                                true,
                                                paraSeparator,
                                                actIII,
                                                "i",
                                                "secondActOutro/tunnel/if_won",
                                                true,
                                                "5");

                                // Update location (hideout cavern)
                                player.location = locations[2];

                                Utils.storyPrinter(
                                                true,
                                                paraSeparator,
                                                actIII,
                                                "o",
                                                "secondActOutro/tunnel/if_won",
                                                true,
                                                "6");
                        } else { // If the player has been fled from the battle
                                Utils.storyPrinter(
                                                true,
                                                paraSeparator,
                                                actIII,
                                                "i",
                                                "secondActOutro/tunnel/if_fled",
                                                true,
                                                "1", "2", "3", "4", "5");

                                Utils.storyPrinter(
                                                false,
                                                paraSeparator,
                                                actIII,
                                                "o",
                                                "secondActOutro/tunnel/if_fled",
                                                true,
                                                "6");

                                // Update location (hideout cavern)
                                player.location = locations[2];
                        }
                }

                // ! ---------Act-IV Intro---------
                Utils.storyPrinter(
                                true,
                                paraSeparator,
                                actIV,
                                "i",
                                "finalActIntro",
                                true,
                                "1", "2", "3", "4", "5", "6");

                // (Get the final and most huge skill upgrade)
                player.chooseFinalSkills(
                                paraSeparator,
                                actIV,
                                "i",
                                "finalActIntro",
                                "6");

                Utils.storyPrinter(
                                true,
                                paraSeparator,
                                actIV,
                                "i",
                                "finalActIntro",
                                true,
                                "7", "8");

                // (Get the final and most huge weapon upgrade)
                String weapon = "THE DRAGON'S FANG";
                player.unlockedCombatWeapons.add(weapon);
                player.combatWeaponCount += 10;

                Utils.printHeading(
                                false,
                                true,
                                "You unlocked " + weapon + "!");
                UIUtils.printPlayerInfo();

                Utils.storyPrinter(
                                true,
                                paraSeparator,
                                actIV,
                                "i",
                                "finalActIntro",
                                true,
                                "9", "10");

                // (Get golds from Alaric)
                int goldGiven = (int) (75 + 15 * player.healers);

                Utils.printHeading(
                                false,
                                true,
                                "You received " + goldGiven + " golds from Alaric!");

                player.gold += goldGiven;
                Utils.pressEnter();

                UIUtils.printPlayerInfo();

                // Update location (Thornfield village)
                player.location = locations[8];

                Utils.storyPrinter(
                                true,
                                paraSeparator,
                                actIV,
                                "i",
                                "finalActIntro",
                                true,
                                "11");

                // (Buy some healers before the final battle)
                // Get a random trader and insert into randomCharacters array
                String product = "";
                String[] traderAndProduct = new String[2];

                do {
                        int randomTraderIndex = (int) (Math.random() * Assets.tradersAndProducts.length);
                        traderAndProduct = Assets.tradersAndProducts[randomTraderIndex];
                        product = traderAndProduct[1];
                } while (!product.equals("healers"));

                // Todo - add some intro for encountering a trader

                Actions.shop(traderAndProduct);

                // Update location (hideout cavern)
                player.location = locations[2];

                Utils.storyPrinter(
                                true,
                                paraSeparator,
                                actIV,
                                "i",
                                "finalActIntro",
                                true,
                                "12");

                // ! ---------Act-IV Outro---------

                // Todo - add some random encounters before the final battle
                // Todo - add 3 battles amidst the final battle
                // Todo - add dialogues for final battle
                // Manage the final battle
                battleResult = Actions.finalBattle(
                                paraSeparator,
                                actIV,
                                "i");

                if (battleResult[0])
                        return;

                // Print Outro After the final battle
                Utils.storyPrinter(
                                false,
                                paraSeparator,
                                epilogue,
                                "outro",
                                true,
                                "1", "2", "3", "4", "5");

                // Print ending screen
                UIUtils.printCompletionMessage();

                // terminating the game loop
                isRunning = false;

                // Reset all player stats for starting a new game
                isNewGame = true;
        }
}
