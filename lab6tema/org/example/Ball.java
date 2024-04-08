package org.example;

import java.awt.*;

/**
 * The Ball class represents a ball with its position and color.
 */
public class Ball {
    // Row position of the ball
    int row;
    // Column position of the ball
    int col;
    // Color of the ball
    Color color;

    /**
     * Constructs a Ball object with the given row, column, and color.
     * @param row The row position of the ball.
     * @param col The column position of the ball.
     * @param color The color of the ball.
     */
    Ball(int row, int col, Color color) {
        this.row = row;
        this.col = col;
        this.color = color;
    }
}
