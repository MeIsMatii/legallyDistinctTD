package entities.enemy;

public class EnemyLevel4 extends Enemy {
    public EnemyLevel4() {
        super(1.75, 1);
        setImage("Coin.png"); //TODO FIX @elias/art guys
    }
    @Override
    public String getName() {
        return "EnemyLevel4";
    }
}