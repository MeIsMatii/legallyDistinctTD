package setting;

import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.MouseInfo;
import util.Clickable;
import util.MainClass;

public class VolumeSlider extends MainClass implements Clickable {

    private static final int SliderWidthPixel = 200;
    private static final int SliderHightPixel = 30;

    // Tracks whether the user started their drag on the slider specifically.
    private boolean isDraggingSlider = false;

    public VolumeSlider() {
        redrawSlider();
    }

    @Override
    public void act() {
        MouseInfo mouseDetails = Greenfoot.getMouseInfo();
        if (mouseDetails != null) {
            //zählt nur als drag wenn die maus über dme slider is
            if (mouseDetails.getButton() == 1 && isMouseOverSlider(mouseDetails)) {
                isDraggingSlider = true;
            }
            //stoppt drag
            if (mouseDetails.getButton() == 0) {
                isDraggingSlider = false;
            }
            if (isDraggingSlider) {
                updateVolumeFromMousePosition(mouseDetails);
            }
        }

        checkClick();
        redrawSlider();
    }

    @Override
    public void onClick() {
        MouseInfo mouseDetails = Greenfoot.getMouseInfo();
        if (mouseDetails == null) {
            return;
        }
        updateVolumeFromMousePosition(mouseDetails);
    }

    // returns ob die maus im slider ist
    private boolean isMouseOverSlider(MouseInfo mouseDetails) {
        int pixelsPerCell = getWorld().getCellSize();
        int actorCenterX = getX() * pixelsPerCell + pixelsPerCell / 2;
        int actorCenterY = getY() * pixelsPerCell + pixelsPerCell / 2;
        int mouseX = mouseDetails.getX() * pixelsPerCell + pixelsPerCell / 2;
        int mouseY = mouseDetails.getY() * pixelsPerCell + pixelsPerCell / 2;
        return Math.abs(mouseX - actorCenterX) <= SliderWidthPixel / 2 && Math.abs(mouseY - actorCenterY) <= SliderHightPixel / 2;
    }

    private void updateVolumeFromMousePosition(MouseInfo mouseDetails) {
        int pixelsPerCell = getWorld().getCellSize();
        int actorLeftEdgeInPixels = getX() * pixelsPerCell - SliderWidthPixel / 2;
        int mouseInPixelsX = mouseDetails.getX() * pixelsPerCell;

        // Mausposition zu prozent
        double fractionAlongSlider = (double) (mouseInPixelsX - actorLeftEdgeInPixels) / SliderWidthPixel;
        int newVolume = (int) (fractionAlongSlider * 100);
        SoundSettings.getInstance().setMasterVolume(newVolume);
        SoundSettings.getInstance().syncGlobalVolume();
    }

    private void redrawSlider() {
        int currentVolume = SoundSettings.getInstance().getMasterVolume();
        int filledWidthInPixels = (int) ((currentVolume / 100.0) * SliderWidthPixel);
        GreenfootImage sliderImage = new GreenfootImage(SliderWidthPixel + 2, SliderHightPixel + 20);

        // Leerenbereich als hintergrund erstellen
        sliderImage.setColor(new Color(180, 180, 180));
        sliderImage.fillRect(0, 0, SliderWidthPixel, SliderHightPixel);

        // gefüllten bereich erstellen
        sliderImage.setColor(new Color(50, 200, 80));
        sliderImage.fillRect(0, 0, filledWidthInPixels, SliderHightPixel);

        // schwarze linie zum unterscheiden zwischen map und der Instanz (bitte nicht löschen)
        sliderImage.setColor(Color.BLACK);
        sliderImage.drawRect(0, 0, SliderWidthPixel, SliderHightPixel);

        // schwarze linie um zu zeigen wo die volume ist
        sliderImage.setColor(Color.BLACK);
        sliderImage.fillRect(filledWidthInPixels - 2, 0, 4, SliderHightPixel);

        // wert in weiß reinschreiben
        sliderImage.setColor(Color.WHITE);
        sliderImage.drawString(currentVolume + "%", Math.max(2, filledWidthInPixels / 2 - 10), SliderHightPixel - 8);

        //nummer wert drunter schreiben
        sliderImage.setColor(Color.BLACK);
        sliderImage.drawString("Volume: " + currentVolume + "%", 55, SliderHightPixel + 15);

        setImage(sliderImage);
    }
}