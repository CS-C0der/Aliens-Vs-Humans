package org.examplegame;

import org.examplegame.entities.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Represents the battlefield for the war. Battlefield class creates new Entities and manages who fights against whom.
 * It also handles the output and determines the winner of the war.
 */
public class Battlefield {

    /*
     * Rules for fighting:
     *      - if Facehuggers attack first: Human+Facehugger become a Zombie
     *      - if Human attacks first: human kills facehugger
     *      - Alf eats cats: Alf always wins against cat
     *      - when borg defeats human: human becomes new borg entity
     */

    // instance variables

    // variables that remembers how many members of a Team are still alive
    private int teamHumanMembers = 0;
    private int teamAlienMembers = 0;

    // variables that remembers who many entities are ready for a fight (aren't in a fight right now).
    ArrayList<Entity> humansReadyToFight = new ArrayList<>();
    ArrayList<Entity> aliensReadyToFight = new ArrayList<>();

    // constructors

    /**
     * Generates a Battlefield with equal number of Humans/Aliens.
     * Plus Adds a number of cats to the battle, which are randomly assigned to a team
     *
     * @param initialTeamSize number of team members of each Team
     * @param initialNumberOfCats number of cats assigned to the battlefield
     * @throws RuntimeException throws Exception if teamsize < 1 or number of cats < 0
     */
    public Battlefield(int initialTeamSize, int initialNumberOfCats) throws RuntimeException {
        if (initialTeamSize <= 0  || initialNumberOfCats < 0){
            throw new RuntimeException("Bad Arguments for Battlefield constructor");
        }

        for (int i = 0; i < initialTeamSize; i++){
            humansReadyToFight.add(new Human());
            teamHumanMembers++;

            aliensReadyToFight.add(new Alien(Race.randomRace()));
            teamAlienMembers++;
        }

        for (int i = 0; i < initialNumberOfCats; i++){
            Cat cat = new Cat();
            if ( cat.getTeam().equals(Team.HUMANS)){
                humansReadyToFight.add(cat);
                teamHumanMembers++;
            } else {
                aliensReadyToFight.add(cat);
                teamAlienMembers++;
            }
        }
    }

    /**
     * Generates a Battlefield with specified amount of Entities.
     * @param humans created on Battlefield
     * @param catsTeamHuman created on Battlefield
     * @param catsTeamAlien created on Battlefield
     * @param aliens created on Battlefield
     * @param race Race of Aliens created on Battlefield (if aliens=0 this is irrelevant)
     */
    public Battlefield(int humans, int catsTeamHuman, int catsTeamAlien, int aliens, Race race){

        for (int i = 0 ; i < humans; i++){
            humansReadyToFight.add(new Human());
            teamHumanMembers++;
        }

        for (int i = 0 ; i < catsTeamHuman; i++){
            humansReadyToFight.add(new Cat());
            teamHumanMembers++;
        }

        for (int i = 0 ; i < catsTeamAlien; i++){
            aliensReadyToFight.add(new Cat());
            teamAlienMembers++;
        }

        for (int i = 0 ; i < aliens; i++){
            aliensReadyToFight.add(new Alien(race));
            teamAlienMembers++;
        }

    }

    // getter methods

    /**
     * @return number of team members of team Human still alive
     */
    public int getTeamHumanMembers() {
        return teamHumanMembers;
    }


    /**
     * @return number of Entities in Team Human who are ready to fight
     */
    public int getReadyHumansCount() {
        return humansReadyToFight.size();
    }

    /**
     * @return number of team members of team Alien still alive
     */
    public int getTeamAlienMembers() {
        return teamAlienMembers;
    }

    /**
     * @return number of Entities in Team Alien who are ready to fight
     */
    public int getReadyAliensCount() {
        return aliensReadyToFight.size();
    }

    // instance methods

