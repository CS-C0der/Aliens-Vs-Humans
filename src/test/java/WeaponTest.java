import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeaponTest {

    @Test
    @DisplayName("Test Weapon Name")
    public void testWeaponName(){
        assertEquals("Phaser", Weapon.PHASER.getName());
        assertEquals("Plasma Cannon", Weapon.PLASMACANNON);
        assertEquals("Crow Bar", Weapon.CROWBAR);
        assertEquals("Shotgun", Weapon.SHOTGUN);
        assertEquals("bare Hands", Weapon.NONE.getName());
    }

    @Test
    @DisplayName("Test Weapon Damage")
    public void testWeaponDamage(){
        assertEquals(15, Weapon.PHASER.getDamage());
        assertEquals(34, Weapon.PLASMACANNON.getDamage());
        assertEquals(10, Weapon.CROWBAR.getDamage());
        assertEquals(20, Weapon.SHOTGUN.getDamage());
        assertEquals(5, Weapon.NONE.getDamage());
    }
}