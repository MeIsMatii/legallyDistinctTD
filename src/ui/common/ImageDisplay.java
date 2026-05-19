package ui.common;

import core.MainClass;
import core.Clickable;

public class ImageDisplay extends MainClass implements Clickable {
    public ImageDisplay(String image, int scaleX, int scaleY) {
        setImage(image);
        getImage().scale(scaleX, scaleY);
    }
}
