package org.example;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    ConfigurationPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;
    public MainFrame() {
        super("My game");
        init();
    }

    public void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Initialize components
        canvas = new DrawingPanel();
        configPanel = new ConfigurationPanel(canvas, this);
        controlPanel = new ControlPanel(this);

        // Set layout
        setLayout(new BorderLayout());

        // Add components to the frame
        add(configPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setWindowSize(5);

        // Pack components and set visible
        pack();
        setVisible(true);
    }

    public void setWindowSize(int size){
        size++;
        size *= 50;
        if ( size + 100 > getHeight()) {
            setSize(size - 15, getHeight());
        }
        setSize(size - 15, size + 100);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}

