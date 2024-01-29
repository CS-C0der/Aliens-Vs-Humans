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
        // ToDo get names out of a CSV file
        static private final String[] humanNames = {"Werner", "Dieter", "Hans-Peter", "Otto", "Alfred", "Heinz", "Paul", "Klaus", "Frank",
                                                        "Anita", "Brigitte", "Cordula", "Daniela", "Erika", "Florentine", "Gundula", "Heike", "Isolde"};
        static private Map<String, Integer> combatantsList = new HashMap<>();
        static{
                // initialize combatantsList with values = 0 for each name. 0 means they don't fight
                for (String name : humanNames){
                        combatantsList.put(name, 0);
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
         * Sets the values of combatantsList to 0 for every Name
         */
        public static void resetCombatantsList(){
                for (String name : humanNames){
                        combatantsList.replace(name, 0);
                }
        }

        /**
         * @return number of different available names
         */
        // class methods
        static int numberOfNames(){
                return humanNames.length;
        }

        /**
         * Return a random human name.
         * <p>
         * If name is already active in combatantsList, choose a different name.
         *
         * @return random Name
         */
        private static String getRandomName(){
                Random random = new Random();
                String name;
                name = humanNames[random.nextInt(humanNames.length)];
                // check if name already exists
                if ( 0 == combatantsList.get(name)) {
                        combatantsList.replace(name, 1); //turn "active" on combatantsList by setting their value to 1
                        return name;
                } else {
                        // person already enlisted, so choose someone else
                        return getRandomName();
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
                        this.hitpoints += armor;        // when armor drops below 0, transfer overlap damage to hitpoints
                        armor = 0;
                }

                if (this.hitpoints <= 0) {
                        this.alive = false;
                        this.hitpoints = 0;
                }
        }

}
