package entities.enemy;

public class EnemyLevel5 extends Enemy {
    public EnemyLevel5() {
        super(2, 1);
        setImage("Coin.png"); //TODO FIX @elias/art guys
    }
    @Override
    public String getName() {
        return "EnemyLevel5";
    }
}