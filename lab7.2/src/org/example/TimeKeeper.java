package org.example;

import java.util.concurrent.TimeUnit;

/**
 * Clasa TimeKeeper reprezintă un fir de execuție care gestionează limita de timp a unui joc.
 */
public class TimeKeeper extends Thread {
    private final Game game; // Jocul pentru care se gestionează limita de timp
    private final long timeLimitMillis; // Limita de timp în milisecunde
    private final long startTimeMillis; // Timpul de start al contorului de timp

    /**
     * Constructor pentru obiectele TimeKeeper.
     *
     * @param game            Jocul pentru care se gestionează limita de timp
     * @param timeLimitMillis Limita de timp în milisecunde pentru desfășurarea jocului
     */
    public TimeKeeper(Game game, long timeLimitMillis) {
        this.game = game;
        this.timeLimitMillis = timeLimitMillis;
        this.startTimeMillis = System.currentTimeMillis();
        setDaemon(true); // Marchează firul de execuție ca fiind un fir daemon
    }

    /**
     * Implementarea logicii de gestionare a limitelor de timp.
     * Firul de execuție verifică periodic dacă limita de timp a fost depășită și întrerupe jocul în consecință.
     */
    @Override
    public void run() {
        while (!isInterrupted()) {
            long elapsedTimeMillis = System.currentTimeMillis() - startTimeMillis;
            if (elapsedTimeMillis >= timeLimitMillis) {
                System.out.println("Game time limit exceeded. Stopping the game.");
                game.stopGame();
                game.setTimeLimitExceeded(true);
                break;
            }

            try {
                TimeUnit.SECONDS.sleep(1); // Așteaptă 1 secundă între verificările de timp
            } catch (InterruptedException e) {
                break; // Întrerupe firul de execuție dacă este întrerupt
            }
        }
    }

    /**
     * Verifică dacă limita de timp a fost depășită.
     *
     * @return true dacă limita de timp a fost depășită, false în caz contrar
     */
    public boolean isTimeLimitExceeded() {
        return false; // Metodă temporară, va fi implementată corect în viitor
    }
}
