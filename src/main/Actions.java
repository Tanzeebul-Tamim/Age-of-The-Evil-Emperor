package main;

import java.util.Random;
import java.util.Scanner;

public class Actions {
    static Scanner scanner = new Scanner(System.in);
    static Player player = EventManager.player;
    public static int location = 0;

    // Method to set player name
    public static String setName() {
        boolean nameSet = false;
        String name;

        do {
            Utils.clearConsole();
            Utils.printHeading(true, "What's your name?");
            name = scanner.nextLine().trim();

            // Check if name is empty
            if (name.isEmpty()) {
                Utils.isEmptyInput("Name");
                continue;
            }

            // Asking the player if he wants to correct his choice
            Utils.clearConsole();
            Utils.printHeading(true, "Your name is " + name + ".", "Do you want to keep this name?");
            System.out.println("(1) Yes!");
            System.out.println("(2) No, I want to change my name.");
            int input = Utils.readPlayerInput("-> ", 2);
            System.out.println();

            if (input == 1)
                nameSet = true;
        } while (!nameSet);
        return name;
    }

    // Printing out important info of the player character
    public static void printPlayerInfo() {
        printStats(false);

        // Printing the unlocked skills
        Utils.printHeading(true, "Unlocked Skills");

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

        Utils.printSeparator(30);
        System.out.println();
        Utils.pressEnter();
    }

    // Print current stats
    public static void printStats(boolean pressEnter) {
        Utils.clearConsole();
        Utils.printHeading(true, "PLAYER STATS");

        System.out.println("NAME: " + player.name);
        System.out.println("LOCATION: " + Assets.locations[location]);
        System.out.println("HP: " + player.hp + "/" + player.maxHp);
        System.out.println("XP: " + player.xp);
        System.out.println("GOLD: " + player.gold);
        System.out.println("HEALER: " + player.healers);
        Utils.printSeparator(28);

        System.out.println();

        if (pressEnter) {
            Utils.pressEnter();
        }
    }

    // Printing the main menu
    public static void printMenu() {
        Utils.clearConsole();
        Utils.printHeading(true, "Menu");
        System.out.println("Choose an action:\n");

        System.out.println("(1) Continue on your journey");
        System.out.println("(2) Character Info");
        System.out.println("(3) Exit Game");
    }

    // Todo Method to shop
    public static void shop(String itemName, String[] trade) {
        String traderType = trade[0];
        String productType = trade[1];

        Utils.clearConsole();
        Utils.printHeading(true, "You meet a mysterious stranger!");
        System.out.printf("Seems like he is a %s.\n He's offering %s at a reasonable price.", traderType, productType);

        if (productType.equals("Healers")) {
            buyHealers();
        } else if (productType.equals("Weapons")) {

        }
    }

    // Todo Method to buy healers
    public static void buyHealers() {
        Random random = new Random();

        // Randomly generate how many healers the trader has
        int healersAvailable = random.nextInt(10 - 1 + 1) + 1;

        // Showing healer price
        int price = (int) (Math.random() * (10 + player.healers * 3) + 10 + player.healers);

        if (healersAvailable == 1) {
            System.out.println("The trader has only one healer available.");
        } else {
            System.out.println("The trader has " + healersAvailable + " healers available.");
        }

        if (price == 1) {
            System.out.println("- Healer: " + price + " gold");
        } else {
            System.out.println("- Healer: " + price + " golds");
        }

        // Ask the player to buy
        System.out.println("Do you want to buy some?\n(1) Yes!\n(2) No thanks");
        int input = Utils.readPlayerInput("-> ", 2);

        // Check if the player wants to buy
        if (input == 1) {
            Utils.clearConsole();
            int totalPrice;
            int quantity;

            // Ask the player how many he wants to buy
            if (healersAvailable == 1) {
                quantity = 1;
                totalPrice = quantity * price;
            } else {
                System.out.println("The trader has " + healersAvailable + " healers available.");
                System.out.println("How many healers do you want to buy?\n");

                quantity = Utils.readPlayerInput("-> ", healersAvailable);
                totalPrice = quantity * price;
            }

            // Check if player has enough gold and proceed to purchase
            String title;
            if (player.gold >= totalPrice) {
                player.gold -= totalPrice;
                player.healers += quantity;

                if (quantity == 1) {
                    if (price == 1) {
                        title = "You bought a healer for " + price + "gold!";
                    } else {
                        title = "You bought a healer for " + price + "golds!";
                    }
                } else {
                    if (price == 1) {
                        title = "You bought " + quantity + " healers for " + price + "gold!";
                    } else {
                        title = "You bought " + quantity + " healers for " + price + "golds!";
                    }
                }

                Utils.printHeading(false, title);
            } else {
                if (quantity == 1) {
                    title = "You don't have enough gold to buy a healer.";
                } else {
                    title = "You don't have enough gold to buy " + quantity + "healers.";
                }

                Utils.printHeading(false, title);
            }

            printStats(true);
        }
    }

