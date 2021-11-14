package slniecko;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;

public class InputHandler implements MouseListener, WindowListener, MouseMotionListener, WindowFocusListener
{

    private boolean isClick;
    private int xclick;
    private int yclick;
    private boolean isClosed;
    private Game game;

    public InputHandler(Game game)
    {
        this.game = game;
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        this.xclick = e.getX();
        this.yclick = e.getY();
        this.isClick = true;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }

    public synchronized boolean isClickAction()
    {
        return this.isClick;
    }

    public final synchronized int[] dajClick()
    {
        this.isClick = false;
        return new int[]{xclick, yclick};
    }

    public synchronized boolean isClosed()
    {
        return this.isClosed;
    }

    @Override
    public void windowClosing(WindowEvent e)
    {
        this.isClosed = true;
    }

    @Override
    public void windowOpened(WindowEvent e)
    {
    }

    @Override
    public void windowClosed(WindowEvent e)
    {
    }

    @Override
    public void windowIconified(WindowEvent e)
    {
    }

    @Override
    public void windowDeiconified(WindowEvent e)
    {
    }

    @Override
    public void windowActivated(WindowEvent e)
    {
    }

    @Override
    public void windowDeactivated(WindowEvent e)
    {
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
    }

    @Override
    public void windowGainedFocus(WindowEvent e)
    {
        this.game.startGame();
    }

    @Override
    public void windowLostFocus(WindowEvent e)
    {
        this.game.pauseGame();
    }
}
