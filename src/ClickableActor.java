
import greenfoot.*;

public class ClickableActor extends MainClass implements Clickable {


    /// Only Actor I made in another project to show how my clickable Interface wroks --Colin
    /// !!!Dont use in the Game and never let somone expand this class!!!
    private boolean clicked = false;

    @Override
    public void act() {
        checkClick();
        updateAppearance();
    }
    /// Only Actor I made in another project to show how my clickable Interface wroks --Colin
    /// !!!Dont use in the Game and never let somone expand this class!!!
    @Override
    public void onClick() {
        clicked = !clicked;
        updateAppearance();
        System.out.println("Actor was clicked! Toggled to: " + clicked);
    }
    /// Only Actor I made in another project to show how my clickable Interface wroks --Colin
    /// !!!Dont use in the Game and never let somone expand this class!!!
    private void updateAppearance() {
        GreenfootImage img = new GreenfootImage(60, 60);
        img.setColor(clicked ? Color.GREEN : Color.RED);
        img.fillOval(0, 0, 60, 60);
        setImage(img);
    }

    /// Only Actor I made in another project to show how my clickable Interface wroks --Colin
    /// !!!Dont use in the Game and never let somone expand this class!!!

}
