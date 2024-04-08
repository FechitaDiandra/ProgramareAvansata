/**
 * The Token class represents a token containing two integers.
 * Each token represents a pair of coordinates on a grid.
 */
public class Token {
    // First integer of the token
    private int first;
    // Second integer of the token
    private int second;

    /**
     * Constructs a Token object with the given integers.
     * @param first The first integer of the token.
     * @param second The second integer of the token.
     */
    public Token(int first, int second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Retrieves the first integer of the token.
     * @return The first integer.
     */
    public int getFirst() {
        return first;
    }

    /**
     * Retrieves the second integer of the token.
     * @return The second integer.
     */
    public int getSecond() {
        return second;
    }

    /**
     * Returns a string representation of the token.
     * The string contains the first and second integers enclosed in parentheses.
     * @return The string representation of the token.
     */
    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
