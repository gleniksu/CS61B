package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import bearmaps.proj2ab.ExtrinsicMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.*;

public class AStarSolver<Vertex> implements ShortestPathsSolver{
    private double spendTime = 0;
    Map<Vertex, Double> disTo = new HashMap<>();
    Map<Vertex, Vertex> edgeTo = new HashMap<>();
    SolverOutcome solverOutcome = SolverOutcome.UNSOLVABLE;
    List<Vertex> solution = new ArrayList<>();
    private int countOperation = 0;
    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        ExtrinsicMinPQ<Vertex> Fringe= initFringe(start, end, input);
        while(Fringe.size() != 0) {
            Vertex currVertex = Fringe.removeSmallest();
            countOperation += 1;
            double distToCurr = disTo.get(currVertex);
            List<WeightedEdge<Vertex>> edges = input.neighbors(currVertex);
            for (WeightedEdge<Vertex> eachVertex : edges) {
                if (!disTo.containsKey(eachVertex.to()) || distToCurr + eachVertex.weight() < disTo.get(eachVertex.to())) {
                    disTo.put(eachVertex.to(), distToCurr
                            + eachVertex.weight());
                    edgeTo.put(eachVertex.to(), currVertex);

                    if (Fringe.contains(eachVertex.to())) {
                        Fringe.changePriority(eachVertex.to(), disTo.get(eachVertex.to())
                                + input.estimatedDistanceToGoal(eachVertex.to(), end));
                    } else {
                        Fringe.add(eachVertex.to(), disTo.get(eachVertex.to())
                                + input.estimatedDistanceToGoal(eachVertex.to(), end));
                    }
                }
            if (Fringe.getSmallest().equals(end)) {
                edgeToConvertList(end);
                solverOutcome = SolverOutcome.SOLVED;
                spendTime = sw.elapsedTime();
                return;
            }
            spendTime = sw.elapsedTime();
            if (spendTime > timeout) {
                solverOutcome = SolverOutcome.TIMEOUT;
                spendTime =sw.elapsedTime();
                return;
            }
            }
        }
    }


    @Override
    public SolverOutcome outcome() {
        return solverOutcome;
    }

    @Override
    public List solution() {
        return solution;
    }

    @Override
    public double solutionWeight() {
        double total = 0;
        for (Vertex v : solution) {
            total = total + disTo.get(v);
        }
        return total;
    }

    @Override
    public int numStatesExplored() {
        return countOperation;
    }

    @Override
    public double explorationTime() {
        return spendTime;
    }

    /**
     * Helper Function
     */
    private ExtrinsicMinPQ<Vertex> initFringe(Vertex start, Vertex end, AStarGraph<Vertex> graph) {
        ExtrinsicMinPQ<Vertex> Fringe = new ArrayHeapMinPQ<>();
        Fringe.add(start, graph.estimatedDistanceToGoal(start, end));
        disTo.put(start, 0.0);
        edgeTo.put(start, null);
        return Fringe;
    }

    private void edgeToConvertList(Vertex end) {
        solution.add(end);
        while (edgeTo.get(end) != null) {
            solution.add(edgeTo.get(end));
            end = edgeTo.get(end);
        }
        Collections.reverse(solution);
    }

}


/*if (Fringe.contains(eachVertex.to())) {
                    //DONE
                    if (disTo.get(eachVertex.to()) < disTo.get(eachVertex.from()) + eachVertex.weight()) {
                        Fringe.changePriority(eachVertex.to(), distToCurr
                                + eachVertex.weight()
                                + input.estimatedDistanceToGoal(eachVertex.to(), end));
                        edgeTo.put(eachVertex.to(), eachVertex.from());
                        disTo.put(eachVertex.to(), eachVertex.weight() + distToCurr);
                    }
                } else {
                    Fringe.add(eachVertex.to(), input.estimatedDistanceToGoal(eachVertex.to(), end)
                            + eachVertex.weight()
                            + distToCurr);
                    edgeTo.put(eachVertex.to(), eachVertex.from());
                    disTo.put(eachVertex.to(), eachVertex.weight() + distToCurr);
                }
            }*/
