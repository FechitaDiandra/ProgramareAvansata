package org.example;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton loadBtn;
    JButton saveBtn;
    JButton exitBtn;

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        loadBtn = new JButton("Load");
        saveBtn = new JButton("Save");
        exitBtn = new JButton("Exit");

        setLayout(new FlowLayout());
        add(loadBtn);
        add(saveBtn);
        add(exitBtn);

        // Add action listeners
        loadBtn.addActionListener(this::loadGame);
        saveBtn.addActionListener(this::saveGame);
        exitBtn.addActionListener(this::exitGame);
    }

    private void loadGame(ActionEvent e) {
        // Load game logic
    }

    private void saveGame(ActionEvent e) {
        // Save game logic
    }

    private void exitGame(ActionEvent e) {
        frame.dispose();
    }
}


