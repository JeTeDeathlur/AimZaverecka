package target;

import java.awt.*;
/**
 * A target that gradually shrinks over time.
 */
public class ShrinkingTarget extends Target {
    private int shrinkSpeed = 1;
    private int minRadius = 5;

    /**
     * Constructs a ShrinkingTarget.
     */
    public ShrinkingTarget(int x, int y, int radius, int panelWidth, int panelHeight) {
        super(x, y, radius, panelWidth, panelHeight);
    }

    /**
     * Updates the radius of the target to simulate shrinking.
     */
    @Override
    public void update() {
        radius -= shrinkSpeed;
        if (radius < minRadius) {
            radius = minRadius; 
        }
    }

    /**
     * Draws the shrinking target.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.PINK);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }
}

