package test;

import factory.TargetFactory;
import model.GameMode;
import org.junit.jupiter.api.Test;
import target.Target;

import static org.junit.jupiter.api.Assertions.*;

public class TargetFactoryTest {

    @Test
    void createdTargetIsNotNullForAllModes() {
        for (GameMode mode : GameMode.values()) {
            Target t = TargetFactory.createTarget(mode, 800, 600);
            assertNotNull(t, "Target should not be null for mode: " + mode);
        }
    }

    @Test
    void targetPositionIsWithinBounds() {
        Target target = TargetFactory.createTarget(GameMode.HARD, 400, 400);
        assertTrue(target.getX() >= 0 && target.getX() <= 400);
        assertTrue(target.getY() >= 0 && target.getY() <= 400);
    }
}
