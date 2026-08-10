package entities.projectiles;

import entities.enemy.Enemy;
import entities.tower.Tower;
import util.HasSound;

import java.util.List;

/**
 * @author Mathilo
 * @author Jannis
 */
public class Explosion extends Projectile implements HasSound {

    private int explosionCounter = 10;
    private int damageCounter = 0;
    private final int damageTimer = 10;
    private boolean hasHit = false;

    public Explosion(Tower owner) {
        super(owner);
        setImage("Explosion.png");
        getImage().scale(100, 100);
        playSound("Explosion.mp3");
    }
    public Explosion() {
        super();
        setImage("Explosion.png");
        getImage().scale(100, 100);
        playSound("Explosion.mp3");
    }

    public void act() {
        if(!hasHit) {
            return;
        }

        damageCounter++;
        if(damageCounter >= damageTimer) {
            damageCounter = 0;

            List<Enemy> inRange = getObjectsInRange((int) getOwner().getRange(), Enemy.class);

            for (Enemy enemy : inRange) {
                enemy.damage(getDamage());
            }
        }

        explosionCounter--;
        if (explosionCounter <= 0) {
            getWorld().removeObject(this);
        }
    }

    public int getExplosionCounter() {
        return explosionCounter;
    }

    public void setExplosionCounter(int explosionCounter) {
        this.explosionCounter = explosionCounter;
    }

    public void onHit(Enemy e) {
        hasHit = true;
    }

}
//für traptower lool