package slniecko;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.net.*;

public class GameObjectImageFactory
{
    private HashMap pool = new HashMap();
    private  static GameObjectImageFactory singleton = new GameObjectImageFactory();

    private GameObjectImageFactory() {}

    public static GameObjectImageFactory getInstance()
    {
        return singleton;
    }

    public synchronized GameObjectImage getGameImage(String imagePath) // "/slniecko/LoadErrorImage.png" // "LoadErrorImage.png"
    {
       GameObjectImage image = (GameObjectImage) pool.get(imagePath);
       try
       {
           if (image == null)
           {
               String my = getClass().getResource(imagePath).getFile();
               File imageFile = new File(my);
               if (imageFile.exists())
               {
                   image = new GameObjectImage(imagePath);
                   String fileName = imageFile.getName();
                   int dotIndex = fileName.lastIndexOf('.');
                   pool.put(fileName.substring(0, dotIndex), image); // ex) "_heart.png"
               }
               else
               {
                   image = new GameObjectImage("/slniecko/_LoadErrorImage.png");
                   pool.put(imagePath, image);
               }
           }
           return image;
       }
       catch (Exception e)
       {
           return null;
       }
    }
}
