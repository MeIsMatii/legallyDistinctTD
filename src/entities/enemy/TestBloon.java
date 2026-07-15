package entities.enemy;

public class TestBloon extends Enemy {
    public TestBloon(double speed, int lives) {
        super(speed, lives);
        setImage("arealEnemy1.png"); //TODO FIX @elias/art guys
    }
    @Override
    public String getName() {
        return "test";
    }
}