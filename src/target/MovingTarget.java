package target;

import java.awt.*;

public class MovingTarget extends Target {
    private int dx, dy;

    public MovingTarget(int x, int y, int radius, int panelWidth, int panelHeight) {
        super(x, y, radius, panelWidth, panelHeight);
        this.dx = 3;
        this.dy = 2;
    }

    @Override
    public void update() {
        x += dx;
        y += dy;

        if (x - radius < 0 || x + radius > panelWidth) dx *= -1;
        if (y - radius < 0 || y + radius > panelHeight) dy *= -1;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }
}
