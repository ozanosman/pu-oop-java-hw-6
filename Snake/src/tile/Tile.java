package tile;

import java.awt.*;

public class Tile
{
    protected int TILE_SIZE = 20;
    protected int ROWS = 30;
    protected int COLS = 30;
    protected int GAME_PANEL_SIZE = 600;

    public void renderTile(Graphics g)
    {
        g.setColor(Color.BLACK);

        for (int i = 0; i <= TILE_SIZE; i++)
        {
            for (int j = 0; j <= TILE_SIZE; j++)
            {
                g.drawLine(i * ROWS, 0, i * ROWS, GAME_PANEL_SIZE);
                g.drawLine(0, j * COLS, GAME_PANEL_SIZE, j * COLS);
            }
        }
    }
}