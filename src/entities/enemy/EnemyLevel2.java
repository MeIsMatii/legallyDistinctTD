package entities.enemy;
/**
 * @author Mathilo
 * @author Elias
 */
public class EnemyLevel2 extends Enemy {
    public EnemyLevel2() {
        super(1.25, 1);
        setImage("enemies/arealEnemy1.png");
    }

    @Override
    public String getName() {
        return "EnemyLevel2";
    }
}