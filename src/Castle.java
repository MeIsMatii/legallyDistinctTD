import greenfoot.*;

import java.util.LinkedList;
import java.util.List;
/**
 * @author (your name) 
 */
public class Castle extends Actor
{
    private int maxMonster;
    private int countdown;
    private int spawnDelay;
    private List<Snake> aliveSnakes = new LinkedList<>();
	
    public Castle(int maxMonster, int spawnDelay){
        //TODO Attribute sinnvoll initialisieren
		this.spawnDelay = spawnDelay;
		this.maxMonster = maxMonster;
		this.countdown = this.spawnDelay;
    }
    
    @Override
    public void act() 
    {
		if(!allSnakesSpawned()) {
			spawnSnake();
		}
    }   
	
	public void spawnSnake() {
		if(countdown<=0 /*TODO && monsterCounter < maxMonster*/){
			World myWorld = getWorld();
			//TODO Snake spawnen
			Snake snake = new Snake(50, 3);
			aliveSnakes.add(snake);
			myWorld.addObject(snake, getX(), getY());


            //TODO Countdown resetten
			this.countdown = this.spawnDelay;
        }
        countdown--;
	}
	public boolean allSnakesSpawned() {
    	if(aliveSnakes.size() <= 0) {
    		return false;
		}
		for(Snake snake: aliveSnakes) {
			if(snake.getLife() <= 0) {
				//die
				getWorld().removeObject(snake);
				aliveSnakes.remove(snake);
			}
		}
        return aliveSnakes.size() > maxMonster;
    }
}