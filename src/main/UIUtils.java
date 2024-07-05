package main;

import java.time.Year;
import java.util.Scanner;

public class UIUtils {
    static Scanner scanner = new Scanner(System.in);
    public static Player player;

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
            Utils.printHeading(true, true, "What's your name?");
            name = Utils.formatName(scanner.nextLine().trim());

            // Check if name is empty
            if (name.isEmpty()) {
                Utils.isEmptyInput("Name");
                continue;
            }

            // Asking the player if he wants to correct his choice
            Utils.clearConsole();
            Utils.printHeading(true, true, "Your name is " + name + ".", "Do you want to keep this name?");

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
        title = "UNLOCKED SKILLS";
        Utils.printHeading(false, true, title);

        System.out.print("COMBAT SKILLS: ");
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

        System.out.print("DEFENSIVE SKILLS: ");
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
        title = "UNLOCKED WEAPONS";
        Utils.printHeading(false, true, title);

        System.out.print("COMBAT WEAPONS: ");
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

        System.out.print("DEFENSIVE WEAPONS: ");
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
        Utils.printHeading(false, true, "PLAYER STATS");

        System.out.println("NAME: " + player.name);
        System.out.println("LOCATION: " + player.location);
        System.out.println("HP: " + player.hp + "/" + player.maxHp);
        System.out.println("XP: " + player.xp);
        System.out.println("GOLD: " + player.gold);
        System.out.println("HEALER: " + player.healers);
        System.out.println("ENEMIES KILLED: " + player.enemiesKilled);
        Utils.printSeparator(24);

        System.out.println();

        if (pressEnter) {
            Utils.pressEnter();
        }
    }

    // Printing the main menu
    public static void printMenu() {
        Utils.clearConsole();
        Utils.printHeading(true, true, "Menu");
        System.out.println("Choose an action:\n");

        System.out.println("(1) Continue on your journey");
        System.out.println("(2) Character Info");
        System.out.println("(3) Exit Game");
    }

    // Let player take a decision
    public static int choose(int lineWidth, String[] titles, String intOut, String heading, String folderName,
            String fileName,
            String... choices) {
        Utils.clearConsole();

        Utils.storyPrinter(true, lineWidth, titles, intOut, folderName, false, fileName);

        Utils.printHeading(true, true, heading);

        for (int i = 0; i < choices.length; i++) {
            String choice = choices[i];
            System.out.printf("(%d) %s\n", i + 1, choice);
        }

        // Get the player's choice
        return Utils.readPlayerInput("-> ", choices.length);
    }

    // Method to print game completion message
    public static void printCompletionMessage() {
        Utils.clearConsole();

        Utils.printHeading(true, true, "AGE OF THE EVIL EMPEROR");

        System.out.println("TEXT RPG BY - TANZEEBUL TAMIM");
        System.out.println(Year.now().getValue() + " Tanzeebul Tamim. All rights reserved.");
        System.out.println("Authenticity: This game is a work of fiction.");
        Utils.printSeparator(50);

        Utils.pressEnter();
    }

}