package entities.enemy;
/**
 * @author Mathilo
 * @author Elias
 */
public class EnemyLevel2 extends Enemy {
    public EnemyLevel2() {
        super(1.25, 10);
        setImage("enemies/sicel.png");
    }

    @Override
    public String getName() {
        return "EnemyLevel2";
    }
}