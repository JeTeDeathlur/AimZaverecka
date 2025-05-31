package target;

import java.awt.*;
/**
 * A target that explodes with a growing visual effect when hit.
 */

public class ExplodingTarget extends Target {
    private int explosionRadius = 0;
    private boolean exploded = false;

    /**
     * Constructs an ExplodingTarget.
     */
    public ExplodingTarget(int x, int y, int radius, int panelWidth, int panelHeight) {
        super(x, y, radius, panelWidth, panelHeight);
    }

    /**
     * Updates the explosion effect if the target has exploded.
     */
    @Override
    public void update() {
        if (exploded) {
            explosionRadius += 4;
            if (explosionRadius > radius * 3) {
                explosionRadius = 0;
                exploded = false;
            }
        }
    }


    /**
     * Draws the target or its explosion effect.
     */
    @Override
    public void draw(Graphics g) {
        if (!exploded) {
            g.setColor(Color.ORANGE);
            g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        } else {
            g.setColor(new Color(255, 100, 0, 128));
            g.fillOval(x - explosionRadius, y - explosionRadius, explosionRadius * 2, explosionRadius * 2);
        }
    }
    /**
     * Triggers explosion when the target is hit.
     */
    @Override
    public boolean contains(int mx, int my) {
        boolean hit = super.contains(mx, my);
        if (hit) exploded = true;
        return hit;
    }
}
