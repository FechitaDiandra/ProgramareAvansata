package org.example;

import java.io.IOException;

/**
 * The Command interface represents an action that can be executed.
 * Implementations of this interface should provide a method to execute the command.
 */
public interface Command {
    
    /**
     * Executes the command.
     * 
     * @throws IOException if an I/O error occurs while executing the command
     */
    void execute() throws IOException;

}
