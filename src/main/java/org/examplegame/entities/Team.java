package org.examplegame.entities;

import java.util.Random;

public enum Team {
    ALIENS,
    HUMANS;

    /**
     * @return random Team (either ALIENS or HUMANS)
     */
    public static Team getRandom(){
        Random random = new Random();
        if (random.nextBoolean()){
            return Team.ALIENS;
        } else {
            return Team.HUMANS;
        }
    }


}
