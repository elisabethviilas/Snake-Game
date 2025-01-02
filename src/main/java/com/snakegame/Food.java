package com.snakegame;

import java.awt.*;
import java.util.Random;

public class Food {
    private Point position; // Represents the food's position on the grid.
    private int gridWidth;
    private int gridHeight;
    private Snake snake; // This allows the food class to check if the food is on the snake's body.

    public Food(int gridWidth, int gridHeight, Snake snake) {
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.snake = snake;
        generateNew(); // Generates food position immediately.
    }

    public void generateNew() { // Generates a random position for the food within the grid, using the Random class.
        Random random = new Random();
        boolean validPosition = false;

        while (!validPosition) { // The loop continues until a valid position is found. Once a valid position (not on the snake's body) is found, validPosition becomes true, exiting the loop.
            int x = random.nextInt(gridWidth);
            int y = random.nextInt(gridHeight);
            position = new Point(x, y);

            // Checks if food is on the snake's body.
            validPosition = true;
            for (Point segment : snake.getBody()) { // Checks if any segment is at the same position as the newly generated food.
                if (segment.equals(position)) {
                    validPosition = false; // If the food spawns on the snake's body, validPosition is set to false, and a new position is generated.
                    break;
                }
            }
        }
    }

    public Point getPosition() { // Returns the current position of the food.
        return position;
    }

    public void draw(Graphics g, int tileSize) { // Draws the food at its current position on the screen.
        g.setColor(Color.RED);
        g.fillRect(position.x * tileSize, position.y * tileSize, tileSize, tileSize); // Draws a filled rectangle at the food's position, scaled by tileSize to fit the grid.
    }
}
