package main;

// Class to store methods to print out every part of the story
public class Story {
    public static void printIntro() {
        Utilities.clearConsole();

        Utilities.printSeparator(30);
        System.out.println("INTRO");
        Utilities.printSeparator(30);

        Utilities.storyPrinter("intro","para1.txt", "para2.txt", "para3.txt");
    }
}
