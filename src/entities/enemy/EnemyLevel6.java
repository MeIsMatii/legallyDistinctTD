package entities.enemy;

import entities.base.Enemy;

/**
 * @author Mathilo
 * @author Elias
 */
public class EnemyLevel6 extends Enemy {
    public EnemyLevel6() {
        super(2.25, 200);
        setImage("leviathan.png"); //TODO FIX @elias/art guys
    }

    @Override
    public String getName() {
        return "EnemyLevel6";
    }
}