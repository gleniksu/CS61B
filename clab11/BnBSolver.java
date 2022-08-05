import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * BnBSolver for the Bears and Beds problem. Each Bear can only be compared to Bed objects and each Bed
 * can only be compared to Bear objects. There is a one-to-one mapping between Bears and Beds, i.e.
 * each Bear has a unique size and has exactly one corresponding Bed with the same size.
 * Given a list of Bears and a list of Beds, create lists of the same Bears and Beds where the ith Bear is the same
 * size as the ith Bed.
 */
public class BnBSolver {

    private List<Bear> sortedBears;
    private List<Bed> sortedBeds;

    public BnBSolver(List<Bear> bears, List<Bed> beds) {
        // TODO: Fix me.
        Pair<List<Bear>, List<Bed>> solutions = quickSort(bears, beds);
        sortedBears = solutions.first();
        sortedBeds  = solutions.second();
    }

    /**
     * Returns List of Bears such that the ith Bear is the same size as the ith Bed of solvedBeds().
     */
    public List<Bear> solvedBears() {
        // TODO: Fix me.
        return sortedBears;    }

    /**
     * Returns List of Beds such that the ith Bear is the same size as the ith Bear of solvedBears().
     */
    public List<Bed> solvedBeds() {
        // TODO: Fix me.
        return sortedBeds;
    }

    private <T extends HiddenComparable> HiddenComparable getFirstItem(List<T> lists) {
        return lists.remove(0);
    }


    private <T> List<T> concatenate(List<T> list1, List<T> list2, List<T> list3) {
        List<T> list = new ArrayList<>();
        list.addAll(list1);
        list.addAll(list2);
        list.addAll(list3);
        return list;
    }


    private Pair<List<Bear>, List<Bed>> quickSort(List<Bear> bears, List<Bed> beds) {

        if (bears.size() <= 1) {
            return new Pair<>(bears, beds);
        }

        List<Bear> bearLess = new ArrayList<>();
        List<Bear> bearEqual = new ArrayList<>();
        List<Bear> bearLarger = new ArrayList<>();
        List<Bed> bedLess = new ArrayList<>();
        List<Bed> bedEqual = new ArrayList<>();
        List<Bed> bedLarger = new ArrayList<>();
        partition(beds, bears.get(0), bedLess, bedEqual, bedLarger);
        partition(bears, bedEqual.get(0), bearLess, bearEqual, bearLarger);

        Pair<List<Bear>, List<Bed>> less = quickSort(bearLess, bedLess);
        bearLess = less.first();
        bedLess = less.second();
        Pair<List<Bear>, List<Bed>> larger = quickSort(bearLarger, bedLarger);
        bearLarger = larger.first();
        bedLarger = larger.second();
        return new Pair<>(concatenate(bearLess, bearEqual, bearLarger), concatenate(bedLess, bedEqual, bedLarger));
    }



    private <T extends Comparable<V>, V extends Comparable<T>> void partition (List<T> items, V pivot, List<T> less,
                                                                               List<T> equal, List<T> bigger) {
        for (int i = 0; i < items.size(); i++) {
            T tempItem = items.get(i);
            int cmp = pivot.compareTo(tempItem);
            if (cmp < 0) {
                less.add(tempItem);
            } else if (cmp == 0) {
                equal.add(tempItem);
            } else {
                bigger.add(tempItem);
            }
        }
    }
}
