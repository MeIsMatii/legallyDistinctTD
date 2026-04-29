import greenfoot.Greenfoot;
import greenfoot.World;

public class HomeButton extends MainClass implements Clickable{

    public HomeButton(){
        setImage("HomeButton.png");
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
