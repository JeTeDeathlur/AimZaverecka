package target;

import java.awt.*;

public class PulsingTarget extends Target {
    private int maxRadius;
    private int minRadius;
    private int pulseSpeed = 1;
    private boolean growing = true;

    public PulsingTarget(int x, int y, int radius, int panelWidth, int panelHeight) {
        super(x, y, radius, panelWidth, panelHeight);
        this.minRadius = radius / 2;
        this.maxRadius = radius * 2;
    }

    @Override
    public void update() {
        if (growing) {
            radius += pulseSpeed;
            if (radius >= maxRadius) {
                growing = false;
            }
        } else {
            radius -= pulseSpeed;
            if (radius <= minRadius) {
                growing = true;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }
}
