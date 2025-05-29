package target;

import java.awt.*;
import java.util.Random;

public class TeleportingTarget extends Target {
    private int teleportCooldown = 60; 
    private int tick = 0;
    private Random rand = new Random();

    public TeleportingTarget(int x, int y, int radius, int panelWidth, int panelHeight) {
        super(x, y, radius, panelWidth, panelHeight);
    }

    @Override
    public void update() {
        tick++;
        if (tick >= teleportCooldown) {
            x = rand.nextInt(panelWidth - 2 * radius) + radius;
            y = rand.nextInt(panelHeight - 2 * radius) + radius;
            tick = 0;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }
}
