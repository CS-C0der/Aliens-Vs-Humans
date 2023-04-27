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
            () -> assertEquals("bare Hands", Weapon.NONE.getName())
        );
    }

    @Test
    @DisplayName("Test org.examplegame.Weapon Damage")
    public void testWeaponDamage(){
        assertAll(
            () -> assertEquals(15, Weapon.PHASER.getDamage()),
            () -> assertEquals(34, Weapon.PLASMACANNON.getDamage()),
            () -> assertEquals(10, Weapon.CROWBAR.getDamage()),
            () -> assertEquals(20, Weapon.SHOTGUN.getDamage()),
            () -> assertEquals(5, Weapon.NONE.getDamage())
        );
    }

    @Test
    public void testGetRandom(){
        //ToDO test getrandomweapon: darf kein none enthalten
        //Weapons.getWeaponsExNone vergleichen mit selbstgemachter liste
        // limits oben/unten testen
        // https://stackoverflow.com/questions/14811014/writing-a-junit-test-for-a-random-number-generator
        System.out.println("List of Weapons:" + Weapon.getWeaponsExNONE());

        Weapon randomWeapon;

        // create Map to memorize amount of Weapon created per Weapon
        Map<Weapon, Integer> countPerWeapon = new HashMap<>();
        // initialize all values with 0
        for (Weapon weapon : Weapon.getWeaponsExNONE()){
            countPerWeapon.put(weapon, 0);
        }

        int currentAmount;

        // generate many random weapons
        for (int i = 0; i < 50; i++){
            randomWeapon = Weapon.getRandom();

            // memorize amount of how often a specific weapon is generated
            switch (randomWeapon){

                case PHASER -> {
                    currentAmount = countPerWeapon.get(Weapon.PHASER);
                    countPerWeapon.replace(Weapon.PHASER, currentAmount +1);
                }
                case PLASMACANNON -> {
                    currentAmount = countPerWeapon.get(Weapon.PLASMACANNON);
                    countPerWeapon.replace(Weapon.PLASMACANNON, currentAmount +1);
                }
                case CROWBAR -> {
                    currentAmount = countPerWeapon.get(Weapon.CROWBAR);
                    countPerWeapon.replace(Weapon.CROWBAR, currentAmount +1);
                }
                case SHOTGUN -> {
                    currentAmount = countPerWeapon.get(Weapon.SHOTGUN);
                    countPerWeapon.replace(Weapon.SHOTGUN, currentAmount +1);
                }
                default -> {
                    fail("None should not be possible for getRandom()");
                }
            } // End of switch Statement

        } // End of for loop

        System.out.println(countPerWeapon);

    }
}