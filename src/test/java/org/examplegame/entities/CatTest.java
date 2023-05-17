package org.examplegame.entities;

import org.examplegame.entities.Cat;
import org.examplegame.Planet;
import org.examplegame.Weapon;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CatTest {

    Cat cat1, cat2;

    @BeforeEach
    void beforeEachTest () {
        cat1 = new Cat();
        cat2 = new Cat();
    }

    @Test
    @DisplayName("Test Constructor of Cat")
    public void testCat(){
        assertAll(
                () -> assertEquals("Cat 1", cat1.getName()),
                () -> assertEquals("Cat 2", cat2.getName()),
                () -> assertEquals(Planet.EARTH, cat1.getHomePlanet()),
                () -> assertEquals(100, cat1.getHitpoints()),
                () -> assertTrue(cat1.isAlive()),
                () -> assertEquals(Weapon.NONE, cat1.getWeapon()),
                () -> assertEquals(9, cat1.getLives())
        );
    }
    @Test
    public void testTakeDamage() {

        // check if hitpoints reduce
        int hpBefore = cat1.getHitpoints();
        cat1.takeDamage(20);
        assertEquals(hpBefore-20, cat1.getHitpoints());

        // cat should only lose 1 live when HP 0, or below and still be alive
        cat1.takeDamage(Integer.MAX_VALUE);
        assertTrue(cat1.isAlive());
        assertEquals(8, cat1.getLives());
        // if damage is more than 100 new live should nevertheless
        // start with 100 (can not be "deader" than dead)
        assertEquals(100, cat1.getHitpoints());

        // check if cat dies when all lives are gone.
        // for loop because per Design there
        // should not be a setter method for lives
        for (int i = 1; i <=9; i++){
            cat2.takeDamage(Integer.MAX_VALUE);
        }
        assertFalse(cat2.isAlive());
    }
    
    @Test
    @DisplayName("Test if printing of losing lives works")
    public void testLosingLives(){
        cat1.takeDamage(Integer.MAX_VALUE);
    }

    @AfterEach
    public void afterEachTest(){
        Cat.resetCatCount();
    }
}