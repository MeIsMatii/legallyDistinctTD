import greenfoot.*;
public class   MapSelector extends World
{


    public MapSelector()
    {
        super(29, 29,20);
        World loadingscreen = new LoadingScreen(null);
        setPaintOrder(SettingsButton.class, MapSelector.class,ImageDisplay.class);
        setBackground("cell_grass.png");
        addObject(new MapPreview(1,loadingscreen), 5,3);///Map Preview
        addObject(new MapPreview(2,loadingscreen), 14,3);///Map Preview
        addObject(new MapPreview(3,loadingscreen), 23,3);///Map Preview
        addObject(new MapPreview(4,loadingscreen), 5,12);///Map Preview
        addObject(new MapPreview(5,loadingscreen), 14,12);///Map Preview
        addObject(new MapPreview(6,loadingscreen), 23,12);///Map Preview
        addObject(new MapPreview(7,loadingscreen), 5,21);///Map Preview
        addObject(new MapPreview(8,loadingscreen), 14,21);///Map Preview
        addObject(new MapPreview(9,loadingscreen), 23,21);///Map Preview
        addObject(new SettingsButton(),27,27);
        for (int y =25; y<=29;y++){
            for(int i = 0; i < getWidth(); i++){
                addObject(new ImageDisplay("dirtsquare.png", 20,20),i, y);}
        }
        addObject(new ImageDisplay("clickwarning.jpg", 300, 60),10,27);
        Greenfoot.start();

    }





}