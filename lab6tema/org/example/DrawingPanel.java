package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Stack;
import java.util.ArrayList;

/**
 * The DrawingPanel class represents the panel where the game is drawn.
 * It handles user interactions and drawing of the game components.
 */
public class DrawingPanel extends JPanel {

    // Grid size
    static int gridSize = 5;
    // Arrays to store the state of sticks and nodes on the grid
    static boolean[][] sticks;
    static boolean[][] nodes;
    // Player turn
    static boolean player1Turn = true; // Player 1 starts the game
    // Stack to store the balls placed on the grid
    static Stack<Ball> balls = new Stack<>();
    // Last position clicked
    Point lastPosition;
    // List to store thick lines
    static ArrayList<Point> thickLines = new ArrayList<>();

    /**
     * Constructs a DrawingPanel object.
     * Initializes the game and adds mouse listener for user interactions.
     */
    public DrawingPanel() {
        initGame();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Get the coordinates of the mouse click
                int mouseX = e.getX();
                int mouseY = e.getY();
                int row = mouseY / 50; // Calculate the clicked row based on mouse Y coordinate
                int col = mouseX / 50; // Calculate the clicked column based on mouse X coordinate

                // Check if the clicked position is a valid node and handle accordingly
                if (isValidNode(row, col)) {
                    if (player1Turn || thickLines.contains(new Point(col, row)) || isAdjacentToBall(row, col)) {
                        if (!sticks[row][col] && !nodes[row][col]) {
                            nodes[row][col] = true; // Mark the node as selected
                            balls.push(new Ball(row, col, player1Turn ? Color.RED : Color.BLUE)); // Add ball to stack
                            player1Turn = !player1Turn; // Switch player turn
                            lastPosition = new Point(col, row); // Memorize last position
                            repaint(); // Redraw the board
                            // Check for winner
                            boolean hasValidMove = checkValidMove();
                            if (!hasValidMove) {
                                JOptionPane.showMessageDialog(null, "Player " + (player1Turn ? "2" : "1") + " wins!");
                            }
                        }
                    }
                }
            }
        });
    }

    /**
     * Initializes the game by setting up sticks, nodes, thick lines, and player turn.
     */
    public static void initGame() {
        sticks = new boolean[gridSize][gridSize];
        nodes = new boolean[gridSize][gridSize];
        thickLines.clear(); // Clear thick lines before initializing
        // Randomly place sticks on the grid
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                sticks[i][j] = Math.random() < 0.5; // Randomly set if there's a stick or not
                // Check if the current position is on the edges or at a certain distance from each other to mark as thick line
                if ((i == 0 || i == gridSize - 1 || j == 0 || j == gridSize - 1) || (i % (gridSize - 1) == 0 && j % (gridSize - 1) == 0)) {
                    thickLines.add(new Point(j, i));
                }
            }
        }
        player1Turn = true; // Reset player turn to player 1
        balls.clear(); // Clear balls stack
    }

    /**
     * Sets the grid size and updates the main frame's size accordingly.
     * @param size The new grid size.
     * @param mainFrame The main frame of the application.
     */
    public static void setGridSize(int size, MainFrame mainFrame) {
        gridSize = size;
        int windowWidth = size * 50 + 100; // Adjust window width based on grid size
        int windowHeight = size * 50 + 150; // Adjust window height based on grid size
        mainFrame.setSize(windowWidth, windowHeight); // Update window size
    }

    /**
     * Handles painting of the game components.
     * @param g The Graphics object.
     */
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
        // Draw sticks
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (sticks[i][j]) {
                    g.setColor(Color.BLACK);
                    g.fillRect(j * 50 + 10, i * 50 + 10, 50, 2); // Draw sticks horizontally
                    g.fillRect(j * 50 + 10, i * 50 + 10, 2, 50); // Draw sticks vertically
                }
            }
        }
        // Draw balls
        for (Ball ball : balls) {
            g.setColor(ball.color);
            g.fillOval(ball.col * 50 + 2, ball.row * 50 + 5, 12, 12); // Draw a ball
        }
    }

    /**
     * Checks if there are any valid moves left on the grid.
     * @return True if there is at least one valid move left, false otherwise.
     */
    private boolean checkValidMove() {
        // Check if there are any valid moves left and if the position to place the piece is not already in the balls stack
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (!sticks[i][j] && !nodes[i][j]) {
                    boolean alreadyPlaced = false;
                    // Check if the position is already occupied by a ball in the balls stack
                    for (Ball ball : balls) {
                        if (ball.row == i && ball.col == j) {
                            alreadyPlaced = true;
                            break;
                        }
                    }
                    if (!alreadyPlaced) {
                        return true; // There is at least one valid move left
                    }
                }
            }
        }
        return false; // No valid moves left or all possible positions are already occupied
    }

    /**
     * Checks if the given row and column represent a valid node on the grid.
     * @param row The row index.
     * @param col The column index.
     * @return True if the node is valid, false otherwise.
     */
    private boolean isValidNode(int row, int col) {
        return row >= 0 && row < gridSize && col >= 0 && col < gridSize && !sticks[row][col] && !nodes[row][col];
    }

    /**
     * Checks if the given row and column are adjacent to any ball on the grid.
     * @param row The row index.
     * @param col The column index.
     * @return True if the position is adjacent to any ball, false otherwise.
     */
    private boolean isAdjacentToBall(int row, int col) {
        for (Ball ball : balls) {
            int ballRow = ball.row;
            int ballCol = ball.col;
            if (Math.abs(row - ballRow) <= 1 && Math.abs(col - ballCol) <= 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Saves the current state of the board to an image file.
     * @param fileName The file name to save the image to.
     * @param mainFrame The main frame of the application.
     */
    public static void saveBoardToFile(String fileName, MainFrame mainFrame) {
        try {
            // Create an image of the current board and save it as a PNG file
            BufferedImage image = new BufferedImage(mainFrame.getWidth(), mainFrame.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = image.createGraphics();
            mainFrame.paint(g2); // Use the mainFrame to paint the image
            File file = new File(fileName);
            ImageIO.write(image, "PNG", file);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle IOException appropriately (show message, log, etc.)
        }
    }

    /**
     * Retrieves the main frame of the application.
     * @return The main frame.
     */
    public MainFrame getMainFrame() {
        return null;
    }

    /**
     * Sets the grid size and updates the drawing panel accordingly.
     * @param size The new grid size.
     */
    public void setGridSize(int size) {
        gridSize = size; // Update grid size
        repaint(); // Redraw the drawing panel to reflect the change
    }

    /**
     * Saves the current state of the board to an image file.
     * @param fileName The file name to save the image to.
     */
    public void saveBoardToFile(String fileName) {
        try {
            // Create an image of the current board and save it as a PNG file
            BufferedImage boardImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = boardImage.createGraphics();
            paint(g2d); // Draw the drawing panel onto the image
            g2d.dispose();
            File output = new File(fileName);
            ImageIO.write(boardImage, "png", output); // Save the image to file
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
}
