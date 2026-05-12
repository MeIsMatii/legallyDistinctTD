package setting;

import greenfoot.Greenfoot;
import greenfoot.World;
import util.Clickable;
import util.MainClass;

public class SlotMachineButtonGamble extends MainClass implements Clickable {

    public SlotMachineButtonGamble() {
        setImage("Slot-MachineDefault.png");
        getImage().scale(200, 200);
    }

    @Override
    public void onClick() {
        int random = Greenfoot.getRandomNumber(4);
        if(random < 3){
            getWorld().showText("You loose. Try again", 15, 20);
        }else{
            Greenfoot.setWorld(new GamblingWonCredits());
        }
    }//slot machien bild weg dafür 3 bilder oder objekte nebeneinander jede viertel sekunde ändern
    //zufallszahl und wenn z.b. 5 ist dann anhalten

    public void act() {
        checkClick();
    }

}
