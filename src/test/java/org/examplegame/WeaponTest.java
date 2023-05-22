package org.examplegame;

import org.examplegame.Weapon;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WeaponTest {

    @Test
    @DisplayName("Test org.examplegame.Weapon Name")
    public void testWeaponName(){
        assertAll(
            () -> assertEquals("Phaser", Weapon.PHASER.getName()),
            () -> assertEquals("Plasma Cannon", Weapon.PLASMACANNON.getName()),
            () -> assertEquals("Crow Bar", Weapon.CROWBAR.getName()),
            () -> assertEquals("Shotgun", Weapon.SHOTGUN.getName()),
            () -> assertEquals("no Weapon", Weapon.NONE.getName())
        );
    }

    @Test
    @DisplayName("Test oWeapon Damage")
    public void testWeaponDamage(){
        assertAll(
            () -> assertEquals(50, Weapon.PHASER.getDamage()),
            () -> assertEquals(67, Weapon.PLASMACANNON.getDamage()),
            () -> assertEquals(10, Weapon.CROWBAR.getDamage()),
            () -> assertEquals(15, Weapon.SHOTGUN.getDamage()),
            () -> assertEquals(6, Weapon.NONE.getDamage())
        );
    }

    @Test
    public void testGetRandom(){

        System.out.println("List of Weapons:" + Weapon.getWeaponsExNONE());

        // create Map to memorize amount of Weapon created per Weapon
        Map<Weapon, Integer> countPerWeapon = new HashMap<>();
        // initialize all values with 0
        for (Weapon weapon : Weapon.getWeaponsExNONE()){
            countPerWeapon.put(weapon, 0);
        }

        // generate many random weapons
        Weapon randomWeapon;
        int currentAmount;

        for (int i = 0; i < 50; i++){
            randomWeapon = Weapon.getRandom();
            if (randomWeapon == Weapon.NONE){
                fail("Weapon NONE should not be part of getRandom()");
            }
            // memorize amount of how often a specific weapon is generated
            currentAmount = countPerWeapon.get(randomWeapon);
            countPerWeapon.replace(randomWeapon, currentAmount +1);

        } // End of for loop

        System.out.println(countPerWeapon);

    }
}