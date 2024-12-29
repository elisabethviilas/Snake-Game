package com.snakegame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private static final int TILE_SIZE = 20;
    private static final int WIDTH = 30; // 30 tiles horizontally
    private static final int HEIGHT = 20; // 20 tiles vertically
    private static final int DELAY = 100; // Game speed (ms)

    private Timer timer;
    private Snake snake;
    private Food food;

    public GamePanel() {
        setPreferredSize(new Dimension(TILE_SIZE * WIDTH, TILE_SIZE * HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        // Initializing game components
        snake = new Snake(WIDTH / 2, HEIGHT / 2);
        food = new Food(WIDTH, HEIGHT);
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
