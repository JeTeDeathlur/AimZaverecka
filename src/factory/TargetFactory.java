package factory;

import model.GameMode;
import target.*;
import java.util.Random;

public class TargetFactory {
    private static final Random rand = new Random();

    public static Target createTarget(GameMode mode, int width, int height) {
        int radius = switch (mode) {
            case EASY -> 40;
            case MEDIUM -> 30;
            case HARD -> 20;
        };

        int maxX = Math.max(1, width - 2 * radius);
        int maxY = Math.max(1, height - 2 * radius);

        int x = rand.nextInt(maxX) + radius;
        int y = rand.nextInt(maxY) + radius;

        return switch (mode) {
            case EASY -> new BasicTarget(x, y, radius);
            case MEDIUM -> rand.nextBoolean()
                    ? new BasicTarget(x, y, radius)
                    : new MovingTarget(x, y, radius);
            case HARD -> rand.nextBoolean()
                    ? new MovingTarget(x, y, radius)
                    : new ShrinkingTarget(x, y, radius);
        };
    }
}
