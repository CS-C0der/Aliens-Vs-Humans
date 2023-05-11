package org.examplegame;

import org.examplegame.entities.Alien;
import org.examplegame.entities.Cat;
import org.examplegame.entities.Entity;
import org.examplegame.entities.Team;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 *  when Fight1on1 is submitted it returns the winner when fight/task is finished
 */
public class Fight1on1 implements Callable<Entity> {

    //ToDo
    //  Fight1on1 gibt nur gewinner zurück. rest macht battlefield. viel einfacher zu testen und intuitiver

    // instance variables
    Entity entityTeamAlien, entityTeamHuman;

    // constructor
    public Fight1on1 (Entity entity1, Entity entity2){
        // order of parameters not fixed. entity1 can be team human or team alien
        if (entity1.getTeam() == Team.ALIENS){
            this.entityTeamAlien = entity1;
            this.entityTeamHuman = entity2;
        } else {
            this.entityTeamHuman = entity1;
            this.entityTeamAlien = entity2;
        }

    }

    @Override
    public Entity call() throws RuntimeException{

        // check if matching of opponents is allowed
        if ( (entityTeamAlien.getClass() == entityTeamHuman.getClass()) && !(entityTeamAlien instanceof Cat) ) {
            throw new RuntimeException("Fight Human vs. Human or Alien vs. Alien is not allowed");
        }

        if (entityTeamAlien instanceof Alien){
            // downcast to use Alien methods
            Alien alien = (Alien) entityTeamAlien;
            switch (alien.getRace()){
                case FACEHUGGER -> {
                    if (entityTeamHuman instanceof Cat){
                        // cat always wins against facehugger
                        return entityTeamHuman;
                    } else {
                        // if not a cat, entity 2 must be human
                        // 50/50 chance who wins, depending on who atacks first
                        Random random = new Random();
                        if (random.nextBoolean()){
                            return entityTeamAlien;
                        } else {
                            return entityTeamHuman;
                        }
                    }
                }
                case BORG -> {

                }
                default -> {

                }
            }
            if (alien.getRace().equals(Race.FACEHUGGER)){

            }
        }

        // ToDo standardrückgabe entfernen!
        Random random = new Random();
        if (random.nextBoolean()){
            return entityTeamAlien;
        } else {
            return entityTeamHuman;
        }

    }

}
