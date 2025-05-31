package panel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
/**
 * Panel displayed after the game ends.
 * Shows the player's performance and allows them to restart or return to the main menu.
 */
public class EndScreenPanel extends JPanel {
    private final JLabel scoreLabel;
    private final JButton playAgainButton;
    private final JButton backToMenuButton;

    /**
     * Constructs the end screen panel and updates the player's total score.
     *
     * @param nickname the player's nickname
     * @param score the score earned in the current game
     * @param hits number of successful hits
     * @param misses number of missed shots
     * @param accuracy hit accuracy in percentage
     */
    public EndScreenPanel(String nickname, int score, int hits, int misses, double accuracy) {
        updateScore(nickname, score);

        String totalScore = getExistingScore(nickname);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Game Over");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        scoreLabel = new JLabel("<html><div align='center'>" +
                "<b><span style='color:green;'>Total Score: " + totalScore + "</span></b>" +
                "<br><br>Score This Game: " + score +
                "<br>Hits: " + hits +
                "<br>Misses: " + misses +
                "<br>Accuracy: " + String.format("%.2f", accuracy) + "%</div></html>");

        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        scoreLabel.setForeground(Color.RED);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);

        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        Dimension buttonSize = new Dimension(200, 40);

        playAgainButton = new JButton("Play Again");
        playAgainButton.setFont(buttonFont);
        playAgainButton.setMaximumSize(buttonSize);
        playAgainButton.setPreferredSize(buttonSize);
        playAgainButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        backToMenuButton = new JButton("Main Screen");
        backToMenuButton.setFont(buttonFont);
        backToMenuButton.setMaximumSize(buttonSize);
        backToMenuButton.setPreferredSize(buttonSize);
        backToMenuButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalGlue());
        add(titleLabel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(scoreLabel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(playAgainButton);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(backToMenuButton);
        add(Box.createVerticalGlue());
    }

    /**
     * Adds a listener to the "Play Again" button.
     *
     * @param listener action listener to trigger a new game
     */
    public void addPlayAgainListener(ActionListener listener) {
        playAgainButton.addActionListener(listener);
    }

    /**
     * Adds a listener to the "Main Screen" button.
     *
     * @param listener action listener to return to main menu
     */
    public void addBackToMenuListener(ActionListener listener) {
        backToMenuButton.addActionListener(listener);
    }
    /**
     * Retrieves the player's existing total score from the file.
     *
     * @param username the player's nickname
     * @return the saved score as a string (defaults to "0" if not found)
     */
    private String getExistingScore(String username) {
        String score = "0";
        File file = new File("Registrations.csv");
        if (!file.exists()) return score;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length >= 3 && parts[0].equals(username)) {
                    score = parts[2];
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
        return score;
    }

    /**
     * Updates the player's total score by adding new points and saving the result back to file.
     *
     * @param username the player's nickname
     * @param scoreToAdd score to add to the existing total
     */
    private void updateScore(String username, int scoreToAdd) {
        File file = new File("Registrations.csv");
        File tempFile = new File("Registrations_temp.csv");

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    if (parts[0].equals(username)) {
                        int existingScore = parts.length == 3 ? Integer.parseInt(parts[2]) : 0;
                        int newScore = existingScore + scoreToAdd;
                        writer.write(parts[0] + "," + parts[1] + "," + newScore);
                    } else {
                        writer.write(line);
                    }
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error updating score: " + e.getMessage());
        }

        if (!file.delete() || !tempFile.renameTo(file)) {
            System.err.println("Error replacing original score file.");
        }
    }
}
