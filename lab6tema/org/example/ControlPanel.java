package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

/**
 * The ControlPanel class represents the control panel for the game.
 * It provides buttons for loading, saving, and exiting the game.
 */
class ControlPanel extends JPanel {
    // Reference to the main frame of the application
    final MainFrame frame;
    // Buttons for loading, saving, and exiting the game
    JButton loadBtn;
    JButton saveBtn;
    JButton exitBtn;

    /**
     * Constructs a ControlPanel object with the given main frame.
     * @param frame The main frame of the application.
     */
    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    // Initialize the control panel with buttons and their actions
    private void init() {
        // Create buttons
        loadBtn = new JButton("Load");
        saveBtn = new JButton("Save");
        exitBtn = new JButton("Exit");

        // Set layout and add buttons
        setLayout(new FlowLayout());
        add(loadBtn);
        add(saveBtn);
        add(exitBtn);

        // Add action listeners to buttons
        loadBtn.addActionListener(this::loadGame);
        saveBtn.addActionListener(this::saveGame);
        exitBtn.addActionListener(this::exitGame);
    }

    /**
     * Retrieves the load button.
     * @return The load button.
     */
    public JButton getLoadButton() {
        return loadBtn;
    }

    // Action performed when the load button is clicked
    private void loadGame(ActionEvent e) {
        // Display a file chooser dialog for loading the game file
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);

        // Check if the user selected a file
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            // You can add logic here to actually load the game from the selected file
            JOptionPane.showMessageDialog(null, "Game loaded from: " + selectedFile.getAbsolutePath());
        } else {
            JOptionPane.showMessageDialog(null, "No file selected.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Action performed when the save button is clicked
    private void saveGame(ActionEvent e) {
        // Create a file chooser for saving the game file
        JFileChooser fileChooser = new JFileChooser();
        // Show the save dialog
        int returnValue = fileChooser.showSaveDialog(this);

        // Check if the user selected a file for saving
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            // Save the game to the selected file
            try {
                frame.saveGame(selectedFile.getAbsolutePath()); // Save the game to the selected file
                JOptionPane.showMessageDialog(null, "Game saved successfully to: " + selectedFile.getAbsolutePath());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error saving game.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Action performed when the exit button is clicked
    private void exitGame(ActionEvent e) {
        // Close the main frame
        frame.dispose();
    }
}
