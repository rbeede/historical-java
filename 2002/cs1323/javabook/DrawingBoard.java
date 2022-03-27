package javabook;

import java.awt.*;

public class DrawingBoard extends MainWindow
{
    private Graphics g;

    public DrawingBoard()
    {
       this("D R A W I N G   B O A R D");
    }

    public DrawingBoard(String title)
    {
        super(title);
        setResizable(false);
        setBackground(Color.white);
    }

    public void setVisible(boolean view)
    {
        super.setVisible(view);
        if (view) {
           g = getGraphics();
        }
    }

    public void show()
    {
        super.show();
        g = getGraphics();
    }

    public void setColor(Color c)
    {
        g.setColor(c);
    }

    public void drawLine(int x1, int x2, int y1, int y2)
    {
        g.drawLine(x1,x2,y1,y2);
    }

    public void drawRectangle(int x, int y, int width, int height)
    {
        g.drawRect(x,y,width,height);
    }

    public void drawRectangle(Rectangle rect)
    {
        g.drawRect(rect.x, rect.y, rect.width, rect.height);
    }

    public void drawCircle(int x, int y, int radius)
    {
        g.drawOval(x-radius,y-radius,radius,radius);
    }

}

