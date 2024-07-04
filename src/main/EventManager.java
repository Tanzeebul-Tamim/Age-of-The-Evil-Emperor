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

                // ! ---------Intro---------
                // Print Story Intro Part I and Part II
                Utils.storyPrinter(true, paraSeparator, "Prologue", "Intro", true,
                                "para1.txt", "para2.txt");

                // Update location
                player.location = "Hidden Cavern";

                // Print Story Intro Part III
                Utils.storyPrinter(true, paraSeparator, "Prologue", "Intro", true,
                                "para3.txt");

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
                                        player.location = "Village";
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
                Utils.storyPrinter(true, paraSeparator, "ACT I - INTRO - THE AWAKENING", "firstActIntro", true,
                                "para1.txt", "para2_1.txt");

                // Print Act-I Intro, Part-II
                // (Get the first skill upgrade)
                player.chooseSkill(paraSeparator, "ACT I - INTRO - THE AWAKENING", "Which skill do you want to learn?",
                                "firstActIntro",
                                "para2_2.txt", true);

                // Print Act-I Intro, Part-III
                Utils.storyPrinter(true, paraSeparator, "ACT I - INTRO - THE AWAKENING", "firstActIntro", true,
                                "para3.txt");

                // Update location
                player.location = "Hideout Cavern";

                // ! ---------Act-I Outro---------
                // Print Act-I Outro, Part-I
                Utils.storyPrinter(true, paraSeparator, "ACT I - OUTRO - THE AWAKENING", "firstActOutro", true,
                                "para1.txt", "para2.txt",
                                "para3.txt");

                // Update location
                player.location = "Nearby Village";

                // Print Act-I Outro, Part-II
                Utils.storyPrinter(true, paraSeparator, "ACT I - OUTRO - THE AWAKENING", "firstActOutro", true,
                                "para4.txt", "para5.txt",
                                "para6_1.txt");

                // Print Act-I Outro, Part-III
                // (Get the first weapon upgrade)
                player.chooseWeapon(paraSeparator, "ACT I - OUTRO - THE AWAKENING",
                                "Which Weapon Will You Choose for the Journey?",
                                "firstActOutro",
                                "para6_2.txt", true);

                // Print Act-I Outro, Part-IV
                Utils.storyPrinter(true, paraSeparator, "ACT I - OUTRO - THE AWAKENING", "firstActOutro", true,
                                "para7.txt");

                // * First Battle
                boolean[] battle1Result = Actions.randomBattle(paraSeparator, "ACT I - OUTRO - THE AWAKENING - BATTLE");

                if (battle1Result[0])
                        return;

                // Print Act-I Outro, Part-V
                if (!battle1Result[1]) // If the player win the battle
                        Utils.storyPrinter(true, paraSeparator, "ACT I - OUTRO - THE AWAKENING", "firstActOutro", true,
                                        "para8_1.txt",
                                        "para9.txt");
                else // If the player has been fled from the battle
                        Utils.storyPrinter(true, paraSeparator, "ACT I - OUTRO - THE AWAKENING", "firstActOutro", true,
                                        "para8_2.txt",
                                        "para9.txt");

                // Update location
                player.location = "Hideout Cavern";

                // ! ---------Act-II Intro---------
                if (!battle1Result[1]) // If the player win the battle
                        Utils.storyPrinter(true, paraSeparator, "ACT II - INTRO- PATHS OF STRATEGY",
                                        "secondActIntro/if_won", true, "para1.txt",
                                        "para2.txt", "para3.txt", "para4.txt", "para5.txt", "para6.txt", "para7.txt",
                                        "para8.txt");
                else // If the player has been fled from the battle
                        Utils.storyPrinter(true, paraSeparator, "ACT II - INTRO- PATHS OF STRATEGY",
                                        "secondActIntro/if_fled", true, "para1.txt",
                                        "para2.txt", "para3.txt", "para4.txt", "para5.txt", "para6.txt", "para7.txt",
                                        "para8.txt");

                // Choosing strategy
                String choice1 = "Lead the covert mission through the tunnels.";
                String choice2 = "Rally support from the villages.";
                int choice = UIUtils.choose(paraSeparator, "ACT II - INTRO- PATHS OF STRATEGY", "Decide wisely!",
                                "secondActIntro", "para1.txt",
                                choice1, choice2);

                // (Get the second skill upgrade)
                player.chooseSkill(paraSeparator, "ACT II - INTRO- PATHS OF STRATEGY",
                                "Which skill do you want to learn this time?",
                                "secondActIntro",
                                "para2.txt", false);

                // (Get the second weapon upgrade)
                player.chooseWeapon(paraSeparator, "ACT II - INTRO- PATHS OF STRATEGY",
                                "Which Weapon Will You Choose for the Journey?",
                                "secondActIntro",
                                "para3.txt", true);

                boolean[] battle2Result;

                if (choice == 1) { // Player chose the tunnel mission
                        // Update location
                        player.location = "Secret Tunnels";

                        // Print Act-II Outro, Part-I
                        Utils.storyPrinter(true, paraSeparator, "ACT II - INTRO - PATHS OF STRATEGY",
                                        "secondActOutro/tunnel", true, "para1.txt",
                                        "para2.txt", "para3.txt", "para4.txt", "para5.txt", "para6.txt");

                        // * Second Battle
                        battle2Result = Actions.battle(paraSeparator, "ACT II - INTRO - PATHS OF STRATEGY - BATTLE",
                                        "Cave Bandit");

                        if (battle2Result[0])
                                return;

                        // ! ---------Act-II Outro---------

                        // Print Act-II Outro, Part-II
                        if (!battle2Result[1]) {// If the player win the battle
                                Utils.storyPrinter(true, paraSeparator, "ACT II - INTRO - PATHS OF STRATEGY",
                                                "secondActOutro/tunnel/if_won", true,
                                                "para1.txt", "para2.txt", "para3.txt", "para4.txt");

                                // Update location
                                player.location = "The Evil Emperor's Fortress";

                                Utils.storyPrinter(true, paraSeparator, "ACT II - INTRO - PATHS OF STRATEGY",
                                                "secondActOutro/tunnel/if_won", true,
                                                "para5.txt");

                                // Update location
                                player.location = "Hideout Cavern";

                                Utils.storyPrinter(true, paraSeparator, "ACT II - OUTRO - PATHS OF STRATEGY",
                                                "secondActOutro/tunnel/if_won", true,
                                                "para6.txt");
                        } else { // If the player has been fled from the battle
                                Utils.storyPrinter(true, paraSeparator, "ACT II - INTRO - PATHS OF STRATEGY",
                                                "secondActOutro/tunnel/if_fled", true,
                                                "para1.txt", "para2.txt", "para3.txt", "para4.txt", "para5.txt");

                                Utils.storyPrinter(false, paraSeparator, "ACT II - OUTRO - PATHS OF STRATEGY",
                                                "secondActOutro/tunnel/if_fled", true,
                                                "para6.txt");

                                // Update location
                                player.location = "Hideout Cavern";
                        }
                } else { // player chose the village mission
                         // Update location
                        player.location = "Enchanted Woods";

                        Utils.storyPrinter(true, paraSeparator, "ACT II - INTRO - PATHS OF STRATEGY",
                                        "secondActOutro/village", true,
                                        "para1.txt");

                        Actions.randomEncounter("ACT II - INTRO - PATHS OF STRATEGY", "secondActOutro/village",
                                        "para4_2.txt", "para4_1.txt");

                        // Update location
                        player.location = "Nearby Village";

                        Utils.storyPrinter(true, paraSeparator, "ACT II - INTRO - PATHS OF STRATEGY",
                                        "secondActOutro/village", true,
                                        "para2.txt");

                        // Update location
                        player.location = "Enchanted Woods";

                        Utils.storyPrinter(true, paraSeparator, "ACT II - INTRO - PATHS OF STRATEGY",
                                        "secondActOutro/village", true,
                                        "para3.txt");

                        // * Second Battle
                        battle2Result = Actions.battle(paraSeparator, "ACT II - INTRO - PATHS OF STRATEGY - BATTLE",
                                        "Evil Emperor's Guard");

                        if (battle2Result[0])
                                return;

                        // ! ---------Act-II Outro---------

                        // Update location
                        player.location = "Hideout Cavern";

                        // Print Act-II Outro, Part-II
                        if (!battle2Result[1]) {// If the player win the battle
                                Utils.storyPrinter(true, paraSeparator, "ACT II - OUTRO - PATHS OF STRATEGY",
                                                "secondActOutro/village/if_won", true,
                                                "para1.txt", "para2.txt", "para3.txt");
                        } else { // If the player has been fled from the battle
                                Utils.storyPrinter(true, paraSeparator, "ACT II - OUTRO - PATHS OF STRATEGY",
                                                "secondActOutro/village/if_fled", true,
                                                "para1.txt", "para2.txt", "para3.txt", "para4.txt", "para5.txt");
                        }
                }

                // ! ---------Act-III Intro---------
                // Print Act-III Intro
                Utils.storyPrinter(true, paraSeparator, "ACT III - INTRO - ALLIES AND AMBUSHES", "thirdActIntro", true,
                                "para1.txt");

                if (choice == 1) { // Player chose the tunnel mission first, will now play the village mission
                        if (!battle2Result[1]) {// If the player had won the previous mission battle
                                Utils.storyPrinter(true, paraSeparator, "ACT III - INTRO - ALLIES AND AMBUSHES",
                                                "thirdActIntro/tunnel/if_won", true,
                                                "para1.txt");
                        } else { // If the player had fled from the previous mission battle
                                Utils.storyPrinter(true, paraSeparator, "ACT III - INTRO - ALLIES AND AMBUSHES",
                                                "thirdActIntro/tunnel/if_fled", true,
                                                "para1.txt");
                        }

                        // Village mission starts

                        // Update location
                        player.location = "Enchanted Woods";

                        Utils.storyPrinter(true, paraSeparator, "ACT III - INTRO - ALLIES AND AMBUSHES",
                                        "secondActOutro/village", true,
                                        "para1.txt");

                        Actions.randomEncounter("ACT III - INTRO - ALLIES AND AMBUSHES", "secondActOutro/village",
                                        "para4_2.txt", "para4_1.txt");

                        // Update location
                        player.location = "Nearby Village";

                        Utils.storyPrinter(true, paraSeparator, "ACT III - INTRO - ALLIES AND AMBUSHES",
                                        "secondActOutro/village", true,
                                        "para2.txt");

                        // Update location
                        player.location = "Enchanted Woods";

                        Utils.storyPrinter(true, paraSeparator, "ACT III - INTRO - ALLIES AND AMBUSHES",
                                        "secondActOutro/village", true,
                                        "para3.txt");

                        // * Second Battle
                        battle2Result = Actions.battle(paraSeparator, "ACT III - INTRO - ALLIES AND AMBUSHES - BATTLE",
                                        "Evil Emperor's Guard");

                        if (battle2Result[0])
                                return;

                        // ! ---------Act-III Outro---------

                        // Update location
                        player.location = "Hideout Cavern";

                        // Print Act-III Outro
                        if (!battle2Result[1]) {// If the player win the battle
                                Utils.storyPrinter(true, paraSeparator, "ACT III - OUTRO - ALLIES AND AMBUSHES",
                                                "secondActOutro/village/if_won", true,
                                                "para1.txt", "para2.txt", "para3.txt");
                        } else { // If the player has been fled from the battle
                                Utils.storyPrinter(true, paraSeparator, "ACT III - OUTRO - ALLIES AND AMBUSHES",
                                                "secondActOutro/village/if_fled", true,
                                                "para1.txt", "para2.txt", "para3.txt", "para4.txt", "para5.txt");
                        }
                } else { // Player chose the village mission first, will now play the tunnel mission
                        if (!battle2Result[1]) {// If the player had won the previous mission battle
                                Utils.storyPrinter(true, paraSeparator, "ACT III - INTRO - ALLIES AND AMBUSHES",
                                                "thirdActIntro/village/if_won", true,
                                                "para1.txt");
                        } else { // If the player had fled from the previous mission battle
                                Utils.storyPrinter(true, paraSeparator, "ACT III - INTRO - ALLIES AND AMBUSHES",
                                                "thirdActIntro/village/if_fled", true,
                                                "para1.txt", "para2.txt");
                        }

                        // Tunnel mission starts

                        // Update location
                        player.location = "Secret Tunnels";

                        // Print Act-II Outro, Part-I
                        Utils.storyPrinter(true, paraSeparator, "ACT III - INTRO - ALLIES AND AMBUSHES",
                                        "secondActOutro/tunnel", true, "para1.txt",
                                        "para2.txt", "para3.txt", "para4.txt", "para5.txt", "para6.txt");

                        // * Second Battle
                        battle2Result = Actions.battle(paraSeparator, "ACT III - INTRO - ALLIES AND AMBUSHES - BATTLE",
                                        "Cave Bandit");

                        if (battle2Result[0])
                                return;

                        // ! ---------Act-III Outro---------

                        // Print Act-III Outro
                        if (!battle2Result[1]) {// If the player win the battle
                                Utils.storyPrinter(true, paraSeparator, "ACT III - INTRO - ALLIES AND AMBUSHES",
                                                "secondActOutro/tunnel/if_won", true,
                                                "para1.txt", "para2.txt", "para3.txt", "para4.txt");

                                // Update location
                                player.location = "The Evil Emperor's Fortress";

                                Utils.storyPrinter(true, paraSeparator, "ACT III - INTRO - ALLIES AND AMBUSHES",
                                                "secondActOutro/tunnel/if_won", true,
                                                "para5.txt");

                                // Update location
                                player.location = "Hideout Cavern";

                                Utils.storyPrinter(true, paraSeparator, "ACT III - OUTRO - ALLIES AND AMBUSHES",
                                                "secondActOutro/tunnel/if_won", true,
                                                "para6.txt");
                        } else { // If the player has been fled from the battle
                                Utils.storyPrinter(true, paraSeparator, "ACT III - INTRO - ALLIES AND AMBUSHES",
                                                "secondActOutro/tunnel/if_fled", true,
                                                "para1.txt", "para2.txt", "para3.txt", "para4.txt", "para5.txt");

                                Utils.storyPrinter(false, paraSeparator, "ACT III - OUTRO - ALLIES AND AMBUSHES",
                                                "secondActOutro/tunnel/if_fled", true,
                                                "para6.txt");

                                // Update location
                                player.location = "Hideout Cavern";
                        }
                }

                // ! ---------Act-IV Intro---------
                Utils.storyPrinter(true, paraSeparator, "ACT IV - INTRO - THE FINAL STAND",
                                "finalActIntro", true,
                                "para1.txt", "para2.txt", "para3.txt", "para4.txt", "para5.txt");

                // (Get the final and most huge skill upgrade)
                player.chooseFinalSkills(paraSeparator, "ACT IV - INTRO - THE FINAL STAND", "finalActIntro",
                                "para6.txt", false);

                Utils.storyPrinter(true, paraSeparator, "ACT IV - INTRO - THE FINAL STAND",
                                "finalActIntro", true,
                                "para7.txt", "para8.txt");

                // (Get the final and most huge weapon upgrade)
                String weapon = "THE DRAGON'S FANG";
                player.unlockedCombatWeapons.add(weapon);
                player.combatWeaponCount += 5;

                Utils.printHeading(false, true, "You unlocked " + weapon + "!");
                UIUtils.printPlayerInfo();
                Utils.pressEnter();

                Utils.storyPrinter(true, paraSeparator, "ACT IV - INTRO - THE FINAL STAND",
                                "finalActIntro", true,
                                "para9.txt", "para10.txt");

                // (Get golds from Alaric)
                int goldGiven = (int) (75 + 15 * player.healers);

                Utils.printHeading(false, true, "You received " + goldGiven + " golds from Alaric!");
                player.gold += goldGiven;
                Utils.pressEnter();

                UIUtils.printPlayerInfo();

                // Update location
                player.location = "Nearby Village";

                Utils.storyPrinter(true, paraSeparator, "ACT IV - INTRO - THE FINAL STAND",
                                "finalActIntro", true, "para11.txt");

                // (Buy some healers before the final battle)
                // Get a random trader and insert into randomCharacters array
                String product = "";
                String[] traderAndProduct;

                do {
                        int randomTraderIndex = (int) (Math.random() * Assets.tradersAndProducts.length);
                        traderAndProduct = Assets.tradersAndProducts[randomTraderIndex];
                        product = traderAndProduct[1];
                } while (!product.equals("healers"));

                Actions.shop(traderAndProduct);

                // Update location
                player.location = "Hideout Cavern";

                Utils.storyPrinter(true, paraSeparator, "ACT IV - INTRO - THE FINAL STAND",
                                "finalActIntro", true,
                                "para12.txt");

                // ! ---------Act-IV Outro---------

                // Manage the final battle
                Actions.finalBattle(paraSeparator, "ACT IV - OUTRO - THE FINAL STAND");

                // Print Outro After the final battle
                Utils.storyPrinter(false, paraSeparator, "Epilogue", "outro", true,
                                "para1.txt", "para2.txt", "para3.txt", "para4.txt", "para5.txt");

                // Print ending screen
                UIUtils.printCompletionMessage();

                // terminating the game loop
                isRunning = false;

                // Reset all player stats for starting a new game
                isNewGame = true;
        }
}
