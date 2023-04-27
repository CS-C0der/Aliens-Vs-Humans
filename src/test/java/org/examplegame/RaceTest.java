package org.examplegame;

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

        System.out.println("List of Races:" + Race.getRacesExZombie());

        Race randomRace;

        // create Map to memorize amount of Race created per Race
        Map<Race, Integer> countPerRace = new HashMap<>();
        // initialize all values with 0
        for (Race weapon : Race.getRacesExZombie()){
            countPerRace.put(weapon, 0);
        }

        int currentAmount;

        // generate many random weapons
        for (int i = 0; i < 50; i++){
            randomRace = Race.randomRace();

            // memorize amount of how often a specific weapon is generated
            switch (randomRace){

                case FACEHUGGER -> {
                    currentAmount = countPerRace.get(Race.FACEHUGGER);
                    countPerRace.replace(Race.FACEHUGGER, currentAmount +1);
                }
                case KLINGON -> {
                    currentAmount = countPerRace.get(Race.KLINGON);
                    countPerRace.replace(Race.KLINGON, currentAmount +1);
                }
                case BORG -> {
                    currentAmount = countPerRace.get(Race.BORG);
                    countPerRace.replace(Race.BORG, currentAmount +1);
                }
                case ALF -> {
                    currentAmount = countPerRace.get(Race.ALF);
                    countPerRace.replace(Race.ALF, currentAmount +1);
                }
                default -> {
                    fail("Zombie should not be possible");
                }
            } // End of switch Statement

        } // End of for loop

        System.out.println(countPerRace);
    }


}