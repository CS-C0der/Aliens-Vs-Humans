package org.examplegame;

import org.examplegame.entities.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

public class Battlefield {

    /*
     * Rules for fighting:
     *      - if Facehuggers attack first: Human becomes Zombie
     *      - if Human attacks first: human kills facehugger
     *      - Alf eats cats: cat loses 1 live per Attack
     *      - when borg defeats human: human becomes new borg entity
     */

    // instance variables
    private TeamMemberCount teamHumanMembers = new TeamMemberCount();
    private TeamMemberCount teamAlienMembers = new TeamMemberCount();
    ArrayList<Entity> humansReadyToFight = new ArrayList<>();
    ArrayList<Entity> aliensReadyToFight = new ArrayList<>();

    // constructor
    public Battlefield(int initialTeamSize, int initialNumberOfCats) throws RuntimeException {
        if (initialTeamSize <= 0  || initialNumberOfCats < 0){
            throw new RuntimeException("Bad Arguments for Battlefield constructor");
        }

        for (int i = 0; i < initialTeamSize; i++){
            humansReadyToFight.add(new Human());
            teamHumanMembers.increment();

            aliensReadyToFight.add(new Alien(Race.randomRace()));
            teamAlienMembers.increment();
        }

        for (int i = 0; i < initialNumberOfCats; i++){
            Cat cat = new Cat();
            if ( cat.getTeam().equals(Team.HUMANS)){
                humansReadyToFight.add(cat);
                teamHumanMembers.increment();
            } else {
                aliensReadyToFight.add(cat);
                teamAlienMembers.increment();
            }
        }
    }

    // getter methods
    public int getTeamHumanMembers() {
        return teamHumanMembers.getValue();
    }

    public int getReadyHumansCount() {
        return humansReadyToFight.size();
    }

    public int getTeamAlienMembers() {
        return teamAlienMembers.getValue();
    }

    public int getReadyAliensCount() {
        return aliensReadyToFight.size();
    }

    // instance methods

    /**
     * @return the Winner Team of the War
     */
    public Team startWar(){
        final ExecutorService threadPool = Executors.newFixedThreadPool(10);
        CompletionService<Entity> completionService = new ExecutorCompletionService<>(threadPool);

        // start several fights so that every TeamMember is in a fight
        int fightsAtStart = Math.min(humansReadyToFight.size() , aliensReadyToFight.size() );
        for (int i=0; i < fightsAtStart; i++) {
            // ToDo nicht nullten nehmen sondern random. weil katzen zum schlusshinzugefügt werden kämpfen sonst nur katzen gegen katzen
            Entity entityTeamHuman = humansReadyToFight.remove(0);
            Entity entityTeamAlien = aliensReadyToFight.remove(0);
            startFight(entityTeamAlien, entityTeamHuman, completionService);
        }

        // as soon as a fight/thread is finished start a new one!
        while (true) {

            try{
                // get (first) result from completionService queue
                Future<Entity> future = completionService.take();   // take() waits until there is a result in the queue
                Entity winner = future.get();

                // process result
                if (winner.getTeam().equals(Team.ALIENS)){
                    teamHumanMembers.decrement();   // 1 member of team human defeated
                    aliensReadyToFight.add(winner); // winner is ready to fight again!
                } else {
                    teamAlienMembers.decrement();
                    humansReadyToFight.add(winner);
                }
                System.out.println(winner.getName() + " wins fight!");

            } catch (Exception e){
                e.printStackTrace();
            }

            // break condition
            if (0 == teamHumanMembers.getValue() ){
                System.out.println("Team Alien Wins!!");
                return Team.ALIENS;
            } else if (0 == teamAlienMembers.getValue()){
                System.out.println("Team Human Wins!!");
                return Team.HUMANS;
            }

            // start new fight (if at least one member of opposing team is ready to fight)
            if (humansReadyToFight.size() > 0 && aliensReadyToFight.size() > 0){
                startFight(humansReadyToFight.remove(0) , aliensReadyToFight.remove(0), completionService);
            }

        }
    }

    /**
     * Creates new fight(thread) and submits it to completionService
     * @param entity1
     * @param entity2
     * @param completionService
     */
    private void startFight(Entity entity1, Entity entity2, CompletionService<Entity> completionService){
        Fight1on1 fight = new Fight1on1(entity1 , entity2);
        // start task (results are stored in queue of completionService)
        completionService.submit(fight);
        System.out.println(entity1.getName() + " starts to fight against " + entity2.getName());
    }

    public void printTeams(){
        System.out.println("");
        System.out.println("Size of Team Alien: " + teamAlienMembers.getValue());
        System.out.println("aliensReadyToFight:");
        aliensReadyToFight.forEach(o -> System.out.println(o.getName()));

        System.out.println("Size of Team Human: " + teamHumanMembers.getValue());
        System.out.println("humansReadyToFight:");
        humansReadyToFight.forEach(o -> System.out.println(o.getName()));
        System.out.println("");
    }


    // ToDO : TeamHumanMembers u. TeamAlienMembers muss von Battlefield verwaltet werden, weil in beiden auch Katzen enthalten sind
    // https://www.baeldung.com/java-executor-wait-for-threads
    // ExecutorService executor = Executors.newFixedThreadPool(20) // threads do a lot of waiting during fight -> makes sense to have more threads than cpus/cores!
    // executerCompletionService
    // oder auch Buch S.984

    //
}
