import javax.swing.*;
import java.awt.*;

public class AimTrainerFrame extends JFrame {
    public AimTrainerFrame() {
        setTitle("Aim Trainer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(800, 600);
        setLocationRelativeTo(null);

        GamePanel gamePanel = new GamePanel();
        add(gamePanel);
        setVisible(true);

        gamePanel.startGame();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AimTrainerFrame::new);
    }
}
