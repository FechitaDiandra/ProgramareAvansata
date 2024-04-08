import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The TokenBag class represents a bag containing tokens for the game.
 * Tokens are pairs of integers representing coordinates on a grid.
 */
public class TokenBag {
    // List to store tokens in the bag
    private List<Token> tokens = new ArrayList<>();

    /**
     * Constructs a TokenBag object with 'n' tokens.
     * Tokens are pairs of integers from 1 to 'n'.
     * @param n The number of tokens to create.
     */
    public TokenBag(int n) {
        // Generate tokens for all possible pairs of integers from 1 to 'n'
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                // Avoid creating tokens with identical coordinates
                if (i != j) {
                    tokens.add(new Token(i, j));
                }
            }
        }
        // Shuffle the tokens in the bag
        Collections.shuffle(tokens);
    }

    /**
     * Retrieves and removes a token from the bag.
     * This operation is synchronized to ensure thread safety.
     * @return The token removed from the bag, or null if the bag is empty.
     */
    public synchronized Token pop() {
        // Check if the bag is not empty
        if (!tokens.isEmpty()) {
            // Remove and return the last token from the list (top of the bag)
            return tokens.remove(tokens.size() - 1);
        }
        // Return null if the bag is empty
        return null;
    }
}
