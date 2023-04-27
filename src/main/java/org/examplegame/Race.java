package org.examplegame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public enum Race {
    FACEHUGGER("Facehugger"),
    ZOMBIE("Zombie"),
    KLINGON("Klingon"),
    BORG("Borg"),
    ALF("Alf");

    private String name;
    static private final List<Race> RacesExZombie;
    static {
        RacesExZombie = new ArrayList<>(Arrays.asList(Race.values()));
        RacesExZombie.remove(Race.ZOMBIE);
    }

    // constructor
    Race(String name){
        this.name = name;
    }

    // class methods
    public static Race randomRace(){
        Random random = new Random();
        return RacesExZombie.get(random.nextInt(RacesExZombie.size()));
    }

    // getter methods
    public static List<Race> getRacesExZombie(){
        return Race.RacesExZombie;
    }

}
