package slniecko;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;

public class GameObjectContainer implements IObject
{

    private ArrayList<IObject> gameObject;
    private int body;

    public GameObjectContainer()
    {
        this.body = 0;
        this.gameObject = new ArrayList<>();
    }

    public GameObjectContainer(Game game, int sirka, int vyska) throws IOException
    {
        this();
        GameObjectImageFactory factory = GameObjectImageFactory.getInstance();
        GameObjectImage image = factory.getGameImage("_sun");
        for (int i = 0; i < game.getObjectCount(); i++)
        {
            this.addObject(new BasicGameObject(sirka, vyska, image));
        }
    }

    public void addObject(IObject objekt)
    {
        this.gameObject.add(objekt);
    }

    @Override
    public void move()
    {
        this.body = 0;
        for (IObject o : this.gameObject)
        {
            o.move();
        }
    }

    @Override
    public void draw(Graphics2D g2)
    {
        for (IObject o : this.gameObject)
        {
            o.draw(g2);
        }
    }

    @Override
    public boolean hit(int x, int y)
    {
        boolean isHited = false;
        for (IObject o : this.gameObject)
        {
            if (o.hit(x, y))
            {
                this.body += 1;
                isHited = true;
            }
        }
        return isHited;
    }

    @Override
    public int giveBody()
    {
        return body > 0 ? body : 1;
    }
}
