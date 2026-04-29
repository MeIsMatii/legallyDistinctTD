import greenfoot.Greenfoot;

public class SettingsButton extends MainClass implements Clickable{

    public SettingsButton(){
        setImage("settingsIcon.png");
        getImage().scale(60,60);
    }
    @Override
    public void onClick(){
        Greenfoot.setWorld(new MapSettings());
    }
    public void act(){
        checkClick();
    }

}
