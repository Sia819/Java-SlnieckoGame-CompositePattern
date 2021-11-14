package slniecko;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;

public class GameObjectContainer implements IObjekt {

    private ArrayList<IObjekt> objekty;
    private int body;

    public GameObjectContainer() {
        this.body = 0;
        this.objekty = new ArrayList<>();
    }

    public GameObjectContainer(Hra hra, int sirka, int vyska) throws IOException {
        this();
        Obrazok obrazok = new Obrazok(hra.getObrazok() != null ? hra.getObrazok() : "/slniecko/1363649436_gadu.png");
        for (int i = 0; i < hra.getPocetObjektov(); i++) {
            this.pridajObjekt(new ZakladnyObjekt(sirka, vyska, obrazok));
        }
    }
    
    public void pridajObjekt(IObjekt objekt) {
        this.objekty.add(objekt);
    }

    @Override
    public void pohniSa() {
        this.body = 0;
        for (IObjekt o : this.objekty) {
            o.pohniSa();
        }
    }

    @Override
    public void nakresliSa(Graphics2D g2) {
        for (IObjekt o : this.objekty) {
            o.nakresliSa(g2);
        }
    }

    @Override
    public boolean zasah(int x, int y) {
        boolean zasah = false;
        for (IObjekt o : this.objekty) {
            if (o.zasah(x, y)) {
                this.body += 1;
                zasah = true;
            }
        }
        return zasah;
    }

    @Override
    public int dajBody() {
        return body > 0 ? body : 1;
    }
}
