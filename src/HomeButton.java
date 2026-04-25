import greenfoot.Greenfoot;
import greenfoot.World;

public class HomeButton extends MainClass implements Clickable{
/// TODO
/// Vlt mal machen das er wirklich zurück geht und nicht nur auf die homepage --Colin
    public HomeButton(){
        setImage("backbutton.png");
        getImage().scale(40,40);

    }
    public void getBack(World destination){
        Greenfoot.setWorld(destination);
    }
    @Override
    public void onClick(){
        getBack(new MapSelector());
    }
    @Override
    public void act(){
        checkClick();
    }

}
