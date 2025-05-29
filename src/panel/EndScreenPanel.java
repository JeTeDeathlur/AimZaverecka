package panel;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;


public class EndScreenPanel extends JPanel {
    private final JLabel scoreLabel;
    private final JButton playAgainButton;
    private final JButton backToMenuButton;

    public EndScreenPanel(int score, int hits, int misses, double accuracy) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.DARK_GRAY);

        JLabel titleLabel = new JLabel("Game Over");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        scoreLabel = new JLabel("<html>Score: " + score +
                "<br>Hits: " + hits +
                "<br>Misses: " + misses +
                "<br>Accuracy: " + String.format("%.2f", accuracy) + "%</html>");
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        playAgainButton = new JButton("Play Again");
        playAgainButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        backToMenuButton = new JButton("Back to main screen");
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

    public void addPlayAgainListener(ActionListener listener) {
        playAgainButton.addActionListener(listener);
    }

    public void addBackToMenuListener(ActionListener listener) {
        backToMenuButton.addActionListener(listener);
    }
}