    // Todo Method to buy weapons
    public static void buyWeapons() {
        Random random = new Random();

        for (String weapon : Assets.weapons) {

        }

        // Randomly generate how many healers the trader has
        int weaponAvailable = random.nextInt(10 - 1 + 1) + 1;

        // Showing healer price
        int price = (int) (Math.random() * (10 + player.healers * 3) + 10 + player.healers);

        if (weaponAvailable == 1) {
            System.out.println("The trader has only one healer available.");
        } else {
            System.out.println("The trader has " + weaponAvailable + " healers available.");
        }

        if (price == 1) {
            System.out.println("- Healer: " + price + " gold");
        } else {
            System.out.println("- Healer: " + price + " golds");
        }

        // Ask the player to buy
        System.out.println("Do you want to buy some?\n(1) Yes!\n(2) No thanks");
        int input = Utils.readPlayerInput("-> ", 2);

        // Check if the player wants to buy
        if (input == 1) {
            Utils.clearConsole();
            int totalPrice;
            int quantity;

            // Ask the player how many he wants to buy
            if (weaponAvailable == 1) {
                quantity = 1;
                totalPrice = quantity * price;
            } else {
                System.out.println("The trader has " + weaponAvailable + " healers available.");
                System.out.println("How many healers do you want to buy?\n");

                quantity = Utils.readPlayerInput("-> ", weaponAvailable);
                totalPrice = quantity * price;
            }

            // Check if player has enough gold and proceed to purchase
            String title;
            if (player.gold >= totalPrice) {
                player.gold -= totalPrice;
                player.healers += quantity;

                if (quantity == 1) {
                    if (price == 1) {
                        title = "You bought a healer for " + price + "gold!";
                    } else {
                        title = "You bought a healer for " + price + "golds!";
                    }
                } else {
                    if (price == 1) {
                        title = "You bought " + quantity + " healers for " + price + "gold!";
                    } else {
                        title = "You bought " + quantity + " healers for " + price + "golds!";
                    }
                }

                Utils.printHeading(false, title);
            } else {
                if (quantity == 1) {
                    title = "You don't have enough gold to buy a healer.";
                } else {
                    title = "You don't have enough gold to buy " + quantity + "healers.";
                }

                Utils.printHeading(false, title);
            }

            printStats(true);
        }
    }

    // Method to calculate a random encounter
    public static void randomEncounter(int paraSeparator, String title) {
        // Random number between 0 and the length of the encounters array
        int encounter = (int) (Math.random() * Assets.actionsOnEncounter.length);

        // Calling the respective methods
        if (Assets.actionsOnEncounter[encounter].equals("Fight")) {
            randomBattle(paraSeparator, title);
        } else if (Assets.actionsOnEncounter[encounter].equals("Walk Away")) {
            // run();
            System.out.println("You're walking away");
        } else {
            // talk();
            System.out.println("You're talking");
        }
    }

