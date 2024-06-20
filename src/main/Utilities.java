package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//* Utility Methods

public class Utilities {
    static Scanner scanner = new Scanner(System.in);

    // Method to get user input from console
    public static int readInt(String prompt, int userChoices) {
        int input;

        do {
            System.out.println(prompt);
            try {
                input = Integer.parseInt(scanner.nextLine());

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

    // Method to handle empty user inputs
    public static void isEmptyInput(String fieldName) {
        Utilities.clearConsole();
        Utilities.printHeading(fieldName + " cannot be empty!");
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
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

    // Method to stop the game until user enters something
    public static void pressEnter() {
        System.out.println("\nPress Enter to continue....");
        scanner.nextLine();
    }

    // Method to print each paragraphs of the story as user presses Enter
    public static void storyPrinter(String folderName, String... paragraphFiles) {
        String projectRoot;

        // Check if running from an IDE and adjusting path accordingly
        if (System.getProperty("java.class.path").contains("bin")) {
            projectRoot = "src/resources/";
        } else {
            projectRoot = "../src/resources/";
        }

        for (int i = 0; i < paragraphFiles.length; i++) {
            String fileName = paragraphFiles[i];
            File file = new File(projectRoot + folderName + "/" + fileName);

            try (Scanner fileScanner = new Scanner(file)) {
                while (fileScanner.hasNextLine()) {
                    String paragraph = fileScanner.nextLine().trim();

                    // Skip empty lines
                    if (paragraph.isEmpty()) {
                        continue;
                    }

                    String[] sentences = paragraph.split("\\.");

                    printSeparator(100);
                    for (String sentence : sentences) {
                        System.out.println(sentence.trim() + ".");
                    }
                    printSeparator(100);

                    if (i == paragraphFiles.length - 1) {
                        Utilities.pressEnter();
                    } else {
                        System.out.println("\nPress Enter to read more...");
                        System.out.println();
                        scanner.nextLine();
                    }
                }
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + e.getMessage());
            }
        }
    }
}