package util;

import java.util.*;
/**
 * Singleton manager to store and retrieve player scores.
 *
 * @param <T> The type of score stored (e.g., Integer, Double).
 */

public class ScoreManager<T> {
    private static ScoreManager instance;
    private final Map<String, List<T>> scores = new HashMap<>();

    private ScoreManager() {}

    /**
     * Returns the singleton instance of ScoreManager.
     */
    public static synchronized <T> ScoreManager<T> getInstance() {
        if (instance == null) instance = new ScoreManager<>();
        return instance;
    }


    /**
     * Adds a score for a given player.
     *
     * @param player the player name
     * @param score  the score to add
     */
    public void addScore(String player, T score) {
        scores.computeIfAbsent(player, k -> new ArrayList<>()).add(score);
    }

    /**
     * Retrieves the list of scores for a player.
     *
     * @param player the player name
     * @return list of scores
     */
    public List<T> getScores(String player) {
        return scores.getOrDefault(player, new ArrayList<>());
    }

    /**
     * Saves the score by printing it to the console.
     *
     * @param nickname the player's nickname
     * @param score    the score to save
     */
    public void saveScore(String nickname, int score) {
        System.out.println("Player Score " + nickname + ": " + score);
        
    }
}
