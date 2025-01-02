package com.snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener { // JPanel is a container for drawing and UI components; ActionListener handles events triggered by the Timer (like the game's update); KeyListener listens for key presses to control the snake.
    private static final int TILE_SIZE = 20; // Defines the size of each tile in the game grid (in pixels).
    private static final int WIDTH = 30; // 30 tiles horizontally.
    private static final int HEIGHT = 20; // 20 tiles vertically.
    private static final int DELAY = 100; // Game speed (ms).

    private Timer timer;
    private Snake snake;
    private Food food;

    public GamePanel() {
        setPreferredSize(new Dimension(TILE_SIZE * WIDTH, TILE_SIZE * HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true); // Makes the GamePanel focusable, so it can receive keyboard events (necessary for controlling the snake).
        addKeyListener(this); // This allows the class to capture and respond to key presses.

        // Initializing game components.
        snake = new Snake(WIDTH / 2, HEIGHT / 2); // Creates a new Snake object, placing it at the center of the grid (half the width and height).
        food = new Food(WIDTH, HEIGHT, snake); // Creates a new Food object, which will be randomly placed within the grid boundaries.
        timer = new Timer(DELAY, this); // Initializes the Timer with the specified DELAY and assigns this as the listener to handle timer events.
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) { // Every time the game needs to be updated, repaint() is called, which triggers paintComponent to be executed and redraw the game elements.
        super.paintComponent(g); // This ensures the previous frame doesn't overlap with the new one.

        // Draw the snake.
        snake.draw(g, TILE_SIZE); // g is the Graphics object used for drawing. TILE_SIZE is passed to ensure the snake is drawn with the correct tile size.

        // Draw the food.
        food.draw(g, TILE_SIZE);

        // Drawing optional grid for better visuals.
        g.setColor(Color.GRAY);
        for (int x = 0; x < getWidth(); x += TILE_SIZE) { // A loop to draw vertical grid lines across the panel.
            for (int y = 0; y < getHeight(); y += TILE_SIZE) { // A loop to draw horizontal grid lines.
                g.drawRect(x, y, TILE_SIZE, TILE_SIZE); // Draws a rectangle at position (x, y) with the size TILE_SIZE by TILE_SIZE to form the grid.
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) { // This method is called every time the timer fires an event (e.g., every 100ms).
        if (snake.checkCollision(WIDTH, HEIGHT)) { // Checks if the snake has collided with the walls or itself. If checkCollision returns true, the game ends, and a "Game Over" message is displayed.
            timer.stop(); //  Stops the Timer, halting the game loop and preventing further updates after the game ends.
            JOptionPane.showMessageDialog(this, "Game Over! Score: " + snake.getLength()); // Displays a message dialog showing the game over message and the player's score (snake length).
        } else { // If there’s no collision, the game continues.
            if (snake.eats(food)) { // Checks if the snake has eaten the food. If true, the snake grows, and a new food is placed.
                snake.grow(); // Makes the snake grow by adding a new segment to its body.
                food.generateNew(); // Generates a new food item at a random position on the grid.
            }
            snake.move(); // Moves the snake in the direction specified by the player.
        }
        repaint(); // Redraws the game panel to update the screen with the new state (snake, food, etc.).
    }

    @Override
    public void keyPressed(KeyEvent e) { // Listens for key presses to change the direction of the snake.
        switch (e.getKeyCode()) { // Handles the arrow key presses to set the snake's direction.
            case KeyEvent.VK_UP: snake.setDirection(Direction.UP);
            case KeyEvent.VK_DOWN: snake.setDirection(Direction.DOWN);
            case KeyEvent.VK_LEFT: snake.setDirection(Direction.LEFT);
            case KeyEvent.VK_RIGHT: snake.setDirection(Direction.RIGHT);
        }
    }

    // These next 2 methods are not used in this game, but are required by KeyListener.
    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
