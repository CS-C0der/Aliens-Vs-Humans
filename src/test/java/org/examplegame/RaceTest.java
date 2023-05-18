package org.examplegame;

import org.examplegame.entities.Alien;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class RaceTest {

    @Test
    public void testRace(){
        assertAll(
                () -> assertNotNull(Race.FACEHUGGER),
                () -> assertNotNull(Race.ZOMBIE),
                () -> assertNotNull(Race.KLINGON),
                () -> assertNotNull(Race.BORG),
                () -> assertNotNull(Race.ALF)
        );
    }

    @Test
    public void testRandomRace(){

        // generate many random aliens
        for (int i = 0; i < 50; i++){
            new Alien(Race.randomRace());  // no need to store object. just create object to increase static variable raceCount in Alien class
        }

        // iterate races
        for (Race race : Race.values()) {
            if (race == Race.ZOMBIE){
                if (Alien.getRaceCount(race) > 0){ fail("no Zombies should be returned by randomRace!"); }
            // not Zombie
            } else if (Alien.getRaceCount(race) == 0){
                fail("no Alien of Race " + race + "created");
            }
            System.out.println("Number of " + race +"s :" + Alien.getRaceCount(race));
        }

    }


}