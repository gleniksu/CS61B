import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hug.
 */
public class Experiments {
    public static void experiment1() {
        BST root =new BST<Integer>();
        List<Integer> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();
        List<Double> y2Values = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 5000; i++) {
            root.add(r.nextInt(10));
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("").yAxisTitle("").build();
    }

    public static void experiment2() {
    }

    public static void experiment3() {
    }

    public static void main(String[] args) {
    }
}
