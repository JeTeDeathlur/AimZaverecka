package strategy;

import panel.GamePanel;

public interface GameStrategy {
    void setupGame(GamePanel panel);
    int getTimeLimit();
    int getTargetRadius();
}
