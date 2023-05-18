package org.examplegame.entities;

import org.examplegame.Planet;
import org.examplegame.Weapon;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Describes a human Entity.
 * Humans have armor. First the armor is reduced. When the armor is 0 the Hitpoints are reduced.
 * Humans have real names. If a name doubles the human is named Werner no. 2 i.e.
 */
public class Human extends Entity {

        // class variables
        // ToDo get Names out of a CSV file
        static private final String[] humanNames = {"Werner", "Dieter", "Hans-Peter", "Otto", "Alfred", "Heinz", "Paul", "Klaus", "Frank" };
        static private Map<String, Integer> nameLibary = new HashMap<>();
        static{
                // initialize nameLibary with values = 0 for each name
                for (String name : humanNames){
                        nameLibary.put(name, 0);
                }
        }

        // instance variables
        private int armor;

        // constructor
        public Human(){
                super(Planet.EARTH, "dummy", Weapon.getRandom());
                this.armor = 100;
                this.setName(getRandomName());
                this.team = Team.HUMANS;
        }

        /**
         * Sets the values of nameLibary to 0 for every Name
         */
        public static void resetNameLibary(){
                for (String name : humanNames){
                        nameLibary.replace(name, 0);
                }
        }

        /**
         * @return number of different available names
         */
        // class methods
        protected static int numberOfNames(){
                return humanNames.length;
        }

        /**
         * Return a Random Human Name.
         * <p>
         * If Name is already in Name Libary add an identifier to the name: Paul no. 2
         *
         * @return random Name
         */
        private static String getRandomName(){
                Random random = new Random();
                String name;
                name = humanNames[random.nextInt(humanNames.length)];
                // check if name already exists
                if ( 0 == nameLibary.get(name)) {
                        nameLibary.replace(name, 1);
                        return name;
                } else {
                        // name already exists
                        int identifier = nameLibary.get(name) + 1;
                        nameLibary.replace(name, identifier);
                        return name+" no. " + identifier;
                }
        }

        // getters
        public int getArmor() {
                return armor;
        }

        // instance methods

        /**
         * Hitpoints are only reduced if armor is 0
         * @param damage Amount of Hitpoints/Armor to reduce
         */
        @Override
        public void takeDamage(int damage) {
                this.armor -= damage;
                if (this.armor <= 0) {
                        this.hitpoints += armor;        // as armor is negative
                        armor = 0;
                }

                if (this.hitpoints <= 0) {
                        this.isAlive = false;
                        this.hitpoints = 0;
                }
        }

}
