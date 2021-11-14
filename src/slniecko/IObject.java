package slniecko;

import java.awt.Graphics2D;

public interface IObject {

    public abstract void move();

    public abstract void draw(Graphics2D paramGraphics2D);

    public abstract boolean hit(int x, int y);

    public abstract int giveBody();
}
