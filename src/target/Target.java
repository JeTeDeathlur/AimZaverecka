package target;

import java.awt.*;

public abstract class Target {
    protected int x, y, radius;
    protected int panelWidth, panelHeight;

    public Target(int x, int y, int radius, int panelWidth, int panelHeight) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
    }

    public abstract void update();
    public abstract void draw(Graphics g);

    public boolean contains(int mx, int my) {
        int dx = x - mx;
        int dy = y - my;
        return dx * dx + dy * dy <= radius * radius;
    }
}
