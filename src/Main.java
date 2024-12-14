import java.util.NoSuchElementException;

import main.EventManager;
import main.Utils;

//! Don't forget to search the "Todo" issues and fix them!!! 😭
// Todo: handle program termination gracefully
// Todo: handle program termination during all input fields

public class Main {
    public static void main(String[] args) {
        try {
            // Start the game
            EventManager.launchGame(true);
        } catch (NoSuchElementException err) {
            // Catching NoSuchElementException when user presses Ctrl+C to intentionally
            // terminate the program
            Utils.terminate(Utils.scanner, true);

        } catch (Exception err) {
            // Catching other unintentional & unexpected exceptions and terminating the
            // program
            Utils.terminate(Utils.scanner, false);

        } finally {
            // Ensure scanner is closed regardless of termination
            if (Utils.scanner != null) {
                Utils.scanner.close();
            }
        }
    }
}
