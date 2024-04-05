package org.example;

import org.exceptions.RepositoryException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * The Repository class represents a repository containing persons and documents.
 * It provides methods to interact with the repository contents.
 */
public class Repository {

    private final Path masterDirectory;
    private final List<Person> persons;
    private final List<Document> documents;

    /**
     * Constructs a Repository with the given master directory path.
     *
     * @param masterDirectoryPath the path of the master directory of the repository
     */
    public Repository(String masterDirectoryPath) {
        this.masterDirectory = Path.of(masterDirectoryPath);
        this.persons = new ArrayList<>();
        this.documents = new ArrayList<>();
    }

    /**
     * Displays the content of the repository.
     *
     * @throws RepositoryException if an error occurs while reading the repository content
     */
    public void displayRepositoryContent() {
        try {
            displayContent(masterDirectory);
        } catch (IOException e) {
            throw new RepositoryException("Error reading repository content: " + e.getMessage());
        }
    }

    private void displayContent(Path directory) throws IOException {
        Files.list(directory)
                .forEach(file -> {
                    try {
                        if (Files.isDirectory(file)) {
                            displayContent(file);
                        } else {
                            System.out.println(file.getFileName());
                        }
                    } catch (IOException e) {
                        throw new RepositoryException("Error reading file: " + file + ": " + e.getMessage());
                    }
                });
    }

    /**
     * Retrieves the list of persons from the repository.
     *
     * @return the list of persons in the repository
     * @throws IOException if an I/O error occurs while accessing the repository
     */
    public List<Person> getUsers() throws IOException {
        List<Person> users = new ArrayList<>();
        Files.list(masterDirectory)
                .filter(Files::isDirectory)
                .forEach(employeeDirectory -> {
                    String folderName = employeeDirectory.getFileName().toString();
                    String[] parts = folderName.split("_");
                    if (parts.length >= 2) {
                        int id = Integer.parseInt(parts[0]);
                        String name = parts[1];
                        users.add(new Person(id, name));
                    }
                });
        return users;
    }

    /**
     * Retrieves the list of documents from the repository.
     *
     * @return the list of documents in the repository
     * @throws IOException if an I/O error occurs while accessing the repository
     */
    public List<Document> getDocuments() throws IOException {
        List<Document> documents = new ArrayList<>();
        Files.list(masterDirectory)
                .forEach(file -> {
                    try {
                        if (Files.isDirectory(file)) {
                            documents.addAll(getDocumentsFromRepo(file));
                        } else {
                            documents.add(new Document(file.getFileName().toString()));
                        }
                    } catch (IOException e) {
                        throw new RepositoryException("Error reading file: " + file + ": " + e.getMessage());
                    }
                });
        return documents;
    }

    private List<Document> getDocumentsFromRepo(Path directory) throws IOException {
        List<Document> documents = new ArrayList<>();
        Files.list(directory)
                .forEach(file -> {
                    if (!Files.isDirectory(file)) {
                        documents.add(new Document(file.getFileName().toString()));
                    }
                });
        return documents;
    }
}
