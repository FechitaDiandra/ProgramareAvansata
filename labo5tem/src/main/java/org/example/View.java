package org.example;

import org.exceptions.ShellException;

import java.awt.*;
import java.io.File;

/**
 * The View class represents a command to view a document.
 * It implements the Command interface, allowing it to be executed as a command.
 */
public class View implements Command {

    /** The command string provided to the View object. */
    private String command;

    /**
     * Constructs a View command with the given command string.
     *
     * @param command the command string representing the file path to view
     */
    public View(String command) {
        this.command = command;
    }

    /**
     * Executes the view command by opening the specified document.
     * If the file path is not provided or the file does not exist, appropriate messages are printed.
     *
     * @throws ShellException if an error occurs while trying to view the document
     */
    @Override
    public void execute() {
        try {
            if (command.length() < 5) {
                System.out.println("Please provide a file path after 'view'.");
                return;
            }

            String filePath = command.substring(5).trim();
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("File does not exist");
                return;
            }

            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
        } catch (Exception e) {
            throw new ShellException("Error trying to view a document: " + e.getMessage());
        }
    }
}
