package org.examplegame;

import org.examplegame.entities.Alien;
import org.examplegame.entities.Cat;
import org.examplegame.entities.Human;

/**
 * Utility class with helper methods
 */
public final class Helper {

    private Helper(){}

    /**
     * resets the static variables: <p>
     * raceCount in Alien <p>
     * nameLibary in Human <p>
     * catCount in Cat
     */
    public static void resetStaticVariables(){
        Alien.resetRaceCount();
        Human.resetNameLibary();
        Cat.resetCatCount();
    }
}
