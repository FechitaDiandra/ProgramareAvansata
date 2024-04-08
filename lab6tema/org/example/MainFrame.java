package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.File;

/**
 * The MainFrame class represents the main window of the application.
 */
public class MainFrame extends JFrame {
    public static final String SAVE_FILE_NAME = "game_state.dat";
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;

    /**
     * Constructs a MainFrame object.
     */
    public MainFrame() {
        super("My game");
        init();
    }

    /**
     * Initializes the components and sets up the frame.
     */
    public void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Initialize components
        canvas = new DrawingPanel();
        configPanel = new ConfigPanel(canvas, this);
        controlPanel = new ControlPanel(this);

        // Set layout
        setLayout(new BorderLayout());

        // Add components to the frame
        add(configPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setWindowSize(5); // Initial window size

        // Pack components and set visible
        pack();
        setVisible(true);

        // Add listener for the "Load" button
        controlPanel.getLoadButton().addActionListener(e -> {
            // Show a file chooser dialog for loading a file
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(this);

            // Check if the user selected a file
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                // Add logic for actually loading the game from the selected file
                JOptionPane.showMessageDialog(null, "Game loaded from: " + selectedFile.getAbsolutePath());
            } else {
                JOptionPane.showMessageDialog(null, "No file selected.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    /**
     * Sets the window size based on the grid size.
     * @param size The grid size.
     */
    public void setWindowSize(int size) {
        size++;
        size *= 50;
        if (size + 100 > getHeight()) {
            setSize(size - 15, getHeight());
        }
        setSize(size - 15, size + 100);
    }

    /**
     * Saves the current game state.
     */
    public void saveGame() {
        Component frame = null;
        String saveFileName = MainFrame.SAVE_FILE_NAME;
        GameStateManager.saveGameState(canvas, saveFileName); // Use GameStateManager to save the game state
        JOptionPane.showMessageDialog(frame, "Game state saved successfully.");
    }

    /**
     * Saves the current game state to the specified file.
     * @param filePath The path of the file to save the game state to.
     * @throws IOException If an I/O error occurs.
     */
    public void saveGame(String filePath) throws IOException {
        GameStateManager.saveGameState(canvas, filePath);
    }

    /**
     * The main method that launches the application.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
