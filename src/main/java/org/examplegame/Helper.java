package org.examplegame;

import org.examplegame.entities.Alien;
import org.examplegame.entities.Cat;
import org.examplegame.entities.Human;

public final class Helper {

    private Helper(){

    }
    public static void resetStaticVariables(){
        Alien.resetRaceCount();
        Human.resetNameLibary();
        Cat.resetCatCount();
    }
}
