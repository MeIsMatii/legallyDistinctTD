package ui.hud;
import core.MainClass;
import greenfoot.*;
public class SaveLoadPopup extends MainClass {

    public SaveLoadPopup(String text){
        int width = 800;
        int height = 600;
        GreenfootImage boxImage = new GreenfootImage(width, height);
        boxImage.setColor(new Color(139, 69, 19));boxImage.fill();
        boxImage.setColor(Color.WHITE);
        boxImage.setFont(new Font("Arial", true, false, 24));
        boxImage.drawString(text, 20, height / 2);
        setImage(boxImage);

    }

}
