package org.examplegame;

import org.examplegame.entities.*;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 *  when Fight1on1 is submitted (call() is executed) it returns the winner/looser as soon as fight/task is finished
 *  every Fight1on1 is a Task
 */
public class Fight1on1 implements Callable<WinnerAndLoser> {

    // instance variables
    Entity entityTeamAlien, entityTeamHuman;

    /**
     * @param entity1 can be Team Human or Team Alien. Constructor takes care
     * @param entity2 can be Team Human or Team Alien. Constructor takes care
     */
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

    /**
     * Processes the 1 on 1 Fight of 2 Entities. Fight1on1 does not handle the mutation of Facehugger to Zombie or assimilation of human to Borg.
     * This is done by Battlefield
     *
     * @return winner and loser of the Fight
     * @throws RuntimeException thrown if Human fights against Human or Alien against Alien.
     */
    @Override
    public WinnerAndLoser call() throws RuntimeException{

        Random random = new Random();

        // check if matching of opponents is allowed
        if ( (entityTeamAlien.getClass() == entityTeamHuman.getClass()) && !(entityTeamAlien instanceof Cat) ) {
            throw new RuntimeException("Fight Human vs. Human or Alien vs. Alien is not allowed");
        }

        if (entityTeamAlien instanceof Alien){
            // downcast to use Alien methods
            Alien alien = (Alien) entityTeamAlien;
            switch (alien.getRace()){
                case FACEHUGGER -> {
                    try{
                        Thread.sleep(random.nextInt(1,4) * 1000);   // fight takes between 1 and 3 seconds
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    if (entityTeamHuman instanceof Cat){
                        // cat always wins against facehugger
                        return new WinnerAndLoser(entityTeamHuman, entityTeamAlien);
                    } else {
                        // if not a cat, entity 2 must be human
                        // 50/50 chance who wins, depending on who attacks first
                        if (random.nextBoolean()){
                            return new WinnerAndLoser(entityTeamAlien, entityTeamHuman);
                        } else {
                            return new WinnerAndLoser(entityTeamHuman, entityTeamAlien);
                        }
                    }
                }
                case ALF -> {
                    try{
                        Thread.sleep(random.nextInt(1,4) * 1000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    if (entityTeamHuman instanceof Cat){
                        // Alf always wins against cat because Alf eats cats
                        return new WinnerAndLoser(alien, entityTeamHuman);
                    } else {
                        // but always loses against humans
                        return new WinnerAndLoser(entityTeamHuman, alien);
                    }

                }
                default -> {
                    return regularFight(entityTeamHuman, entityTeamAlien);
                }
            }
        } else {
            // entityTeam alien is Cat
            return regularFight(entityTeamHuman, entityTeamAlien);
        }
    }

    /**
     * processes regular fight (no Facehugger or Alf involved)
     *
     * @param entityTeamHuman
     * @param entityTeamAlien
     * @return winner and loser of fight
     */
    private WinnerAndLoser regularFight(Entity entityTeamHuman, Entity entityTeamAlien){
        try {
            // fight should take between 1 & 3 seconds
            Random random = new Random();
            Thread.sleep(random.nextInt(1,4) * 1000);

            // fight until one entity is dead
            while (true){
                // Human attacks
                entityTeamAlien.takeDamage(entityTeamHuman.doDamge());
                if (!entityTeamAlien.isAlive()){
                    break;
                }

                // Alien attacks
                entityTeamHuman.takeDamage(entityTeamAlien.doDamge());
                if (!entityTeamHuman.isAlive()) {
                    break;
                }
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        // return Result
        if (entityTeamHuman.isAlive()){
            return new WinnerAndLoser(entityTeamHuman, entityTeamAlien);
        } else {
            return new WinnerAndLoser(entityTeamAlien, entityTeamHuman);
        }
    }



}
