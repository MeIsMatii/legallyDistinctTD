package core;

import greenfoot.Greenfoot;
import greenfoot.World;
import ui.common.ImageDisplay;

public class Player extends MainClass {

    private int coins;
    private int health;

    private int oldCoins;
    private int oldHealth;

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


    public Player() {
        this(0, 100);
    }

    public Player(int startCoins, int startHealth) {
        coins = startCoins;
        health = startHealth;

        oldCoins = 0;
        oldHealth = 0;
    }                                               //noch sehr stolz auf mich

    public void act() {
        coinCheat();
        show(getWorld());


        oldCoins = coins;
        oldHealth = health;
    }

    @Override
    public void addedToWorld(World world) {
        world.addObject(new ImageDisplay("heart.png", 30, 30), 1, 2);  //ganz oben Links, jemand muss noch herz bild ertsellen und dann hier einfügen
        world.addObject(new ImageDisplay("Coin.png", 45, 45), 1, 3);  //ganz oben Links aber unter dem herzen, jemand muss noch coins bild ertsellen und dann hier einfügen
    }

    public void damage(int damage) {
        setHealth(health-damage);
        if(health<0) {
            getWorld().showText("you lost", 400, 400);
        }
    }

    public void show(World world) {//zeigt Leben oben auf der map
        if(oldHealth != health) {
            world.showText("Leben: " + getHealth(), 2, 2);//ganz oben links,rechts vom herzen
        }
        if(oldCoins != coins) {
            world.showText("Coins: " + getCoins(), 2, 3);//rechts vom coinpng
        }
    }

    public void coinCheat() {
        if (Greenfoot.isKeyDown("c")) {
            setCoins(getCoins() + 100000);
        }
    }


}