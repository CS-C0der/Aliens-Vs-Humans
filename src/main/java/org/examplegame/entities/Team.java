package org.examplegame.entities;

import org.examplegame.Race;

import java.util.Random;

public enum Team {
    ALIENS,
    HUMANS;

    public static Team getRandom(){
        Random random = new Random();
        if (random.nextBoolean()){
            return Team.ALIENS;
        } else {
            return Team.HUMANS;
        }
    }


}
