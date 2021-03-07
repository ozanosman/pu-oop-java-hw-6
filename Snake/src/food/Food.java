package food;

import java.awt.*;

public class Food
{
    public static int positionX;
    public static int positionY;
    public static int size;

    public Food(int positionX, int positionY, int size)
    {
        this.positionX = positionX;
        this.positionY = positionY;
        this.size = size;
    }

    public void renderFood(Graphics g)
    {
        g.setColor(Color.RED);
        g.fillOval(positionX, positionY, size, size);
    }
}