package strategy;

import panel.GamePanel;

public class MediumStrategy implements GameStrategy {
    public void setupGame(GamePanel panel) {

    }
    public int getTimeLimit() {
        return 20;
    }
    public int getTargetRadius() {
        return 30;
    }
}