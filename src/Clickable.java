import greenfoot.*;
public interface Clickable {

    default void checkClick() {
        if (Greenfoot.mouseClicked(this)) {
            onClick();
        }
    }

    default void onClick(){
        System.out.println("onClick isnt defined");
    }
}