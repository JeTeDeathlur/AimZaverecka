package panel;

import aimtrainer.AimTrainerFrame;
import factory.TargetFactory;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import model.GameMode;
import target.ExplodingTarget;
import target.Target;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;



/**
 * GamePanel is the main gameplay component of the Aim Trainer application.
 * It manages target generation, mouse interactions, time control, scoring logic,
 * and transitions to the end screen upon game completion.
 *
 * It supports multiple active targets, including special types such as
 * ExplodingTarget, which splits into two smaller targets upon being hit.
 */
public class GamePanel extends JPanel {

    //for test
    public int getHits() { return hits; }
    public int getMisses() { return total - hits; }
    public void incrementHits() { hits++; }
    public int calculateScore(String mode) { return setScore(mode); }

    //for test^^^
    private int hits = 0;
    private int total = 0;
    private int totalScore = 0;
    private List<Target> activeTargets = new ArrayList<>();

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
     * Constructs a new GamePanel for a given player and game mode.
     *
     * @param parentFrame the parent JFrame to which this panel belongs
     * @param nickname    the player's nickname
     * @param gameMode    the selected game difficulty ("EASY", "MEDIUM", "HARD")
     * @param password    the player's password (optional)
     */
    public GamePanel(JFrame parentFrame, String nickname, String gameMode, String password) {
        this.parentFrame = parentFrame;
        this.nickname = nickname;
        this.gameMode = gameMode;
        this.password = password;

        loadBackgroundImage();

        addMouseListener(new MouseAdapter() {
            /**
             * Mouse interaction handler that detects hits on active targets.
             * - Increases score and time for hits
             * - Decreases time for misses
             * - If an ExplodingTarget is hit, two new targets are added
             */


            @Override
            public void mousePressed(MouseEvent e) {
                if (gameOver) return;

                boolean hit = false;
                List<Target> newTargets = new ArrayList<>();

                Iterator<Target> iterator = activeTargets.iterator();
                while (iterator.hasNext()) {
                    Target t = iterator.next();
                    if (t.contains(e.getX(), e.getY())) {
                        hit = true;
                        hits++;
                        timeLeft += 500;
                        iterator.remove();

                        if (t instanceof ExplodingTarget exploding && exploding.isReadyToSpawn()) {
                            newTargets.addAll(exploding.spawnChildren());
                        }
                        break;
                    }
                }

                if (!hit) {
                    timeLeft -= 3000;
                }


                if (activeTargets.isEmpty()) {
                    activeTargets.add(TargetFactory.createTarget(GameMode.valueOf(gameMode), getWidth(), getHeight()));
                }

                activeTargets.addAll(newTargets);

                total++;
                repaint();
            }


        });

        startGameLoop();
    }



    /**
     * Loads the background image appropriate to the selected game mode.
     * Falls back to a black background if the image cannot be loaded.
     */

    private void loadBackgroundImage() {
        String path = switch (gameMode) {
            case "EASY" -> "/Pictures/easy.png";
            case "MEDIUM" -> "/Pictures/medium.png";
            case "HARD" -> "/Pictures/hard.png";
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
     * Initializes the main game loop, including:
     * - A high-frequency timer for updating targets (movement, animation, etc.)
     * - A countdown timer to track remaining game time
     * - The first target generation
     */

    private void startGameLoop() {
        spawnTimer = new Timer(16, e -> {
            for (Target t : activeTargets) {
                t.update();
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
     * Generates a new target based on the current game mode.
     * Clears any previous active targets before adding a new one.
     */

    private void generateNewTarget() {
        if (getWidth() == 0 || getHeight() == 0) {
            SwingUtilities.invokeLater(this::generateNewTarget);
            return;
        }

        GameMode mode = GameMode.valueOf(gameMode);
        activeTargets.clear();
        activeTargets.add(TargetFactory.createTarget(mode, getWidth(), getHeight()));
    }


    /**
     * Displays the end screen panel with game statistics and options
     * to return to the main menu or restart the game.
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
     * Renders the background, active targets, and player statistics.
     *
     * @param g the Graphics context used to draw the UI
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

        for (Target t : activeTargets) {
            t.draw(g);
        }

        drawStats(g);
    }

    /**
     * Draws the in-game statistics (nickname, hit count, total shots,
     * remaining time, and calculated score).
     *
     * @param g the Graphics context used to render the stats
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
     * Calculates the player's score based on the number of hits and
     * the selected game difficulty.
     *
     * @param mode the difficulty level as a string
     * @return the total calculated score
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
