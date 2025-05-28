package aimtrainer;
 
import javax.swing.*;
import panel.GamePanel;
import panel.StartMenuPanel;

public class AimTrainerFrame extends JFrame {
    public AimTrainerFrame() {
        setTitle("Aim Trainer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setContentPane(new StartMenuPanel(this));
        setVisible(true);
    }
 
    public void startGame(String nickname, String gameMode,String password) {
        setContentPane(new GamePanel(nickname, gameMode, password ));
        revalidate();
    }
}