    // Creating a random battle with a random enemy
    public static boolean[] randomBattle(int paraSeparator, String title) {
        int randomIndex = (int) (Math.random() * Assets.enemies.length);
        String enemyName = Assets.enemies[randomIndex];
        Enemy enemy = new Enemy(enemyName, player.xp);

        Utils.clearConsole();

        Utils.printSeparator(paraSeparator);
        System.out.println(Utils.printTab(paraSeparator, true) + title);
        Utils.printSeparator(paraSeparator);

        System.out.println();

        System.out.println("LOCATION: " + Assets.locations[Actions.location]);
        System.out.println();

        Utils.printHeading(false, "You encountered a " + enemyName + "." + " You will have to fight him!");
        Utils.pressEnter();
        return battle(enemy);
    }

    // Method to manage and organize battles
    public static boolean[] battle(Enemy enemy) {
        String[] name = player.getName().split(" ");
        String lastName = name[name.length - 1];

        // Main battle loop
        while (true) {
            // Print the info of this battle round
            Utils.clearConsole();
            Utils.printHeading(true, "BATTLE");
            System.out.println(lastName + " vs " + enemy.name);

            Utils.printHeading(
                    false,
                    enemy.name + "\n HP: " + enemy.hp + "/" + enemy.maxHp + "\n",
                    player.name + "\n HP: " + player.hp + "/" + player.maxHp);

            System.out.println("Choose an action: ");
            Utils.printSeparator(20);

            System.out.println("(1) Fight\n(2) Use Healer\n(3) Run Away");
            int input = Utils.readPlayerInput("-> ", 3);

            // React accordingly to player input
            if (input == 1) { // Fight
                // Calculate damage and damage taken
                int damage = player.attack() - enemy.defend();
                int damageTaken = enemy.attack() - player.defend();

                // Check that damage and damageTaken isn't negative
                if (damageTaken < 0) {
                    // Add some damage if player defends very well
                    damage -= damageTaken / 2;
                    damageTaken = 0;
                }

                // Making sure damage isn't negative
                if (damage < 0)
                    damage = 0;

                // Deal damage to both parties
                player.hp -= damageTaken;
                enemy.hp -= damage;

                // Print the info of this battle round
                Utils.clearConsole();
                Utils.printHeading(true, "BATTLE");
                System.out.println(lastName + " vs " + enemy.name);

                // Print each attack outcome
                System.out.println("\nYou hit the " + enemy.name + " causing " + damage + " damage.");
                System.out.println("You received " + damageTaken + " damage from the " + enemy.name + ".");

                Utils.pressEnter();

                // Check if the player is still alive or dead
                if (player.hp <= 0) {
                    // Determining if player is alive and if player fled
                    boolean isDead = hasPlayerDied(true); // Method to end the game
                    boolean hasFled = false;
                    boolean[] result = { isDead, hasFled };
                    return result;
                } else if (enemy.hp <= 0) {
                    // Tell the player that he won the battle
                    Utils.clearConsole();
                    Utils.printHeading(true, "You defeated the " + enemy.name + "!");

                    // Increase player hp
                    player.xp += enemy.xp;
                    System.out.println("You earned " + enemy.xp + " XP!");

                    // Random drops
                    int healerCount = (int) (Math.random() * enemy.xp);
                    int goldEarned = (int) (Math.random() * enemy.xp);

                    if (healerCount > 0) {
                        player.healers += healerCount;

                        if (healerCount == 1) {
                            System.out.println("You collected a healer from the " + enemy.name + "'s corpse!");
                        } else {
                            System.out.format("You collected %d healers from the %s's corpse!\n", healerCount,
                                    enemy.name);
                        }
                    }

                    if (goldEarned > 0) {
                        player.gold += goldEarned;

                        if (goldEarned == 1) {
                            System.out.println("You collected a gold from the " + enemy.name + "'s corpse!");
                        } else {
                            System.out.format("You collected %d golds from the %s's corpse!\n", goldEarned, enemy.name);
                        }
                    }

                    // Press enter and print stats
                    Utils.clearConsole();
                    printStats(true);

                    // Determining if player is alive and if player fled
                    boolean isDead = hasPlayerDied(false); // Method to end the game
                    boolean hasFled = false;
                    boolean[] result = { isDead, hasFled };
                    return result;
                }

            } else if (input == 2) { // Use Healer
                // Print the info of this battle round
                Utils.clearConsole();
                Utils.printHeading(true, "BATTLE");
                System.out.println(lastName + " vs " + enemy.name);

                if (player.healers > 0 && player.hp < player.maxHp) {
                    System.out.println(
                            "Do you want to use the healer to recover your health? (" + player.healers + " left.)\n");
                    System.out.println("(1) Yes\n(2) No, maybe later");
                    input = Utils.readPlayerInput("-> ", 2);
                    Utils.clearConsole();

                    if (input == 1) {
                        // Player used healer
                        player.hp += 15;
                        System.out.println("HP +15!");
                        System.out
                                .println("You used a healer. Your health has been restored back to " + player.hp + "!");
                        Utils.pressEnter();
                    }
                } else {
                    if (player.healers <= 0) {
                        Utils.printHeading(false, "You don't have enough healers.");
                        Utils.pressEnter();
                    } else {
                        Utils.printHeading(false, "You're already at full health.");
                        Utils.pressEnter();
                    }
                }

                // Determining if player is alive and if player fled
                boolean isDead = hasPlayerDied(false); // Method to end the game
                boolean hasFled = false;
                boolean[] result = { isDead, hasFled };
                return result;
            } else { // Run Away
                // Print the info of this battle round
                Utils.clearConsole();
                Utils.printHeading(true, "BATTLE");
                System.out.println(lastName + " vs " + enemy.name);

                // Chance of 35% escape
                if (Assets.locations[location] == "Final") { // Todo: Replace the "Final" with the final location
                    Utils.printHeading(false, "YOU CANNOT ESCAPE THE EVIL EMPEROR!!!");
                    Utils.pressEnter();

                    // Determining if player is alive and if player fled
                    boolean isDead = hasPlayerDied(false); // Method to end the game
                    boolean hasFled = false;
                    boolean[] result = { isDead, hasFled };
                    return result;
                } else {
                    if (Math.random() * 10 + 1 <= 3.5) {
                        Utils.printHeading(false, "You ran away from the " + enemy.name + "!");
                        Utils.pressEnter();

                        // Determining if player is alive and if player fled
                        boolean isDead = hasPlayerDied(false); // Method to end the game
                        boolean hasFled = true;
                        boolean[] result = { isDead, hasFled };
                        return result;
                    } else {
                        Utils.printHeading(false, "You couldn't manage to escape unharmed.");
                        // Calculate the amount of the damage the player takes
                        int damageTaken = enemy.attack();

                        // Check that damage and damageTaken isn't negative
                        if (damageTaken < 0) {
                            damageTaken = 0;
                        }

                        // Print outcomes
                        Utils.printHeading(true, "In the chaos, you've sustained " +
                                damageTaken + " damage!");
                        Utils.pressEnter();
                        Utils.clearConsole();

                        // Deal damage to player
                        player.hp -= damageTaken;

                        // Check if the player is still alive or dead
                        if (player.hp <= 0) {
                            // Determining if player is alive and if player fled
                            boolean isDead = hasPlayerDied(true); // Method to end the game
                            boolean hasFled = false;
                            boolean[] result = { isDead, hasFled };
                            return result;
                        } else {
                            // Print stats after printing a new line
                            System.out.println();
                            printStats(true);

                            // Determining if player is alive and if player fled
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

    // Method to manage and organize the final battle
    public static void finalBattle() {
        // creating the evil emperor object and letting the player fight against him
        battle(new Enemy("THE EVIL EMPEROR", 200));
        
    }

    // Method that gets called when the player is dead
    public static boolean hasPlayerDied(boolean isDead) {
        if (isDead) {
            EventManager.isNewGame = true;
            Utils.clearConsole();
            Utils.printHeading(true, "You died!");
            Utils.printHeading(
                    false,
                    "You earned " + player.xp + " XP on your journey. Try to earn more next time!");
            System.out.println("Thank you for playing my game. I hope you enjoyed it!");
            Utils.pressEnter();
            EventManager.isRunning = false;
            return isDead;
        } else {
            return isDead;
        }
    }
}
