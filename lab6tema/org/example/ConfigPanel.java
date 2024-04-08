package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ConfigPanel class represents the configuration panel for the game.
 * It allows users to adjust the grid size and start a new game.
 */
class ConfigPanel extends JPanel {

    private DrawingPanel drawingPanel;
    private MainFrame mainFrame;
    private JSpinner spinner;
    private JButton newGameButton;

    /**
     * Constructs a ConfigPanel object with the given drawing panel and main frame.
     * @param drawingPanel The drawing panel associated with the configuration panel.
     * @param mainFrame The main frame of the application.
     */
    public ConfigPanel(DrawingPanel drawingPanel, MainFrame mainFrame) {
        this.drawingPanel = drawingPanel;
        this.mainFrame = mainFrame;

        // Create components
        JLabel label = new JLabel("Grid Size:");
        spinner = new JSpinner(new SpinnerNumberModel(5, 5, 20, 1));
        newGameButton = new JButton("New Game");

        // Set layout and add components
        setLayout(new FlowLayout());
        add(label);
        add(spinner);
        add(newGameButton);

        // Add action listener to the new game button
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve the selected grid size
                int gridSize = (int) spinner.getValue();
                // Update window size
                mainFrame.setWindowSize(gridSize);
                // Set grid size in drawing panel
                drawingPanel.setGridSize(gridSize);
                // Initialize the game in drawing panel
                drawingPanel.initGame();
                // Repaint the drawing panel
                drawingPanel.repaint();
            }
        });
    }

    /**
     * Updates the value of the spinner to the specified value.
     * @param value The value to set on the spinner.
     */
    public void updateSpinnerValue(int value) {
        spinner.setValue(value);
    }

}
