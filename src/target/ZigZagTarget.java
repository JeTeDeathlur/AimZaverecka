package target;

import java.awt.*;

public class ZigZagTarget extends Target {
    private final int panelWidth;
    private final int panelHeight;
    private int dx = 4;
    private int dy = 2;
    private int counter = 0;

    public ZigZagTarget(int x, int y, int radius, int panelWidth, int panelHeight) {
        super(x, y, radius, panelWidth, panelHeight);
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
    }

    @Override
    public void update() {
        x += dx;
        y += dy;
        counter++;
        if (counter % 15 == 0) {
            dx = -dx; 
        }

        if (x < radius || x > panelWidth - radius) dx = -dx;
        if (y < radius || y > panelHeight - radius) dy = -dy;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }
}
