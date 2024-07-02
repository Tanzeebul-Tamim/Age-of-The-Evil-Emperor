package main;

import java.util.ArrayList;
import java.util.Random;

public class Actions {
    static Player player = EventManager.player;
    public static int location = 0;
    static int paraSeparator = 100;

    // Creating a random encounter with a random entity
    public static void randomEncounter(String title, String folderName, String fileNameIfFled,
            String fileNameIfNotFled) {
        // Array to store 3 random characters from each type of entities
        String[] randomCharacters = new String[3];

        // Get a random enemy and insert into randomCharacters array
        int randomEnemyIndex = (int) (Math.random() * Assets.enemies.length);
        String enemyName = Assets.enemies[randomEnemyIndex];
        randomCharacters[0] = enemyName;

        // Get a random fellow and insert into randomCharacters array
        int randomFellowIndex = (int) (Math.random() * Assets.fellows.length);
        String fellowName = Assets.fellows[randomFellowIndex];
        randomCharacters[1] = fellowName;

        // Get a random trader and insert into randomCharacters array
        int randomTraderIndex = (int) (Math.random() * Assets.tradersAndProducts.length);
        String[] traderAndProduct = Assets.tradersAndProducts[randomTraderIndex];
        String traderName = traderAndProduct[0];
        randomCharacters[2] = traderName;

        // Get a random entity from the randomCharacters array
        int randomEntityIndex = (int) (Math.random() * randomCharacters.length);
        String randomEntity = randomCharacters[randomEntityIndex];

        // variable for random messages from random entities
        int randomMessageIndex;
        String randomMessage;
        int input;

        Utils.clearConsole();

        Utils.printSeparator(paraSeparator);
        System.out.println(Utils.printTab(paraSeparator, true) + title);
        Utils.printSeparator(paraSeparator);

        System.out.println();

        System.out.println("LOCATION: " + Assets.locations[UIUtils.location]);
        System.out.println();

        Utils.printSeparator(paraSeparator);
        System.out.println(
                "As you journey onward, a mysterious figure catches your eye. The air feels charged with uncertainty.\nPrepare yourself for whatever lies ahead. Stay alert!");
        Utils.printSeparator(paraSeparator);
        Utils.pressEnter();
        System.out.println();

        // Consequences of encountering each type of entities
        if (randomEntityIndex == 0) { // encountered an enemy
            // Randomly generate how an enemy will interact upon encounter
            int randomInteractIndex = (int) (Math.random() * Assets.interactionsOfEnemy.length);
            String action = Assets.interactionsOfEnemy[randomInteractIndex];

            if (action.equals("Fight")) { // The enemy attacks you upon encounter
                boolean[] battleResult = battle(paraSeparator, title, randomEntity);

                if (battleResult[0])
                    return;

                if (!battleResult[1])
                    Utils.storyPrinter(true, paraSeparator, title, folderName, true, fileNameIfNotFled);
                else
                    Utils.storyPrinter(true, paraSeparator, title, folderName, true, fileNameIfFled);
            } else if (action.equals("Taunt")) { // The enemy taunts you
                Utils.printHeading(true, false, "You encountered a " + randomEntity + "."
                        + " With a menacing grin, he stops to taunt you. Their words are filled with threats and malice.");

                Utils.pressEnter();

                randomMessageIndex = (int) (Math.random() * Assets.tauntingMessages.length);
                randomMessage = Assets.tauntingMessages[randomMessageIndex];

                Utils.printMessage(randomEntity + " -\n\n" + randomMessage);

                System.out.println();

                System.out.println("Will you seize the moment to attack?\n");

                System.out.println("(1) Attack the enemy!");
                System.out.println("(2) Walk away");
                input = Utils.readPlayerInput("-> ", 2);

                if (input == 1) {
                    Enemy enemy = new Enemy(randomEntity, player.xp);
                    boolean[] battleResult = fight(paraSeparator, title, enemy);

                    if (battleResult[0])
                        return;

                    if (!battleResult[1])
                        Utils.storyPrinter(true, paraSeparator, title, folderName, true, fileNameIfNotFled);
                    else
                        Utils.storyPrinter(true, paraSeparator, title, folderName, true, fileNameIfFled);
                }
            } else { // The enemy walks away
                Utils.printHeading(true, false, "You encountered a " + randomEntity + "."
                        + " But luckily, he walks away without engaging you.");

                System.out.println("Do you want to attack him?\n");

                System.out.println("(1) Attack from behind!");
                System.out.println("(2) Let go");
                input = Utils.readPlayerInput("-> ", 2);

                if (input == 1) {
                    Enemy enemy = new Enemy(randomEntity, player.xp);
                    boolean[] battleResult = fight(paraSeparator, title, enemy);

                    if (battleResult[0])
                        return;

                    if (!battleResult[1])
                        Utils.storyPrinter(true, paraSeparator, title, folderName, true, fileNameIfNotFled);
                    else
                        Utils.storyPrinter(true, paraSeparator, title, folderName, true, fileNameIfFled);
                }
            }
        } else { // encountered a fellow or a trader
            Utils.printHeading(false, false,
                    "Seems like he is a " + randomEntity + ". You need not worry; he poses no threat.");

            Utils.pressEnter();

            if (randomEntityIndex == 1) { // encountered a fellow
                randomMessageIndex = (int) (Math.random() * Assets.fellowMessages.length);
                randomMessage = Assets.fellowMessages[randomMessageIndex];

                Utils.printSeparator(paraSeparator);
                System.out.println(randomEntity + " -\n\n" + randomMessage);
                Utils.printSeparator(paraSeparator);

                Utils.pressEnter();

                Utils.printHeading(true, false,
                        "Would you like to thank him? There might be a reward in it for you!");

                System.out.println("(1) Yes, Thank him");
                System.out.println("(2) No, Continue your journey");
                input = Utils.readPlayerInput("-> ", 2);
                System.out.println();

                if (input == 1) { // Thank him
                    Utils.clearConsole();

                    int maxHealers = 3; // Maximum number of healers to receive
                    int healerCount = (int) (Math.random() * maxHealers) + 1;

                    Utils.printHeading(true, true, "You thanked the " + randomEntity + ".");

                    if (healerCount > 0) {
                        System.out.println("The " + randomEntity + " smiles warmly and hands you a healer.");
                        player.healers += healerCount;

                        Utils.pressEnter();
                        System.out.println();

                        Utils.printMessage(
                                randomEntity + " -\n\n" + "Take this, it may come in handy on your journey.");

                        Utils.clearConsole();

                        if (healerCount == 1) {
                            Utils.printHeading(false, false,
                                    "You have received a healer from the " + randomEntity + "!");
                        } else {
                            Utils.printHeading(false, false,
                                    "You have received " + healerCount + " healers from the " + randomEntity + "!");
                        }

                        Utils.pressEnter();
                    }
                }

            } else { // encountered a trader
                randomMessageIndex = (int) (Math.random() * Assets.traderMessages.length);
                randomMessage = Assets.traderMessages[randomMessageIndex];

                Utils.printSeparator(paraSeparator);
                System.out.println(randomEntity + " -\n\n" + randomMessage);
                Utils.printSeparator(paraSeparator);

                Utils.pressEnter();
                System.out.println();

                Utils.printHeading(true, true, "Would you like to:");

                System.out.println("(1) Browse his wares?");
                System.out.println("(2) Politely decline and move on");
                input = Utils.readPlayerInput("-> ", 2);
                System.out.println();

                if (input == 1) { // shop
                    shop(traderAndProduct);
                }
            }
        }
    }

