package entities.tower;

import greenfoot.GreenfootImage;

public class TestTower extends Tower {
    public TestTower(boolean isPlacing, int range) {
        super(isPlacing, range);
        setImage("TestTower2_2.png");


        GreenfootImage img = getImage();
        //img.scale(100, 100);

        setImage(img);
    }

    public void act() {
        super.act();

    }
}
