package panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import target.*;

public class GamePanel extends JPanel {
    private Target target;
    private int hits = 0;
    private int total = 0;
    private long startTime;
    private Timer timer;

    public GamePanel(String nickname, String gameMode) {
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
    }



    public void startGame() {
        startTime = System.currentTimeMillis();
        generateNewTarget();
        timer = new Timer(1000 * 30, e -> endGame());
        timer.setRepeats(false);
        timer.start();
    }

    private void generateNewTarget() {
        Random rand = new Random();
        int radius = 30;
        int x = rand.nextInt(getWidth() - radius * 2) + radius;
        int y = rand.nextInt(getHeight() - radius * 2) + radius;
        target = new Target(x, y, radius) {
            @Override
            public void update() {
                
            }

            @Override
            public void draw(Graphics g) {

            }
        };
    }

    private void endGame() {
        repaint();
        JOptionPane.showMessageDialog(this, "Time's up!\nHits: " + hits + "/" + total +
                "\nAccuracy: " + (total > 0 ? (hits * 100 / total) : 0) + "%");
        System.exit(0);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (target != null) {
            target.draw(g);
        }
        g.setColor(Color.PINK);
        g.drawString("Hits: " + hits, 10, 20);
        g.drawString("Total: " + total, 10, 40);
    }
}
