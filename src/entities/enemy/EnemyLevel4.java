package entities.enemy;
/**
 * @author Mathilo
 * @author Elias
 */
public class EnemyLevel4 extends Enemy {
    public EnemyLevel4() {
        super(1.75, 1);
        setImage("enemies/Minni-boss1.png");
    }

    @Override
    public String getName() {
        return "EnemyLevel4";
    }
}