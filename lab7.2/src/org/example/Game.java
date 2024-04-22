package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa Game reprezintă jocul în sine, care coordonează interacțiunea între jucători și sacul de jetoane.
 */
public class Game {
    private final int numPlayers; // numărul total de jucători
    private int currentPlayerIndex = 0; // indexul jucătorului curent
    private TimeKeeper timekeeperThread; // firul de execuție care gestionează limita de timp
    private boolean timeLimitExceeded = false; // indică dacă a fost depășită limita de timp
    private final List<Player> players = new ArrayList<>(); // lista de jucători
    List<Integer> points = new ArrayList<>(); // lista de puncte
    private final long timeLimitMillis; // limita de timp în milisecunde

    /**
     * Constructor pentru obiectele Game.
     *
     * @param n Numărul de jetoane de generat (n x n jetoane)
     * @param numPlayers Numărul total de jucători
     * @param timeLimitMillis Limita de timp în milisecunde pentru desfășurarea jocului
     */
    public Game(int n, int numPlayers, long timeLimitMillis) {
        Bag bag = new Bag(n);
        this.numPlayers = numPlayers;
        this.timeLimitMillis = timeLimitMillis;

        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player("Player " + (i + 1), bag, n, this));
            points.add(0);
        }
    }

    /**
     * Verifică dacă limita de timp a fost depășită.
     *
     * @return true dacă limita de timp a fost depășită, false în caz contrar
     */
    public synchronized boolean isTimeLimitExceeded() {
        return timeLimitExceeded;
    }

    /**
     * Setează starea indicatoarei care indică dacă limita de timp a fost depășită.
     *
     * @param timeLimitExceeded true dacă limita de timp a fost depășită, false în caz contrar
     */
    public synchronized void setTimeLimitExceeded(boolean timeLimitExceeded) {
        this.timeLimitExceeded = timeLimitExceeded;
    }

    /**
     * Inițiază jocul, creând un fir de execuție pentru gestionarea limitelor de timp și pornind fiecare jucător.
     */
    public void startGame() {
        timekeeperThread = new TimeKeeper(this, timeLimitMillis);
        timekeeperThread.start();

        for (Player player : players) {
            player.start();
        }
        try {
            for (Player player : players) {
                player.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!timekeeperThread.isTimeLimitExceeded()) {
            updatePoints();
            determineWinner();
        }
    }

    /**
     * Actualizează punctele fiecărui jucător în funcție de numărul de puncte acumulate.
     */
    public void updatePoints() {
        for (int i = 0; i < numPlayers; i++) {
            points.set(i, players.get(i).getPoints());
        }
    }

    /**
     * Returnează jucătorul curent care urmează să joace.
     *
     * @return Jucătorul curent
     */
    public synchronized Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    /**
     * Indică că un jucător a terminat rândul și actualizează indexul jucătorului curent.
     */
    public synchronized void playerFinishedTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % numPlayers;
        notifyAll();
    }

    /**
     * Determină câștigătorul jocului în funcție de punctele acumulate de fiecare jucător.
     * Se afișează mesajele corespunzătoare.
     */
    public void determineWinner() {
        // lista pentru a stoca castigatorii jocului
        List<Player> winners = new ArrayList<>();
        int maxPoints = 0;
        int winner = -1;
        // Se parcurge lista de puncte pentru fiecare jucător și se determină jucătorul cu cele mai multe puncte.
        for (int i = 0; i < numPlayers; i++) {
            if (points.get(i) > maxPoints) {
                maxPoints = points.get(i);
                winner = i;
            }
        }
        // Se parcurge din nou lista de puncte pentru a găsi toți jucătorii care au punctajul maxim.
        for (int i = 0; i < numPlayers; i++) {
            if (points.get(i) == maxPoints) {
                winners.add(players.get(i)); // adaugă în winners
            }
        }
        // Se verifică dacă există un singur câștigător sau mai mulți, și se afișează mesajele corespunzătoare.
        if (winner == -1) {
            System.out.println("No winner! All players have 0 points");
            System.out.println("The winner is " + players.get(winner).getNamePlayer());
        } else if (winners.size() == 1) {
            System.out.println("The winner is " + winners.get(0).getNamePlayer());

        } else {
            System.out.println("The winners are: ");
            for (Player player : winners) {
                System.out.println(player.getNamePlayer());
            }
        }
        stopGame();
    }

    /**
     * Oprește jocul, întrerupând firul de execuție care gestionează limitele de timp.
     */
    public void stopGame() {
        if (timekeeperThread != null && timekeeperThread.isAlive()) {
            timekeeperThread.interrupt();
        }
        synchronized (this) {
            notifyAll();
        }
    }
}
