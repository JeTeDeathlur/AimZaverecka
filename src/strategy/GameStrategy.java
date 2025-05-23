package strategy;

import aimtrainer.panel.GamePanel;

public interface GameStrategy {
    void setupGame(GamePanel panel);
    int getTimeLimit();
    int getTargetRadius();
}
