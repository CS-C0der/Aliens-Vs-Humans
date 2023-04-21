import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityTest {

    // Create concrete class that extends Entity in order to test i.e. constructor
    public class ConcreteEntity extends Entity{
        public ConcreteEntity(Planet homePlanet, String name, Weapon weapon) {
            super(homePlanet, name, weapon);
        }

        @Override
        public int takeDamage(){
            return -1;
        }
    };

    private ConcreteEntity entity;

    @BeforeEach
    public void beforeEachTest(){
        Planet homePlanet = Planet.MARS;
        String name = "Entity1";
        Weapon weapon = Weapon.SHOTGUN;

        entity = new ConcreteEntity(homePlanet, name, weapon);
    }

    @Test
    public void testEntityConstructor(){
        assertAll(
                () -> assertEquals(Planet.MARS, entity.getHomePlanet()),
                () -> assertEquals("Entity1", entity.getName()),
                () -> assertEquals(100, entity.getHitpoints()),
                () -> assertEquals(true, entity.isAlive()),
                () -> assertEquals(Weapon.SHOTGUN, entity.getWeapon())
        );
    }
}