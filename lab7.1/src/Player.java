import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The Player class represents a player participating in the game.
 * Each player extracts tokens from a shared token bag.
 */
public class Player implements Runnable {
    // Player's name
    private String playerName;
    // List to store tokens extracted by the player
    private List<Token> tokens = new ArrayList<>();
    // Token bag from which tokens are extracted
    private TokenBag bag;
    // Lock for synchronizing access to the token bag
    private static Lock bagLock = new ReentrantLock();
    // Maximum sequence value of tokens extracted by the player
    private int maxSequenceValue = 0;

    /**
     * Constructs a Player object with the given name and token bag.
     * @param name The name of the player.
     * @param bag The token bag from which the player extracts tokens.
     */
    public Player(String name, TokenBag bag) {
        this.playerName = name;
        this.bag = bag;
    }

    /**
     * The run method represents the player's behavior during the game.
     * The player continuously extracts tokens from the bag until it's empty.
     */
    @Override
    public void run() {
        // Continuously extract tokens from the bag until it's empty
        while (true) {
            Token token = extractToken();
            // If the bag is empty, break out of the loop
            if (token == null) {
                break;
            }
            // Add the extracted token to the player's list
            tokens.add(token);
            // Print the token extracted by the player
            System.out.println(playerName + " extracted " + token);
            // Update the maximum sequence value
            int sequenceValue = tokens.size();
            if (sequenceValue > maxSequenceValue) {
                maxSequenceValue = sequenceValue;
            }
        }
        // Print a message indicating that the player has extracted all tokens
        System.out.println(playerName + " has extracted all tokens. Max sequence value: " + maxSequenceValue);
    }

    /**
     * Extracts a token from the token bag.
     * This method is synchronized to ensure thread safety.
     * @return The extracted token, or null if the bag is empty.
     */
    private Token extractToken() {
        bagLock.lock(); // Acquire the lock
        try {
            return bag.pop(); // Extract a token from the bag
        } finally {
            bagLock.unlock(); // Release the lock
        }
    }

    /**
     * Retrieves the list of tokens extracted by the player.
     * @return The list of tokens.
     */
    public List<Token> getTokens() {
        return tokens;
    }

    /**
     * Retrieves the name of the player.
     * @return The name of the player.
     */
    public String getName() {
        return playerName;
    }
}
