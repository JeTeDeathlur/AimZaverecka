package panel;

import aimtrainer.AimTrainerFrame;
import factory.TargetFactory;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import model.GameMode;
import target.Target;

public class GamePanel extends JPanel {
    private int hits = 0;
    private int total = 0;
    private Target target;
    private Timer spawnTimer;
    private Timer countdownTimer;
    private int timeLeft = 10_000;

    private final String nickname;
    private final String gameMode;
    private final String password;
    private final JFrame parentFrame;

    public GamePanel(JFrame parentFrame, String nickname, String gameMode, String password) {
        this.parentFrame = parentFrame;
        this.nickname = nickname;
        this.gameMode = gameMode;
        this.password = password;

        setBackground(Color.BLACK);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
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

    private void generateNewTarget() {
        target = TargetFactory.createTarget(GameMode.valueOf(gameMode), getWidth(), getHeight());
    }

    private void showEndScreen() {
        double accuracy = total > 0 ? (100.0 * hits / total) : 0.0;

        EndScreenPanel endScreen = new EndScreenPanel(hits, hits, total - hits, accuracy);

        endScreen.addPlayAgainListener(e -> {
            System.out.println(e);
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (target != null) {
            target.draw(g);
        }
        drawStats(g);
    }

    private void drawStats(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Nickname: " + nickname, 10, 20);
        g.drawString("Hits: " + hits, 10, 45);
        g.drawString("Total: " + total, 10, 70);
        g.drawString("Time: " + timeLeft / 1000.0 + "s", 10, 95);
    }
}
