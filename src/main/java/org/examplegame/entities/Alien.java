package org.examplegame.entities;

import org.examplegame.Planet;
import org.examplegame.Weapon;
import org.examplegame.entities.Entity;

public class Alien extends Entity {

    // ToDo: name of alien should be race + racecount
    //  - allow only 1 Alf -> name should be just Alf
    //  - a zombi can only mutate from facehugger

    // constructor
    public Alien(Planet homePlanet, String name, Weapon weapon){

        super(homePlanet, name, weapon);
    }
}
