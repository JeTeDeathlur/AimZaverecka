package target;

import java.awt.*;
/**
 * A target that fades in and out repeatedly.
 */

public class FadingTarget extends Target {
    private float alpha = 1.0f;
    private float fadeSpeed = 0.02f;
    private boolean fadingOut = true;

    /**
     * Constructs a FadingTarget.
     */
    public FadingTarget(int x, int y, int radius, int panelWidth, int panelHeight) {
        super(x, y, radius, panelWidth, panelHeight);
    }

    /**
     * Updates the opacity (alpha) of the target.
     */
    @Override
    public void update() {
        if (fadingOut) {
            alpha -= fadeSpeed;
            if (alpha <= 0.1f) {
                fadingOut = false;
            }
        } else {
            alpha += fadeSpeed;
            if (alpha >= 1.0f) {
                fadingOut = true;
            }
        }
    }


    /**
     * Draws the target with transparency based on alpha.
     */
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.setColor(Color.GREEN);
        g2d.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        g2d.dispose();
    }
}

