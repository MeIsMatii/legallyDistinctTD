package hud;

public class Player {

    private int coins;
    private int health;

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }


    public Player(){
        this(0,100);
    }

    public Player(int startCoins, int startHealth){
        coins = startCoins;
        health = startHealth;
    }

}