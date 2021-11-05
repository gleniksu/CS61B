import org.junit.Test;
import static org.junit.Assert.*;

public class BubbleGridTest {

    @Test
    public void testBasic(){

        int[][] grid = {{1, 0, 0, 0},
                        {1, 1, 1, 0}};
        int[][] darts = {{1, 0}};
        int[] expected = {2};

        validate(grid, darts, expected);
    }

    private void validate(int[][] grid, int[][] darts, int[] expected){
        BubbleGrid sol = new BubbleGrid(grid);
        assertArrayEquals(expected, sol.popBubbles(darts));
    }

    @Test
    public void testLab() {
        int[][] i = new int[2][2];
        int[] j = new int [3];
        int index = 0;
        for (int num: j) {
            num = num + index;
            index += 1;
        }
    }
}
