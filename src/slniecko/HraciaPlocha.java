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

public class HraciaPlocha extends JComponent implements ActionListener {

    private int sirka;
    private int vyska;
    private final Hra hra;
    private InputHandler input;
    private IObjekt objekt;

    public HraciaPlocha(Hra hra, int sirka, int vyska) throws IOException {
        this.hra = hra;
        this.sirka = sirka;
        this.vyska = vyska;

        this.input = new InputHandler(hra);

        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        hra.addWindowListener(input);
        hra.addWindowFocusListener(input);

        ObjektContainer kontajner = new ObjektContainer();
        kontajner.pridajObjekt(new ObjektContainer(hra, sirka, vyska));
        kontajner.pridajObjekt(new BonusovyObjekt(sirka, vyska));
        this.objekt = kontajner;
        
        this.setPreferredSize(new Dimension(this.sirka, this.vyska));
        this.setMinimumSize(new Dimension(this.sirka, this.vyska));

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage(getClass().getResource("/slniecko/1363733258_target.png"));
        Point hotSpot = new Point(16, 16);
        Cursor cursor = toolkit.createCustomCursor(image, hotSpot, "Target");
        this.setCursor(cursor);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        this.generateBackground(g2);
        this.objekt.nakresliSa(g2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.input.isClickAction()) {
            int[] suradnice = this.input.dajClick();
            if (this.objekt.zasah(suradnice[0], suradnice[1])) {
                System.out.println("Objekt zasiahnutý");
                this.hra.editScore(this.objekt.dajBody());
            } else {
                this.hra.editScore(-1);
            }
        }
        this.objekt.pohniSa();
        this.repaint();
    }

    private void generateBackground(Graphics2D g2) {
        g2.setColor(new Color(5245641));
        g2.fillRect(0, 0, this.sirka, this.vyska);

        g2.setColor(Color.gray);
        for (int i = 20; i < this.sirka; i += 20) {
            g2.drawLine(i, 0, i, this.vyska);
        }

        for (int i = 20; i < this.vyska; i += 20) {
            g2.drawLine(0, i, this.sirka, i);
        }
    }
}
