package util;

import java.util.Scanner;

/**
 * Reusable input helpers for console interaction.
 * Provides non-empty string reading, integer parsing with re-prompting,
 * and menu choice validation so invalid input never crashes the program.
 */
public class InputValidator {

    private final Scanner scanner;

    /**
     * Creates a validator that reads from the given scanner.
     *
     * @param scanner shared Scanner used by the application
     */
    public InputValidator(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Reads a non-empty line from the user, re-prompting until valid input is given.
     *
     * @param prompt message shown before reading
     * @return trimmed non-empty string
     */
    public String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (input != null && !input.trim().isEmpty()) {
                return input.trim();
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    /**
     * Reads an integer from the user, re-prompting on invalid (non-integer) input.
     *
     * @param prompt message shown before reading
     * @return parsed integer value
     */
    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (input == null || input.trim().isEmpty()) {
                System.out.println("Input cannot be empty. Please enter an integer.");
                continue;
            }
            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer. Please try again.");
            }
        }
    }

    /**
     * Reads a menu choice constrained to an inclusive range [min, max].
     * Rejects out-of-range selections without crashing.
     *
     * @param prompt message shown before reading
     * @param min    minimum allowed choice (inclusive)
     * @param max    maximum allowed choice (inclusive)
     * @return validated menu choice within range
     */
    public int readMenuChoice(String prompt, int min, int max) {
        while (true) {
            int choice = readInt(prompt);
            if (choice >= min && choice <= max) {
                return choice;
            }
            System.out.println("Choice out of range. Please enter a number between "
                    + min + " and " + max + ".");
        }
    }

    /**
     * Reads a yes/no confirmation. Accepts y/yes or n/no (case-insensitive).
     *
     * @param prompt message shown before reading
     * @return true if the user confirmed yes, false otherwise
     */
    public boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (input == null) {
                System.out.println("Please enter y/yes or n/no.");
                continue;
            }
            String normalized = input.trim().toLowerCase();
            if (normalized.equals("y") || normalized.equals("yes")) {
                return true;
            }
            if (normalized.equals("n") || normalized.equals("no")) {
                return false;
            }
            System.out.println("Please enter y/yes or n/no.");
        }
    }
}
