package org.examplegame;

import org.examplegame.entities.*;
import org.junit.jupiter.api.*;

import java.util.concurrent.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Fight1on1Test {

    final ExecutorService threadPool = Executors.newFixedThreadPool(10);

    @BeforeEach
    public void beforeEachTest(){

        Helper.resetStaticVariables();
    }

    @Test
    @DisplayName("Test fight1on1")
    public void testRun(){
        // x facehugger mensch würfeln test ob beide mal gewinnen. (mensch wird zu Zombie wird von Battlefield gemanaget - weil eh geprüft wird wer gewinnt)
        // facehugger cat -> katze gewinnt, weil facehugger katze nicht infizieren kann
        // zombie mensch
        // alf mensch
        // alf katze
        // borg mensch
        // borg katze : gibt es borg katzen? warum nicht?
        // klingon mensch
        // mensch katze -> funktionieren 9 leben?

    }

    @Test
    @DisplayName("Test Fight Facehugger vs. Human")
    public void testFacehuggerHuman(){

        int facehuggerWins = 0;
        int humanWins = 0;
        // do 10 fights
        for (int i = 0; i < 10; i++){
            // create new Task (Fight)
            Fight1on1 fight = new Fight1on1(new Alien(Race.FACEHUGGER), new Human());
            // start task and store return value
            Future<Entity> winner = threadPool.submit(fight);
            try {
                if (winner.get() instanceof Alien){
                    facehuggerWins++;
                } else {
                    humanWins++;
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        }
        System.out.println("Fachugger Wins: " + facehuggerWins + "  - Human Wins: " +humanWins);
        // in 10 fight with random winner each Entity should at least win once!
        assertFalse(facehuggerWins == 0 || humanWins == 0, "each Entity should at least win once!");

    }

    @Test
    @DisplayName("Test Fight Facehugger Cat")
    public void testFacehuggerCat(){
        Fight1on1 fight = new Fight1on1(new Alien(Race.FACEHUGGER), new Cat());
        // start task and store return value
        Future<Entity> winner = threadPool.submit(fight);
        try {
            assertTrue(winner.get() instanceof Cat, "Winner should be Cat!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void afterEachTest(){
        Helper.resetStaticVariables();
    }

    @AfterAll
    public void afterAllTests(){
        threadPool.shutdown();
    }

}

