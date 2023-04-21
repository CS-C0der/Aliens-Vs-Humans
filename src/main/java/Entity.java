public abstract class Entity {

    // Instance variables
    // private final Planet homePlanet;
    //private final String name;
    private int hitpoints = 100;
    private String name;
    private boolean isAlive = true;
    private Weapon weapon;
    private Planet homePlanet;

    // constructor
    public Entity(Planet homePlanet, String name, Weapon weapon) {
        this.homePlanet = homePlanet;
        this.name = name;
        this.weapon = weapon;
    }



    // setter methods
    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    };

    // getter methods
    public Planet getHomePlanet(){
        return this.homePlanet;
    };

    public String getName(){
        return name;
    }

    public int getHitpoints(){
        return hitpoints;
    };

    public boolean isAlive(){
        return isAlive;
    };

    public Weapon getWeapon(){
        return weapon;
    };

    // Instance methods
    public void takeDamage(int damage){

    };

    public int doDamge(){
        return weapon.getDamage();
    };

}