    /**
     * Starts the war. Number of Teammembers and Number of Cats is already set in the constructor.
     * Start several fights, so that all Entities are in a fight (if size of Teams is equal)
     */
    public void startWar(){
        final ExecutorService threadPool = Executors.newCachedThreadPool();
        CompletionService<WinnerAndLoser> completionService = new ExecutorCompletionService<>(threadPool);
        ReentrantLock lock = new ReentrantLock();

        // print Teams at Beginning of War
        System.out.println("War Aliens vs. Humans starts!");
        printTeams();

        // start several fights so that every TeamMember is in a fight
        int fightsAtStart = Math.min(humansReadyToFight.size() , aliensReadyToFight.size() );
        for (int i=0; i < fightsAtStart; i++) {
            Random random = new Random();
            // pick random element of entity ready to fight
            // otherwise cats always fight cats because they are always added to the list at the end
            Entity entityTeamHuman = humansReadyToFight.remove(random.nextInt(0, humansReadyToFight.size()));
            Entity entityTeamAlien = aliensReadyToFight.remove(random.nextInt(0, aliensReadyToFight.size()));
            startFight(entityTeamAlien, entityTeamHuman, completionService);
        }

        // as soon as a fight/thread is finished process results and start a new one!
        // this is accomplished by completionService.take(). take() waits until there is a result in the
        // queue of completion service and takes the first element of the queue
        while (true) {

            try{
                // get (first) result from completionService queue
                Future<WinnerAndLoser> future = completionService.take();   // take() waits until there is a result in the queue
                Entity winner = future.get().getWinner();
                Entity loser = future.get().getLoser();

                // process result
                if (winner.getTeam().equals(Team.ALIENS)){
                    teamHumanMembers--;   // 1 member of team human defeated

                    // special cases: facehugger and borg
                    if (winner instanceof Alien){   // remember: could also be cat...
                        Alien alienWinner = (Alien) winner;
                        if ( ((Alien) winner).getRace().equals(Race.FACEHUGGER)){
                            // acquire lock to make sure Result and current Score are always printed directly after each other!
                            // otherwise output can be confusing
                            lock.lock();
                            try {
                                printResult(future.get());
                                ((Alien) winner).mutate((Human)loser);       // loser can't be cat because facehuggers never wins against cats
                                printCurrentScore();
                            } finally {
                                lock.unlock();
                            }
                        } else if (alienWinner.getRace().equals(Race.BORG)){
                            lock.lock();
                            try {
                                printResult(future.get());
                                // human respawns as new borg. cats can't become borg
                                if (! (loser instanceof Cat)){
                                    teamAlienMembers++;
                                    aliensReadyToFight.add(new Alien(Race.BORG));
                                }
                                printCurrentScore();
                            } finally {
                                lock.unlock();
                            }
                        } else {
                            // team alien but not borg or facehugger or cat
                            lock.lock();
                            try {
                                printResult(future.get());
                                printCurrentScore();
                            } finally {
                                lock.unlock();
                            }
                        }
                    } else {
                        // Cat (Team Alien) wins
                        lock.lock();
                        try {
                            printResult(future.get());
                            printCurrentScore();
                        } finally {
                            lock.unlock();
                        }
                    }
                    aliensReadyToFight.add(winner); // winner is ready to fight again!
                } else {
                    // winner is team human
                    lock.lock();
                    try {
                        printResult(future.get());
                        teamAlienMembers--;
                        printCurrentScore();
                    } finally {
                        lock.unlock();
                    }
                    humansReadyToFight.add(winner);
                }

                // break condition
                if (0 == teamHumanMembers ){
                    System.out.println("Team Alien Wins!!\n");
                    break;
                } else if (0 == teamAlienMembers){
                    System.out.println("Team Human Wins!!\n");
                    break;
                }

                // start new fight (if at least one member of opposing team is ready to fight)
                if (humansReadyToFight.size() > 0 && aliensReadyToFight.size() > 0){
                    startFight(humansReadyToFight.remove(0) , aliensReadyToFight.remove(0), completionService);
                } else {
                    System.out.println(winner.getName() + " can't start new fight. All opponents are busy in fights");
                }

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Creates new fight(thread) and submits it to completionService
     * @param entity1 can be Team Human or Team Alien. Fight1on1 class takes care
     * @param entity2 can be Team Human or Team Alien. Fight1on1 class takes care
     * @param completionService stores the results of the tasks in a queue in the order of the completion of the task
     */
    private void startFight(Entity entity1, Entity entity2, CompletionService<WinnerAndLoser> completionService){
        System.out.println(entity1.getName() + " starts to fight against " + entity2.getName());
        Fight1on1 fight = new Fight1on1(entity1 , entity2);
        // start task (results are stored in queue of completionService)
        completionService.submit(fight);
    }

    /**
     * Prints result of the fight to Console
     * @param winnerAndLoser contains the winner and the loser entity as an object reference
     */
    private void printResult(WinnerAndLoser winnerAndLoser){
        Entity winner = winnerAndLoser.getWinner();
        Entity loser = winnerAndLoser.getLoser();

        if (winner instanceof Alien){
            // downcast to Alien to access getRace()
            Alien alien = (Alien) winner;

            switch (alien.getRace()){
                case FACEHUGGER -> {
                    System.out.print(alien.getName() + " defeats " + loser.getName() + " -");
                    System.out.println(" They mutate to a Zombie");
                }
                case BORG -> {
                    // only humans respawn as borg. cats cannot become borg
                    if (! (loser instanceof Cat)){
                        System.out.print(alien.getName() + " defeats " + loser.getName() + ". ");
                        System.out.println(loser.getName() + " respawns as a new Borg");
                    } else {
                        printResultDefault(alien, loser);
                    }
                }
                case ALF -> {
                    if (loser instanceof Cat){
                        System.out.println(alien.getName() + " eats " + loser.getName());
                    } else {
                        printResultDefault(alien, loser);
                    }
                }
                default -> printResultDefault(alien, loser);
            }
        } else if (winner instanceof Human){
            printResultDefault(winner, loser);
        } else {
            // winner is cat
            System.out.println(winner.getName() + " kills " + loser.getName() + " with its claws");
        }
    }

    private void printResultDefault(Entity winnerEntity, Entity loserEntity){
        System.out.println(winnerEntity.getName() + " kills " + loserEntity.getName() + " with " + winnerEntity.getWeapon().getName());
    }

    /**
     * Prints to console how many Aliens/Humans are still alive
     */
    public void printCurrentScore(){
        System.out.println("Fighters Team Alien still alive: " + teamAlienMembers + " - Fighters Team Human still alive: " + teamHumanMembers );
    }

    public void printTeams(){
        System.out.println("Size of Team Alien: " + teamAlienMembers);
        System.out.print("Aliens ready to fight: ");
        aliensReadyToFight.forEach(o -> System.out.print(o.getName() + ", "));
        System.out.print("\n");

        System.out.println("Size of Team Human: " + teamHumanMembers);
        System.out.print("Humans ready to fight: ");
        humansReadyToFight.forEach(o -> System.out.print(o.getName()+ ", ") );
        System.out.print("\n");
    }
}
