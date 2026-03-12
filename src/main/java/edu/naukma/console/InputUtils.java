package edu.naukma.console;

import java.util.Scanner;

public class InputUtils {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Safely reads an integer from console input.
     *
     * @return integer value entered by the user
     */
    public int readInt() {
        while (true) {
            try {
                int result = Integer.parseInt(scanner.nextLine());
                if (result < 0) {
                    System.out.print("Please enter a non-negative number: ");
                    continue;
                }
                return result;
            } catch (NumberFormatException e) {
                System.out.print("Please enter valid number: ");
            }
        }
    }

    /**
     * Safely reads an integer from console input with a prompt.
     *
     * @param prompt the message to display to the user before input
     * @return integer value entered by the user
     */
    public int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);

                int result = Integer.parseInt(scanner.nextLine());
                if (result < 0) {
                    System.out.print("Please enter a non-negative number: ");
                    continue;
                }
                return result;
            } catch (NumberFormatException e) {
                System.out.print("Please enter valid number: ");
            }
        }
    }

    /**
     * Safely reads a non-empty string from console input.
     *
     * @return non-empty string entered by the user
     */
    public String readString() {
        while (true) {
            String result = scanner.nextLine();
            if (result.trim().isEmpty()) {
                System.out.print("Input cannot be empty. Please enter a valid string: ");
                continue;
            }
            return result;
        }
    }

    /**
     * Safely reads a non-empty string from console input with a prompt.
     *
     * @param prompt the message to display to the user before input
     * @return non-empty string entered by the user
     */
    public String readString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String result = scanner.nextLine();
            if (result.trim().isEmpty()) {
                System.out.print("Input cannot be empty. Please enter a valid string: ");
                continue;
            }
            return result;
        }
    }

    /**
     * Generic method to allow the user to select an enum value from a list of options.
     *
     * @param enumClass the class of the enum to choose from
     * @param <T>       the type of the enum
     * @return the selected enum value
     */
    public  <T extends Enum<T>> T chooseEnum(Class<T> enumClass) {
        T[] elements = enumClass.getEnumConstants();

        while (true) {
            for (int i = 0; i < elements.length; i++) {
                System.out.println((i+1) + " - " + elements[i]);
            }

            int choice = readInt("Enter number: ");

            if (choice > 0 && choice <= elements.length) return elements[choice-1];
            else System.out.println("[Enter proper variant!]");
        }
    }
}
