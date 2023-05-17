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
        // cats are not loyal to humans!
        // So cats are randomly assigned to Team Human or Team Alien
        this.team = Team.getRandom();
        String newName = "Cat " + Cat.getCatCount() + " (Team " + this.team + ")";
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
     * Reset catCount to 0
     */
    public static void resetCatCount(){ Cat.catCount = 0; }

    /**
     * Handle how Cats take damage as they have 9 lives.
     * If HP(Hitpoints) gets below zero cats lose one live and start
     * the next life with 100 HP.
     *
     * @param damage Amount of Hitpoints to reduce
     */
    @Override
    public void takeDamage(int damage){
        // toDO maybe double damage or more to make more balanced?
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
                // System.out.println(this.getName() + " lost one life. Still " + this.lives + " remaining!");
            }

        }
    }
}
