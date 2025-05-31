package test;


import org.junit.jupiter.api.Test;
import target.ExplodingTarget;
import target.Target;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExplodingTargetTest {

    @Test
    void triggersExplosionOnHit() {
        ExplodingTarget t = new ExplodingTarget(100, 100, 30, 500, 500);
        assertTrue(t.contains(100, 100));
        assertTrue(t.isReadyToSpawn());
    }

    @Test
    void spawnsTwoChildrenAfterExplosion() {
        ExplodingTarget t = new ExplodingTarget(100, 100, 30, 500, 500);
        t.contains(100, 100);
        List<Target> children = t.spawnChildren();
        assertEquals(2, children.size());
    }
}
