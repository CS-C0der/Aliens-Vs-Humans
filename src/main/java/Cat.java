public class Cat extends Entity{

    public Cat(Planet homePlanet, String name, Weapon weapon){
        super(homePlanet, name, weapon);
    }

    @Override
    public void takeDamage(int damage){
        // wenn HP < 0 set life = life - 1
    }
}
