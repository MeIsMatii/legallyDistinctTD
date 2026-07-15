package entities.enemy;

public class EnemyLevel6 extends Enemy {
    public EnemyLevel6() {
        super(2.25, 1);
        setImage("Coin.png"); //TODO FIX @elias/art guys
    }
    @Override
    public String getName() {
        return "EnemyLevel6";
    }
}