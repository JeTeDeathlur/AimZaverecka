package panel;

import model.GameMode;
import util.RegexValidator;
import aimtrainer.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StartMenuPanel extends JPanel {
    private final AimTrainerFrame frame;

    public StartMenuPanel(AimTrainerFrame frame) {
        this.frame = frame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("Aim Trainer", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        add(new JLabel("Enter nickname:"), gbc);

        gbc.gridx = 1;
        JTextField nicknameField = new JTextField(15);
        add(nicknameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        add(new JLabel("Select difficulty:"), gbc);

        gbc.gridx = 1;
        JComboBox<GameMode> difficultyBox = new JComboBox<>(GameMode.values());
        add(difficultyBox, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JButton startButton = new JButton("Start Game");
        add(startButton, gbc);

        startButton.addActionListener((ActionEvent e) -> {
            String nickname = nicknameField.getText();
            if (!RegexValidator.isValidNickname(nickname)) {
                JOptionPane.showMessageDialog(this, "Invalid nickname! (3-15 letters/numbers/underscores)");
                return;
            }

            GameMode selectedMode = (GameMode) difficultyBox.getSelectedItem();
            frame.startGame(nickname, selectedMode.name());
        });
    }
}
