package strategy;

import aimtrainer.panel.GamePanel;

public class EasyStrategy implements GameStrategy {
    public void setupGame(GamePanel panel) {
    }
    public int getTimeLimit() {
        return 30;
    }
    public int getTargetRadius() {
        return 40;
    }
}