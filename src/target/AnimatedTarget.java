package target;


import java.util.Random;

/**
 * Abstract base class for targets that have animated behavior.
 * Provides a random number generator for subclasses to use.
 */
public abstract class AnimatedTarget extends Target {
    protected Random random = new Random();

    /**
     * Constructs an animated target.
     *
     * @param x the x-coordinate of the target's center
     * @param y the y-coordinate of the target's center
     * @param radius the radius of the target
     * @param panelWidth width of the panel containing the target
     * @param panelHeight height of the panel containing the target
     */
    public AnimatedTarget(int x, int y, int radius, int panelWidth, int panelHeight) {
        super(x, y, radius, panelWidth, panelHeight);
    }
    /**
     * Updates the target's animation state.
     * Called periodically in the game loop.
     */
    public abstract void update();
}
