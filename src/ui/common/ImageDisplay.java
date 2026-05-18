package ui.common;

import core.MainClass;

public class ImageDisplay extends MainClass {
    public ImageDisplay(String image, int scaleX, int scaleY) {
        setImage(image);
        getImage().scale(scaleX, scaleY);
    }
}
