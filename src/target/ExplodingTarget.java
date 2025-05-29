package target;

import java.awt.*;

public class ExplodingTarget extends Target {
    private int explosionRadius = 0;
    private boolean exploded = false;

    public ExplodingTarget(int x, int y, int radius, int panelWidth, int panelHeight) {
        super(x, y, radius, panelWidth, panelHeight);
    }

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

    @Override
    public boolean contains(int mx, int my) {
        boolean hit = super.contains(mx, my);
        if (hit) exploded = true;
        return hit;
    }
}
