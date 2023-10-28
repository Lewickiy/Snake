import com.lewickiy.snake.Game;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class GameTest {
    @Parameterized.Parameter
    public int setSpeed;
    @Parameterized.Parameter(1)
    public int expectedSpeed;

    @Parameterized.Parameters(name = "if set speed {0} actual result must be {1}")
    public static Object[][] getSpeedResult() {
        return new Object[][] {
                {3, 3},
                {6, 6},
                {16, 16},
                {7, 7},
                {0, 1},
                {-1, 1}
        };
    }

    @Test
    public void checkSetSpeed() {
        assertEquals(new Game(setSpeed).getSpeed(), expectedSpeed);
    }
}
