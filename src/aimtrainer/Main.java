package aimtrainer;

import javax.swing.SwingUtilities;

/**
 * Entry point of the Aim Trainer application.
 */
public class Main {
    /**
     * Launches the application on the Event Dispatch Thread.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(AimTrainerFrame::new);
    }
}

