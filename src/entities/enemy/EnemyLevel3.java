package entities.enemy;
/**
 * @author Mathilo
 * @author Elias
 */
public class EnemyLevel3 extends Enemy {
    public EnemyLevel3() {
        super(1.5, 1);
        setImage("enemies/arealEater.png");
    }

    @Override
    public String getName() {
        return "EnemyLevel3";
    }
}
