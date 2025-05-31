package target;

import java.awt.*;
/**
 * A simple static target that does not change over time.
 */
public class BasicTarget extends Target {
    /**
     * Constructs a BasicTarget with given position, radius and panel size.
     */
    public BasicTarget(int x, int y, int radius, int panelWidth, int panelHeight) {
        super(x, y, radius, panelWidth, panelHeight);
    }

    /**
     * No update behavior; the target remains static.
     */
    @Override
    public void update() {}

    /**
     * Draws the target as a white circle.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }
}
