package core;

import entities.Entity;
import greenfoot.Greenfoot;
import greenfoot.World;
import map.levels.Map;
import map.levels.util.GameOverPopUp;
import map.menu.PauseMenu;
import ui.common.ImageDisplay;
import ui.settings.SettingsPopup;

import java.util.List;

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
        isPaused = false;
    }                                               //noch sehr stolz auf mich

    private boolean isPaused;
    private String lastPressedKey;

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

    public void act() {
        checkPaused();
        coinCheat();
        show(getWorld());
        saveTest();


        oldCoins = coins;
        oldHealth = health;
    }

    @Override
    public void addedToWorld(World world) {
        world.addObject(new ImageDisplay("heart.png", 30, 30), 40, 40);  //ganz oben Links, jemand muss noch herz bild ertsellen und dann hier einfügen
        world.addObject(new ImageDisplay("Coin.png", 45, 45), 120, 40);  //ganz oben Links aber unter dem herzen, jemand muss noch coins bild ertsellen und dann hier einfügen
    }


    public void checkPaused(){



        if ("escape".equals(Greenfoot.getKey())) {
            setPaused(!isPaused());
            //System.out.printf("isPaused: %s\n", isPaused);
            pauseEntity();
            List<PauseMenu> pauseMenus = getWorld().getObjects(PauseMenu.class);
            List<SettingsPopup> settingsPopups = getWorld().getObjects(SettingsPopup.class);
            if (pauseMenus.isEmpty()) {
                getWorld().addObject(new PauseMenu(), getWorld().getWidth() / 2, getWorld().getHeight() / 2);
            }else {
                for (PauseMenu pauseMenu : pauseMenus){
                    pauseMenu.onRemove();
                }
            }
            if (!settingsPopups.isEmpty()){
                for (SettingsPopup settingsPopup : settingsPopups){
                    settingsPopup.onRemove();
                }
            }


        }else if ("space".equals(Greenfoot.getKey())){
            setPaused(!isPaused());
            pauseEntity();
        }

    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public void pauseEntity(){
        List<Entity> entities = getWorld().getObjects(Entity.class);
        for(int i = 0; i<entities.size(); i++){
            entities.get(i).setPaused(isPaused());
        }
    }

    public void damage(int damage) {
        setHealth(health - damage);
        if (health < 0) {
            //getWorld().showText("you lost", 400, 400);
            GameOverPopUp gameOverPopUp = new GameOverPopUp();
            getWorld().addObject(gameOverPopUp,getWorld().getWidth()/2,getWorld().getHeight()/2);

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

    public void onContinue(){
        setPaused(false);
        pauseEntity();
    }

    public void saveTest() {
        if(Greenfoot.isKeyDown("space")) {
            Map map = (Map) getWorld();
            map.getGameSaveManager().saveGame();
        }
    }



}
