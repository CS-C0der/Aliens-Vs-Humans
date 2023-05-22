package org.examplegame.entities;

import org.examplegame.Planet;
import org.examplegame.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityTest {

    // Create concrete class that extends Entity in order to test i.e. constructor
    public class ConcreteEntity extends Entity {
        public ConcreteEntity(Planet homePlanet, String name, Weapon weapon) {
            super(homePlanet, name, weapon);
        }

    }

    private ConcreteEntity entity;

    @BeforeEach
    public void beforeEachTest(){
        String name = "Entity1";
        entity = new ConcreteEntity(Planet.DELTAQUADRANT, name, Weapon.SHOTGUN);

    }

    @Test
    @DisplayName("Test Constructor (and getter methods)")
    public void testEntityConstructor(){
        assertAll(
                () -> assertEquals(Planet.DELTAQUADRANT, entity.getHomePlanet()),
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
        // remember: @BeforeEach sets Weapon to shotgun
        assertEquals(entity.getWeapon().getDamage() , entity.doDamge() );
        entity.setWeapon(Weapon.CROWBAR);
        // different result with crowbar?
        assertEquals(entity.getWeapon().getDamage() , entity.doDamge() );
    }

}