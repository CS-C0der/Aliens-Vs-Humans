package org.examplegame.entities;

import org.examplegame.entities.Entity;
import org.examplegame.Planet;
import org.examplegame.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityTest {

    // Create concrete class that extends org.examplegame.entities.Entity in order to test i.e. constructor
    public class ConcreteEntity extends Entity {
        public ConcreteEntity(Planet homePlanet, String name, Weapon weapon) {
            super(homePlanet, name, weapon);
        }

    };

    private ConcreteEntity entity;

    @BeforeEach
    public void beforeEachTest(){
        String name = "Entity1";
        entity = new ConcreteEntity(Planet.MARS, name, Weapon.SHOTGUN);

    }

    @Test
    @DisplayName("Test Constructor (and getter methods)")
    public void testEntityConstructor(){
        assertAll(
                () -> assertEquals(Planet.MARS, entity.getHomePlanet()),
                () -> assertEquals("Entity1", entity.getName()),
                () -> assertEquals(100, entity.getHitpoints()),
                () -> assertEquals(true, entity.isAlive()),
                () -> assertEquals(Weapon.SHOTGUN, entity.getWeapon())
        );
    }

    // No Tests for getter methods needed
    // Already tested functionality in testEntityConstructor()

    @Test
    public void testSetWeapon(){
        // remember: @BeforeEach sets org.examplegame.Weapon to shotgun
        entity.setWeapon(Weapon.PHASER);
        assertEquals(Weapon.PHASER.getName(), entity.getWeapon().getName());
    }

    // ToDo: test for takeDamage (can be implemented in org.examplegame.entities.Entity and later overwritten in org.examplegame.entities.Human and cat)
    //  - override bei Katzen (lives)
    //  - override org.examplegame.entities.Human (armor)

    @Test
    public void testTakeDamage(){
        // check if hitpoints reduce
        int hpBefore = entity.getHitpoints();
        entity.takeDamage(20);
        assertEquals(hpBefore-20, entity.getHitpoints());

        // check if isAlive is set correctly
        entity.takeDamage(Integer.MAX_VALUE);
        assertFalse(entity.isAlive());
    }

    @Test
    public void testDoDamage(){
        // remember: @BeforeEach sets org.examplegame.Weapon to shotgun (damage 20)
        assertEquals(20,entity.doDamge() );
        entity.setWeapon(Weapon.CROWBAR);
        // different result with crowbar?
        assertEquals(10,entity.doDamge() );
    }

}