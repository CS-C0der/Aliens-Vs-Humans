package org.examplegame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * possible Races of Aliens. Note: Human is not defined as a race in this game
 * Each Race has a name and a home planet which is automatically assigned on creation
 */
public enum Race {
    FACEHUGGER("Facehugger", Planet.BLACKMESA),
    ZOMBIE("Zombie", Planet.BLACKMESA),
    KLINGON("Klingon", Planet.KRONOS),
    BORG("Borg", Planet.DELTAQUADRANT),
    ALF("Alf", Planet.MELMAC);

    private final String name;
    private final Planet homePlanet;
    static private final List<Race> RacesExZombie;
    static {
        RacesExZombie = new ArrayList<>(Arrays.asList(Race.values()));
        RacesExZombie.remove(Race.ZOMBIE);
    }

    // constructor
    Race(String name, Planet planet){
        this.name = name;
        this.homePlanet = planet;
    }

    // class methods
    public static Race randomRace(){
        Random random = new Random();
        return RacesExZombie.get(random.nextInt(RacesExZombie.size()));
    }

    // getter methods

    /**
     * @return a list of all Races excluding Zombies. (There are no Zombies at the start of a war)
     */
    public static List<Race> getRacesExZombie(){
        return Race.RacesExZombie;
    }

    public String getName() {
        return name;
    }

    public Planet getHomePlanet(){
        return homePlanet;
    }
}
