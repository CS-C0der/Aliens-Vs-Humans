import org.examplegame.Planet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlanetTest {

    @Test
    @DisplayName("all org.examplegame.Planet Constants exist")
    public void planetTest(){
        assertAll(
                () -> assertNotNull(Planet.EARTH),
                () -> assertNotNull(Planet.MARS),
                () -> assertNotNull(Planet.MELMAC),
                () -> assertNotNull(Planet.BLACKMESA),
                () -> assertNotNull(Planet.KRONOS)
        );
    }
}