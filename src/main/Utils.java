package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Todo - add pause game method
// Todo - add background music method
// Todo - add clear console method which actually clears the console instead of printing numerous new lines
//* Utility Methods

public class Utils {
    static Scanner scanner = new Scanner(System.in);

    // Method to get user input from console
    public static int readPlayerInput(String prompt, int userChoices) {
        int input;

        do {
            System.out.println(prompt);
            try {
                input = Integer.parseInt(scanner.nextLine());

                if (input < 1 || input > userChoices) {
                    if (userChoices > 4) {
                        System.out.printf("\nPlease choose between 1 to %d\n", userChoices);
                    } else {
                        System.out.print("\nPlease choose between ");
                        for (int i = 1; i < userChoices; i++) {
                            if (i == userChoices - 1) {
                                System.out.printf("%d ", i);
                            } else {
                                System.out.printf("%d, ", i);
                            }
                        }
                        System.out.printf("and %d\n", userChoices);
                    }
                }
            } catch (Exception e) {
                input = -1;
                System.out.println("\nPlease enter an integer!");
            }
        } while (input < 1 || input > userChoices);
        return input;
    }

    // Method to format player name so that the initials always begin with uppercase
    // letters
    public static String formatName(String name) {
        // Split the name by spaces to handle multiple words
        String[] words = name.split(" ");
        StringBuilder formattedName = new StringBuilder();

        for (String word : words) {
            if (word.length() > 0) {
                // Convert the first character to uppercase and the rest to lowercase
                formattedName.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }

        // Trim the trailing space and return the formatted name
        return formattedName.toString().trim();
    }

    // Method to handle empty user inputs
    public static void isEmptyInput(String fieldName) {
        clearConsole();
        printHeading(true, true, fieldName + " cannot be empty!");
        System.out.println("Press Enter to continue...");
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

    // Method to stop the game until user enters something
    public static void pressEnter() {
        System.out.println("\nPress Enter to continue....");
        scanner.nextLine();
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

    // Method to print tab in heading according to separator length
    public static String printTab(int paraSeparator, boolean story) {
        int tabCount = paraSeparator / 16;
        String tab = "";

        for (int i = 0; i < tabCount; i++) {
            tab += "\t";
        }

        return tab;
    }

    // Method to wrap text based on a specified character limit
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

    // Method to print messages
    public static void printMessage(String message) {
        String[] sentences = message.split("\\.");

        printSeparator(100);
        for (String sentence : sentences) {
            printWrappedText(sentence.trim() + ".", 100);
        }

        System.out.println("\nPress Enter");
        printSeparator(100);
        System.out.println();
        scanner.nextLine();
    }

    // Method to print a heading
    public static void printHeading(boolean newLine, boolean lineWidth, String... titles) {
        int maxLen = titles[0].length();
        for (int i = 1; i < titles.length; i++) {
            String title = titles[i];

            if (title.length() > maxLen) {
                maxLen = title.length();
            }
        }

        if (lineWidth) {
            printSeparator(maxLen * 2);
            for (int i = 0; i < titles.length; i++) {
                String title = titles[i];
                System.out.println(printTab(maxLen * 2) + title);
            }
            printSeparator(maxLen * 2);
        } else {
            if (maxLen + 1 > 100) {
                printSeparator(100);
                for (int i = 0; i < titles.length; i++) {
                    String title = titles[i];
                    System.out.println(printTab(5) + title);
                }
                printSeparator(100);
            } else {
                printSeparator(maxLen + 1);
                for (int i = 0; i < titles.length; i++) {
                    String title = titles[i];
                    System.out.println(printTab(5) + title);
                }
                printSeparator(maxLen + 1);
            }
        }
        if (newLine)
            System.out.println();
    }

    // Method to print each parts of the story
    public static void storyPrinter(
            boolean location,
            int paraSeparator,
            String title,
            String folderName,
            boolean pressEnter,
            String... fileNames) {
        clearConsole();

        printSeparator(paraSeparator);
        System.out.println(printTab(paraSeparator, true) + title);
        printSeparator(paraSeparator);

        System.out.println();

        if (location) {
            System.out.println("LOCATION: " + EventManager.player.location);
            System.out.println();
        }

        paragraphPrinter(paraSeparator, folderName, pressEnter, fileNames);
    }

    // Method to print para heading
    public static void printParaHeading(
            boolean location,
            int paraSeparator,
            String[] titles,
            String intOut) {
        printSeparator(paraSeparator);
        if (intOut.equals("i")) {
            System.out.println(printTab(paraSeparator, true) + titles[0] + " - INTRO");
        } else if (intOut.equals("o")) {
            System.out.println(printTab(paraSeparator, true) + titles[0] + " - OUTRO");
        }
        System.out.println(printTab(paraSeparator, true) + titles[1]);
        printSeparator(paraSeparator);

        System.out.println();

        if (location) {
            System.out.println("LOCATION: " + EventManager.player.location);
            System.out.println();
        }
    }

    // Method to print each parts of the story
    public static void storyPrinter(
            boolean location,
            int paraSeparator,
            String[] titles,
            String intOut,
            String folderName,
            boolean pressEnter,
            String... fileNames) {
        clearConsole();
        printParaHeading(location, paraSeparator, titles, intOut);
        paragraphPrinter(paraSeparator, folderName, pressEnter, fileNames);
    }

    // Method to print each paragraphs of the story as user presses Enter
    public static void paragraphPrinter(
            int lineWidth,
            String folderName,
            boolean pressEnter,
            String... paragraphFiles) {
        String projectRoot;

        // Adjusting path based on where the application is running from
        if (System.getProperty("java.class.path").contains("bin")) {
            projectRoot = "src/resources/";
        } else {
            projectRoot = "../src/resources/";
        }

        for (int i = 0; i < paragraphFiles.length; i++) {
            String fileName = paragraphFiles[i];
            File file = new File(projectRoot + folderName + "/para" + fileName + ".txt");

            try (Scanner fileScanner = new Scanner(file)) {
                while (fileScanner.hasNextLine()) {
                    String paragraph = fileScanner.nextLine().trim();

                    // Skip empty lines
                    if (paragraph.isEmpty()) {
                        continue;
                    }

                    // Replace placeholders with actual values
                    Player player = EventManager.player;
                    String[] name = player.getName().split(" ");
                    String lastName = name[name.length - 1];

                    // Replace name
                    paragraph = paragraph.replace("<name>", lastName);

                    // Replace combat skill
                    if (!player.unlockedCombatSkills.isEmpty()) {
                        paragraph = paragraph.replace("<combSkill>",
                                player.unlockedCombatSkills.get(player.unlockedCombatSkills.size() - 1));
                    } else {
                        paragraph = paragraph.replace("<combSkill>", "");
                    }

                    // Replace defensive skill
                    if (!player.unlockedDefensiveSkills.isEmpty()) {
                        paragraph = paragraph.replace("<defSkill>",
                                player.unlockedDefensiveSkills.get(player.unlockedDefensiveSkills.size() - 1));
                    } else {
                        paragraph = paragraph.replace("<defSkill>", "");
                    }

                    // Replace combat weapon
                    if (!player.unlockedCombatWeapons.isEmpty()) {
                        paragraph = paragraph.replace("<combWeapon>",
                                player.unlockedCombatWeapons.get(player.unlockedCombatWeapons.size() - 1));
                    } else {
                        paragraph = paragraph.replace("<combWeapon>", "");
                    }

                    // Replace defensive weapon
                    if (!player.unlockedDefensiveEquipments.isEmpty()) {
                        paragraph = paragraph.replace("<defWeapon>",
                                player.unlockedDefensiveEquipments.get(player.unlockedDefensiveEquipments.size() - 1));
                    } else {
                        paragraph = paragraph.replace("<defWeapon>", "");
                    }

                    String[] sentences = paragraph.split("\\.");

                    printSeparator(100);
                    for (String sentence : sentences) {
                        // Adding custom line breaks
                        if (sentence.contains("<break>")) {
                            String[] splittedSentenceParts = sentence.split("<break>");

                            for (int j = 0; j < splittedSentenceParts.length; j++) {
                                String splitPart = splittedSentenceParts[j];
                                if (j == 0) {
                                    printWrappedText(splitPart.trim() + " -", lineWidth);
                                    System.out.println();
                                } else {
                                    printWrappedText(splitPart.trim() + ".", lineWidth);
                                }
                            }
                        } else {
                            printWrappedText(sentence.trim() + ".", lineWidth);
                        }
                    }

                    if (pressEnter) {
                        if (i == paragraphFiles.length - 1) {
                            printSeparator(100);
                            pressEnter();
                        } else {
                            System.out.println("\nPress Enter");
                            printSeparator(100);
                            System.out.println();
                            scanner.nextLine();
                        }
                    } else {
                        printSeparator(100);
                        System.out.println();
                    }
                }
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + e.getMessage());
            }
        }
    }

    // Method to check a specific item in an array
    public static boolean checkArr(String[] arr, String item) {
        boolean found = false;
        for (String elem : arr) {
            if (elem.equals(item)) {
                found = true;
                break;
            }
        }

        return found;
    }
}