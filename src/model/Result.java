package model;

/**
 * Represents the result of a single game session,
 * including hit count, total shots, and elapsed time.
 */
public class Result {
    private final int hits;
    private final int total;
    private final long time;

    /**
     * Constructs a new game result.
     *
     * @param hits number of successful hits
     * @param total total number of shots
     * @param time time taken in milliseconds
     */
    public Result(int hits, int total, long time) {
        this.hits = hits;
        this.total = total;
        this.time = time;
    }

    /**
     * @return the number of hits
     */
    public int getHits() {
        return hits;
    }

    /**
     * @return the total number of shots
     */
    public int getTotal() {
        return total;
    }

    /**
     * @return the time taken in milliseconds
     */
    public long getTime() {
        return time;
    }

    /**
     * Calculates the accuracy percentage (0–100).
     *
     * @return accuracy as a percentage
     */
    public double getAccuracy() {
        return total > 0 ? (hits * 100.0 / total) : 0;
    }
}
