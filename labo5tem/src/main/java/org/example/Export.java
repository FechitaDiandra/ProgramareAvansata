package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.exceptions.ShellException;

import java.io.File;
import java.io.IOException;

/**
 * The Export class represents a command to export documents from a repository to a JSON file.
 * It implements the Command interface, allowing it to be executed as a command.
 */
public class Export implements org.example.Command {
    
    private final Repository repository;
    private final String exportPath;

    /**
     * Constructs an Export command with the given repository and export path.
     * 
     * @param repository the repository from which documents will be exported
     * @param exportPath the path where the JSON file will be exported
     */
    public Export(Repository repository, String exportPath) {
        this.repository = repository;
        this.exportPath = exportPath;
    }

    /**
     * Executes the export command by serializing documents from the repository to a JSON file.
     * 
     * @throws ShellException if an error occurs during the export process
     */
    @Override
    public void execute() throws ShellException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File outputFile = new File(exportPath);
            objectMapper.writeValue(outputFile, repository.getDocuments());
            System.out.println("Repository documents exported to JSON file: " + outputFile.getName());
        } catch (IOException e) {
            throw new ShellException("Error trying to export a JSON file: " + e.getMessage());
        }
    }
}
