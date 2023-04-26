import entities.Entity;

import java.util.Map;

public class Human extends Entity {

        // ToDo get Names out of a CSV file
        static private String[] humanNames = {"Werner", "Dieter", "Hans-Peter", "Otto", "Alfred", "Heinz", "Paul", "Klaus", "Frank" };
        static private Map nameLibary;

        // forschleife oder Stream um die values auf 0 zu setzen

        // ToDo if name for second time add (number) -> Paul (2)

        // constructor
        public Human(Planet homePlanet, String name, Weapon weapon){

                super(homePlanet, name, weapon);
        }
}
