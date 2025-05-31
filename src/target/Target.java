package target;

import java.awt.*;


/**
 * Abstract base class for all types of targets.
 */
public abstract class Target {
    protected int x, y, radius;
    protected int panelWidth, panelHeight;

    //for test
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }
    //for tests^


    /**
     * Constructs a Target with position, radius and panel bounds.
     */
    public Target(int x, int y, int radius, int panelWidth, int panelHeight) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
    }


    /**
     * Updates the target's state.
     */
    public abstract void update();
    /**
     * Renders the target on the screen.
     */
    public abstract void draw(Graphics g);

    /**
     * Checks if a point (mouse click) is inside the target.
     */

    public boolean contains(int mx, int my) {
        int dx = x - mx;
        int dy = y - my;
        return dx * dx + dy * dy <= radius * radius;
    }
}
