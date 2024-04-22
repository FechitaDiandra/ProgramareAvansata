package org.example;

public class Main {
    public static void main(String[] args) {
        int n = 4;
        int numPlayers = 3;
        long timeLimitMillis = 500000;
        Game game = new Game(n, numPlayers,timeLimitMillis);
        game.startGame();
    }
}