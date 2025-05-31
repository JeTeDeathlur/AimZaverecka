package aimtrainer;
 
import javax.swing.*;
import panel.GamePanel;
import panel.StartMenuPanel;

/**
 * The main frame of the Aim Trainer application.
 * <p>
 * This class initializes the main window and allows switching between the
 * start menu and the game panel.
 * </p>
 */

public class AimTrainerFrame extends JFrame {

    /**
     * Constructs a new AimTrainerFrame.
     * <p>
     * Sets up the basic window properties such as title, size, and default close
     * operation. Initializes the content pane with the start menu.
     * </p>
     */

    public AimTrainerFrame() {
        setTitle("Aim Trainer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setContentPane(new StartMenuPanel(this));
        setVisible(true);
    }

    /**
     * Starts the game by switching the content pane to the GamePanel.
     *
     * @param nickname the player's nickname
     * @param gameMode the selected game mode
     * @param password the optional password for the game mode
     */
    public void startGame(String nickname, String gameMode, String password) {
        setContentPane(new GamePanel(this, nickname, gameMode, password));
        revalidate();
    }
    
}