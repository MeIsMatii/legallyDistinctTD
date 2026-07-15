package entities.enemy;

public class EnemyLevel1 extends Enemy {
    public EnemyLevel1() {
        super(1, 1);
        setImage("arealEnemy1.png"); //TODO FIX @elias/art guys
    }

    @Override
    public String getName() {
        return "EnemyLevel1";
    }
}
