package net.rpg.main;

import java.util.Scanner;

public class GameLogic {
    static Scanner scanner = new Scanner(System.in);

    static Player player;

    // Method to get user input from console
    public static int readInt(String prompt, int userChoices) {
        int input;

        do {
            System.out.println(prompt);
            try {
                input = Integer.parseInt(scanner.next());
                
                if (input < 1 || input > userChoices) {
                    System.out.print("\nPlease choose between option ");
                    for (int i = 1; i < userChoices; i++) {
                        if (i == userChoices - 1) {
                            System.out.printf("%d ", i);
                        } else {
                            System.out.printf("%d, ", i);
                        }
                    }
                    System.out.printf("and %d\n", userChoices);
                }
            } catch (Exception e) {
                input = -1;
                System.out.println("\nPlease enter an integer!");
            }
        } while (input < 1 || input > userChoices);
        return input;
    }

    // Method to simulate clearing out the console
    public static void clearConsole() {
        for (int i = 0; i < 100; i++)
            System.out.println();
    }

    // Method to print a separator with length n
    public static void printSeparator(int n) {
        for (int i = 0; i < n; i++)
            System.out.print("-");
        System.out.println();
    }

    // Method to print a heading
    public static void printHeading(String title) {
        printSeparator(30);
        System.out.println(title);
        printSeparator(30);
    }

    // Method to stop the game until user enters anything
    public static void anythingToContinue() {
        System.out.println("\nEnter anything to continue....");
        scanner.next();
    }

    // Method start the game
    public static void startGame() {
        boolean nameSet = false;
        String name;

        // Print title screen
        clearConsole();
        printSeparator(40);
        printSeparator(30);
        System.out.println("AGE OF THE EVIL EMPEROR");
        System.out.println("TEXT RPG BY - TANZEEBUL TAMIM");
        printSeparator(30);
        printSeparator(40);
        anythingToContinue();

        // Getting the player name
        do {
            clearConsole();
            printHeading("What's your name?");
            name = scanner.next();

            // Asking the player if he wants to correct his choice
            clearConsole();
            printHeading("Your name is " + name + "\nDo you want to keep this name?");
            System.out.println("(1) Yes!");
            System.out.println("(2) No, I want to change my name.");
            int input = readInt("->", 2);
            System.out.println();

            if (input == 1) nameSet = true;
        } while (!nameSet);

        // Create new player object with the name
        player = new Player(name);
    }
}
