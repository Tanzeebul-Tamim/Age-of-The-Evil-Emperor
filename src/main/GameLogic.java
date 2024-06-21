package main;

public class GameLogic {
    static Player player = GameEngine.player;
    public static int location = 0;

    // Method to calculate a random encounter
    public static void randomEncounter(int paraSeparator, String title) {
        // Random number between 0 and the length of the encounters array
        int encounter = (int) (Math.random() * Names.actionsOnEncounter.length);

        // Calling the respective methods
        if (Names.actionsOnEncounter[encounter].equals("Fight")) {
            randomBattle(paraSeparator, title);
        } else if (Names.actionsOnEncounter[encounter].equals("Walk Away")) {
            // run();
            System.out.println("You're running away");
        } else if (Names.actionsOnEncounter[encounter].equals("Talk")) {
            // talk();
            System.out.println("You're talking");
        } else {
            // explore();
            System.out.println("You're exploring");
        }
    }

    // Printing out important info of the player character
    public static void showPlayerInfo() {
        UtilityMethods.clearConsole();
        UtilityMethods.printHeading(true, "CHARACTER INFO");

        System.out.println("NAME: " + player.name);
        System.out.println("LOCATION: " + Names.locations[location]);
        System.out.println("HP: " + player.hp + "/" + player.maxHp);
        System.out.println("XP: " + player.xp);
        UtilityMethods.printSeparator(28);

        System.out.println();

        // Printing the unlocked skills
        UtilityMethods.printHeading(true, "Unlocked Skills");

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

                if (i == player.unlockedDefSkills.size() - 1) {
                    System.out.print(skill + "\n");
                } else {
                    System.out.print(skill + ", ");
                }
            }
        }

        UtilityMethods.printSeparator(30);
        System.out.println();
        UtilityMethods.pressEnter();
    }

    // Print current stats
    public static void printStats() {
        UtilityMethods.printHeading(true, "PLAYER STATUS");

        System.out.println("LOCATION: " + Names.locations[location]);
        System.out.println("HP: " + player.hp + "/" + player.maxHp);
        System.out.println("XP: " + player.xp);
        UtilityMethods.printSeparator(26);

        System.out.println();
        UtilityMethods.pressEnter();
    }

    // Creating a random battle with a random enemy
    public static boolean[] randomBattle(int paraSeparator, String title) {
        int randomIndex = (int) (Math.random() * Names.enemies.length);
        String enemyName = Names.enemies[randomIndex];
        Enemy enemy = new Enemy(enemyName, player.xp);

        UtilityMethods.clearConsole();

        UtilityMethods.printSeparator(paraSeparator);
        System.out.println(UtilityMethods.printTab(paraSeparator, true) + title);
        UtilityMethods.printSeparator(paraSeparator);

        System.out.println();

        System.out.println("LOCATION: " + Names.locations[GameLogic.location]);
        System.out.println();

        UtilityMethods.printHeading(false, "You encountered a " + enemyName + "." + " You will have to fight him!");
        UtilityMethods.pressEnter();
        return battle(enemy);
    }

    // The main battle method
    public static boolean[] battle(Enemy enemy) {
        // Main battle loop
        while (true) {
            UtilityMethods.clearConsole();
            UtilityMethods.printHeading(true, "BATTLE");

            String[] name = player.getName().split(" ");
            String lastName = name[name.length - 1];
            System.out.println(lastName + " vs " + enemy.name);

            UtilityMethods.printHeading(
                    false,
                    enemy.name + "\n HP: " + enemy.hp + "/" + enemy.maxHp + "\n",
                    player.name + "\n HP: " + player.hp + "/" + player.maxHp);

            System.out.println("Choose an action: ");
            UtilityMethods.printSeparator(20);

            System.out.println("(1) Fight\n(2) Heal Wounds\n(3) Run Away");
            int input = UtilityMethods.readPlayerInput("-> ", 3);

            // React accordingly to player input
            if (input == 1) {
                // Calculate damage and damage taken
                int damage = player.attack() - enemy.defend();
                int damageTaken = enemy.attack() - player.defend();
                // Fight

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
                UtilityMethods.clearConsole();
                UtilityMethods.printHeading(true, "BATTLE");
                System.out.println(lastName + " vs " + enemy.name);

                System.out.println("\nYou hit the " + enemy.name + " causing " + damage + " damage.");
                System.out.println("You received " + damageTaken + " damage from the " + enemy.name + ".");

                UtilityMethods.pressEnter();

                // Check if the player is still alive or dead
                if (player.hp <= 0) {
                    boolean isDead = hasPlayerDied(true); // Method to end the game
                    boolean hasFled = false;
                    boolean[] result = { isDead, hasFled };
                    return result;
                } else if (enemy.hp <= 0) {
                    // Tell the player that he won the battle
                    UtilityMethods.clearConsole();
                    UtilityMethods.printHeading(true, "You defeated the " + enemy.name + "!");

                    // Increase player hp
                    player.xp += enemy.xp;
                    System.out.println("You earned " + enemy.xp + " XP!");
                    UtilityMethods.pressEnter();
                    UtilityMethods.clearConsole();

                    printStats();
                    boolean isDead = hasPlayerDied(false); // Method to end the game
                    boolean hasFled = false;
                    boolean[] result = { isDead, hasFled };
                    return result;
                }

            } else if (input == 2) {
                // Heal Wounds
                System.out.println("You'healed wound");
                boolean isDead = hasPlayerDied(false); // Method to end the game
                boolean hasFled = false;
                boolean[] result = { isDead, hasFled };
                return result;
            } else {
                // Run Away
                UtilityMethods.clearConsole();
                // Chance of 35% escape
                if (Names.locations[location] == "Final") { // Todo: Replace the "Final" with the final location name
                    UtilityMethods.printHeading(false, "YOU CANNOT ESCAPE THE EVIL EMPEROR!!!");
                    UtilityMethods.pressEnter();
                    boolean isDead = hasPlayerDied(false); // Method to end the game
                    boolean hasFled = false;
                    boolean[] result = { isDead, hasFled };
                    return result;
                } else {
                    if (Math.random() * 10 + 1 <= 3.5) {
                        UtilityMethods.printHeading(false, "You ran away from the " + enemy.name + "!");
                        UtilityMethods.pressEnter();
                        boolean isDead = hasPlayerDied(false); // Method to end the game
                        boolean hasFled = true;
                        boolean[] result = { isDead, hasFled };
                        return result;
                    } else {
                        UtilityMethods.printHeading(false, "You couldn't manage to escape unharmed.");

                        // Calculate the amount of the damage the player takes
                        int damageTaken = enemy.attack();

                        // Check that damage and damageTaken isn't negative
                        if (damageTaken < 0) {
                            damageTaken = 0;
                        }

                        UtilityMethods.printHeading(true, "In the chaos, you've sustained " +
                                damageTaken + " damage!");
                        UtilityMethods.pressEnter();
                        UtilityMethods.clearConsole();

                        // Deal damage to player
                        player.hp -= damageTaken;

                        // Check if the player is still alive or dead
                        if (player.hp <= 0) {
                            boolean isDead = hasPlayerDied(true); // Method to end the game
                            boolean hasFled = false;
                            boolean[] result = { isDead, hasFled };
                            return result;
                        } else {
                            System.out.println();
                            printStats();
                            boolean isDead = hasPlayerDied(false); // Method to end the game
                            boolean hasFled = true;
                            boolean[] result = { isDead, hasFled };
                            return result;
                        }
                    }
                }
            }
        }
    }

    // Printing the main menu
    public static void printMenu() {
        UtilityMethods.clearConsole();
        UtilityMethods.printHeading(true, "Menu");
        System.out.println("Choose an action:\n");

        System.out.println("(1) Continue on your journey");
        System.out.println("(2) Character Info");
        System.out.println("(3) Exit Game");
    }

    // Method that gets called when the player is dead
    public static boolean hasPlayerDied(boolean isDead) {
        if (isDead) {
            GameEngine.isNewGame = true;
            UtilityMethods.clearConsole();
            UtilityMethods.printHeading(true, "You died!");
            UtilityMethods.printHeading(
                    false,
                    "You earned " + player.xp + " XP on your journey. Try to earn more next time!");
            System.out.println("Thank you for playing my game. I hope you enjoyed it!");
            UtilityMethods.pressEnter();
            GameEngine.isRunning = false;
            return isDead;
        } else {
            return isDead;
        }
    }
}
