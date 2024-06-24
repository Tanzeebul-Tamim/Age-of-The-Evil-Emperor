package main;

import java.util.ArrayList;
import java.util.Random;

public class Actions {
    static Player player = EventManager.player;
    public static int location = 0;

    // Todo Method to calculate a random encounter
    public static void randomEncounter(int paraSeparator, String title) {
        // Random number between 0 and the length of the encounters array
        int encounter = (int) (Math.random() * Assets.actionsOnEncounter.length);

        // Calling the respective methods
        if (Assets.actionsOnEncounter[encounter].equals("Fight")) {
            randomBattle(paraSeparator, title);
        } else if (Assets.actionsOnEncounter[encounter].equals("Walk Away")) {
            System.out.println("The abc walked away"); // Todo
            // do you want to attack?
        } else {
            // talk();
            System.out.println("You're talking"); // Todo
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

        System.out.println("LOCATION: " + Assets.locations[UIUtils.location]);
        System.out.println();

        Utils.printHeading(false, "You encountered a " + enemyName + "." + " You will have to fight him!");
        Utils.pressEnter();
        return battle(paraSeparator, title, enemy);
    }

    // Method to manage and organize battles
    public static boolean[] battle(int paraSeparator, String title, Enemy enemy) {
        String[] name = player.getName().split(" ");
        String lastName = name[name.length - 1];
        String battleTitle = lastName + " vs " + enemy.name;

        // Main battle loop
        while (true) {
            Utils.clearConsole();

            // Print chapter name
            Utils.printHeading(true, title);

            // Print the info of this battle round
            Utils.printHeading(true, "BATTLE");

            System.out.println(battleTitle);

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

                System.out.println(battleTitle);


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

                        Utils.printHeading(true, "***** GAME COMPLETED *****");
                        System.out.printf("Congratulations, %s!\n", player.getName());
                        System.out.println(
                                "You have defeated the EVIL EMPEROR and saved Eldoria from the brink of destruction!");

                        Utils.printSeparator(30);
                        Utils.printSeparator(50);
                    } else {
                        Utils.printHeading(true, "You defeated the " + enemy.name + "!");
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
                Utils.printHeading(true, "BATTLE");
                
                System.out.println(battleTitle);


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
                        continue;
                    }
                } else {
                    if (player.hp == player.maxHp) {
                        Utils.printHeading(false, "You're already at full health.");
                        Utils.pressEnter();
                        continue;
                    } else {
                        Utils.printHeading(false, "You don't have enough healers.");
                        Utils.pressEnter();
                        continue;
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
                
                System.out.println(battleTitle);


                // Chance of 35% escape
                if (enemy.name.equals("THE EVIL EMPEROR")) {
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
    public static void finalBattle(int paraSeparator, String title) {
        // creating the evil emperor object and letting the player fight against him
        battle(paraSeparator, title, new Enemy("THE EVIL EMPEROR", 200));

        // Printing the proper ending
        UIUtils.printCompletionMessage();
        EventManager.isRunning = false;
    }

    // Method to shop
    public static void shop(String[] trade) {
        String traderType = trade[0];
        String product = trade[1];

        Utils.clearConsole();
        Utils.printHeading(true, "You meet a mysterious stranger!");
        System.out.printf("Seems like he is a %s.\n He's offering %s at a reasonable price.", traderType, product);

        Utils.pressEnter();

        // Ask the player to buy
        System.out.printf("Do you want to buy some %s?\n(1) Yes!\n(2) No thanks\n", product);
        int input = Utils.readPlayerInput("-> ", 2);

        if (input == 1) {
            if (product.equals("healers")) {
                buyHealers(traderType);
            } else if (product.equals("weapons")) {
                buyWeapons(traderType);
            } else {
                String title = "What would you like to buy?";
                Utils.clearConsole();

                Utils.printSeparator(title.length());
                System.out.println(title);
                Utils.printSeparator(title.length());

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
        int price = (int) (Math.random() * (10 + player.healers * 3) + 10 + player.healers);

        if (price == 1) {
            System.out.println("Healer: " + price + " gold");
        } else {
            System.out.println("Healer: " + price + " golds");
        }

        Utils.clearConsole();
        boolean doneBuying = false;
        int totalPrice;
        int quantity;
        String title;

        do {
            // Show available quantity
            if (healersAvailable == 1) {
                title = "The " + traderType + " has only one healer available.";

                Utils.printSeparator(title.length());
                System.out.println(title);
                Utils.printSeparator(title.length());
            } else {
                title = "The " + traderType + " has " + healersAvailable + " healers available.";

                Utils.printSeparator(title.length());
                System.out.println(title);
                Utils.printSeparator(title.length());
            }

            System.out.println();

            System.out.println("How many healers do you want to buy?\n");
            quantity = Utils.readPlayerInput("-> ", healersAvailable);

            System.out.println();

            // Ask the player how many he wants to buy
            if (quantity > healersAvailable) {
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

            // Check if player has enough gold and proceed to purchase
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
                Utils.pressEnter();
                buyHealers(traderType);
                break;
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
        String currentWeapon = player.unlockedCombatWeapons.get(player.combatWeaponCount);

        title = "Which type of weapon are you interested in purchasing?";

        Utils.printSeparator(title.length());
        System.out.println(title);
        Utils.printSeparator(title.length());

        System.out.println("(1) Combat Weapon");
        System.out.println("(2) Defensive Weapon");

        int input = Utils.readPlayerInput("-> ", 2);

        System.out.println();
        do {
            if (input == 1) {
                doneBuying = buyWeaponType(doneBuying, currentWeapon, traderType, "combat",
                        Assets.combatWeapons, player.combatWeaponCount, player.gold,
                        player.unlockedCombatWeapons);
            } else {
                doneBuying = buyWeaponType(doneBuying, currentWeapon, traderType, "defensive",
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

        System.out.println("The " + traderType + " has the following " + weaponType + " weapons available:");
        System.out.println("Which one are you interested in?");
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

        if (input > weaponCount) {
            if (input == weaponCount + 1) {
                expense = prices[input - 1];
            } else {
                System.out.printf(
                        "You need more experience to wield the %s. Keep training and come back when you're stronger.\n",
                        selectedWeapon);
                Utils.pressEnter();
                doneBuying = false;
                return doneBuying;
            }
        } else if (input < weaponCount) {
            System.out.printf(
                    "Your current weapon %s is already better than %s. There's no need to buy this one.\n",
                    currentWeapon, selectedWeapon);
            Utils.pressEnter();
            doneBuying = false;
            return doneBuying;
        } else {
            System.out.printf("You already own this weapon. There's no need to buy this %s.\n",
                    selectedWeapon);
            Utils.pressEnter();
            doneBuying = false;
            return doneBuying;
        }

        // Check if player has enough gold and proceed to purchase
        if (gold >= expense) {
            gold -= expense;
            unlockedWeapons.add(selectedWeapon);
            weaponCount++;

            if (expense == 1) {
                title = "You bought a " + weaponType + " weapon, " + selectedWeapon + " for " + expense + "gold!";
            } else {
                title = "You bought a " + weaponType + " weapon, " + selectedWeapon + " for " + expense + "golds!";
            }

            Utils.printHeading(false, title);
        } else {
            title = "You don't have enough gold to buy a " + selectedWeapon;

            Utils.printHeading(false, title);
            Utils.pressEnter();
            buyWeapons(traderType);
            doneBuying = false;
            return doneBuying;
        }

        Utils.pressEnter();
        UIUtils.printStats(true);
        doneBuying = true;
        return doneBuying;
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
