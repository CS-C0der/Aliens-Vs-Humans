package org.examplegame;

import org.examplegame.entities.*;
import org.junit.jupiter.api.*;

import java.util.concurrent.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Fight1on1Test {

    final ExecutorService threadPool = Executors.newCachedThreadPool();

    @BeforeEach
    public void beforeEachTest(){

        Helper.resetStaticVariables();
    }

    @Test
    @DisplayName("Test fight1on1")
    public void testRun(){
        // x facehugger mensch würfeln test ob beide mal gewinnen. (mensch wird zu Zombie wird von Battlefield gemanaget - weil eh geprüft wird wer gewinnt)
        // x facehugger cat -> katze gewinnt, weil facehugger katze nicht infizieren kann
        // x alf mensch -> alf verliert
        // x alf katze -> alf gewinnt
        // (borg mensch) -> brauch man nicht. regulärer figth (mensch wird zu borg wird von battlefield gemanaget)
        // xklingon mensch
        // xmensch katze -> funktionieren 9 leben?

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
            Future<WinnerAndLoser> winnerAndLoser = threadPool.submit(fight);
            try {
                if (winnerAndLoser.get().getWinner() instanceof Alien){
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
        Future<WinnerAndLoser> winnerAndLoser = threadPool.submit(fight);
        try {
            assertTrue(winnerAndLoser.get().getWinner() instanceof Cat, "Winner should be Cat!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Test Fight Alf Cat")
    public void testAlfCat(){
        Fight1on1 fight = new Fight1on1(new Alien(Race.ALF), new Cat());
        // start task and store return value
        Future<WinnerAndLoser> winnerAndLoser = threadPool.submit(fight);
        try {
            assertTrue(winnerAndLoser.get().getWinner() instanceof Alien, "Winner should be Alf!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Test Fight Alf Human")
    public void testAlfHuman(){
        Fight1on1 fight = new Fight1on1(new Alien(Race.ALF), new Human());
        // start task and store return value
        Future<WinnerAndLoser> winnerAndLoser = threadPool.submit(fight);
        try {
            assertTrue(winnerAndLoser.get().getWinner() instanceof Human, "Winner should be Human!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Test Regular Fight")
    public void testRegularFight(){
        Fight1on1 fight = new Fight1on1(new Alien(Race.BORG), new Cat());
        // start task and store return value
        Future<WinnerAndLoser> winnerAndLoser = threadPool.submit(fight);

        try {
            Entity winner = winnerAndLoser.get().getWinner();
            System.out.println("Winner is: " + winner.getName());
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

