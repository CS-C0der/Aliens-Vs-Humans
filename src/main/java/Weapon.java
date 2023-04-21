/**
 * Enumeration of Weapons. Each Weapon has a name and a value representing the damage is does.
 */
public enum Weapon {
    PHASER("Phaser", 15),
    PLASMACANNON("Plasma Cannon", 34),
    NONE("bare Hands", 5),
    CROWBAR("Crow Bar", 10),
    SHOTGUN("Shotgun", 20);

    private String name;
    private int damage;

    Weapon(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

}
