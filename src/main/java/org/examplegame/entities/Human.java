package org.examplegame.entities;

import org.examplegame.Planet;
import org.examplegame.Weapon;
import org.examplegame.entities.Entity;

import java.util.Map;

public class Human extends Entity {

        // class variables
        // ToDo get Names out of a CSV file
        static private final String[] humanNames = {"Werner", "Dieter", "Hans-Peter", "Otto", "Alfred", "Heinz", "Paul", "Klaus", "Frank" };
        static private Map nameLibary;

        // ToDo forschleife oder Stream um die values auf 0 zu setzen

        // ToDo if name for second time add (number) -> Paul (2)

        // instance variables
        private int armor;

        // constructor
        public Human(){
                super(Planet.EARTH, "dummy", Weapon.getRandom());
                this.armor = 100;

        }

        // class methods
        static int numberOfNames(){
                return humanNames.length;
        }

        // getters
        public int getArmor() {
                return armor;
        }

}
