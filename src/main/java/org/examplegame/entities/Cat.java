package org.examplegame.entities;

import org.examplegame.Planet;
import org.examplegame.Weapon;

public class Cat extends Entity {

    // class variables
    static int catCount = 0;

    // instance variables
    private int lives;

    // constructor
    public Cat(){

        super(Planet.EARTH, "dummy", Weapon.NONE);
        Cat.incrementCatCount();
        String newName = "org.examplegame.entities.Cat " + Cat.getCatCount();
        this.setName(newName);
        this.lives = 9;     // In some countries cats may have only 7 lives
    }

    //class methods
    public static int getCatCount(){
        return catCount;
    }

    // getter methods
    public int getLives(){
        return this.lives;
    }

    // instance methods

    /**
     * Increment catCount by 1
     */
    public static void incrementCatCount(){
        catCount++;
    }

    /**
     * Handle how Cats take damage as they have 9 lives.
     * If HP(Hitpoints) gets below zero cats lose one live and start
     * the next life with 100 HP.
     *
     * @param damage Amount of Hitpoints to reduce
     */
    @Override
    public void takeDamage(int damage){
        this.hitpoints -= damage;
        if (this.hitpoints <= 0) {
            if (this.lives <= 1){
                // lost its last life
                this.isAlive = false;
                this.hitpoints = 0;
            } else {
                // next life with 100 HP
                this.lives--;
                this.hitpoints = 100;
            }

        }
    }
}
