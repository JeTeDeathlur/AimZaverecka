package target;

import java.awt.*;

public class MovingTarget extends Target {
    private int dx = 3;
    private int dy = 2;

    public MovingTarget(int x, int y, int radius) {
        super(x, y, radius);
    }

    @Override
    public void update() {
        x += dx;
        y += dy;
        if (x < radius || x > 800 - radius) dx *= -1;
        if (y < radius || y > 600 - radius) dy *= -1;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }
}
