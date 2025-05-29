package panel;
 
import aimtrainer.AimTrainerFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import model.GameMode;
import util.RegexValidator;
 
public class StartMenuPanel extends JPanel {
    private final AimTrainerFrame frame;
    private final Map<String, String> credentials = new HashMap<>();
 
    public StartMenuPanel(AimTrainerFrame frame) {
        this.frame = frame;
        loadCredentials();
 
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
 
        JLabel titleLabel = new JLabel("Aim Trainer", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(titleLabel, gbc);
 
        gbc.gridwidth = 1; gbc.gridy++;
        add(new JLabel("Enter nickname:"), gbc);
 
        gbc.gridx = 1;
        JTextField nicknameField = new JTextField(15);
        add(nicknameField, gbc);
 
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Enter password:"), gbc);
 
        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(15);
        add(passwordField, gbc);
 
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Mode:"), gbc);
 
        gbc.gridx = 1;
        JPanel modePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        JRadioButton loginButton = new JRadioButton("Login", true);
        JRadioButton signupButton = new JRadioButton("Sign Up");
        ButtonGroup modeGroup = new ButtonGroup();
        modeGroup.add(loginButton);
        modeGroup.add(signupButton);
        modePanel.add(loginButton);
        modePanel.add(signupButton);
        add(modePanel, gbc);
 
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Select difficulty:"), gbc);
 
        gbc.gridx = 1;
        JComboBox<GameMode> difficultyBox = new JComboBox<>(GameMode.values());
        add(difficultyBox, gbc);
 
        gbc.gridy++; gbc.gridx = 0; gbc.gridwidth = 2;
        JButton startButton = new JButton("Start Game");
        add(startButton, gbc);
 
        startButton.addActionListener((ActionEvent e) -> {
            String nickname = nicknameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            boolean canGameStart = false;

            if (!RegexValidator.isValidNickname(nickname)) {
                JOptionPane.showMessageDialog(this,
                        "Invalid nickname! (3–15 letters/numbers/underscores)",
                        "Invalid Nickname",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (signupButton.isSelected()) {
                if (credentials.containsKey(nickname)) {
                    JOptionPane.showMessageDialog(this,
                            "Nickname already exists! Try login.",
                            "Nickname Exists",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    credentials.put(nickname, password);
                    saveToFile(nickname + "," + password);
                    JOptionPane.showMessageDialog(this,
                            "Registration successful!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    canGameStart = true;
                }
            } else {
                if (!credentials.containsKey(nickname)) {
                    JOptionPane.showMessageDialog(this,
                            "Nickname not found! Please sign up.",
                            "Nickname Not Found",
                            JOptionPane.WARNING_MESSAGE);
                } else if (!credentials.get(nickname).equals(password)) {
                    JOptionPane.showMessageDialog(this,
                            "Incorrect password!",
                            "Authentication Failed",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Login successful!",
                            "Welcome",
                            JOptionPane.INFORMATION_MESSAGE);
                    canGameStart = true;
                }
            }
 
            if (canGameStart) {
                frame.startGame(nickname, ((GameMode) difficultyBox.getSelectedItem()).name(), password);
            }
        });
    }




    private void loadCredentials() {
        File file = new File("Registrations.csv");
        if (!file.exists()) return;
 
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String nickname = parts[0].trim();
                    String password = parts[1].trim();
                    credentials.put(nickname, password);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading credentials: " + e.getMessage());
        }
    }
 
    public void saveToFile(String data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Registrations.csv", true))) {
            bw.write(data);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }
    
 
 
    public Boolean loginCheck(String user) {
        return credentials.containsKey(user);
    }
}