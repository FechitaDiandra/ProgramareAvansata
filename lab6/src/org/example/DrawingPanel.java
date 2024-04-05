package org.example;
import javax.swing.*;
import java.awt.*;

class DrawingPanel extends JPanel {

    int gridSize = 5;

    public void setGridSize(int size){
        this.gridSize = size;

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        // Draw the grid
        int width = gridSize * 50;
        int height = gridSize * 50;

        // Draw vertical lines
        for (int x = 10; x <= width + 10; x += 50) {
            g.drawLine(x, 10, x, height + 10);
        }

        // Draw horizontal lines
        for (int y = 10; y <= height + 10; y += 50) {
            g.drawLine(10, y, width + 10, y);
        }
    }
}
