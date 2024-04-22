package org.example;

import java.util.ArrayList;
import java.util.List;

public class Player extends Thread {
    private final String namePlayer;
    private final Bag bag;//sacul de jetoane folosit
    private int points = 0;//numarul de puncte acumulate de catre jucator
    private final int n;//numarul total de jetoane pt a forma o secventa valida
    private final Game game;//comunic cu jocul
    private final List<Token> sequence;//secventa curenta a jucatorului


    public Player(String namePlayer, Bag bag, int n, Game game) {
        this.namePlayer = namePlayer;
        this.bag = bag;
        this.n = n;
        this.game = game;
        this.sequence = new ArrayList<>();
    }

    public int getPoints() {
        return points;
    }

    public String getNamePlayer() {
        return namePlayer;
    }
//logica principala a threadului
    @Override
    public void run() {
        //daca bag nu e gol,numarul punctelor acumulate de jucator mai mic ca nr maxim, limita de timp nu depisata
        while (!bag.isEmpty() && points < n && !game.isTimeLimitExceeded()) {
            synchronized (game) {//sincronizez obiectul (threadul accesa si modifica)
                while (game.getCurrentPlayer() == this) {//daca jucatorul curent este cel care ruleaza firul de executie daca este true e randul lui
                    //jucatorul curent daca este in asteptare
                    try {
                        game.wait();//firul de executie este in suspendare,pemitand altor threaduri sa acceseze si sa modifice game
                    } catch (InterruptedException e) {//in caz de threadul este intrerupt
                        System.out.println("Player thread interrupted.");
                        return;
                    }
                }
            }
//extrage un jeton din sac
            Token token = bag.extract();

            if (token != null) {
                sequence.add(token);
//formeaza secventa continuand sa extraga jetoane din sac
                while (sequence.size() < n) {
                    Token nextToken = bag.extract();//extrag
                    if (nextToken != null && isNextTokenValid(sequence, nextToken)) {
                        sequence.add(nextToken);//daca este valid adaug la secventa
                    } else {
                        break;
                    }
                }
                points = sequence.size();//actualizez punctajul
                System.out.println(namePlayer + " created sequence " + sequence + " and has " + points + " points.");
                game.playerFinishedTurn();//anunta ca obiectul game si a terminat randul de joc
                synchronized (game) {
                    game.notifyAll();// trimite notificare ca si a terminat randu
                }
            }
        }
    }
// verifica daca urmatorul jeton este valid pentru a fi adaugat la secventa curenta a jucatorului
    private boolean isNextTokenValid(List<Token> sequence, Token nextToken) {
        if (sequence.isEmpty()) {
            return true;
        } else {
            Token lastToken = sequence.get(sequence.size() - 1);
            return lastToken.second == nextToken.first;
        }
    }
}
