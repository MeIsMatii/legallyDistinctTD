package core;

import greenfoot.Greenfoot;
import greenfoot.World;
import maps.levels.GameMap;
import maps.levels.util.GameOverPopUp;
import ui.common.ImageDisplay;
import util.multiplayer.NetworkManager;

public class Player extends MainClass {

    private int coins;
    private int health;

    private int oldCoins;
    private int oldHealth;

    public Player() {
        this(0, -1);
    }

    public Player(int startCoins, int startHealth) {
        coins = startCoins;
        health = startHealth;

        oldCoins = 0;
        oldHealth = 0;

    }                                               //noch sehr stolz auf mich


    private String lastPressedKey;

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;

        if(getWorldOfType(GameMap.class).isMultiplayer() && NetworkManager.getInstance().isHost()) {
            String msg = "SET_COINS" + "," + getCoins();
            NetworkManager.getInstance().sendData(msg);
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;


        String msg = "SET_HEALTH" + "," + getHealth();
        NetworkManager.getInstance().sendData(msg);
    }

    public void act() {
        coinCheat();
        show(getWorld());


        oldCoins = coins;
        oldHealth = health;
    }

    @Override
    public void addedToWorld(World world) {
        world.addObject(new ImageDisplay("heart.png", 30, 30), 40, 40);  //ganz oben Links, jemand muss noch herz bild ertsellen und dann hier einfügen
        world.addObject(new ImageDisplay("Coin.png", 45, 45), 120, 40);  //ganz oben Links aber unter dem herzen, jemand muss noch coins bild ertsellen und dann hier einfügen
    }




    public void damage(int damage) {
        setHealth(health - damage);
        if (health <= 0) {
            //getWorld().showText("you lost", 400, 400);
            GameOverPopUp gameOverPopUp = new GameOverPopUp();
            getWorld().addObject(gameOverPopUp,getWorld().getWidth()/2,getWorld().getHeight()/2);

            getWorldOfType(GameMap.class).pauseObjects(isPaused(), true);

            GameMap gameMap = (GameMap) getWorld();
            gameMap.getGameSaveManager().removeSaveFile();
        }
    }

    public void show(World world) {//zeigt Leben oben auf der map
        if (oldHealth != health) {
            world.showText(String.valueOf(getHealth()), 80, 40);//ganz oben links,rechts vom herzen

        }
        if (oldCoins != coins) {
            world.showText(String.valueOf(getCoins()), 160, 40);//rechts vom coinpng
        }
    }

    public void coinCheat() {
        if (Greenfoot.isKeyDown("c")) {
            setCoins(getCoins() + 100000);
        }
    }





}
