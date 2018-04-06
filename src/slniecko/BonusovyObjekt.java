/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slniecko;

import java.awt.Graphics2D;
import java.io.IOException;

/**
 *
 * @author Unlink
 */
public class BonusovyObjekt extends ZakladnyObjekt {

    private boolean zobrazeny;
    private int counter;
    
    public BonusovyObjekt(int sirkaIhriska, int vyskaIhriska) throws IOException {
        super(sirkaIhriska, vyskaIhriska, new Obrazok("/slniecko/1363725493_heart.png", 20, 20));
        this.zobrazeny = false;
        this.counter = 0;
    }

    @Override
    public int dajBody() {
        return 5;
    }

    @Override
    public boolean zasah(int x, int y) {
        boolean zasah = this.zobrazeny && super.zasah(x, y);
        if (zasah) {
            this.zobrazeny = false;
            this.counter = 0;
        }
        return zasah;
    }

    @Override
    public void nakresliSa(Graphics2D g2) {
        if (this.zobrazeny) {
            super.nakresliSa(g2);
        }
    }

    @Override
    public void pohniSa() {
        this.counter++;
        //Prvých 10s je off, potom on
        if (this.counter > 50 * 10) {
            this.zobrazeny = true;
        }
        //Po 15s ho zas vypnem
        if (this.counter > 50 * 15) {
            this.zobrazeny = false;
            this.counter = 0;
        }
        if (this.zobrazeny) {
            super.pohniSa();
        }
    }
    
    
    
}
