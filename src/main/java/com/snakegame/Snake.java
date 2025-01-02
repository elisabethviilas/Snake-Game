package com.snakegame;

import java.awt.*;
import java.util.LinkedList;

public class Snake {
    private LinkedList<Point> body; // Stores the snake's body segments.
    private Direction direction;    // Stores the current direction of movement.

    public Snake(int startX, int startY) {
        body = new LinkedList<>();
        body.add(new Point(startX, startY)); // Adds the initial position of the snake's head to the body.
        direction = Direction.UP; // Initializes the snake's direction.
    }

    public void move() {
        Point head = body.getFirst();
        Point newHead = switch (direction) {
            case UP -> new Point(head.x, head.y - 1);
            case DOWN -> new Point(head.x, head.y + 1);
            case LEFT -> new Point(head.x - 1, head.y);
            case RIGHT -> new Point(head.x + 1, head.y);
        };
        body.addFirst(newHead); // Adds the new head segment based on the direction.
        body.removeLast(); // Removes the tail segment to maintain the same length unless snake is growing.
    }

    public void grow() {
        body.addLast(body.getLast());
    }

    public boolean checkCollision(int gridWidth, int gridHeight) {
        Point head = body.getFirst();

        // Ensures the snake's head is within grid boundaries.
        if (head.x < 0 || head.y < 0 || head.x >= gridWidth || head.y >= gridHeight) {
            return true;
        }

        // Ensures the snake's head does not overlap any other segment.
        return body.stream().skip(1).anyMatch(segment -> segment.equals(head));
    }

    public boolean eats(Food food) { // Checks if the snake's head overlaps the position of the food.
        return body.getFirst().equals(food.getPosition());
    }

    public void setDirection(Direction newDirection) { // Updates the direction of the snake's movement.
        // Prevents the snake from reversing directly.
        if ((direction == Direction.UP && newDirection != Direction.DOWN) ||
                (direction == Direction.DOWN && newDirection != Direction.UP) ||
                (direction == Direction.LEFT && newDirection != Direction.RIGHT) ||
                (direction == Direction.RIGHT && newDirection != Direction.LEFT)) {
            direction = newDirection;
        }
    }

    public void draw(Graphics g, int tileSize) { // Draws each segment of the snake on the game grid.
        g.setColor(Color.GREEN);
        for (Point segment : body) {
            g.fillRect(segment.x * tileSize, segment.y * tileSize, tileSize, tileSize); // Draws a rectangle for each segment.
        }
    }

    public int getLength() { // Returns the size of the snake. Useful for displaying the score.
        return body.size();
    }

    // Gets the current position of the snake. This method is used in Food class.
    public LinkedList<Point> getBody() {
        return body;
    }
}
