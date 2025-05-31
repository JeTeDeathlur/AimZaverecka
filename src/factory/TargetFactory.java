package factory;

import java.util.Random;
import model.GameMode;
import target.*;

/**
 * Factory class responsible for creating different types of targets
 * based on the selected game mode.
 */

public class TargetFactory {
    private static final Random rand = new Random();

    /**
     * Creates and returns a randomly selected target instance
     * depending on the game mode.
     *
     * @param mode the selected game mode (EASY, MEDIUM, HARD)
     * @param panelWidth the width of the panel where the target will be placed
     * @param panelHeight the height of the panel where the target will be placed
     * @return a new Target instance appropriate for the selected difficulty
     */

    public static Target createTarget(GameMode mode, int panelWidth, int panelHeight) {
        int radius = switch (mode) {
            case EASY -> 40;
            case MEDIUM -> 30;
            case HARD -> 20;
        };

        int maxX = Math.max(1, panelWidth - 2 * radius);
        int maxY = Math.max(1, panelHeight - 2 * radius);
        int x = rand.nextInt(maxX) + radius;
        int y = rand.nextInt(maxY) + radius;

        return switch (mode) {
            case EASY -> switch (rand.nextInt(3)) {
                case 0 -> new BasicTarget(x, y, radius, panelWidth, panelHeight);
                case 1 -> new MovingTarget(x, y, radius, panelWidth, panelHeight);
                default -> new PulsingTarget(x, y, radius, panelWidth, panelHeight);
            };
            case MEDIUM -> switch (rand.nextInt(4)) {
                case 0 -> new MovingTarget(x, y, radius, panelWidth, panelHeight);
                case 1 -> new ShrinkingTarget(x, y, radius, panelWidth, panelHeight);
                case 2 -> new FadingTarget(x, y, radius, panelWidth, panelHeight);
                default -> new ExplodingTarget(x, y, radius, panelWidth, panelHeight);
            };
            case HARD -> switch (rand.nextInt(4)) {
                case 0 -> new ShrinkingTarget(x, y, radius, panelWidth, panelHeight);
                case 1 -> new ExplodingTarget(x, y, radius, panelWidth, panelHeight);
                case 2 -> new TeleportingTarget(x, y, radius, panelWidth, panelHeight);
                default -> new ZigZagTarget(x, y, radius, panelWidth, panelHeight);
            };
        };
    }
}
