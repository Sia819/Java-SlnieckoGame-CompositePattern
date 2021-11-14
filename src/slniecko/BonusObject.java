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
public class BonusObject extends BasicGameObject {

    private boolean zobrazeny;
    private int counter;

    public BonusObject(int sirkaIhriska, int vyskaIhriska) throws IOException {
        super(sirkaIhriska, vyskaIhriska, new GameObjectImage("/slniecko/1363725493_heart.png", 20, 20));
        this.zobrazeny = false;
        this.counter = 0;
    }

    @Override
    public int giveBody() {
        return 5;
    }

    @Override
    public boolean hit(int x, int y) {
        boolean zasah = this.zobrazeny && super.hit(x, y);
        if (zasah) {
            this.zobrazeny = false;
            this.counter = 0;
        }
        return zasah;
    }

    @Override
    public void draw(Graphics2D g2) {
        if (this.zobrazeny) {
            super.draw(g2);
        }
    }

    @Override
    public void move() {
        this.counter++;
        //Prv첵ch 10s je off, potom on
        if (this.counter > 50 * 10) {
            this.zobrazeny = true;
        }
        //Po 15s ho zas vypnem
        if (this.counter > 50 * 15) {
            this.zobrazeny = false;
            this.counter = 0;
        }
        if (this.zobrazeny) {
            super.move();
        }
    }



}
