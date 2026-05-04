package entity.tower;

import entity.Entity;
import hud.Player;
import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.MouseInfo;
import map.util.Path;
import util.Clickable;

import java.util.Objects;


abstract class Tower extends Entity implements Clickable {
    private boolean isPlacing;
    private boolean isRangeVisible;
    private final GreenfootImage sprite;

    private boolean canPlace;

    private final int range;


    public Tower(boolean isPlacing, int range, String spriteName) {
        this.sprite = new GreenfootImage(spriteName);
        setRangeVisibility(false, null);
        this.isPlacing = isPlacing;
        this.canPlace = true;

        this.range = range;
    }

    public void act() {
        if (isPlacing) {
            followCursor();
            checkClick();
        }
        this.canPlace = true; //default value


    }

    public void setRangeVisibility(boolean state, Color color) {
        if (!state) {
            setImage(sprite);
        } else {
            displayRange(color);
        }

        isRangeVisible = state;
    }

    public void onHit(Entity hitter) {
        if (!(hitter instanceof Path) && !(hitter instanceof Tower)) { ///is there a better way than instanceof?
            canPlace = true;
            return;
        }
        canPlace = false;
    }

    public void checkHover(boolean isHovering) {
        if (isPlacing) {
            return;
        }
        if (isHovering) {
            onHover();
        } else if (isRangeVisible) {
            setRangeVisibility(false, null);
        }
        //so i can implement an else tree :D
    }


    public void onClick() {
        if (isPlacing && canPlace) {
            place();
        } else {
            ///TODO open upgrades menu @Colin
        }
    }

    public void onHover() { ///TODO FIX so it also stays when i stop moving the cursor --mathilo
        if (!isRangeVisible) {
            setRangeVisibility(true, new Color(128, 128, 128, 128));
        }
    }


    public void place() {
        isPlacing = false;
        setRangeVisibility(false, null);
    }

    public void followCursor() {

        MouseInfo mouseInfo = Greenfoot.getMouseInfo();

        if (mouseInfo == null) {
            return;
        }

        int mouseX = mouseInfo.getX();
        int mouseY = mouseInfo.getY();

        setLocation(mouseX, mouseY);
        if (!isRangeVisible) {
            if (canPlace) {
                setRangeVisibility(true, new Color(128, 128, 128, 128));
            } else {
                setRangeVisibility(true, new Color(128, 0, 0, 128));
            }
        } else {
            if (Objects.equals(getImage().getColor(), new Color(128, 0, 0, 128)) && canPlace) {
                //red range, should be grey
                setImage(sprite);
                setRangeVisibility(true, new Color(128, 128, 128, 128));
            } else if (Objects.equals(getImage().getColor(), new Color(128, 128, 128, 128)) && !canPlace) {
                //grey range, should be red
                setImage(sprite);
                setRangeVisibility(true, new Color(128, 0, 0, 128));
            }
        }


    }

    public void displayRange(Color color) {
        ///AAAAAAAAAAAAAAAAAAAAAAAAAAAA WHY DID I DO THIS
        /// WHY DID I NOT JUST LET SOMEONE ELSE DO IT
        // AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA --Mathilo
        int radius = range * getWorld().getCellSize();
        int diameter = radius * 2;

        GreenfootImage canvas = getGreenfootImage(diameter, color);

        setImage(canvas);

        isRangeVisible = true;
    }

    private GreenfootImage getGreenfootImage(int diameter, Color color) {
        GreenfootImage img = getImage();

        int size = Math.max(diameter, Math.max(img.getWidth(), img.getHeight()));
        GreenfootImage canvas = new GreenfootImage(size, size);

        canvas.setColor(color); //semi transparent grey  //fuck you intelliJ, im speak BE not AE
        canvas.fillOval(0, 0, diameter, diameter);
        canvas.drawOval(0, 0, diameter, diameter);

        canvas.drawImage(img, (size - img.getWidth()) / 2, (size - img.getHeight()) / 2); //so the original image is not in the top left
        return canvas;
    }
}
