package org.examplegame.entities;

import org.examplegame.Planet;
import org.examplegame.Weapon;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HumanTest {

    ArrayList<Human> humans = new ArrayList<>();
    Human human1;

    @BeforeEach
    public void beforeEachTest(){
        int numberOfNames = Human.numberOfNames();

        // create one more human than names available to check for double names
        // (names assigned random to humans)
        for (int i=0 ; i < numberOfNames; i++){
            humans.add(new Human());
        }
        human1 = humans.get(0);

    }

    @Test
    @DisplayName("Test Constructor of Human")
    public void testHuman(){

        printNameList();

        assertAll(
                () -> assertFalse(hasDublicateNames(humans)),
                () -> assertEquals(Planet.EARTH, human1.getHomePlanet()),
                () -> assertEquals(100, human1.getHitpoints()),
                () -> assertTrue(human1.isAlive()),
                // all Humans wear Weapons. check if human has weapon and that it isn't "NONE"
                () -> assertTrue(Weapon.getWeaponsExNONE().contains(human1.getWeapon())),
                () -> assertEquals(100, human1.getArmor())

        );

    }

    @Test
    @DisplayName("Test if armor affects takeDamage")
    public void testTakeDamage(){
        // hp and armor of each human set to 100 in beforeEach
        human1.takeDamage(90);
        assertEquals(10, human1.getArmor());
        assertEquals(100, human1.getHitpoints());

        human1.takeDamage(30);
        assertEquals(0, human1.getArmor());
        assertEquals(80, human1.getHitpoints());
    }

    private boolean hasDublicateNames(ArrayList<Human> humans) {
        boolean dublicate = false;
        String name1 = humans.get(0).getName();

        // iterate over all but first human in list!
        for (int i=1; i < humans.size(); i++){
            if (name1.equals(humans.get(i).getName())){
                dublicate = true;
                break;
            }
        }

        return dublicate;
    }

    @AfterEach
    public void afterEachTest(){
        Human.resetNameLibary();
    }

    /**
     * Prints names of all Humans contained in humans list
     */
    private void printNameList(){
        System.out.print("Names of humans: ");
        for (Human human : humans){
            System.out.print(human.getName() + ", ");
        }
        System.out.print("\n");
    }

}