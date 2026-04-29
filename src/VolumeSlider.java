import greenfoot.*;

public class VolumeSlider extends MainClass implements Clickable {


    private static final int VolumeStepsPerClick = 10;
    private static final int SliderWidthPixel = 200;
    private static final int SliderHightPixel = 30;

    public VolumeSlider() {
        redrawSlider();
    }

    @Override
    public void act() {
        checkClick();
        redrawSlider();//zum updaten des sliders
    }
    @Override
    public void onClick() {
        MouseInfo mouseDetails = Greenfoot.getMouseInfo();
        if (mouseDetails == null) {
            return;
        }
        //pixelgröße X zu ZellSize
        int pixelsPerCell = getWorld().getCellSize();
        int actorLeftEdgeInPixels = getX() * pixelsPerCell - SliderWidthPixel / 2;

        // Wo hat der spieler hingedrückt
        int mouseInPixelsX = mouseDetails.getX() * pixelsPerCell;
        double clickPositionAlongBar = (double)(mouseInPixelsX - actorLeftEdgeInPixels) / SliderWidthPixel;

        // lauter oder leiser machen
        if (clickPositionAlongBar < 0.5) {
            SoundSettings.getInstance().decreaseVolume(VolumeStepsPerClick);
        } else {
            SoundSettings.getInstance().increaseVolume(VolumeStepsPerClick);
        }
    }

    private void redrawSlider() {
        int currentVolume = SoundSettings.getInstance().getMasterVolume();
        int filledWidthInPixels = (int)((currentVolume / 100.0) * SliderWidthPixel);//wie viel soll ausgefüllt sein
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

        //nummer wert drunter schreiben
        sliderImage.setColor(Color.BLACK);
        sliderImage.drawString("Volume: " + currentVolume + "%", 55, SliderHightPixel + 15);

        setImage(sliderImage);

        ///TODO make slider jump to the mouse instead of incrementing by certain steps --mathilo 4 colin
    }
}
