package entities.enemy;

import core.Player;
import entities.Entity;
import entities.projectiles.Projectile;
import entities.tower.Tower;

import java.util.List;

public class RedBloon extends Enemy {
    public RedBloon(double speed, int lives) {
        super(speed, lives);
        setImage("Coin.png"); //TODO FIX @elias/art guys
    }
}