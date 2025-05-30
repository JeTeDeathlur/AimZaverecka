package target;

import java.awt.*;

public class ShrinkingTarget extends Target {
    private int shrinkSpeed = 1;
    private int minRadius = 5;

    public ShrinkingTarget(int x, int y, int radius, int panelWidth, int panelHeight) {
        super(x, y, radius, panelWidth, panelHeight);
    }

    @Override
    public void update() {
        radius -= shrinkSpeed;
        if (radius < minRadius) {
            radius = minRadius; 
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.PINK);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }
}

