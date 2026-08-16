package entities.enemy;
/**
 * @author Mathilo
 * @author Elias
 */
public class EnemyLevel5 extends Enemy {
    public EnemyLevel5() {
        super(2, 30);
        setImage("Coin.png"); //TODO FIX @elias/art guys
    }

    @Override
    public String getName() {
        return "EnemyLevel5";
    }
}