import greenfoot.Actor;

public class Rock extends Actor
{
	
	
	/**
	 * Will be called on a hit.
	 */
	public void hit(){
		getWorld().addObject(new Star(), getX(), getY()); //Bei einem Treffer wird kurz ein Stern eingeblendet
		
	}

}