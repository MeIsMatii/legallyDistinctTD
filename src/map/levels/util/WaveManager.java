package map.levels.util;

import core.MainClass;
import entities.enemy.*;
import greenfoot.Greenfoot;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;


// === Metadata === \\
/**
 * @author Fred
 * @version 1.1-beta
 */


// ========== WAVE MANAGER ========== \\
public class WaveManager extends MainClass {


    // ===== Instance Variable ===== \\
    private static WaveManager INSTANCE;



    // ===== Constructor ===== \\
    private WaveManager(){}



    // ===== WaveManager Getter ===== \\
    public static WaveManager getInstance() {
        
        if(INSTANCE == null) {
            INSTANCE = new WaveManager();
        }

        return INSTANCE;
    }



    // ===== Enemy Map ===== \\
    private static final Map<Integer, List<Supplier<Enemy>>> ENEMY_MAP =
        Map.of(
            10, List.of(
                EnemyLevel1::new
            ),

            20, List.of(
                EnemyLevel1::new,
                EnemyLevel2::new
            ),

            30, List.of(
                EnemyLevel1::new,
                EnemyLevel2::new,
                EnemyLevel3::new
            ),

            50, List.of(
                EnemyLevel1::new,
                EnemyLevel2::new,
                EnemyLevel3::new,
                EnemyLevel4::new
            ),

            70, List.of(
                EnemyLevel1::new,
                EnemyLevel2::new,
                EnemyLevel3::new,
                EnemyLevel4::new,
                EnemyLevel5::new
            ),

            100, List.of(
                EnemyLevel1::new,
                EnemyLevel2::new,
                EnemyLevel3::new,
                EnemyLevel4::new,
                EnemyLevel5::new,
                EnemyLevel6::new
            )

        );

    // ===== Enemy List for other classes to fetch ===== \\
    private static final Map<String, Supplier<Enemy>> ENEMY_LIST = Map.of(
        "EnemyLevel1", EnemyLevel1::new,
        "EnemyLevel2", EnemyLevel2::new,
        "EnemyLevel3", EnemyLevel3::new,
        "EnemyLevel4", EnemyLevel4::new,
        "EnemyLevel5", EnemyLevel5::new
    );

    public static Map<String, Supplier<Enemy>> getEnemyList() {
        return ENEMY_LIST;
    }



    // ===== Map Generator ===== \\
    public List<Enemy> generateWave(int level) {

        // === Enemy Array === \\
        List<Enemy> enemies = new ArrayList<>();


        // === Mapped Enemy Array === \\
        List<Supplier<Enemy>> mappedEnemies = ENEMY_MAP.get(mapCheck(level));



        // === Generator Logic === \\
        for (int i = 0; i<3; i++) {

            int random = Greenfoot.getRandomNumber(level + 1);
            int amount = random + 2;


            for (int e = amount; e >0; e--) {

                int zahl = Greenfoot.getRandomNumber(mappedEnemies.size());

                Enemy enemy = mappedEnemies.get(zahl).get();
                enemies.add(enemy);
            }

        }

        return enemies;
    }



    // ===== Map Check ===== \\
    private int mapCheck(int level) {

        // === Map Target === \\
        int mapTarget = level;


        // === Checks Map for nearest Key === \\
        while(!ENEMY_MAP.containsKey(mapTarget)) {
            mapTarget++;
        }

        return mapTarget;
    }



}
