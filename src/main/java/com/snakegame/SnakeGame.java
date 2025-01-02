package com.snakegame;

import javax.swing.*;

public class SnakeGame extends JFrame { // By extending it, the SnakeGame class inherits all methods and properties of a JFrame, allowing us to create and customize the game window.
    public SnakeGame() {
        setTitle("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensures the application terminates completely when the window is closed.
        setResizable(false); // Prevents the user from resizing the game window. This game is grid-based, so allowing the window to resize could distort the grid and game logic.
        add(new GamePanel());
        pack(); // Ensures that the window size matches the preferred size of GamePanel.
        setLocationRelativeTo(null); // Passing null means it centers the window relative to the user's primary monitor.
        setVisible(true); // Makes the JFrame visible on the screen.
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SnakeGame::new);
    }

}
