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
    public static void printHeading(String... titles) {
        int maxLen = titles[0].length();
        for (int i = 1; i < titles.length; i++) {
            String title = titles[i];

            if (title.length() > maxLen) {
                maxLen = title.length();
            }
        }

        printSeparator(maxLen * 2);
        for (int i = 0; i < titles.length; i++) {
            String title = titles[i];
            System.out.println(printTab(maxLen * 2) + title);
        }
        printSeparator(maxLen * 2);
    }

    // Method to print tabs for chapter title
    public static String printTab(int paraSeparator) {
        int tabCount = paraSeparator / 4;
        String tab = "";
        double dTabCount = paraSeparator / 16D;
        int roundedDTabCount = (int) Math.ceil(dTabCount);

        if (tabCount < 1) {
            for (int i = 0; i < roundedDTabCount; i++) {
                tab += " ";
            }
        } else {
            for (int i = 0; i < tabCount; i++) {
                tab += " ";
            }
        }

        return tab;
    }

    public static String printTab(int paraSeparator, boolean story) {
        int tabCount = paraSeparator / 16;
        String tab = "";

        for (int i = 0; i < tabCount; i++) {
            tab += "\t";
        }

        return tab;
    }

    // Method to stop the game until user enters something
    public static void pressEnter() {
        System.out.println("\nPress Enter to continue....");
        scanner.nextLine();
    }

    // Method to print each parts of the story
    public static void storyPrinter(int paraSeparator, String title, String folderName, String... fileNames) {
        Utilities.clearConsole();

        Utilities.printSeparator(paraSeparator);
        System.out.println(printTab(paraSeparator, true) + title);
        Utilities.printSeparator(paraSeparator);

        System.out.println();

        Utilities.paragraphPrinter(paraSeparator, folderName, fileNames);
    }

    // wrap text based on a specified character limit
    public static void printWrappedText(String text, int lineWidth) {
        int index = 0;
        while (index < text.length()) {
            int endIndex = Math.min(index + lineWidth, text.length());
            String line = text.substring(index, endIndex);

            if (endIndex < text.length() && text.charAt(endIndex) != ' ') {
                int lastSpaceIndex = line.lastIndexOf(' ');
                if (lastSpaceIndex != -1) {
                    line = line.substring(0, lastSpaceIndex);
                    endIndex = index + lastSpaceIndex + 1;
                }
            }

            System.out.println(line.trim());
            index = endIndex;
        }
    }

    // Method to print each paragraphs of the story as user presses Enter
    public static void paragraphPrinter(int lineWidth, String folderName, String... paragraphFiles) {
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
                        printWrappedText(sentence.trim() + ".", lineWidth);
                    }

                    if (i == paragraphFiles.length - 1) {
                        printSeparator(100);
                        Utilities.pressEnter();
                    } else {
                        System.out.println("\nPress Enter");
                        printSeparator(100);
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