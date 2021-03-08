package game;

import player.Snake;
import renderer.SwingRenderer;
import food.Food;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class GameBoard implements ActionListener
{
    private SwingRenderer renderer;
    private int action;

    public static int x[] = new int[400];
    public static int y[] = new int[400];

    public static boolean inGame = true;

    private static boolean executed = false;

    private Timer timer = new Timer(350, this);

    private static int FOOD_EATEN;
    private static int SCORE_POINTS;

    public static boolean moveLeft = false;
    public static boolean moveRight = true;
    public static boolean moveUp = false;
    public static boolean moveDown = false;

    public GameBoard(SwingRenderer renderer, int action)
    {
        this.renderer = renderer;
        this.action = action;
        startGame();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (this.action == 1)
        {
            this.move();
            this.eatFood();
            this.bodyCollision();
            this.wallCollision();
            this.gameWon();
            this.renderer.repaint();
            timer.start();
        }

        if (this.action == 2)
        {
            this.resetGame();
        }

        timer.setDelay(100);

        this.renderer.requestFocus();
    }

    private void startGame()
    {
        Snake.snakeParts = 4;

        this.createSnake();
        this.randomFood();
    }

    private void randomFood()
    {
        ArrayList<Integer> foodCoordinatesX = new ArrayList<>();
        ArrayList<Integer> foodCoordinatesY = new ArrayList<>();

        Food.size = 20;
        int start = 5;

        for (int i = 0; i < 20; i++)
        {
            foodCoordinatesX.add(start);
            foodCoordinatesY.add(start);
            start += 30;
        }

        Collections.shuffle(foodCoordinatesX);
        Food.positionX = foodCoordinatesX.get(0);
        Collections.shuffle(foodCoordinatesY);
        Food.positionY = foodCoordinatesY.get(0);
    }

    private void createSnake()
    {
        for (int i = 1; i <= Snake.snakeParts; i++)
        {
            x[i] = 0;
            y[i] = 0;
        }

        inGame = true;

        Snake.size = 30;
        SwingRenderer.gameOverLabel.setText("");

        x[0] = 300;
        y[0] = 300;

        moveLeft = false;
        moveRight = true;
        moveUp = false;
        moveDown = false;

        for (int i = 1; i <= Snake.snakeParts; i++)
        {
            x[i] -= x[0] - 30;
            y[i] = y[0];
        }
    }

    private void bodyCollision()
    {
        for (int i = 1; i < Snake.snakeParts; i++)
        {
            if (x[0] == x[i] && y[0] == y[i])
            {
                gameLost();
            }
        }
    }

    private void wallCollision()
    {
        if (x[0] >= 600 || x[0] < 0 || y[0] >= 600 || y[0] < 0)
        {
            gameLost();
        }
    }

    private void eatFood()
    {
        if (x[0] == Food.positionX - 5 && y[0] == Food.positionY - 5)
        {
            FOOD_EATEN += 1;
            SCORE_POINTS += 50;

            randomFood();
            SwingRenderer.foodScoreLabel.setText(String.valueOf(FOOD_EATEN));
            SwingRenderer.scorePointsLabel.setText(String.valueOf(SCORE_POINTS));
            Snake.snakeParts++;
        }
    }

    private void move()
    {
        for (int i = Snake.snakeParts; i > 0; i--)
        {
            x[i] = x[(i - 1)];
            y[i] = y[(i - 1)];
        }

        if (moveLeft)
        {
            x[0] -= 30;
        }

        if (moveRight)
        {
            x[0] += 30;
        }

        if (moveUp)
        {
            y[0] -= 30;
        }

        if (moveDown)
        {
            y[0] += 30;
        }
    }

    private void resetGame()
    {
        timer.stop();
        inGame = true;
        executed = false;
        SwingRenderer.gameOverLabel.setText("");
        FOOD_EATEN = 0;
        SCORE_POINTS = 0;
        SwingRenderer.foodScoreLabel.setText(String.valueOf(FOOD_EATEN));
        SwingRenderer.scorePointsLabel.setText(String.valueOf(SCORE_POINTS));
        startGame();
    }

    private void gameWon()
    {
        if (SCORE_POINTS == 300)
        {
            Snake.size = 0;
            Food.size = 0;
            SwingRenderer.gameOverLabel.setText("Вие спечелихте!");
            timer.stop();
            inGame = false;
            executed = true;
        }
    }

    private void gameLost()
    {
        if (!executed)
        {
            Snake.size = 0;
            Food.size = 0;
            SwingRenderer.gameOverLabel.setText("Вие загубихте!");
            timer.stop();
            inGame = false;
            executed = true;
        }
    }
}