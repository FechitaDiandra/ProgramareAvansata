package Compulsory;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Specifică calea către directorul temporar
        String temporaryDirectoryPath = "C:\\Users\\User\\Desktop\\Java\\laborator5\\Master";

        // Creează un obiect de tip DocumentRepository pentru directorul temporar
        DocumentRepository repository = new DocumentRepository(temporaryDirectoryPath);

        // Afișează conținutul repository-ului
        repository.displayRepositoryContent();
    }
}


