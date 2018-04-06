package slniecko;

import java.awt.Graphics2D;

public interface IObjekt {

    public abstract void pohniSa();

    public abstract void nakresliSa(Graphics2D paramGraphics2D);

    public abstract boolean zasah(int x, int y);

    public abstract int dajBody();
}
