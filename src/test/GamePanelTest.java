package test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import panel.GamePanel;
import javax.swing.*;
import static org.junit.jupiter.api.Assertions.*;

public class GamePanelTest {

    private GamePanel panel;

    @BeforeEach
    void setup() {
        panel = new GamePanel(new JFrame(), "tester", "EASY", "pass");
        panel.setSize(800, 600);
    }

    @Test
    void scoreShouldIncreaseOnHit() {
        panel.setVisible(true);
        panel.requestFocus();
        panel.dispatchEvent(new java.awt.event.MouseEvent(
                panel, java.awt.event.MouseEvent.MOUSE_PRESSED,
                System.currentTimeMillis(), 0,
                panel.getWidth() / 2, panel.getHeight() / 2, 1, false
        ));


        assertTrue(panel.getHits() + panel.getMisses() > 0);
    }

    @Test
    void scoreCalculationMatchesDifficulty() {
        for (int i = 0; i < 5; i++) {
            panel.incrementHits();
        }
        int score = panel.calculateScore("MEDIUM");
        assertEquals(5 * 2, score);
    }
}
