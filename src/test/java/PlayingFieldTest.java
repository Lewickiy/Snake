import com.lewickiy.snake.entity.PlayingField;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class PlayingFieldTest {
    @Parameterized.Parameter
    public int size;
    @Parameterized.Parameter(1)
    public int expectedHeight;
    @Parameterized.Parameter(2)
    public int expectedWidth;

    @Parameterized.Parameters
    public static Object[][] getSetValuesHeightWidth() {
        return new Object[][] {
                {-1, 20, 20},
                {0, 20, 20},
                {21, 21, 21},
                {70, 20, 20}
        };
    }

    @Test
    public void testSizeHeightWidth() {
        PlayingField pf = new PlayingField(size);
        int actualHeight = pf.getHeight();
        int actualWidth = pf.getWidth();

        assertTrue(
                (actualWidth == actualHeight)
                        && (actualWidth == expectedWidth)
                        && (actualHeight == expectedHeight)
        );
    }
}