    // Creating a battle (to be used in random encounter method)
    public static boolean[] battle(int paraSeparator, String title, String enemyName) {
        Enemy enemy = new Enemy(enemyName, player.xp);

        System.out.println();
        Utils.printHeading(false, false, "You encountered a " + enemyName + "." + " You will have to fight him!");
        Utils.pressEnter();

        return fight(paraSeparator, title, enemy);
    }

    // Overloading the previous method to create a random battle with a random enemy
    // (to be used when using directly)
    public static boolean[] randomBattle(int paraSeparator, String title) {
        int randomIndex = (int) (Math.random() * Assets.enemies.length);
        String enemyName = Assets.enemies[randomIndex];
        Enemy enemy = new Enemy(enemyName, player.xp);

        Utils.clearConsole();

        Utils.printSeparator(paraSeparator);
        System.out.println(Utils.printTab(paraSeparator, true) + title);
        Utils.printSeparator(paraSeparator);

        System.out.println();

        System.out.println("LOCATION: " + Assets.locations[UIUtils.location]);
        System.out.println();

        Utils.printHeading(false, false, "You encountered a " + enemyName + "." + " You will have to fight him!");
        Utils.pressEnter();
        return fight(paraSeparator, title, enemy);
    }

    // Method to manage and organize battles
    public static boolean[] fight(int paraSeparator, String title, Enemy enemy) {
        String[] name = player.getName().split(" ");
        String lastName = name[name.length - 1];
        String battleTitle = lastName + " VS " + enemy.name;

        // Main battle loop
        while (true) {
            Utils.clearConsole();

            // Print chapter name
            Utils.printHeading(false, true, title);

            // Print the info of this battle round
            Utils.printHeading(false, true, battleTitle);

            Utils.printHeading(true,
                    false,
                    enemy.name + "\n HP: " + enemy.hp + "/" + enemy.maxHp + "\n",
                    player.name + "\n HP: " + player.hp + "/" + player.maxHp);

            Utils.printHeading(true, true, "Choose an action:");

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

                // Print chapter name
                Utils.printHeading(false, true, title);

                // Print the info of this battle round
                Utils.printHeading(false, true, battleTitle);

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

                    if (enemy.name.equals("THE EVIL EMPEROR")) {
                        Utils.printSeparator(50);
                        Utils.printSeparator(30);

                        Utils.printHeading(true, true, "***** GAME COMPLETED *****");
                        System.out.printf("Congratulations, %s!\n", player.getName());
                        System.out.println(
                                "You have defeated the EVIL EMPEROR and saved Eldoria from the brink of destruction!");

                        Utils.printSeparator(30);
                        Utils.printSeparator(50);
                    } else {
                        Utils.printHeading(true, true, "You defeated the " + enemy.name + "!");
                    }

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
                            System.out.format("You collected %d golds from the %s's corpse!\n", goldEarned,
                                    enemy.name);
                        }
                    }

