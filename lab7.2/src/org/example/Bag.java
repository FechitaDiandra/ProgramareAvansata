package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Clasa Bag reprezintă un sac de jetoane.
 */
public class Bag {
    private final List<Token> tokens;

    /**
     * Returnează lista de jetoane din sac.
     *
     * @return Lista de jetoane din sac
     */
    public List<Token> getTokens() {
        return tokens;
    }

    /**
     * Constructorul clasei Bag.
     *
     * @param n Numărul de jetoane de generat (n x n jetoane)
     */
    Bag(int n) {
        tokens = new ArrayList<>();
        Random rand = new Random();

        // Generarea și adăugarea jetoanelor în sac
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tokens.add(new Token(i, j));
            }
        }
        shuffleTokens(); // Amestecarea jetoanelor în sac
    }

    /**
     * Amestecă jetoanele din sac.
     */
    public void shuffleTokens() {
        Collections.shuffle(tokens);
    }

    /**
     * Extrage și returnează primul jeton din sac, dacă sacul nu este gol.
     *
     * @return Primul jeton din sac sau null dacă sacul este gol
     */
    synchronized Token extract() {
        if (!tokens.isEmpty()) {
            return tokens.remove(0);
        }
        return null;
    }

    /**
     * Verifică dacă sacul este gol.
     *
     * @return true dacă sacul este gol, false în caz contrar
     */
    synchronized boolean isEmpty() {
        return tokens.isEmpty();
    }
}
