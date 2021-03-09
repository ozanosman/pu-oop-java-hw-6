package renderer;

import game.GameBoard;
import player.Snake;
import food.Food;
import tile.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Клас наследяващ JFrame, прилагащ MouseListener, KeyListener и съдържащ конструктор и методи за визуализиране на приложението.
 *
 * @author Озан Осман
 */
public class SwingRenderer extends JFrame implements MouseListener, KeyListener
{
    GameBoard gameBoard;

    private JLabel statsLabel = new JLabel("Статистики:", SwingConstants.HORIZONTAL);
    private JLabel scoreLabel = new JLabel("Точки:   ");
    private JLabel foodLabel = new JLabel("Изядена храна:   ");

    public static JLabel scorePointsLabel = new JLabel("0");
    public static JLabel foodScoreLabel = new JLabel("0");

    public static JLabel gameOverLabel = new JLabel();

    /**
     * Конструктор съдържащ характеристиките за създаване на прозореца, в която се визуализира игралната дъска, бутоните и неговите елементи.
     */
    public SwingRenderer()
    {
        this.setTitle("Snake");
        this.setLayout(new BorderLayout());

        Snake snake = new Snake(0, 0, 0);
        Tile tile = new Tile();
        Food food = new Food(0, 0, 0);

        JPanel tilesPanel = new JPanel()
        {
            public void paintComponent(Graphics g)
            {
                snake.renderSnake(g);
                tile.renderTile(g);
                food.renderFood(g);
            }
        };

        JPanel labelsPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();

        tilesPanel.setLayout(new GridBagLayout());
        labelsPanel.setLayout(new GridBagLayout());
        buttonsPanel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;

        tilesPanel.addMouseListener(this);

        c.insets = new Insets(10, 0, 10, 10);

        JButton startButton = new JButton("Започни игра");
        JButton resetButton = new JButton("Рестартиране");

        c.insets = new Insets(10, 0, 10, 0);

        buttonsPanel.add(startButton, c);

        c.insets = new Insets(10, 20, 10, 0);

        buttonsPanel.add(resetButton, c);

        c.insets = new Insets(10, 10, 10, 0);

        startButton.setPreferredSize(new Dimension(125, 30));

        statsLabel.setPreferredSize(new Dimension(300, 30));

        JLabel controlTitleLabel = new JLabel("Контроли: ", SwingConstants.CENTER);

        JLabel moveUpLabel = new JLabel("За придвижване нагоре:     ↑ / W / Щракнете с мишката над главата на змията");
        JLabel moveDownLabel = new JLabel("За придвижване надолу:     ↓ / S / Щракнете с мишката под главата на змията");
        JLabel moveLeftLabel = new JLabel("За придвижване наляво:     ← / A / Щракнете с мишката наляво от главата на змията");
        JLabel moveRightLabel = new JLabel("За придвижване надясно:     → / D / Щракнете с мишката надясно от главата на змията");

        c.insets = new Insets(0, 0, 0, 0);

        labelsPanel.add(statsLabel, c);

        c.gridy = 1;

        labelsPanel.add(scoreLabel, c);

        c.insets = new Insets(0, 20, 0, 20);

        labelsPanel.add(scorePointsLabel, c);

        c.gridy = 2;

        c.insets = new Insets(0, 0, 0, 0);

        labelsPanel.add(foodLabel, c);

        c.insets = new Insets(0, 20, 0, 20);

        labelsPanel.add(foodScoreLabel, c);

        c.insets = new Insets(50, 0, 10, 0);

        c.gridy = 3;

        labelsPanel.add(controlTitleLabel, c);

        c.insets = new Insets(0, 0, 0, 0);

        c.gridy = 4;

        labelsPanel.add(moveUpLabel, c);

        c.gridy = 5;

        labelsPanel.add(moveDownLabel, c);

        c.gridy = 6;

        labelsPanel.add(moveLeftLabel, c);

        c.gridy = 7;

        labelsPanel.add(moveRightLabel, c);

        c.insets = new Insets(150, 210, 0, 0);

        c.gridy = 8;

        gameOverLabel.setForeground(Color.RED);
        labelsPanel.add(gameOverLabel, c);

        scorePointsLabel.setForeground(Color.RED);
        foodScoreLabel.setForeground(Color.RED);

        add(tilesPanel, BorderLayout.CENTER);
        add(labelsPanel, BorderLayout.EAST);
        add(buttonsPanel, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(1200, 700);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setFocusable(true);

        startButton.addActionListener(new GameBoard(this, 1));
        resetButton.addActionListener(new GameBoard(this, 2));

        addKeyListener(this);
    }

    /**
     * Метод, който сравнява позицията на змията и позицията на кликане на мишката и променя посоката на движение на змията.
     *
     * @param e     обект на супер класа
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {
        if (GameBoard.inGame)
        {
            if (gameBoard.moveUp && e.getX() > gameBoard.x[0] && (e.getY() > 0 && e.getY() < 600))
            {
                gameBoard.moveRight = true;
                gameBoard.moveUp = false;
            }
            else if (gameBoard.moveUp && e.getX() < gameBoard.x[0] && (e.getY() > 0 && e.getY() < 600))
            {
                gameBoard.moveLeft = true;
                gameBoard.moveUp = false;
            }
            else if (gameBoard.moveDown && e.getX() > gameBoard.x[0] && (e.getY() > 0 && e.getY() < 600))
            {
                gameBoard.moveRight = true;
                gameBoard.moveDown = false;
            }
            else if (gameBoard.moveDown && e.getX() < gameBoard.x[0] && (e.getY() > 0 && e.getY() < 600))
            {
                gameBoard.moveLeft = true;
                gameBoard.moveDown = false;
            }
            else if (gameBoard.moveLeft && e.getY() > gameBoard.y[0] && (e.getX() > 0 && e.getX() < 600))
            {
                gameBoard.moveDown = true;
                gameBoard.moveLeft = false;
            }
            else if (gameBoard.moveLeft && e.getY() < gameBoard.y[0] && (e.getX() > 0 && e.getX() < 600))
            {
                gameBoard.moveUp = true;
                gameBoard.moveLeft = false;
            }
            else if (gameBoard.moveRight && e.getY() > gameBoard.y[0] && (e.getX() > 0 && e.getX() < 600))
            {
                gameBoard.moveDown = true;
                gameBoard.moveRight = false;
            }
            else if (gameBoard.moveRight && e.getY() < gameBoard.y[0] && (e.getX() > 0 && e.getX() < 600))
            {
                gameBoard.moveUp = true;
                gameBoard.moveRight = false;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
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

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    /**
     * Метод, който мърда змията на горе, долу, ляво и дясно с клавишите от клавиатурата.
     *
     * @param e     обект на супер класа
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W && (!gameBoard.moveDown))
        {
            gameBoard.moveUp = true;
            gameBoard.moveRight = false;
            gameBoard.moveLeft = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S && (!gameBoard.moveUp))
        {
            gameBoard.moveDown = true;
            gameBoard.moveRight = false;
            gameBoard.moveLeft = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A && (!gameBoard.moveRight))
        {
            gameBoard.moveLeft = true;
            gameBoard.moveUp = false;
            gameBoard.moveDown = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D && (!gameBoard.moveLeft))
        {
            gameBoard.moveRight = true;
            gameBoard.moveUp = false;
            gameBoard.moveDown = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}