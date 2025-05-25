package target;

import java.awt.*;

public abstract class Target {
    protected int x, y, radius;

    public Target(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public abstract void update();
    public abstract void draw(Graphics g);

    public boolean contains(int mx, int my) {
        int dx = x - mx;
        int dy = y - my;
        return dx * dx + dy * dy <= radius * radius;
    }
}
