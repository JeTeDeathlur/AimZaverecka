package test;


import model.Result;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ResultTest {

    @Test
    void calculatesCorrectAccuracy() {
        Result result = new Result(7, 10, 12000);
        assertEquals(70.0, result.getAccuracy());
    }

    @Test
    void accuracyZeroWhenNoShots() {
        Result result = new Result(0, 0, 3000);
        assertEquals(0.0, result.getAccuracy());
    }
}
