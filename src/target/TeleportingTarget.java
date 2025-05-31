package target;

import java.awt.*;
import java.util.Random;

/**
 * A target that randomly teleports after a set cooldown period.
 */
public class TeleportingTarget extends Target {
    private int teleportCooldown = 30; 
    private int tick = 0;
    private Random rand = new Random();

    /**
     * Constructs a TeleportingTarget.
     */
    public TeleportingTarget(int x, int y, int radius, int panelWidth, int panelHeight) {
        super(x, y, radius, panelWidth, panelHeight);
    }

    /**
     * Teleports the target to a new random location after cooldown.
     */
    @Override
    public void update() {
        tick++;
        if (tick >= teleportCooldown) {
            x = rand.nextInt(panelWidth - 2 * radius) + radius;
            y = rand.nextInt(panelHeight - 2 * radius) + radius;
            tick = 0;
        }
    }

    /**
     * Draws the teleporting target.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }
}
