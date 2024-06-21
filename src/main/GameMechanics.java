package main;

import java.util.Scanner;

//* Game Mechanics

public class GameMechanics {
    static Scanner scanner = new Scanner(System.in);
    public static Player player;

    public static boolean isRunning;
    public static int location = 0;

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
            int input = Utilities.readPlayerInput("-> ", 2);
            System.out.println();

            if (input == 1)
                nameSet = true;
        } while (!nameSet);

        // Create new player object with the name
        player = new Player(name);

        // Print Story Intro
        Utilities.storyPrinter(100, "Story", "Intro", true, "para1.txt", "para2.txt", "para3.txt");

        // Show initial character info
        showPlayerInfo();

        // Setting isRunning to true, so the game loop can continue
        isRunning = true;

        // Start main game loop
        gameLoop();
    }

    // Method to calculate a random encounter
    public static void randomEncounter() {
        // Random number between 0 and the length of the encounters array
        int encounter = (int) (Math.random() * Names.actionsOnEncounter.length);

        // Calling the respective methods
        if (Names.actionsOnEncounter[encounter].equals("Fight")) {
            randomBattle();
        } else if (Names.actionsOnEncounter[encounter].equals("Walk Away")) {
            // run();
        } else if (Names.actionsOnEncounter[encounter].equals("Talk")) {
            // talk();
        } else {
            // explore();
        }
    }

    // Method to continue the journey
    public static void continueJourney() {
        // Print Act-I Intro, Part-I
        Utilities.storyPrinter(100, "ACT I - INTRO", "firstActIntro", true, "para1.txt");

        // Print Act-I Intro, Part-II
        // (Get the first upgrade and show the character info)
        player.chooseAbility(100, "ACT I - INTRO", "Which skill do you want to learn?", "firstActIntro", "para2.txt");
        showPlayerInfo();

        // Print Act-I Intro, Part-III
        Utilities.storyPrinter(100, "ACT I - INTRO", "firstActIntro", true, "para3.txt");

        // Print Act-I Outro, Part-I
        Utilities.storyPrinter(100, "ACT I - OUTRO", "firstActOutro", true, "para1.txt", "para2.txt", "para3.txt",
                "para4.txt", "para5.txt", "para6.txt", "para7.txt");
    }

    // Printing out important info of the player character
    public static void showPlayerInfo() {
        Utilities.clearConsole();
        Utilities.printHeading("CHARACTER INFO");

        System.out.println("NAME: " + player.name);
        System.out.println("LOCATION: " + Names.locations[location]);
        System.out.println("HP: " + player.hp + "/" + player.maxHp);
        System.out.println("XP: " + player.xp);
        Utilities.printSeparator(28);

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

    // Creating a random battle with a random enemy
    public static void randomBattle() {
        int randomIndex = (int) (Math.random() * Names.enemies.length);
        String enemyName = Names.enemies[randomIndex];
        Enemy enemy = new Enemy(enemyName, player.xp);

        Utilities.clearConsole();
        Utilities.printHeading("You encountered a " + enemyName + "." + "You will have to fight him!");
        Utilities.pressEnter();
        battle(enemy);
    }

    // The main battle method
    public static void battle(Enemy enemy) {
        // Main battle loop
        while (true) {
            Utilities.clearConsole();
            Utilities.printHeading(enemy.name + "\nHP: " + enemy.hp + "/" + enemy.maxHp);
            Utilities.printHeading(player.name + "\nHP: " + player.hp + "/" + player.maxHp);

            System.out.println("Choose an action: ");
            Utilities.printSeparator(20);

            System.out.println("(1) Fight\n(2) Heal Wounds\n(3) Run Away");
            int input = Utilities.readPlayerInput("-> ", 3);

            // React accordingly to player input
            if (input == 1) {
                // Fight
                // Calculate damage and damage taken
                int damage = player.attack() - enemy.defend();
                int damageTaken = enemy.attack() - player.defend();

                // Check that damage and damageTaken isn't negative
                if (damageTaken < 0) {
                    // Add some damage if player defends very well
                    damage -= damageTaken / 2;
                    damageTaken = 0;
                }

                if (damage < 0)
                    damage = 0;

                // Deal damage to both parties
                player.hp -= damageTaken;
                enemy.hp -= damage;

                // Print the info of this battle round
                Utilities.clearConsole();
                Utilities.printHeading("BATTLE SUMMARY");

                System.out.println("You strike the " + enemy.name + " for " + damage + " damage.");
                System.out.println("You receive " + damageTaken + " damage from the " + enemy.name + ".");

                Utilities.pressEnter();

                // Check if the player is still alive or dead
                if (player.hp <= 0) {
                    playerDied(); // Method to end the game
                    break;
                } else if (enemy.hp <= 0) {
                    // Tell the player that he won the battle
                    Utilities.clearConsole();
                    Utilities.printHeading("You defeated the " + enemy.name + "!");

                    // Increase player hp
                    player.xp += enemy.xp;
                    System.out.println("You earned " + enemy.xp + " XP!");

                    // Show the updated info and continue
                    showPlayerInfo();
                    Utilities.pressEnter();
                    break;
                }

            } else if (input == 2) {

            } else {
                // Run Away
                Utilities.clearConsole();
                // Chance of 35% escape
                if (Names.locations[location] == "Final") { // Todo: Replace the "Final" with the final location name
                    Utilities.printHeading("YOU CANNOT ESCAPE THE EVIL EMPEROR!!!");
                    Utilities.pressEnter();
                } else {
                    if (Math.random() * 10 + 1 <= 3.5) {
                        Utilities.printHeading("You ran away from the " + enemy.name + "!");
                        Utilities.pressEnter();
                        break;
                    } else {
                        Utilities.printHeading("You couldn't manage to escape.");

                        // Calculate the amount of the damage the player takes
                        int damageTaken = enemy.attack();
                        System.out.println("In your hurry you took " + damageTaken + " damage!");
                        Utilities.pressEnter();

                        // Check if the player is still alive or dead
                        if (player.hp <= 0) {
                            playerDied(); // Method to end the game
                            break;
                        }
                    }
                }
            }
        }
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

    // Method that gets called when the player is dead
    public static void playerDied() {
        Utilities.clearConsole();
        Utilities.printHeading("You died....");
        Utilities.printHeading("You earned " + player.xp + " XP on your journey. Try to earn more next time!");
        System.out.println("Thank you for playing my game. I hope you enjoyed it!");

        isRunning = false;
    }

    // Main game loop
    public static void gameLoop() {
        while (isRunning) {
            printMenu();
            int input = Utilities.readPlayerInput("-> ", 3);

            if (input == 1)
                continueJourney();
            else if (input == 2)
                showPlayerInfo();
            else
                isRunning = false;
        }
    }
}
