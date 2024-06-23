package main;

import java.time.Year;
import java.util.Scanner;

public class UIUtils {
    static Scanner scanner = new Scanner(System.in);
    public static Player player;
    public static int location = 0;

    // Method to print game opening
    public static void printOpening() {
        Utils.clearConsole();

        Utils.printSeparator(40);
        Utils.printSeparator(30);

        System.out.println("AGE OF THE EVIL EMPEROR");
        System.out.println("TEXT RPG BY - TANZEEBUL TAMIM");

        Utils.printSeparator(30);
        Utils.printSeparator(40);

        Utils.pressEnter();
    }

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
        player = EventManager.player;
        printStats(false);
        String title;

        // Printing the unlocked skills
        title = "Unlocked Skills";
        Utils.printHeading(true, title);

        System.out.print("Combat Skills: ");
        if (player.unlockedCombatSkills.size() == 0) {
            System.out.print("None\n");
        } else {
            for (int i = 0; i < player.unlockedCombatSkills.size(); i++) {
                String skill = player.unlockedCombatSkills.get(i);

                if (i == player.unlockedCombatSkills.size() - 1) {
                    System.out.print(skill + "\n");
                } else {
                    System.out.print(skill + ", ");
                }
            }
        }

        System.out.print("Defensive Skills: ");
        if (player.unlockedDefensiveSkills.size() == 0) {
            System.out.print("None\n");
        } else {
            for (int i = 0; i < player.unlockedDefensiveSkills.size(); i++) {
                String skill = player.unlockedDefensiveSkills.get(i);

                if (i == player.unlockedDefensiveSkills.size() - 1) {
                    System.out.print(skill + "\n");
                } else {
                    System.out.print(skill + ", ");
                }
            }
        }
        Utils.printSeparator(title.length() * 2);

        System.out.println();

        // Printing the unlocked weapons
        title = "Unlocked Weapons";
        Utils.printHeading(true, title);

        System.out.print("Combat Weapons: ");
        if (player.unlockedCombatWeapons.size() == 0) {
            System.out.print("None\n");
        } else {
            for (int i = 0; i < player.unlockedCombatWeapons.size(); i++) {
                String weapon = player.unlockedCombatWeapons.get(i);

                if (i == player.unlockedCombatWeapons.size() - 1) {
                    System.out.print(weapon + "\n");
                } else {
                    System.out.print(weapon + ", ");
                }
            }
        }

        System.out.print("Defensive Weapons: ");
        if (player.unlockedDefensiveEquipments.size() == 0) {
            System.out.print("None\n");
        } else {
            for (int i = 0; i < player.unlockedDefensiveEquipments.size(); i++) {
                String skill = player.unlockedDefensiveEquipments.get(i);

                if (i == player.unlockedDefensiveEquipments.size() - 1) {
                    System.out.print(skill + "\n");
                } else {
                    System.out.print(skill + ", ");
                }
            }
        }
        Utils.printSeparator(title.length() * 2);

        System.out.println();

        // Press enter to Continue
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
        Utils.printSeparator(24);

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

    // Method to print game completion message
    public static void printCompletionMessage(Player player) {
        Utils.clearConsole();

        Utils.printSeparator(50);
        Utils.printSeparator(30);

        Utils.printHeading(true, "***** GAME COMPLETED *****");
        System.out.printf("Congratulations, %s!\n", player.getName());
        System.out.println("You have defeated the EVIL EMPEROR and saved Eldoria from the brink of destruction!");

        Utils.printSeparator(30);
        Utils.printSeparator(50);

        Utils.pressEnter();
        Utils.clearConsole();

        Utils.storyPrinter(false, 100, "Epilogue", "outro", true, "para1.txt", "para2.txt");

        Utils.printHeading(true, "AGE OF THE EVIL EMPEROR");
        System.out.println();
        System.out.println("TEXT RPG BY - TANZEEBUL TAMIM");
        System.out.println("© " + Year.now().getValue() + " Tanzeebul Tamim. All rights reserved.");
        System.out.println("Authenticity: This game is a work of fiction.");

        Utils.printSeparator(50);

        Utils.pressEnter();
    }

}