import java.awt.*;
public class Target {
    private int x;
    private int y;
    private int radius;

    public Target(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public boolean contains(int mx, int my) {
        int dx = x - mx;
        int dy = y - my;
        return dx * dx + dy * dy <= radius * radius;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }
}

