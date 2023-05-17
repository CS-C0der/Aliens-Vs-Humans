package org.examplegame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Enumeration of Weapons. Each org.examplegame.Weapon has a name and a value representing the damage is does.
 */
public enum Weapon {
    PHASER("Phaser", 50),
    PLASMACANNON("Plasma Cannon", 67),
    NONE("no Weapon", 6),
    CROWBAR("Crow Bar", 10),
    SHOTGUN("Shotgun", 15);

    static private final List<Weapon> weaponsExNONE;
    static {
        weaponsExNONE = new ArrayList<>(Arrays.asList(Weapon.values()));
        weaponsExNONE.remove(Weapon.NONE);
    }

    private String name;
    private int damage;

    Weapon(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public static List<Weapon> getWeaponsExNONE(){
        return weaponsExNONE;
    }

    public static Weapon getRandom() {
        Random random = new Random();

        return weaponsExNONE.get(random.nextInt(weaponsExNONE.size()));
    }


}
