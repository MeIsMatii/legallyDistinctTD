package util;

import core.MainClass;
import entities.enemy.Enemy;
import entities.enemy.EnemyLevel1;
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
                int random = Greenfoot.getRandomNumber(level);
                int amount = random + 2;
                for (int e = amount; e >0; e--) {
                    Enemy enemy = createEnemyOfLevel(level);
                    enemies.add(enemy);
                }

            }

        }
        //else if () {

        //}
        /*
        private static final Map<Integer, List<Supplier<Enemy>>> LEVEL_ENEMIES =
            Map.of(
                1, List.of(Goblin::new),
                2, List.of(Goblin::new, Orc::new),
                3, List.of(Orc::new, Dragon::new)
            );

        public static Enemy createEnemy(int level) {
            List<Supplier<Enemy>> enemies = LEVEL_ENEMIES.get(level);

            if (enemies == null || enemies.isEmpty()) {
                throw new IllegalArgumentException("Ungültiges Level");
            }

            Supplier<Enemy> supplier =
                enemies.get(RANDOM.nextInt(enemies.size()));

            return supplier.get();
        }

        */







        return enemies;
    }



    public  Enemy createEnemyOfLevel(int level){

        return new EnemyLevel1();
    }
}
