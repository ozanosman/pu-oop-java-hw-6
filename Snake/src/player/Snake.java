package player;

import game.GameBoard;

import java.awt.*;

public class Snake
{
    protected static int positionX;
    protected static int positionY;
    public static int size;

    public static int snakeParts;

    public Snake(int positionX, int positionY, int size)
    {
        this.positionX = positionX;
        this.positionY = positionY;
        this.size = size;
    }

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