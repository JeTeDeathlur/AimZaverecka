package util;

import java.util.*;

public class ScoreManager<T> {
    private static ScoreManager instance;
    private final Map<String, List<T>> scores = new HashMap<>();

    private ScoreManager() {}

    public static synchronized <T> ScoreManager<T> getInstance() {
        if (instance == null) instance = new ScoreManager<>();
        return instance;
    }

    public void addScore(String player, T score) {
        scores.computeIfAbsent(player, k -> new ArrayList<>()).add(score);
    }

    public List<T> getScores(String player) {
        return scores.getOrDefault(player, new ArrayList<>());
    }

    public void saveScore(String nickname, int score) {
        System.out.println("Player Score " + nickname + ": " + score);
        
    }
}
