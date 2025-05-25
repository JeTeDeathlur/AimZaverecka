package util;

import java.io.*;
import java.util.*;

public class ScoreManager<T> {
    private static ScoreManager instance;
    private Map<String, List<T>> scores;

    private ScoreManager() {
        scores = new HashMap<>();
    }

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
        System.out.println("Skóre hráče " + nickname + ": " + score);
    }
}