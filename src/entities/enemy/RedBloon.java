package entities.enemy;

public class RedBloon extends Enemy {
    public RedBloon(double speed, int lives) {
        super(speed, lives);
        setImage("Coin.png"); //TODO FIX @elias/art guys
    }
}