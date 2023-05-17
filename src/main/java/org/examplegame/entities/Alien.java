package org.examplegame.entities;

import org.examplegame.Planet;
import org.examplegame.Race;
import org.examplegame.Weapon;
import org.examplegame.entities.Entity;

import java.util.HashMap;
import java.util.Map;

public class Alien extends Entity {

    // instance variables
    private boolean doesEatCats;
    private boolean canUseItem;
    private Race race;

    // Map to store count per Race for naming Borg no1, Borg no2 etc.
    private static Map<Race, Integer> raceCount = new HashMap<>();

    static{
        // initialize raceCount with values = 0 for each race
        for (Race race : Race.values()){
            raceCount.put(race, 0);
        }
    }

    // ToDo: name of alien should be race + racecount
    //  - a zombi can only mutate from facehugger
    //  - mutate: waffe von mensch übernehmen

    // constructor
    public Alien(Race race){

        super(race.getHomePlanet(), "dummy", null);

        // increase raceCount in static map
        int currentRaceCount = Alien.getRaceCount(race)+1;
        Alien.raceCount.replace(race, currentRaceCount);


        this.setName(race.getName() + " no. " + currentRaceCount);
        this.race = race;
        this.team = Team.ALIENS;

        if (Race.FACEHUGGER == race || Race.ALF == race) {
            this.canUseItem = false;
            this.setWeapon(Weapon.NONE);
        } else {
            this.canUseItem = true;
            this.setWeapon(Weapon.PHASER);
        }

        if (Race.ALF == race){
            doesEatCats = true;
        } else {
            doesEatCats = false;
        }
    }

    // class methods
    public static int getRaceCount(Race race){
        return Alien.raceCount.get(race);
    }

    // getter methods
    public boolean doesEatCats(){
        return doesEatCats;
    }

    public boolean canUseItem(){
        return canUseItem;
    }

    public Race getRace() {
        return race;
    }

    /**
     * If Facehugger defeats Human: Facehugger + human become one unit/entity and mutate to a zombie
     * see: https://half-life.fandom.com/wiki/Headcrab
     * @param human : zombie adopts weapon of human defeated by the Facehugger
     */
    // instance methods
    public void mutate(Human human) throws RuntimeException{

        if (! (Race.FACEHUGGER.equals(this.race) ) ){
            throw new RuntimeException("Error - Only Facehuggers can mutate!");
        }
        this.race = Race.ZOMBIE;
        // adopts weapon from human
        this.canUseItem = true;
        this.setWeapon(human.getWeapon());

        // adjust numbers in RaceCount
        Alien.raceCount.replace(Race.FACEHUGGER, Alien.raceCount.get(Race.FACEHUGGER) -1);
        Alien.raceCount.replace(Race.ZOMBIE, Alien.raceCount.get(Race.ZOMBIE) +1);

        this.setName(race.getName() + " no. " + Alien.raceCount.get(Race.ZOMBIE));
    }

    /**
     * Sets the values of raceCount to 0 for every Race
     */
    public static void resetRaceCount(){
        for (Race race : Race.values()){
            Alien.raceCount.replace(race, 0);
        }
    }

}
