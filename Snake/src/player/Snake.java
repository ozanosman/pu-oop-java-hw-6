package player;

import game.GameBoard;

import java.awt.*;

/**
 * Клас съдържащ конструктор и метод за визуализиране на змията.
 *
 * @author Озан Осман
 */
public class Snake
{
    protected static int positionX;
    protected static int positionY;
    public static int size;

    public static int snakeParts;

    /**
     * Конструктор на елемента "Snake".
     *
     * @param positionX     позиция "X" на елемента
     * @param positionY     позиция "Y" на елемента
     * @param size      размер на елемента
     */
    public Snake(int positionX, int positionY, int size)
    {
        this.positionX = positionX;
        this.positionY = positionY;
        this.size = size;
    }

    /**
     * Клас визуализиращ змията и неговото тяло.
     *
     * @param g     обект на супер класа
     */
    public void renderSnake(Graphics g)
    {
        for (int i = 0; i < snakeParts; i++)
        {
            if (i == 0)
            {
                g.setColor(Color.GRAY);
                g.fillOval(GameBoard.x[i], GameBoard.y[i], size, size);
            }
            else
            {
                g.setColor(Color.BLUE);
                g.fillRect(GameBoard.x[i], GameBoard.y[i], size, size);
            }
        }
    }
}