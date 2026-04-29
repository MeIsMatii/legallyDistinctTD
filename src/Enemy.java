abstract class Enemy extends Entity {
    int lives;
    int speed;
    // move()
    abstract void moveTo(int x, int y);
    /*how to implement in each enemy:
        {
            turnTowards(x, y);
            move(speed);
        }
    */
}
