package core;

import greenfoot.Greenfoot;
import greenfoot.World;
import maps.levels.GameMap;
import ui.common.BackButton;
import ui.common.ImageDisplay;
import ui.hud.QuestionPopup;
import ui.hud.buttons.RetryButton;
import util.HasSound;
import util.multiplayer.NetworkManager;

public class Player extends MainClass implements HasSound {

    private int coins;
    private int health;

    private int oldCoins;
    private int oldHealth;

    private boolean isGameOver = false;


    public Player(int startCoins, int startHealth) {
        coins = startCoins;
        health = startHealth;

        oldCoins = 0;
        oldHealth = 0;

    }                                               //noch sehr stolz auf mich

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;

        if (getWorldOfType(GameMap.class).isMultiplayer()) {
            String msg = "SET_COINS" + "," + coins;
            NetworkManager.getInstance().sendData(msg);
        }
    }

    public void setCoins(int coins, boolean wasSentFromMultiplayer) {
        this.coins = coins;

        if (!wasSentFromMultiplayer) {
            String msg = "SET_COINS" + "," + coins;
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
        if (health <= 0 && !isGameOver) {
            //getWorld().showText("you lost", 400, 400);
            QuestionPopup questionPopup = new QuestionPopup("You lost!\n Restart would you like start a new game?", new BackButton(), new RetryButton());
            getWorld().removeObject(questionPopup.getCloseButton());
            questionPopup.setCloseButton(null);
            getWorld().addObject(questionPopup, getWorld().getWidth() / 2, getWorld().getHeight() / 2);

            getWorldOfType(GameMap.class).pauseObjects(true, true);

            GameMap gameMap = (GameMap) getWorld();
            gameMap.getGameSaveManager().removeSaveFile();

            playSound("gameOverSound.mp3");
            isGameOver = true;
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
