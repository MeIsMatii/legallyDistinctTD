package util;

import greenfoot.Actor;
import greenfoot.World;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface Animations {

    /**
     * TO DO SET IT (e.g. for TestTower: return "testTower2_2"<br>
     *
     * <h1> IMPORTANT </h1>
     * if the file is inside a folder (e.g. entities/towers/testTower/testTower_idle.png)<br>
     * then return the whole path. (e.g. entities/towers/testTower)
     *
     * <br>
     *
     * @return the Spritepath  for the Interface to use.
     */
    String getSpritePath();

    /**
     * return the fileName<br>
     * (e.g. <br><br>
     * Files:<br>
     * entities/towers/testTower/testTower_idle.png<br>
     * entities/towers/testTower/testTower_0.png<br>
     * entities/towers/testTower/testTower_1.png<br><br>
     * <p>
     * Then:<br>
     * return "testTower")
     *
     * @return the SpriteName
     */
    String getSpriteName();

    /**
     * @return how fast the animation should be (in ticks (we are using 45t/s)).
     */
    int getAnimationSpeed();

    int getFrameCounter();

    void setFrameCounter(int counter);

    List<String> getFrameList();

    void setFrameList(List<String> frames);

    int getCurrentFrameIndex();

    void setCurrentFrameIndex(int i);

    void isAnimating(boolean isAnimating);


    default void addedToWorld(World w) {
        generateFrameList();
    }

    default void generateFrameList() {
        String folderPath = "images/" + getSpritePath();
        System.out.printf("generating frameList for %s\n", folderPath);

        List<String> frames = new ArrayList<>();
        for (int i = 0; i < 100; i++) { //100 should be enough //@Febo is a madman if we have more than 100f for 1 thingy
            String fileName = folderPath + "/" + getSpriteName() + "_" + i + ".png";
            File file = new File(fileName);

            if (file.exists()) {
                frames.add(fileName);
            } else { //last frame
                break;
            }
        }
        setFrameList(frames);

        Actor self = (Actor) this;
        self.setImage("images/" + getSpritePath() + "/" + getSpriteName() + "_idle.png");
    }

    default void startAnimation() {
        if (getFrameList() == null || getFrameList().size() <= 0) {
            return;
        }

        Actor self = (Actor) this;
        isAnimating(true);
        setCurrentFrameIndex(1);
        self.setImage(getFrameList().get(0));

        playAnimation();

    }

    default void playAnimation() {
        Actor self = (Actor) this;


        self.setImage(getFrameList().get(getCurrentFrameIndex()));
        setCurrentFrameIndex(getCurrentFrameIndex() + 1);

        if (getCurrentFrameIndex() >= getFrameList().size()) {
            isAnimating(false);
            self.setImage("images/" + getSpritePath() + "/" + getSpriteName() + "_idle.png");
        }
    }
}

