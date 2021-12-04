package slniecko;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JComponent;

public class PlayArea extends JComponent implements ActionListener
{

    private int width;
    private int height;
    private final Game game;
    private InputHandler input;
    private IObject gameObject;

    public PlayArea(Game hra, int sirka, int vyska) throws IOException
    {
        this.game = hra;
        this.width = sirka;
        this.height = vyska;

        this.input = new InputHandler(hra);

        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        hra.addWindowListener(input);
        hra.addWindowFocusListener(input);

        GameObjectContainer gameContainer = new GameObjectContainer();
        gameContainer.addObject(new GameObjectContainer(hra, sirka, vyska)); // 20개 태양 생성
        gameContainer.addObject(new BonusObject(sirka, vyska)); // 1개 보너스 생성
        this.gameObject = gameContainer;

        this.setPreferredSize(new Dimension(this.width, this.height));
        this.setMinimumSize(new Dimension(this.width, this.height));

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        GameObjectImageFactory factory = GameObjectImageFactory.getInstance(); // 타겟 이미지를 생성.
        Image image = factory.getGameImage("_target").getImage();
        Point hotSpot = new Point(16, 16);
        Cursor cursor = toolkit.createCustomCursor(image, hotSpot, "Target");
        this.setCursor(cursor);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        this.generateBackground(g2);
        this.gameObject.draw(g2);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (this.input.isClickAction())
        {
            int[] suradnice = this.input.dajClick();
            if (this.gameObject.hit(suradnice[0], suradnice[1]))
            {
                System.out.println("Objekt zasiahnut첵");
                this.game.editScore(this.gameObject.giveBody());
            } else
            {
                this.game.editScore(-1);
            }
        }
        this.gameObject.move();
        this.repaint();
    }

    private void generateBackground(Graphics2D g2)
    {
        g2.setColor(new Color(5245641));
        g2.fillRect(0, 0, this.width, this.height);

        g2.setColor(Color.gray);
        for (int i = 20; i < this.width; i += 20)
        {
            g2.drawLine(i, 0, i, this.height);
        }

        for (int i = 20; i < this.height; i += 20)
        {
            g2.drawLine(0, i, this.width, i);
        }
    }
}
