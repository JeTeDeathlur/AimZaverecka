package target;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A special type of target that simulates an explosion when hit.
 * Upon being clicked for the first time, it visually explodes and spawns
 * two new BasicTarget instances. It only explodes once and cannot generate
 * more children thereafter.
 */
public class ExplodingTarget extends Target {
    private int explosionRadius = 0;
    private boolean exploded = false;
    private boolean hasExploded = false;

    /**
     * Constructs an ExplodingTarget with the given properties.
     *
     * @param x           the x-coordinate of the target center
     * @param y           the y-coordinate of the target center
     * @param radius      the radius of the target
     * @param panelWidth  the width of the game panel
     * @param panelHeight the height of the game panel
     */
    public ExplodingTarget(int x, int y, int radius, int panelWidth, int panelHeight) {
        super(x, y, radius, panelWidth, panelHeight);
    }

    /**
     * Updates the state of the explosion animation.
     * If the target is exploding, it gradually increases the explosion radius
     * and resets after reaching a maximum size.
     */
    @Override
    public void update() {
        if (exploded) {
            explosionRadius += 4;
            if (explosionRadius > radius * 3) {
                explosionRadius = 0;
                exploded = false;
            }
        }
    }


    /**
     * Draws the target on the screen.
     * If the target is exploding, it renders a semi-transparent explosion effect.
     *
     * @param g the graphics context used for drawing
     */
    @Override
    public void draw(Graphics g) {
        if (!exploded) {
            g.setColor(Color.ORANGE);
            g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        } else {
            g.setColor(new Color(255, 100, 0, 128));
            g.fillOval(x - explosionRadius, y - explosionRadius, explosionRadius * 2, explosionRadius * 2);
        }
    }


    /**
     * Checks if the target was clicked and triggers explosion if it hasn't exploded yet.
     *
     * @param mx the x-coordinate of the mouse click
     * @param my the y-coordinate of the mouse click
     * @return true if the target was hit, false otherwise
     */
    @Override
    public boolean contains(int mx, int my) {
        boolean hit = super.contains(mx, my);
        if (hit && !hasExploded) {
            exploded = true;
            hasExploded = true;
        }
        return hit;
    }

    /**
     * Indicates whether the target has already exploded and is ready to spawn children.
     *
     * @return true if explosion occurred and children can be spawned, false otherwise
     */
    public boolean isReadyToSpawn() {
        return hasExploded;
    }

    /**
     * Creates two smaller BasicTarget instances as children of the exploded target.
     *
     * @return a list containing two new BasicTarget instances
     */
    public List<Target> spawnChildren() {
        List<Target> children = new ArrayList<>();
        int childRadius = radius / 2;

        children.add(new BasicTarget(x - radius, y - radius, childRadius, panelWidth, panelHeight));
        children.add(new BasicTarget(x + radius, y + radius, childRadius, panelWidth, panelHeight));

        return children;
    }

}
