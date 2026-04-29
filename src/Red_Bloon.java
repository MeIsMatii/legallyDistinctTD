public class Red_Bloon extends Enemy {
    int lives;
    int speed;
    @Override
    void moveTo(int x, int y) {
        turnTowards(x, y);
        move(speed);
    }

    @Override
    void onHit() {
        lives--;
        if (lives <= 0){
            getWorld().removeObject(this);
            //money(or whatever it will be called)++;
            //how do you push again?
        }
    }
}
