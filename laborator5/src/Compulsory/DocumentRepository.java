package Compulsory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a repository for storing documents organized by persons.
 */
public class DocumentRepository {
    private final File masterDirectory;
    private Map<Person, List<Document>> documents;

    /**
     * Constructs a DocumentRepository with the specified master directory path.
     *
     * @param masterDirectoryPath the path to the master directory containing documents
     * @throws IllegalArgumentException if the master directory path is invalid
     */
    public DocumentRepository(String masterDirectoryPath) {
        this.masterDirectory = new File(masterDirectoryPath);
        this.documents = new HashMap<>();

        // Verify if the master directory path is valid
        if (!masterDirectory.exists() || !masterDirectory.isDirectory()) {
            throw new IllegalArgumentException("Invalid master directory path: " + masterDirectoryPath);
        }

        loadDocuments();
    }

    /**
     * Loads documents from the master directory into the repository.
     *
     * @throws IllegalStateException if the directory name format is invalid
     */
    private void loadDocuments() {
        File[] files = masterDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    List<Document> documentList = new ArrayList<>();
                    String title = file.getName();
                    String[] parts = title.split("_");
                    // Verify if the directory name format is as expected
                    if (parts.length < 2) {
                        throw new IllegalStateException("Invalid directory name format: " + title);
                    }
                    // Set the default author for the document
                    Person employer = new Person(parts[0], parts[1]); // parts[0] is the ID, parts[1] is the name

                    // Iterate through the employer's files and add documents
                    File[] employerFiles = file.listFiles();
                    if (employerFiles != null) {
                        for (File employerFile : employerFiles) {
                            if (employerFile.isFile()) { // Verify if it's a file
                                Document document = new Document(employerFile.getName());
                                documentList.add(document);
                            }
                        }
                    }

                    // Add documents to the document map
                    documents.put(employer, documentList);
                }
            }
        }
    }

    /**
     * Displays the content of the document repository.
     */
    public void displayRepositoryContent() {
        System.out.println("Documents in repository:");
        for (Map.Entry<Person, List<Document>> entry : documents.entrySet()) {
            System.out.println("Person: " + entry.getKey().name());
            for (Document document : entry.getValue()) {
                System.out.println("File: " + document.name());
            }
        }
    }

    @Override
    public String toString() {
        return "DocumentRepository{" +
                "masterDirectory=" + masterDirectory +
                ", documents=" + documents +
                '}';
    }
}
