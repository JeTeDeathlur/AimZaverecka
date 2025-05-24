package panel;


import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import target.*;
import factory.*;

public class GamePanel extends JPanel {
    private int hits = 0;
    private int total = 0;
    private Target target;
    private Timer timer;
    private final String nickname;
    private final String gameMode;

    public GamePanel(String nickname, String gameMode) {
        this.nickname = nickname;
        this.gameMode = gameMode;
        setBackground(Color.BLACK);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (target != null && target.contains(e.getX(), e.getY())) {
                    hits++;
                }
                total++;
                generateNewTarget();
                repaint();
            }
        });

        startGameLoop();
    }

    private void startGameLoop() {
        int delay = getDelayForMode(gameMode);
        timer = new Timer(delay, e -> {
            generateNewTarget();
            repaint();
        });
        timer.start();
    }

    private int getDelayForMode(String mode) {
        switch (GameMode.valueOf(mode)) {
            case EASY: return 2000;
            case MEDIUM: return 1000;
            case HARD: return 700;
            default: return 1500;
        }
    }

    private void generateNewTarget() {
        target = TargetFactory.createTarget(GameMode.valueOf(gameMode), getWidth(), getHeight());
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
    }
}