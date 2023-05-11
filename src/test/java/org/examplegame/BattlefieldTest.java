package org.examplegame;

import org.examplegame.entities.Alien;
import org.examplegame.entities.Cat;
import org.examplegame.entities.Human;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BattlefieldTest {

    private Battlefield battlefield, battlefieldExCats;
    private ArrayList<Battlefield> battlefields = new ArrayList<>();

    @BeforeEach
    public void beforeEachTest(){
        int numberOfCats = 3;
        int teamSize = 5;

        for (int i=0; i < 50; i++){
            battlefields.add(new Battlefield(5,7));
            Helper.resetStaticVariables();
        }

        battlefield = new Battlefield(teamSize, numberOfCats);
        battlefieldExCats = new Battlefield(teamSize, 0);

    }

    @Test
    @DisplayName("Tests consructor of battlefield")
    public void testBattlefield(){
        battlefields.forEach(o -> o.printTeams());
        assertAll(
                () -> assertEquals(5, battlefieldExCats.getTeamHumanMembers(), "Human Team Members at Start"),
                () -> assertEquals(5, battlefieldExCats.getTeamAlienMembers(), "Alien Team Members at Start"),
                () -> assertEquals(5, battlefieldExCats.getReadyHumansCount()),
                () -> assertEquals(5, battlefieldExCats.getReadyAliensCount()),
                // check if all values of teamHumanMembers in battlefields are the same (all are equal to first element of battelfields)
                // if cats are assigned randomly this should be false: (check if all TeamHumanMembers are the same as TeamHumanMembers in first entry of battlefields)
                () -> assertFalse( battlefields.stream().allMatch(o -> o.getTeamHumanMembers() == battlefields.get(0).getTeamHumanMembers()) )
        );
    }

    @Test
    @DisplayName("Test startWar method")
    public void testStartWar(){
        for (int i = 0; i < 5; i++){
            battlefields.get(i).startWar();
        }
    }

    @AfterEach
    public void afterEachTest(){
        Helper.resetStaticVariables();

    }

}