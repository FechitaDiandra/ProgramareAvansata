package org.example;

import java.io.*;

/**
 * The GameStateManager class provides methods for saving and loading game states.
 */
public class GameStateManager {

    /**
     * Saves the current game state to a file.
     * @param gameState The game state to save.
     * @param fileName The name of the file to save the game state to.
     */
    public static void saveGameState(DrawingPanel gameState, String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(gameState); // Write the game state object to the file
            System.out.println("Game state saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving game state.");
        }
    }

    /**
     * Loads a game state from a file.
     * @param fileName The name of the file to load the game state from.
     * @return The loaded game state, or null if loading failed.
     */
    public static DrawingPanel loadGameState(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            DrawingPanel gameState = (DrawingPanel) inputStream.readObject(); // Read the game state object from the file
            System.out.println("Game state loaded successfully.");
            return gameState;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Error loading game state.");
            return null;
        }
    }
}
