package test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import panel.StartMenuPanel;
import static org.junit.jupiter.api.Assertions.*;

public class StartMenuPanelTest {

    private StartMenuPanel panel;

    @BeforeEach
    void setup() {
        panel = new StartMenuPanel(null);
    }

    @Test
    void loginCheckReturnsTrueForExistingUser() {
        panel.saveToFile("testuser,password123");
        assertTrue(panel.loginCheck("testuser"));
    }

    @Test
    void loginCheckReturnsFalseForNonExistingUser() {
        assertFalse(panel.loginCheck("ghost_user"));
    }
}
