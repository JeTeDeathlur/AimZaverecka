package target;

import java.awt.*;

public class BasicTarget extends Target {
    public BasicTarget(int x, int y, int radius) {
        super(x, y, radius);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }
}
