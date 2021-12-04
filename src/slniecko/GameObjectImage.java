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
            if (imagePath.startsWith("/")) // 파일 Path 를 전달 받는 경우
            {
                this.bitmap = ImageIO.read(getClass().getResource(imagePath));
            }
            else // File 이름만 전달받는 경우
            {
                this.bitmap = ImageIO.read(new URL(imagePath));
            }
        }
        catch (Exception exception)
        {
            throw new IOException("Cannot Image Loaded!", exception);
        }
    }

    public void setDrawSize(int width, int height)
    {
        BufferedImage myimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = myimg.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(this.bitmap, 0, 0, width, height, null);
        g.dispose();
        this.bitmap = myimg;
    }

    private BufferedImage cropImage(BufferedImage src, int width, int height)
    {
        BufferedImage dest = src.getSubimage(0, 0, width, height);
        return dest;
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

    public BufferedImage getImage()
    {
        return this.bitmap;
    }
}
