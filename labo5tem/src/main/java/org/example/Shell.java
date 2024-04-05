package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The Shell class represents a simple command-line shell.
 * It reads user input and executes corresponding commands.
 */
public class Shell {

    /**
     * The main method starts the shell and listens for user input.
     * It creates and executes commands based on the user input.
     *
     * @param args command-line arguments (not used)
     * @throws IOException if an I/O error occurs while reading user input
     */
    public static void main(String[] args) throws IOException {
        String input;
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            input = buffer.readLine();
            Command command = createCommand(input);
            if (command != null) {
                command.execute();
            } else if (input.equals("exit")) {
                return;
            } else {
                System.out.println("Other command: " + input);
            }
        }
    }

    /**
     * Creates a command based on the user input.
     *
     * @param input the user input string
     * @return the corresponding command object, or null if no matching command is found
     */
    private static Command createCommand(String input) {
        if (input.contains("view")) {
            return new View(input);
        } else if (input.equals("report")) {
            return new Report(new Repository("C:\\Users\\User\\Desktop\\Java\\labo5tem"));
        } else if (input.equals("export")) {
            return new Export(new Repository("C:\\Users\\User\\Desktop\\Java\\labo5tem"), "export.json");
        }
        return null;
    }
}
