import org.junit.Test;

public class TestExperimentHelper {

    @Test
    public void testOptimalIPl() {
        int[] actList = new int[9];
        int[] expList = {0, 1, 2, 4, 6, 8, 10, 13};
        for (int i = 1; i <= 8; i++) {
            actList[i] = ExperimentHelper.optimalIPL(i);
            System.out.print(ExperimentHelper.optimalIPL(i));
            System.out.print(" ");

        }
    }

    @Test
    public void testAverageDepth() {
        ;
    }
}
