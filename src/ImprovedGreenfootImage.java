import greenfoot.Color;
import greenfoot.GreenfootImage;


public class ImprovedGreenfootImage extends GreenfootImage {
    private int rotation = 0;

    public ImprovedGreenfootImage(String filename) throws IllegalArgumentException {
        super(filename);
    }

    public ImprovedGreenfootImage(int width, int height) {
        super(width, height);
    }

    public ImprovedGreenfootImage(GreenfootImage image) throws IllegalArgumentException {
        super(image);
        if (image instanceof ImprovedGreenfootImage){
            ImprovedGreenfootImage improvedImage = (ImprovedGreenfootImage) image;
            rotation = improvedImage.rotation;
        }
    }

    public ImprovedGreenfootImage(String string, int size, Color foreground, Color background) {
        super(string, size, foreground, background);
    }

    public ImprovedGreenfootImage(String string, int size, Color foreground, Color background, Color outline) {
        super(string, size, foreground, background, outline);
    }

    @Override
    public void rotate(int degrees) {
        rotation = (rotation + degrees) % 360;
        super.rotate(degrees);
    }

    @Override
    public void drawString(String string, int x, int y) {
        super.rotate(-rotation);
        super.drawString(string, x, y);
        super.rotate(rotation);
    }

    @Override
    public void drawImage(GreenfootImage image, int x, int y) {
        super.rotate(-rotation);
        super.drawImage(image, x, y);
        super.rotate(rotation);
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        super.rotate(-rotation);
        super.drawLine(x1, y1, x2, y2);
        super.rotate(rotation);
    }

    @Override
    public void drawOval(int x, int y, int width, int height) {
        super.rotate(-rotation);
        super.drawOval(x, y, width, height);
        super.rotate(rotation);
    }

    @Override
    public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
        super.rotate(-rotation);
        super.drawPolygon(xPoints, yPoints, nPoints);
        super.rotate(rotation);
    }

    @Override
    public void drawRect(int x, int y, int width, int height) {
        super.rotate(-rotation);
        super.drawRect(x, y, width, height);
        super.rotate(rotation);
    }

}