package strategy;

import aimtrainer.panel.GamePanel;

public class HardStrategy implements GameStrategy {
    public void setupGame(GamePanel panel) {
      
    }
    public int getTimeLimit() {
        return 15;
    }
    public int getTargetRadius() {
        return 20;
    }
}