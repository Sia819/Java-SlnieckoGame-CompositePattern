package slniecko;

import java.awt.Graphics2D;
import java.util.Random;

public class BasicGameObject implements IObjekt {

    private int x;
    private int y;
    private int deltaX;
    private int deltaY;
    private final int sirkaIhriska;
    private final int vyskaIhriska;
    private final Obrazok obrazok;

    /**
     * @param sirkaIhriska
     * @param vyskaIhriska
     * @param obrazok
     */
    public BasicGameObject(int sirkaIhriska, int vyskaIhriska, Obrazok obrazok) {
        this.sirkaIhriska = sirkaIhriska;
        this.vyskaIhriska = vyskaIhriska;
        this.obrazok = obrazok;
        this.reset();
    }

    @Override
    public void pohniSa() {
        this.x += this.deltaX;
        this.y += this.deltaY;
        if ((this.x < 0) || (this.x + this.obrazok.getSirka() > this.sirkaIhriska)) {
            this.deltaX = (-this.deltaX);
            this.x += this.deltaX;
        }
        if ((this.y < 0) || (this.y + this.obrazok.getVyska() > this.vyskaIhriska)) {
            this.deltaY = (-this.deltaY);
            this.y += this.deltaY;
        }
    }

    @Override
    public void nakresliSa(Graphics2D g2) {
        this.obrazok.vykresli(g2, x, y);
    }

    @Override
    public boolean zasah(int x, int y) {
        boolean zasah = (x >= this.x) && (x <= this.x + this.obrazok.getSirka()) && (y >= this.y) && (y <= this.y + this.obrazok.getVyska());
        if (zasah) {
            reset();
        }
        return zasah;
    }

    private void reset() {
        Random rand = new Random();
        this.x = rand.nextInt(this.sirkaIhriska - this.obrazok.getSirka());
        this.y = rand.nextInt(this.vyskaIhriska - this.obrazok.getVyska());

        this.deltaX = (rand.nextInt(11) - 5);
        this.deltaY = (rand.nextInt(11) - 5);
    }

    @Override
    public int dajBody() {
        return 1;
    }
}
