package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ConfigurationPanel extends JPanel {
    JLabel label;
    JTextField textField;
    JButton newGameButton;
    JSpinner spinner;

    public ConfigurationPanel(DrawingPanel drawingPanel, MainFrame main) {
        label = new JLabel("Grid Size:");
        spinner = new JSpinner(new SpinnerNumberModel(5, 5, 20, 1)); // Initial value, min, max, step size
        newGameButton = new JButton("New Game");

        setLayout(new FlowLayout());
        add(label);
        add(spinner);
        add(newGameButton);

        // Add action listener to the newGameButton
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int gridSize = (int) spinner.getValue();
                drawingPanel.setGridSize(gridSize);
                drawingPanel.repaint();
                main.setWindowSize(gridSize);
            }
        });

    }
}
