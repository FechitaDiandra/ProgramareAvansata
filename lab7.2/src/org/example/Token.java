package org.example;

/**
 * Clasa Token reprezintă un jeton cu două valori întregi.
 */
public class Token {
    public int first; // Prima valoare a jetonului
    public int second; // A doua valoare a jetonului

    /**
     * Constructor pentru obiectele Token.
     *
     * @param first  Prima valoare a jetonului
     * @param second A doua valoare a jetonului
     */
    Token(int first, int second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Returnează o reprezentare sub formă de șir a obiectului Token.
     *
     * @return Șirul care reprezintă obiectul Token
     */
    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
