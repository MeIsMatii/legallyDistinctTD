import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;

import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 */
public class MovingActor extends ImprovedActor {
    private final GreenfootImage[][] movingActorImages = new ImprovedGreenfootImage[4][4];
    private int animationStep = 0;

    /**
     * Creates a MovingActor
     */
    public MovingActor() {
        String imgFolder = "." + File.separator + "images" + File.separator + this.getClass().getSimpleName() + File.separator;
        //File folder = new File(imgFolder);
        for(int i = 0; i < Direction.values().length; i++){
            try {
                //check for movement animation images
                for (int j = 0; j < 4; j++) {
                    String imgName = Direction.values()[i].name() + j + ".png";
                    File img = new File(imgFolder, imgName);
                    if(img.exists()){
                        movingActorImages[i][j] = new ImprovedGreenfootImage(img.getCanonicalPath());
                        int rotationAmount  = i % 2 == 1? -i : i;
                        movingActorImages[i][j].rotate(rotationAmount*90);//0 degrees => right
                    }else{
                        if(j==0) { //wenn kein Bild vorhanden ist => Standard nehmen
                            movingActorImages[i][j] = new ImprovedGreenfootImage(getImage());
                        }else{
                            movingActorImages[i][j] = new ImprovedGreenfootImage(movingActorImages[i][j - 1]);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        setImageRotation(Direction.SOUTH);
    }


    /**
     * Determines if the MovingActor can move one steps without hitting any obstacles (i.e. Rocks).
     */
    public boolean canMove(){
        return canMove(1);
    }

    /**
     * Determines if the MovingActor can move distance amount of steps without hitting any obstacles (i.e. Rocks).
     * @param distance the distance
     */
    public boolean canMove(int distance) {
        World myWorld = getWorld();

        int x = getNextX(distance);
        int y = getNextY(distance);

        //System.out.println("direction=" + getRotation() + ", dx=" + dx + ", dy=" + dy + ", current.x=" + getX()  + ", current.y=" + getY()  + ", next.x=" + x  + ", next.y=" + getY());

        List<Rock> rocks = myWorld.getObjectsAt(x, y, Rock.class);
        //Bad Solution: Shall be fixed once interfaces are introduced
        List<InventorySlot> slots = myWorld.getObjectsAt(x, y, InventorySlot.class);

        return rocks.isEmpty() && slots.isEmpty();
    }

    public int getNextX(int distance){
        double radians = Math.toRadians(getRotation());
        int dx = (int) Math.round(Math.cos(radians) * distance);

        return getX()+dx;
    }

    public int getNextY(int distance){
        double radians = Math.toRadians(getRotation());
        int dy = (int) Math.round(Math.sin(radians) * distance);

        return  getY()+dy;
    }



    public void setRotation(int rotation){
        Direction direction = Direction.getDirectionByRotation(rotation);
        setImageRotation(direction);
    }

    /**
     * Turn left
     */
    public void turnLeft() {
        Direction dir = Direction.getDirectionByRotation(getRotation());
        Direction newDirection = Direction.byValue(dir.getValue()-1);
        setImageRotation(newDirection);
    }

    /**
     * Turn right.
     */
    public void turnRight() {
        Direction dir = Direction.getDirectionByRotation(getRotation());
        Direction newDirection = Direction.byValue(dir.getValue()+1);
        setImageRotation(newDirection);
    }


    public void turn(Direction direction){
        setImageRotation(direction);
    }

    public void turn(int rotation){
        Direction direction = Direction.getDirectionByRotation(rotation);
        turn(direction);
    }

    /**
     * Sets the direction of the MovingActor, also ensures that the correct image is set.
     * @param direction the target Direction
     */
    public void setImageRotation(Direction direction) {
        super.setRotation(direction.getRotation());
        setImage(movingActorImages[direction.getValue()][animationStep]);
    }

    /**
     * Does what a MovingActor does
     */
    public void act() {
        super.act();
    }

    /**
     * Shows the given text above the MovingActor
     * @param text to show
     */
    public void say(boolean text){
        say(String.valueOf(text));
    }

    /**
     * Shows the given text above the MovingActor
     * @param text to show
     */
    public void say(int text){
        say(String.valueOf(text));
    }

    /**
     * Shows the given text above the MovingActor
     * @param text to show
     */
    public void say(double text){
        say(String.valueOf(text));
    }

    /**
     * Shows the given text above the MovingActor
     * @param text to show
     */
    public void say(String text){
        int y = getY() - 1;
        if (y < 0){
            y = 1;
        }
        getWorld().showText(text, getX(), y);
        System.out.println("a " + this.getClass().getName() + " says: " + text);
        Greenfoot.delay(4);
        getWorld().showText("", getX(), y);

    }


    /**
     * Moves the amount of steps, if possible
     * @param steps to move
     */
    public void move(int steps){
        animationStep = (animationStep+1) % 4;
        setImage(movingActorImages[Direction.getDirectionByRotation(getRotation()).getValue()][animationStep]);
        if(canMove(steps)){
            super.move(steps);
        }
    }


}

enum Direction{
    EAST(0),
    SOUTH(1),
    WEST(2),
    NORTH(3);
    private final int value;

    Direction(int value){
        this.value = value;
    }


    public static Direction getDirectionByRotation(int rotation){
        rotation = rotation % 360;
        int value = rotation / 90;
        return Direction.byValue(value);

    }

    public int getValue() {
        return value;
    }

    public int getRotation(){
        return value*90;
    }

    public static Direction byValue(int value){
        value = value < 0 ? value + 4: value;
        value = value % 4;
        switch (value){
            case 0:
                return EAST;
            case 1:
                return SOUTH;
            case 2:
                return WEST;
            default:
                return  NORTH;
        }
    }
}