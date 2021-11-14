/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slniecko;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * @author Unlink
 */
public class GameObjectImage
{

    private BufferedImage bitmap;

    public GameObjectImage(String imagePath) throws IOException
    {
        try
        {
            if (imagePath.startsWith("/"))
            {
                this.bitmap = ImageIO.read(getClass().getResource(imagePath));
            } else
            {
                this.bitmap = ImageIO.read(new URL(imagePath));
            }
        }
        catch (Exception exception)
        {
            throw new IOException("Cannot Image Loaded!", exception);
        }
    }

    public GameObjectImage(String imagePath, int width, int height) throws IOException
    {
        this(imagePath);
        BufferedImage dimg = new BufferedImage(width, height, this.bitmap.getType());
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(this.bitmap, 0, 0, width, height, 0, 0, this.bitmap.getWidth(), this.bitmap.getHeight(), null);
        g.dispose();
        this.bitmap = dimg;
    }

    public void draw(Graphics2D g2, int x, int y)
    {
        g2.drawImage(this.bitmap, null, x, y);
    }

    public int getWidth()
    {
        return this.bitmap.getWidth();
    }

    public int getHeight()
    {
        return this.bitmap.getHeight();
    }
}
