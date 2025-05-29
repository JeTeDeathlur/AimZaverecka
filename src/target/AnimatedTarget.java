package target;


import java.util.Random;

public abstract class AnimatedTarget extends Target {
    protected Random random = new Random();

    public AnimatedTarget(int x, int y, int radius, int panelWidth, int panelHeight) {
        super(x, y, radius, panelWidth, panelHeight);
    }

    public abstract void update();
}
