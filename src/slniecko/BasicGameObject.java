package slniecko;

import java.awt.Graphics2D;
import java.util.Random;

public class BasicGameObject implements IObject {

    private int x;
    private int y;
    private int deltaX;
    private int deltaY;
    private final int areaWidth;
    private final int areaHeight;
    private final GameObjectImage image;

    /**
     * @param areaWidth
     * @param areaHeight
     * @param image
     */
    public BasicGameObject(int areaWidth, int areaHeight, GameObjectImage image) {
        this.areaWidth = areaWidth;
        this.areaHeight = areaHeight;
        this.image = image;
        this.resetLocation();
    }

    @Override
    public void move() {
        this.x += this.deltaX;
        this.y += this.deltaY;
        if ((this.x < 0) || (this.x + this.image.getWidth() > this.areaWidth)) {
            this.deltaX = (-this.deltaX);
            this.x += this.deltaX;
        }
        if ((this.y < 0) || (this.y + this.image.getHeight() > this.areaHeight)) {
            this.deltaY = (-this.deltaY);
            this.y += this.deltaY;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        this.image.draw(g2, x, y);
    }

    @Override
    public boolean hit(int x, int y) {
        boolean zasah = (x >= this.x) && (x <= this.x + this.image.getWidth())
                && (y >= this.y) && (y <= this.y + this.image.getHeight());
        if (zasah) {
            resetLocation();
        }
        return zasah;
    }

    private void resetLocation() {
        Random rand = new Random();
        this.x = rand.nextInt(this.areaWidth - this.image.getWidth());
        this.y = rand.nextInt(this.areaHeight - this.image.getHeight());

        this.deltaX = (rand.nextInt(11) - 5);
        this.deltaY = (rand.nextInt(11) - 5);
    }

    @Override
    public int giveBody() {
        return 1;
    }
}
