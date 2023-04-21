import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityTest {

    // Create concrete class that extends Entity in order to test i.e. constructor
    public class ConcreteEntity extends Entity{
        public ConcreteEntity(Planet homePlanet, String name, Weapon weapon) {
            super(homePlanet, name, weapon);
        }

    };

    private ConcreteEntity entity;

    @BeforeEach
    public void beforeEachTest(){
        String name = "Entity1";
        entity = new ConcreteEntity(Planet.MARS, name, Weapon.SHOTGUN);

    }

    @Test
    @DisplayName("Test Constructor (and getter methods)")
    public void testEntityConstructor(){
        assertAll(
                () -> assertEquals(Planet.MARS, entity.getHomePlanet()),
                () -> assertEquals("Entity1", entity.getName()),
                () -> assertEquals(100, entity.getHitpoints()),
                () -> assertEquals(true, entity.isAlive()),
                () -> assertEquals(Weapon.SHOTGUN, entity.getWeapon())
        );
    }

    // No Tests for getter methods needed
    // Already tested functionality in testEntityConstructor()

    @Test
    public void testSetWeapon(){
        // remember: @BeforeEach sets Weapon to shotgun
        entity.setWeapon(Weapon.PHASER);
        assertEquals(Weapon.PHASER.getName(), entity.getWeapon().getName());
    }

    // ToDo: test for takeDamage (can be implemented in Entity and later overwritten in Human and cat)
    //  - if HP < 0 set isAlive = false
    //  - override bei Katzen (lives)
    //  - override Human (armor)

    @Test
    public void testDoDamage(){
        // remember: @BeforeEach sets Weapon to shotgun (damage 20)
        assertEquals(20,entity.doDamge() );
        entity.setWeapon(Weapon.CROWBAR);
        // different result with crowbar?
        assertEquals(10,entity.doDamge() );
    }

}