package panel;

import aimtrainer.AimTrainerFrame;
import factory.TargetFactory;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import model.GameMode;
import target.Target;

/**
 * The main game panel where the user plays the aim trainer game.
 * Handles target generation, scoring, time tracking, and input events.
 */
public class GamePanel extends JPanel {
    private int hits = 0;
    private int total = 0;
    private int totalScore = 0;
    private Target target;
    private Timer spawnTimer;
    private Timer countdownTimer;
    private int timeLeft = 10_000;

    private boolean gameOver = false;

    private final String nickname;
    private final String gameMode;
    private final String password;
    private final JFrame parentFrame;

    private Image backgroundImage;

    /**
     * Constructs the game panel for a given player and mode.
     *
     * @param parentFrame the parent JFrame
     * @param nickname player's nickname
     * @param gameMode difficulty level selected
     * @param password player's password
     */
    public GamePanel(JFrame parentFrame, String nickname, String gameMode, String password) {
        this.parentFrame = parentFrame;
        this.nickname = nickname;
        this.gameMode = gameMode;
        this.password = password;

        loadBackgroundImage();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (gameOver) return;

                if (target != null && target.contains(e.getX(), e.getY())) {
                    hits++;
                    timeLeft += 500;
                } else {
                    timeLeft -= 3000;
                }
                total++;
                generateNewTarget();
                repaint();
            }
        });

        startGameLoop();
    }
    /**
     * Loads the background image based on the selected game mode.
     */
    private void loadBackgroundImage() {
        String path = switch (gameMode) {
            case "EASY" -> "/Pictures/easy.jpg";
            case "MEDIUM" -> "/Pictures/medium.jpg";
            case "HARD" -> "/Pictures/hard.jpg";
            default -> null;
        };

        if (path != null) {
            try {
                backgroundImage = new ImageIcon(getClass().getResource(path)).getImage();
            } catch (Exception e) {
                System.err.println("Background image not found: " + path);
                backgroundImage = null;
            }
        }
    }
    /**
     * Starts the game loop including the target update and countdown timers.
     */
    private void startGameLoop() {
        spawnTimer = new Timer(16, e -> {
            if (target != null) {
                target.update();
            }
            repaint();
        });

        spawnTimer.start();

        countdownTimer = new Timer(10, e -> {
            timeLeft -= 10;
            if (timeLeft <= 0) {
                spawnTimer.stop();
                countdownTimer.stop();
                showEndScreen();
            }
            repaint();
        });
        countdownTimer.start();

        generateNewTarget();
    }
    /**
     * Generates a new target based on the selected difficulty.
     */
    private void generateNewTarget() {
        if (getWidth() == 0 || getHeight() == 0) {
            SwingUtilities.invokeLater(this::generateNewTarget);
            return;
        }

        GameMode mode = GameMode.valueOf(gameMode);
        target = TargetFactory.createTarget(mode, getWidth(), getHeight());
    }

    /**
     * Ends the game and transitions to the end screen panel.
     */
    private void showEndScreen() {
        gameOver = true;

        double accuracy = total > 0 ? (100.0 * hits / total) : 0.0;

        EndScreenPanel endScreen = new EndScreenPanel(nickname, this.totalScore, hits, total - hits, accuracy);

        endScreen.addPlayAgainListener(e -> {
            parentFrame.setContentPane(new GamePanel(parentFrame, nickname, gameMode, password));
            parentFrame.revalidate();
            parentFrame.repaint();
        });

        endScreen.addBackToMenuListener(e -> {
            parentFrame.setContentPane(new StartMenuPanel((AimTrainerFrame) parentFrame));
            parentFrame.revalidate();
            parentFrame.repaint();
        });

        parentFrame.setContentPane(endScreen);
        parentFrame.revalidate();
        parentFrame.repaint();
    }


    /**
     * Custom rendering of the game panel, including background and stats.
     *
     * @param g the graphics context
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());
        }

        if (target != null) {
            target.draw(g);
        }
        drawStats(g);
    }

    /**
     * Draws the game stats (nickname, hits, total shots, time, score).
     *
     * @param g the graphics context
     */
    private void drawStats(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Nickname: " + nickname, 10, 20);
        g.drawString("Hits: " + hits, 10, 45);
        g.drawString("Total: " + total, 10, 70);
        g.drawString("Time: " + timeLeft / 1000.0 + "s", 10, 95);
        this.totalScore = setScore(gameMode);
        g.drawString("Score: " + this.totalScore, 10, 120);
    }
    /**
     * Calculates the score based on the difficulty mode.
     *
     * @param mode the difficulty level as string
     * @return score multiplier based on hits
     */
    private int setScore(String mode) {
        return switch (mode) {
            case "EASY" -> this.hits * 1;
            case "MEDIUM" -> this.hits * 2;
            case "HARD" -> this.hits * 3;
            default -> 0;
        };
    }
}
