/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slniecko;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author Unlink
 */
public class GameObjectImage
{
    
    private BufferedImage bitmap;

    public GameObjectImage(String adresa) throws IOException {
        try {
            if (adresa.startsWith("/")) {
                this.bitmap = ImageIO.read(getClass().getResource(adresa));
            }
            else {
                this.bitmap = ImageIO.read(new URL(adresa));
            }
        }
        catch (Exception exception) {
            throw new IOException("Nepodarilo na nacitat obrazok", exception);
        }
    }
    
    public GameObjectImage(String adresa, int sirka, int vyska) throws IOException {
        this(adresa);
        BufferedImage dimg = new BufferedImage(sirka, vyska, this.bitmap.getType());  
        Graphics2D g = dimg.createGraphics();  
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
        RenderingHints.VALUE_INTERPOLATION_BILINEAR);  
        g.drawImage(this.bitmap, 0, 0, sirka, vyska, 0, 0, this.bitmap.getWidth(), this.bitmap.getHeight(), null);  
        g.dispose();  
        this.bitmap = dimg;
    }
    
    public void vykresli(Graphics2D g2, int x, int y) {
        g2.drawImage(this.bitmap, null, x, y);
    }
    
    public int getSirka() {
        return this.bitmap.getWidth();
    }
    
    public int getVyska() {
        return this.bitmap.getHeight();
    }
}
