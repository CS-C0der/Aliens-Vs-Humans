package org.examplegame.entities;

import org.examplegame.Planet;
import org.examplegame.Race;
import org.examplegame.Weapon;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AlienTest {

    private Alien facehugger1, facehugger2, facehugger3, zombie, klingon, borg, alf;
    private Human human1;

    @BeforeEach
    public void beforeEachTest(){
        // solange neue aliens erschaffen, bis jede rasse einmal da!
        //ToDO in alienconstructor verschiedene methoden aufrufen für verschiedene rassen?
        // methoden protected

        facehugger1 = new Alien(Race.FACEHUGGER);
        facehugger2 = new Alien(Race.FACEHUGGER);
        facehugger3 = new Alien(Race.FACEHUGGER);
        zombie = new Alien(Race.ZOMBIE);
        klingon = new Alien(Race.KLINGON);
        borg = new Alien(Race.BORG);
        alf = new Alien(Race.ALF);
        human1 = new Human();
        facehugger2.mutate(human1);
    }


    @Test
    @DisplayName("Test Constructor of Alien")
    public void testAlien(){

        assertAll(
                () -> assertEquals(100, facehugger1.getHitpoints()),
                () -> assertTrue(facehugger1.isAlive()),
                // check if doesEatCats is set properly
                () -> assertTrue(alf.doesEatCats() == true),
                () -> assertFalse(facehugger1.doesEatCats() || zombie.doesEatCats() || klingon.doesEatCats() || borg.doesEatCats()),
                // check if canUseItem is set properly
                () -> assertTrue(zombie.canUseItem() && klingon.canUseItem() && borg.canUseItem()),
                () -> assertFalse(facehugger1.canUseItem() || alf.canUseItem()),
                // check if Weapons are set properly (Zombie takes weapon from killed human)
                () -> assertTrue(Weapon.getWeaponsExNONE().contains(klingon.getWeapon())),
                () -> assertTrue(Weapon.getWeaponsExNONE().contains(borg.getWeapon())),
                () -> assertEquals(Weapon.NONE, facehugger1.getWeapon()),
                () -> assertEquals(Weapon.NONE, alf.getWeapon()),
                () -> assertEquals(human1.getWeapon(), facehugger2.getWeapon(), "Zombie gets weapon of human"),
                // check if homeplanet is set properly
                () -> assertEquals(Planet.BLACKMESA, facehugger1.getHomePlanet()),
                () -> assertEquals(Planet.BLACKMESA, zombie.getHomePlanet()),
                () -> assertEquals(Planet.KRONOS, klingon.getHomePlanet()),
                () -> assertEquals(Planet.DELTAQUADRANT, borg.getHomePlanet()),
                () -> assertEquals(Planet.MELMAC, alf.getHomePlanet()),
                // check if raceCount is set properly (3 facehuggers created, 1 mutated to zombie)
                () -> assertEquals(2, Alien.getRaceCount(Race.FACEHUGGER), "RaceCount of Facehugger"),
                // check if name is set properly
                () -> assertEquals("Facehugger no. 2", facehugger2.getName())

        );
    }

    @AfterEach
    public void afterEachTest(){
        Alien.resetRaceCount();
        System.out.println("RaceCount Map afterEachTest: ");
        for (Race race : Race.values()){
            System.out.println("Number of " + race + "s: " + Alien.getRaceCount(race));
        }

    }
}