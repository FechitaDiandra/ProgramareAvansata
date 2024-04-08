/**
 * The Main class represents the entry point of the program. It initializes
 * a token bag and creates multiple player threads to participate in a game.
 */
public class Main {

    /**
     * The main method is the entry point of the program.
     * It initializes the game parameters, creates player threads, and starts the game.
     * @param args The command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        // Number of players in the game
        int numPlayers = 4;
        // Number of tokens in the bag
        int n = 3;
        // Initialize the token bag with 'n' tokens
        TokenBag bag = new TokenBag(n);

        // Create and start threads for each player
        for (int i = 0; i < numPlayers; i++) {
            // Create a player with a unique name and provide the token bag
            Player player = new Player("Player " + (i + 1), bag);
            // Start the player thread
            new Thread(player).start();
        }
    }
}
