package food;

import java.awt.*;

/**
 * Клас съдържащ конструктор и метод за визуализиране на храната.
 *
 * @author Озан Осман
 */
public class Food
{
    public static int positionX;
    public static int positionY;
    public static int size;

    /**
     * Конструктор на елемента "Food".
     *
     * @param positionX     позиция "X" на елемента
     * @param positionY     позиция "Y" на елемента
     * @param size      размер на елемента
     */
    public Food(int positionX, int positionY, int size)
    {
        this.positionX = positionX;
        this.positionY = positionY;
        this.size = size;
    }

    /**
     * Клас визуализиращ храната.
     *
     * @param g     обект на супер класа
     */
    public void renderFood(Graphics g)
    {
        g.setColor(Color.RED);
        g.fillOval(positionX, positionY, size, size);
    }
}