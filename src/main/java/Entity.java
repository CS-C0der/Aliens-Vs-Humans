public abstract class Entity {

    // Instance variables
    // private final Planet homePlanet;
    //private final String name;
    private int hitpoints = 100;
    private boolean isAlive = true;
    private Weapon weapon;
    private Planet homePlanet;

    // constructor
    public Entity(Planet homePlanet, String name, Weapon weapon) {

    }

    // getter methods

    // setter methods

    // Instance methods
    public abstract int takeDamage();


    public Planet getHomePlanet(){
        return this.homePlanet;
    };

    public String getName(){
        return "";
    }

    public int getHitpoints(){
        return 0;
    };

    public boolean isAlive(){
        return false;
    };

    public Weapon getWeapon(){
        return Weapon.NONE;
    };

    public int getDamageUnarmed(){
        return -1;
    };
}
