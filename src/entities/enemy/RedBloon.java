package entities.enemy;

import core.Player;
import entities.Entity;
import entities.tower.Tower;

import java.util.List;

public class RedBloon extends Enemy {
    public RedBloon(double speed, int lives) {
        super(speed, lives);
        setImage("Coin.png"); //TODO FIX @elias/art guys
    }

    public void onHit(Entity hitter) {
        if (hitter instanceof Tower) // TODO REPLACE WITH PROJECTILE @ELIAS
        {
            lives--;
            if (lives <= 0) {
                List<Player> player = getWorld().getObjects(Player.class);
                Player player1 = player.get(0);
                player1.setCoins(player1.getCoins() + 1);
                getWorld().removeObject(this);
            }
        }
    }
}