                    // Increase enemy kill count
                    player.enemiesKilled++;

                    if (player.enemiesKilled == 1) {
                        System.out.println("Well done! You've defeated your first enemy and proven your strength.");
                    } else {
                        System.out.format("Congratulations! You have defeated %d enemies so far.\n",
                                player.enemiesKilled);
                    }

                    // Press enter and print stats
                    Utils.pressEnter();
                    Utils.clearConsole();
                    UIUtils.printStats(true);

                    // Determining if player is alive and if player fled
                    boolean isDead = hasPlayerDied(false); // Method to end the game
                    boolean hasFled = false;
                    boolean[] result = { isDead, hasFled };
                    return result;
                }

            } else if (input == 2) { // Use Healer
                // Print the info of this battle round
                Utils.clearConsole();

                // Print chapter name
                Utils.printHeading(false, true, title);

                // Print the info of this battle round
                Utils.printHeading(true, true, battleTitle);

                if (player.healers > 0 && player.hp < player.maxHp) {
                    System.out.println(
                            "Do you want to use the healer to recover your health? (" + player.healers + " left.)\n");
                    System.out.println("(1) Yes\n(2) No, maybe later");
                    input = Utils.readPlayerInput("-> ", 2);
                    Utils.clearConsole();

                    if (input == 1) {
                        // Player used healer
                        player.hp = player.hp + 15 > player.maxHp ? player.maxHp : player.hp + 15;
                        player.healers--;

                        Utils.printHeading(true, false,
                                "You used a healer. Your health has been restored back to " + player.hp + "!");
                        System.out.println("HP +15!");
                        Utils.pressEnter();
                        continue;
                    }
                } else {
                    if (player.hp == player.maxHp) {
                        Utils.printHeading(false, false, "You're already at full health.");
                        Utils.pressEnter();
                        continue;
                    } else {
                        Utils.printHeading(false, false, "You don't have enough healers.");
                        Utils.pressEnter();
                        continue;
                    }
                }
            } else { // Run Away
                // Print the info of this battle round
                Utils.clearConsole();

                // Print chapter name
                Utils.printHeading(false, true, title);

                // Print the info of this battle round
                Utils.printHeading(true, true, battleTitle);

                System.out.println(battleTitle);
                System.out.println();

                // Chance of 35% escape
                if (enemy.name.equals("THE EVIL EMPEROR")) {
                    Utils.printHeading(false, false, "YOU CANNOT ESCAPE THE EVIL EMPEROR!!!");
                    Utils.pressEnter();

                    continue;
                } else {
                    if (Math.random() * 10 + 1 <= 4.5) {
                        Utils.printHeading(false, false, "You ran away from the " + enemy.name + "!");
                        Utils.pressEnter();

                        // Determining if player is alive and if player fled
                        boolean isDead = hasPlayerDied(false); // Method to end the game
                        boolean hasFled = true;
                        boolean[] result = { isDead, hasFled };
                        return result;
                    } else {
                        Utils.printHeading(true, false, "You couldn't manage to escape unharmed!");
                        // Calculate the amount of the damage the player takes
                        int damageTaken = enemy.attack();

                        // Check that damage and damageTaken isn't negative
                        if (damageTaken < 0) {
                            damageTaken = 0;
                        }

                        // Print outcomes
                        System.out.println("In the chaos, you've sustained " +
                                damageTaken + " damage!");
                        System.out.println();
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
                            UIUtils.printStats(true);

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
    public static void finalBattle(int paraSeparator, String title) { // Todo handle death cases and exit game if dies
        // creating the evil emperor object and letting the player fight against him
        fight(paraSeparator, title, new Enemy("THE EVIL EMPEROR", 200));
        EventManager.isRunning = false;
    }

    // Method to shop
    public static void shop(String[] trade) {
        String traderType = trade[0];
        String product = trade[1];

        Utils.clearConsole();
        Utils.printHeading(false, false, "The " + traderType + " is offering " + product + " at a reasonable price.");

        // Ask the player to buy
        System.out.printf("Do you want to buy some %s?\n\n(1) Yes!\n(2) No thanks\n\n", product);
        int input = Utils.readPlayerInput("-> ", 2);

        if (input == 1) {
            if (product.equals("healers")) {
                buyHealers(traderType);
            } else if (product.equals("weapons")) {
                buyWeapons(traderType);
            } else {
                Utils.clearConsole();

                String title = "What would you like to buy?";

                Utils.printHeading(true, true, title);

                System.out.println("(1) Healers");
                System.out.println("(2) Weapons");

                input = Utils.readPlayerInput("-> ", 2);

                if (input == 1) {
                    buyHealers(traderType);
                } else {
                    buyWeapons(traderType);
                }
            }
        }
    }

    // Method to buy healers
    public static void buyHealers(String traderType) {
        Utils.clearConsole();
        Random random = new Random();

        // Randomly generate how many healers the trader has
        int healersAvailable = random.nextInt(10 - 1 + 1) + 1;

        // Showing healer price
        int price = (int) (Math.random() * (5 + player.healers * 3) + 10 + player.healers);

        boolean doneBuying = false;
        int totalPrice;
        int quantity;
        String title;

        do {
            if (price == 1) {
                Utils.printHeading(false, true, "Item: Healer", "Price: " + price + " gold",
                        "Balance: " + player.gold + " golds", "Restores: 15 HP");
            } else {
                Utils.printHeading(false, true, "Item: Healer", "Price: " + price + " golds",
                        "Balance: " + player.gold + " golds", "Restores: 15 HP");
            }

            // Show available quantity
            if (healersAvailable == 1) {
                title = "The " + traderType + " has only one healer available.";
                System.out.println(title);
            } else {
                title = "The " + traderType + " has " + healersAvailable + " healers available.";
                System.out.println(title);
            }

            System.out.println("How many healers do you want to buy?\n");
            quantity = Utils.readPlayerInput("-> ", healersAvailable);

            // Ask the player how many he wants to buy
            if (quantity > healersAvailable) {
                Utils.clearConsole();

                if (healersAvailable == 1) {
                    System.out.println("The " + traderType + " has only one healer available.");
                } else {
                    System.out.println("The " + traderType + " has " + healersAvailable + " healers available.");
                }
                Utils.pressEnter();
                continue;
            } else {
                totalPrice = quantity * price;
            }

            System.out.println();

            // Check if player has enough gold and proceed to purchase
            if (player.gold >= totalPrice) {
                player.gold -= totalPrice;
                player.healers += quantity;

                if (quantity == 1) {
                    if (price == 1) {
                        title = "You bought a healer for " + price + " gold!";
                    } else {
                        title = "You bought a healer for " + price + " golds!";
                    }
                } else {
                    if (price == 1) {
                        title = "You bought " + quantity + " healers for " + price + " gold!";
                    } else {
                        title = "You bought " + quantity + " healers for " + price + " golds!";
                    }
                }

                Utils.printHeading(true, false, title);
            } else {
                if (quantity == 1) {
                    title = "You want to buy " + quantity + " healers for " + totalPrice + " golds!";
                } else {
                    title = "You want to buy " + quantity + " healers for " + totalPrice + " golds!";
                }

                Utils.printHeading(false, true, title, " But unfortunately you only have " + player.gold + " golds!");
                Utils.pressEnter();

                Utils.clearConsole();
                Utils.printHeading(true, false, "Would you like to continue with your purchase decision?");

                System.out.println("(1) Yes, I'll adjust the quantity");
                System.out.println("(2) No, maybe later");
                int input = Utils.readPlayerInput("-> ", 2);

                if (input == 1) {
                    Utils.clearConsole();
                    continue;
                } else {
                    break;
                }
            }

            doneBuying = true;
            Utils.pressEnter();
            UIUtils.printStats(true);
        } while (!doneBuying);
    }

    // Method to buy weapons
    public static void buyWeapons(String traderType) {
        Utils.clearConsole();
        boolean doneBuying = false;
        String title;
        String currentCombatWeapon = player.unlockedCombatWeapons.size() > 0
                ? player.unlockedCombatWeapons.get(player.combatWeaponCount - 1)
                : "No weapon";
        String currentDefensiveWeapon = player.unlockedDefensiveEquipments.size() > 0
                ? player.unlockedDefensiveEquipments.get(player.defensiveEquipmentCount - 1)
                : "No weapon";

        title = "Which type of weapon are you interested in purchasing?";

        Utils.printHeading(true, false, title);

        System.out.println("(1) Combat Weapon");
        System.out.println("(2) Defensive Weapon");

        int input = Utils.readPlayerInput("-> ", 2);

        System.out.println();
        do {
            if (input == 1) {
                doneBuying = buyWeaponType(doneBuying, currentCombatWeapon, traderType, "Combat",
                        Assets.combatWeapons, player.combatWeaponCount, player.gold,
                        player.unlockedCombatWeapons);
            } else {
                doneBuying = buyWeaponType(doneBuying, currentDefensiveWeapon, traderType, "Defensive",
                        Assets.defensiveEquipment, player.defensiveEquipmentCount, player.gold,
                        player.unlockedDefensiveEquipments);
            }
        } while (!doneBuying);

    }

    // Method to buy a specific type of weapon
    public static boolean buyWeaponType(boolean doneBuying, String currentWeapon, String traderType, String weaponType,
            String[] weaponArray, int weaponCount, int gold, ArrayList<String> unlockedWeapons) {
        String title;
        Utils.clearConsole();

        Utils.printHeading(false, false,
                "The " + traderType + " has the following " + weaponType + " weapons available:");
        Utils.printHeading(false, true, "Weapon type: " + weaponType,
                "Balance: " + player.gold + " golds");
        System.out.println("Which one are you interested in?\n");
        System.out.println("Balance: " + player.gold + " golds");
        System.out.println();
        int[] prices = new int[weaponArray.length];
        int expense;

        for (int i = 0; i < weaponArray.length; i++) {
            String weapon = weaponArray[i];
            // Showing combatWeapon price
            int price = (int) (Math.random() * (10 + weaponCount * 3) + 10
                    + weaponCount);
            prices[i] = price;

            if (price == 1) {
                System.out.printf("(%d) %s: %d gold\n", i + 1, weapon, price);
            } else {
                System.out.printf("(%d) %s: %d golds\n", i + 1, weapon, price);
            }
        }

        int input = Utils.readPlayerInput("-> ", weaponArray.length);
        System.out.println();

        String selectedWeapon = weaponArray[input - 1];
        String action = weaponType.equals("Combat") ? "wield" : "wear";
        String quality = weaponType.equals("Combat") ? "experience" : "endurance";

        if (input > weaponCount) {
            if (input == weaponCount + 1) {
                expense = prices[input - 1];
            } else {
                Utils.printHeading(false, false,
                        "You need more " + quality + " to " + action + " the " + selectedWeapon
                                + "! Keep training and come back when you're stronger.");

                System.out.println("Would you like to continue with your purchase decision?");
                System.out.println();

                String type = weaponType.equals("Combat") ? "Defensive" : "Combat";

                System.out.println("(1) Yes, I'll choose a different option");
                System.out.println("(2) I want to explore " + type + " weapons");
                System.out.println("(3) Maybe later");
                input = Utils.readPlayerInput("-> ", 3);

                if (input == 1) {
                    doneBuying = buyWeaponType(doneBuying, currentWeapon, traderType, weaponType, weaponArray,
                            weaponCount, gold,
                            unlockedWeapons);
                    ;
                    return doneBuying;
                } else if (input == 2) {
                    buyWeapons(traderType);
                    doneBuying = false;
                    return doneBuying;
                } else {
                    doneBuying = true;
                    return doneBuying;
                }
            }
        } else if (input < weaponCount) {
            Utils.printHeading(false, false,
                    "Your current weapon " + currentWeapon + " is already better than " + selectedWeapon
                            + ". There's no need to buy this one.");

            System.out.println("Would you like to continue with your purchase decision?");
            System.out.println();

            String type = weaponType.equals("Combat") ? "Defensive" : "Combat";

            System.out.println("(1) Yes, I'll choose a different option");
            System.out.println("(2) I want to explore " + type + " weapons");
            System.out.println("(3) Maybe later");
            input = Utils.readPlayerInput("-> ", 3);

            if (input == 1) {
                doneBuying = buyWeaponType(doneBuying, currentWeapon, traderType, weaponType, weaponArray, weaponCount,
                        gold,
                        unlockedWeapons);
                ;
                return doneBuying;
            } else if (input == 2) {
                buyWeapons(traderType);
                doneBuying = false;
                return doneBuying;
            } else {
                doneBuying = true;
                return doneBuying;
            }
        } else {
            Utils.printHeading(false, false,
                    "You already own this weapon. There's no need to buy this " + selectedWeapon + ".");

            System.out.println("Would you like to continue with your purchase decision?");
            System.out.println();

            String type = weaponType.equals("Combat") ? "Defensive" : "Combat";

            System.out.println("(1) Yes, I'll choose a different option");
            System.out.println("(2) I want to explore " + type + " weapons");
            System.out.println("(3) Maybe later");
            input = Utils.readPlayerInput("-> ", 3);

            if (input == 1) {
                doneBuying = buyWeaponType(doneBuying, currentWeapon, traderType, weaponType, weaponArray, weaponCount,
                        gold,
                        unlockedWeapons);
                ;
                return doneBuying;
            } else if (input == 2) {
                buyWeapons(traderType);
                doneBuying = false;
                return doneBuying;
            } else {
                doneBuying = true;
                return doneBuying;
            }
        }

        // Check if player has enough gold and proceed to purchase
        if (gold >= expense) {
            gold -= expense;
            unlockedWeapons.add(selectedWeapon);
            weaponCount++;

            if (expense == 1) {
                title = "You bought a " + weaponType + " weapon, " + selectedWeapon + " for " + expense + " gold!";
            } else {
                title = "You bought a " + weaponType + " weapon, " + selectedWeapon + " for " + expense + " golds!";
            }

            Utils.printHeading(false, false, title);
        } else {
            if (expense == 1) {
                title = "You want to buy a " + weaponType + " weapon " + selectedWeapon + ", for " + expense + " gold!";
            } else {
                title = "You want to buy a " + weaponType + " weapon " + selectedWeapon + ", for " + expense
                        + " golds!";
            }

            Utils.printHeading(false, true, title, " But unfortunately you only have " + player.gold + " golds!");
            Utils.pressEnter();

            System.out.println("Would you like to continue with your purchase decision?");
            System.out.println();

            String type = weaponType.equals("Combat") ? "Defensive" : "Combat";

            System.out.println("(1) Yes, I'll choose a different option");
            System.out.println("(2) I want to explore " + type + " weapons");
            System.out.println("(3) Maybe later");
            input = Utils.readPlayerInput("-> ", 3);

            if (input == 1) {
                buyWeaponType(doneBuying, currentWeapon, traderType, weaponType, weaponArray, weaponCount, gold,
                        unlockedWeapons);
                doneBuying = false;
                return doneBuying;
            } else if (input == 2) {
                buyWeapons(traderType);
                doneBuying = false;
                return doneBuying;
            } else {
                doneBuying = true;
                return doneBuying;
            }
        }

        Utils.pressEnter();
        Utils.clearConsole();
        UIUtils.printPlayerInfo();
        doneBuying = true;
        return doneBuying;
    }

    // Method that gets called when the player is dead
    public static boolean hasPlayerDied(boolean isDead) {
        if (isDead) {
            EventManager.isNewGame = true;
            Utils.clearConsole();
            Utils.printHeading(false, true, "You died!");
            Utils.printHeading(true,
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