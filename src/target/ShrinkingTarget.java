package target;

import java.awt.*;

public class ShrinkingTarget extends Target {
    public ShrinkingTarget(int x, int y, int radius) {
        super(x, y, radius);
    }

    @Override
    public void update() {
        if (radius > 10) radius--;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }
}

