import greenfoot.*;
public interface Clickable {

    default void checkClick() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse == null) {
            return;
        }

        if(mouse.getActor() == this) {
            onClick();
        }
    }

    default void onClick(){
        System.out.println("onClick isnt defined");
    }
}