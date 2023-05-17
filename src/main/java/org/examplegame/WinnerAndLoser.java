package org.examplegame;

import org.examplegame.entities.Entity;

public class WinnerAndLoser {
    private Entity winner;
    private Entity loser;

    public WinnerAndLoser(Entity winner, Entity loser){
        this.winner = winner;
        this.loser = loser;
    }

    public Entity getWinner() {
        return winner;
    }

    public Entity getLoser() {
        return loser;
    }
}
