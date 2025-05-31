package target;

import java.awt.*;

/**
 * A target that moves in a straight direction and bounces off edges.
 */

public class MovingTarget extends Target {
    private int dx, dy;

    /**
     * Constructs a MovingTarget.
     */
    public MovingTarget(int x, int y, int radius, int panelWidth, int panelHeight) {
        super(x, y, radius, panelWidth, panelHeight);
        this.dx = 3;
        this.dy = 2;
    }

    /**
     * Updates the position of the target and handles bouncing.
     */
    @Override
    public void update() {
        x += dx;
        y += dy;

        if (x - radius < 0 || x + radius > panelWidth) dx *= -1;
        if (y - radius < 0 || y + radius > panelHeight) dy *= -1;
    }

    /**
     * Draws the target as a red circle.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }
}
