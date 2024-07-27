package main;

import java.util.ArrayList;
import java.util.Random;

public class Actions {
    static Player player = EventManager.player; // Get the player object
    static int paraSeparator = 100; // Separator width

    // Calculating enemy characters' xp according to the player's current health
    static int enemyXp = player.xp + 5;
    static int evilEmperorXp = player.xp + 50;

    // Creating a random encounter with a random entity
    public static void randomEncounter(
            String[] titles,
            String intOut,
            String folderName,
            String fileNameIfFled,
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

        // Print chapter name
        Utils.printSeparator(paraSeparator);
        if (intOut.equals("i")) {
            System.out.println(Utils.printTab(paraSeparator, true) + titles[0] + " - INTRO");
        } else if (intOut.equals("o")) {
            System.out.println(Utils.printTab(paraSeparator, true) + titles[0] + " - OUTRO");
        }
        System.out.println(Utils.printTab(paraSeparator, true) + titles[1]);
        Utils.printSeparator(paraSeparator);

        System.out.println();

        System.out.println("LOCATION: " + player.location);
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
                boolean[] battleResult = battle(paraSeparator, titles, intOut, randomEntity);

                if (battleResult[0])
                    return;

                if (!battleResult[1])
                    Utils.storyPrinter(true, paraSeparator, titles, intOut, folderName, true, fileNameIfNotFled);
                else
                    Utils.storyPrinter(true, paraSeparator, titles, intOut, folderName, true, fileNameIfFled);
            } else if (action.equals("Taunt")) { // The enemy taunts you
                Utils.printHeading(false, false, "You encountered a " + randomEntity + "."
                        + "\nWith a menacing grin, he stops to taunt you. Their words are filled with threats and malice.");

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
                    Enemy enemy = new Enemy(randomEntity, enemyXp);
                    boolean[] battleResult = fight(paraSeparator, titles, intOut, enemy);

                    if (battleResult[0])
                        return;

                    if (!battleResult[1])
                        Utils.storyPrinter(true, paraSeparator, titles, intOut, folderName, true, fileNameIfNotFled);
                    else
                        Utils.storyPrinter(true, paraSeparator, titles, intOut, folderName, true, fileNameIfFled);
                }
            } else { // The enemy walks away
                Utils.printHeading(true, false, "You encountered a " + randomEntity + "."
                        + " But luckily, he walks away without engaging you.");

                System.out.println("Do you want to attack him?\n");

                System.out.println("(1) Attack from behind!");
                System.out.println("(2) Let go");
                input = Utils.readPlayerInput("-> ", 2);

                if (input == 1) {
                    Enemy enemy = new Enemy(randomEntity, enemyXp);
                    boolean[] battleResult = fight(paraSeparator, titles, intOut, enemy);

                    if (battleResult[0])
                        return;

                    if (!battleResult[1])
                        Utils.storyPrinter(true, paraSeparator, titles, intOut, folderName, true, fileNameIfNotFled);
                    else
                        Utils.storyPrinter(true, paraSeparator, titles, intOut, folderName, true, fileNameIfFled);
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
                    int healerCount = (int) (Math.random() * maxHealers);

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
                            Utils.printHeading(
                                    false,
                                    false,
                                    "You have received a healer from the " + randomEntity + "!");
                        } else {
                            Utils.printHeading(
                                    false,
                                    false,
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
    public static boolean[] battle(int paraSeparator, String[] titles, String intOut, String enemyName) {
        Enemy enemy = new Enemy(enemyName, enemyXp);

        System.out.println();
        Utils.printHeading(false, false, "You encountered a " + enemyName + "." + " You have to defeat him!");

        Utils.pressEnter();

        return fight(paraSeparator, titles, intOut, enemy);
    }

    // Overloading the previous method to create a random battle with a random enemy
    // (to be used when using directly)
    public static boolean[] randomBattle(int paraSeparator, String[] titles, String intOut) {
        int randomIndex = (int) (Math.random() * Assets.enemies.length);
        String enemyName = Assets.enemies[randomIndex];
        Enemy enemy = new Enemy(enemyName, enemyXp);

        Utils.clearConsole();

        // Print chapter name
        Utils.printSeparator(paraSeparator);
        if (intOut.equals("i")) {
            System.out.println(Utils.printTab(paraSeparator, true) + titles[0] + " - INTRO - BATTLE");
        } else if (intOut.equals("o")) {
            System.out.println(Utils.printTab(paraSeparator, true) + titles[0] + " - OUTRO - BATTLE");
        }
        System.out.println(Utils.printTab(paraSeparator, true) + titles[1]);
        Utils.printSeparator(paraSeparator);

        System.out.println();

        System.out.println("LOCATION: " + player.location);
        System.out.println();

        Utils.printHeading(false, false, "You encountered a " + enemyName + "." + " You have to defeat him!");
        Utils.pressEnter();
        return fight(paraSeparator, titles, intOut, enemy);
    }

    // Method to manage and organize battles
    public static boolean[] fight(int paraSeparator, String[] titles, String intOut, Enemy enemy) {
        String[] name = player.getName().split(" ");
        String lastName = name[name.length - 1];
        String battleTitle = lastName + " VS " + enemy.name;

        // Main battle loop
        while (true) {
            Utils.clearConsole();

            // Print chapter name
            Utils.printSeparator(paraSeparator);
            if (intOut.equals("i")) {
                System.out.println(Utils.printTab(paraSeparator, true) + titles[0] + " - INTRO - BATTLE");
            } else if (intOut.equals("o")) {
                System.out.println(Utils.printTab(paraSeparator, true) + titles[0] + " - OUTRO - BATTLE");
            }
            System.out.println(Utils.printTab(paraSeparator, true) + titles[1]);
            Utils.printSeparator(paraSeparator);

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
                Utils.printSeparator(paraSeparator);
                if (intOut.equals("i")) {
                    System.out.println(Utils.printTab(paraSeparator, true) + titles[0] + " - INTRO - BATTLE");
                } else if (intOut.equals("o")) {
                    System.out.println(Utils.printTab(paraSeparator, true) + titles[0] + " - OUTRO - BATTLE");
                }
                System.out.println(Utils.printTab(paraSeparator, true) + titles[1]);
                Utils.printSeparator(paraSeparator);

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
                        Utils.printSeparator(80);
                        Utils.printHeading(false, true, "***** GAME COMPLETED *****");
                        Utils.printSeparator(80);

                        String title = "\nCongratulations, " + player.getName() + "!\n";
                        System.out.println(title);
                        Utils.printSeparator(title.length());
                        System.out.println(
                                "You have defeated the EVIL EMPEROR and saved Eldoria from the brink of destruction!");
                    } else {
                        Utils.printHeading(true, true, "You defeated the " + enemy.name + "!");
                    }

                    // Increase player hp
                    player.xp += enemy.xp;
                    System.out.println("You earned " + enemy.xp + " XP!");

                    // Random drops
                    int healerCount = enemy.name.equals("THE EVIL EMPEROR") ? 15 : (int) (Math.random() * enemy.xp);
                    int goldEarned = enemy.name.equals("THE EVIL EMPEROR") ? 100 : (int) (Math.random() * enemy.xp);

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
                Utils.printHeading(false, true, titles);

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

                        Utils.printHeading(
                                true,
                                false,
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
                Utils.printHeading(false, true, titles);

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
                        System.out.println("In the chaos, you've sustained " + damageTaken + " damage!");
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
    public static boolean[] finalBattle(int paraSeparator, String[] titles, String intOut) {
        // creating the evil emperor object and letting the player fight against him
        // Todo - Player taking too much damage. Fix this issue
        return fight(paraSeparator, titles, intOut, new Enemy("THE EVIL EMPEROR", evilEmperorXp));
    }

    // Method to shop
    public static void shop(String[] traderAndProduct) {
        String traderType = traderAndProduct[0];
        String product = traderAndProduct[1];

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
            if (player.gold < price) {
                if (price == 1) {
                    title = "A healer costs " + price + " gold!";
                } else {
                    title = "A healer costs " + price + " golds!";
                }

                Utils.printHeading(false, true, title,
                        "But unfortunately you only have " + player.gold + " golds!");
                Utils.pressEnter();
                break;
            }

            if (price == 1) {
                if (player.gold == 1) {
                    Utils.printHeading(false, false, "Item: Healer", "Price: " + price + " gold",
                            "Balance: " + player.gold + " gold", "Restores: 15 HP");
                } else {
                    Utils.printHeading(false, false, "Item: Healer", "Price: " + price + " gold",
                            "Balance: " + player.gold + " golds", "Restores: 15 HP");
                }
            } else {
                if (player.gold == 1) {
                    Utils.printHeading(false, false, "Item: Healer", "Price: " + price + " golds",
                            "Balance: " + player.gold + " gold", "Restores: 15 HP");
                } else {
                    Utils.printHeading(false, false, "Item: Healer", "Price: " + price + " golds",
                            "Balance: " + player.gold + " golds", "Restores: 15 HP");
                }
            }

            // Show available quantity
            if (healersAvailable == 1) {
                title = "The " + traderType + " has only one healer available.";
                System.out.println(title);

                quantity = 1;
                Utils.pressEnter();
            } else {
                title = "The " + traderType + " has " + healersAvailable + " healers available.";
                System.out.println(title + "\nHow many do you want to buy?\n");

                quantity = Utils.readPlayerInput("-> ", healersAvailable);
            }

            // Calculate the total price
            totalPrice = quantity * price;
            Utils.clearConsole();

            // Check if player has enough gold and proceed to purchase
            if (player.gold >= totalPrice) {
                player.gold -= totalPrice;
                player.healers += quantity;

                if (quantity == 1) {
                    if (price == 1) {
                        title = "You bought a healer for " + totalPrice + " gold!";
                    } else {
                        title = "You bought a healer for " + totalPrice + " golds!";
                    }
                } else {
                    if (price == 1) {
                        title = "You bought " + quantity + " healers for " + totalPrice + " gold!";
                    } else {
                        title = "You bought " + quantity + " healers for " + totalPrice + " golds!";
                    }
                }

                Utils.printHeading(true, false, title);
            } else {
                if (price == 1) {
                    title = "You want to buy " + quantity + " healers for " + totalPrice + " gold!";
                } else {
                    title = "You want to buy " + quantity + " healers for " + totalPrice + " golds!";
                }

                Utils.printHeading(false, true, title,
                        "But unfortunately you only have " + player.gold + " golds!");
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

    // Todo - after switching to combat weapon list from defensive weapon list or
    // vice versa, when you try to exit the shop method, it takes you to the
    // list of the opposite type of weapons, it properly should exit the shop
    // method and continue to the story. Fix this issue.

    // Method to buy weapons
    public static void buyWeapons(String traderType) {
        Utils.clearConsole();
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
        if (input == 1) {
            buyWeaponType(
                    false,
                    currentCombatWeapon,
                    traderType,
                    "Combat",
                    Assets.combatWeapons,
                    player.combatWeaponCount,
                    player.gold,
                    player.unlockedCombatWeapons);
        } else {
            buyWeaponType(
                    false,
                    currentDefensiveWeapon,
                    traderType,
                    "Defensive",
                    Assets.defensiveEquipment,
                    player.defensiveEquipmentCount,
                    player.gold,
                    player.unlockedDefensiveEquipments);
        }
    }

    // Method to switch weapon type that you want to buy
    public static void buyWeapons(String traderType, String weaponType) {
        Utils.clearConsole();
        boolean doneBuying = false;
        String currentCombatWeapon = player.unlockedCombatWeapons.size() > 0
                ? player.unlockedCombatWeapons.get(player.combatWeaponCount - 1)
                : "No weapon";
        String currentDefensiveWeapon = player.unlockedDefensiveEquipments.size() > 0
                ? player.unlockedDefensiveEquipments.get(player.defensiveEquipmentCount - 1)
                : "No weapon";
        do {
            if (weaponType.equals("Combat")) {
                doneBuying = buyWeaponType(
                        doneBuying,
                        currentCombatWeapon,
                        traderType,
                        "Combat",
                        Assets.combatWeapons,
                        player.combatWeaponCount,
                        player.gold,
                        player.unlockedCombatWeapons);
            } else {
                doneBuying = buyWeaponType(
                        doneBuying,
                        currentDefensiveWeapon,
                        traderType,
                        "Defensive",
                        Assets.defensiveEquipment,
                        player.defensiveEquipmentCount,
                        player.gold,
                        player.unlockedDefensiveEquipments);
            }
        } while (!doneBuying);
    }

    // Method to print choices while exploring available weapons from a trader
    public static boolean weaponPurchaseDecisions(
            boolean doneBuying,
            String currentWeapon,
            String traderType,
            String oppositeType,
            String chosenType,
            String[] weaponArray,
            int weaponCount,
            int gold,
            ArrayList<String> unlockedWeapons) {
        System.out.println("Would you like to continue with your purchase decision?");
        System.out.println();

        System.out.println("(1) Yes, I'll choose a different option");
        System.out.println("(2) I want to explore " + oppositeType + " weapons");
        System.out.println("(3) Maybe later");
        int input = Utils.readPlayerInput("-> ", 3);

        if (input == 1) {
            doneBuying = buyWeaponType(
                    doneBuying,
                    currentWeapon,
                    traderType,
                    chosenType,
                    weaponArray,
                    weaponCount,
                    gold,
                    unlockedWeapons);
            return doneBuying;
        } else if (input == 2) {
            buyWeapons(traderType, oppositeType);
            doneBuying = false;
            return doneBuying;
        } else {
            doneBuying = true;
            return doneBuying;
        }
    }

    // Method to buy a specific type of weapon
    public static boolean buyWeaponType(
            boolean doneBuying,
            String currentWeapon,
            String traderType,
            String weaponType,
            String[] weaponArray,
            int weaponCount,
            int gold,
            ArrayList<String> unlockedWeapons) {
        String title;
        Utils.clearConsole();

        Utils.printHeading(false, false,
                "The " + traderType + " has the following " + weaponType + " weapons available:");
        Utils.printHeading(false, true, "Weapon type: " + weaponType,
                "Balance: " + player.gold + " golds");
        System.out.println("Which one are you interested in?\n");
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
                if (i + 1 < 10) {
                    System.out.printf("(0%d) %s: %d gold\n", i + 1, weapon, price);
                } else {
                    System.out.printf("(%d) %s: %d gold\n", i + 1, weapon, price);
                }
            } else {
                if (i + 1 < 10) {
                    System.out.printf("(0%d) %s: %d golds\n", i + 1, weapon, price);
                } else {
                    System.out.printf("(%d) %s: %d golds\n", i + 1, weapon, price);
                }
            }
        }

        int input = Utils.readPlayerInput("-> ", weaponArray.length);
        System.out.println();

        String selectedWeapon = weaponArray[input - 1];
        String action = weaponType.equals("Combat") ? "wield" : "wear";
        String quality = weaponType.equals("Combat") ? "experience" : "endurance";
        String oppositeWpnType = weaponType.equals("Combat") ? "Defensive" : "Combat";

        if (input > weaponCount) {
            if (input == weaponCount + 1) {
                expense = prices[input - 1];
            } else {
                Utils.printHeading(false, false,
                        "You need more " + quality + " to " + action + " the " + selectedWeapon
                                + "! Keep training and come back when you're worthy.");

                return weaponPurchaseDecisions(
                        doneBuying,
                        currentWeapon,
                        traderType,
                        oppositeWpnType,
                        weaponType,
                        weaponArray,
                        weaponCount,
                        gold,
                        unlockedWeapons);
            }
        } else if (input < weaponCount) {
            Utils.printHeading(
                    false,
                    false,
                    "Your current weapon " + currentWeapon + " is already better than " + selectedWeapon
                            + ". There's no need to buy this one.");

            return weaponPurchaseDecisions(
                    doneBuying,
                    currentWeapon,
                    traderType,
                    oppositeWpnType,
                    weaponType,
                    weaponArray,
                    weaponCount,
                    gold,
                    unlockedWeapons);
        } else {
            Utils.printHeading(
                    false,
                    false,
                    "You already own this weapon. There's no need to buy a " + selectedWeapon + ".");

            return weaponPurchaseDecisions(
                    doneBuying,
                    currentWeapon,
                    traderType,
                    oppositeWpnType,
                    weaponType,
                    weaponArray,
                    weaponCount,
                    gold,
                    unlockedWeapons);
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

            Utils.printHeading(
                    false,
                    false,
                    title,
                    " But unfortunately you only have " + player.gold + " golds!");
            Utils.pressEnter();

            return weaponPurchaseDecisions(
                    doneBuying,
                    currentWeapon,
                    traderType,
                    oppositeWpnType,
                    weaponType,
                    weaponArray,
                    weaponCount,
                    gold,
                    unlockedWeapons);
        }

        Utils.pressEnter();
        Utils.clearConsole();
        UIUtils.printPlayerInfo();
        doneBuying = true;
        return doneBuying;
    }

    // Todo - add a feature to ask the player if he wants to continue playing, if
    // yes, revive the player for a cost of some golds

    // Method that gets called when the player is dead
    public static boolean hasPlayerDied(boolean isDead) {
        if (isDead) {
            EventManager.isNewGame = true;
            Utils.clearConsole();
            Utils.printHeading(false, true, "You died!");
            Utils.printHeading(
                    true,
                    false,
                    "You earned " + player.xp + " XP on your journey. Try to earn more next time!");
            System.out.println("Thank you for playing my game. I hope you enjoyed it!");
            Utils.pressEnter();

            EventManager.isRunning = false;
        }

        return isDead;
    }
}