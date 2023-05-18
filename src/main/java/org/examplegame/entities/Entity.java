package org.examplegame.entities;

import org.examplegame.Planet;
import org.examplegame.Weapon;

/**
 * Describes a general Entity. Entities have a name, hitpoints, a weapon, a Home Planet.
 * Each Entity also belongs to a Team and knows if it is alive.
 */
public abstract class Entity {

    // Instance variables
    protected int hitpoints;
    private String name;
    protected boolean isAlive = true;
    private Weapon weapon;
    private final Planet homePlanet;
    protected Team team;

    // constructor
    public Entity(Planet homePlanet, String name, Weapon weapon) {
        this.homePlanet = homePlanet;
        this.name = name;
        this.weapon = weapon;
        this.hitpoints = 100;
    }



    // setter methods
    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }

    public void setName(String name){
        this.name = name;
    }

    // getter methods
    public Team getTeam() {
        return team;
    }
    public Planet getHomePlanet(){
        return this.homePlanet;
    }

    public String getName(){
        return name;
    }

    public int getHitpoints(){
        return hitpoints;
    }

    public boolean isAlive(){
        return isAlive;
    }

    public Weapon getWeapon(){
        return weapon;
    }

    /**
     * Reduces hitpoints of entity by the value of "damage".
     * If Hitpoints equals or is below zero isAlive is
     * set to false. Also Hitpoints is set to zero.
     * Hitpoints can not become negative!
     *
     * @param damage Amount of Hitpoints to reduce
     */
    // Instance methods
    public void takeDamage(int damage){
        this.hitpoints -= damage;
        if (this.hitpoints <= 0) {
            this.isAlive = false;
            this.hitpoints = 0;
        }
    }

    /**
     * @return damage of equipped weapon
     */
    public int doDamge(){
        return weapon.getDamage();
    }

}
