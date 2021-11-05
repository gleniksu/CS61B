import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class UnionFind{

    // DONE - Add instance variables?
    private int[] inSets;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n){
        //DONE
        inSets = new int[n];
        for(int i=0; i < n; i++){
            inSets[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex){
        // DONE
        if (vertex < 0 || vertex > inSets.length -1){
            throw new IllegalArgumentException("Invalid index");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // DONE
        validate(v1);
        return -parent(find(v1));
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1){
        // DONE
        validate(v1);
        return inSets[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        // DONE
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        // DONE
        validate(v1);
        validate(v2);
        if (!connected(v1, v2)){
            int sizeV1 = sizeOf(v1);
            int sizeV2 = sizeOf(v2);
            if(sizeV1 > sizeV2) {
                inSets[find(v1)] = -(sizeV1 + sizeV2);
                inSets[find(v2)] = find(v1);
            } else {
                inSets[find(v2)] = -(sizeV1 +sizeV2);
                inSets[find(v1)] = find(v2);
            }
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex){
        // DONE
        validate(vertex);
        int root = vertex;
        while (inSets[root] >= 0) {
            root = inSets[root];
        }
        //Path Compression
        while (inSets[vertex] >= 0) {
            int nextIndex = inSets[vertex];
            inSets[vertex] = root;
            vertex = nextIndex;
        }
        return root;
    }
}
