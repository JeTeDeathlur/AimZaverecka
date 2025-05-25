package model;

public class Result {
    private final int hits;
    private final int total;
    private final long time;

    public Result(int hits, int total, long time) {
        this.hits = hits;
        this.total = total;
        this.time = time;
    }

    public int getHits() {
        return hits;
    }

    public int getTotal() {
        return total;
    }

    public long getTime() {
        return time;
    }

    public double getAccuracy() {
        return total > 0 ? (hits * 100.0 / total) : 0;
    }
}
