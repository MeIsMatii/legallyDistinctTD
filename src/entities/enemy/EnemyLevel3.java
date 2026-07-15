package entities.enemy;

public class EnemyLevel3 extends Enemy {
    public EnemyLevel3() {
        super(1.5, 1);
        setImage("arealEater.png"); //TODO FIX @elias/art guys
    }
    @Override
    public String getName() {
        return "EnemyLevel3";
    }
}
