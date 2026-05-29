package entities.tower;

import greenfoot.GreenfootImage;
import greenfoot.World;

public class TestTower extends Tower {
    public TestTower(boolean isPlacing, int range) {
        super(isPlacing, range);
        setImage("TestTower2_2.png");


        GreenfootImage img = getImage();
        //img.scale(100, 100);

        setImage(img);
    }
    //Upgrades need: str description +int Level + option to buy

    public String upgrade1(){
    System.out.println("test1");
    return "test1";
    }
    public String upgrade2(){
        System.out.println("test2");
        return "test2";
    }
    public String  upgrade3(){
        System.out.println("test3");
        return "test3";
    }
    public void act() {
        super.act();

    }
}
