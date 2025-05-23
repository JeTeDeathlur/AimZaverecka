package factory;

import aimtrainer.model.GameMode;
import aimtrainer.target.*;

import java.util.Random;

public class TargetFactory {
    private static final Random rand = new Random();

    public static Target createTarget(GameMode mode, int panelWidth, int panelHeight) {
        int radius = switch (mode) {
            case EASY -> 40;
            case MEDIUM -> 30;
            case HARD -> 20;
        };

        int x = rand.nextInt(panelWidth - 2 * radius) + radius;
        int y = rand.nextInt(panelHeight - 2 * radius) + radius;

        return switch (mode) {
            case EASY -> new BasicTarget(x, y, radius);
            case MEDIUM -> rand.nextBoolean() ?
                    new BasicTarget(x, y, radius) : new MovingTarget(x, y, radius);
            case HARD -> rand.nextBoolean() ?
                    new MovingTarget(x, y, radius) : new ShrinkingTarget(x, y, radius);
        };
    }
}
