package util;

import core.MainClass;
import entities.enemy.Enemy;
import greenfoot.Greenfoot;

import java.util.ArrayList;
import java.util.List;

public class WaveManager extends MainClass {

    public List<Enemy> generateWave(int level) {

        // Enemy Array
        List<Enemy> enemies = new ArrayList<>();


        // Easy Entry Level
        if (level < 10) {
            for (int i = 0; i<3; i++) {
                //int random = Greenfoot.getRandomNumber()

            }

        }





        return enemies;
    }
}
