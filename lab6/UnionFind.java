public class UnionFind {

    // DONE - Add instance variables?
    private int[] parents;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        // DONE
        parents = new int[n];
        for(int i: parents) {
            i = -1;
        }

    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) throws Exception {
        // DONE
        if(vertex > parents.length-1 && vertex < 0){
            throw new Exception("Error!");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) throws Exception {
        // DONE
        validate(v1);
        return -(parent(find(v1)));
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) throws Exception {
        // DONE
        validate(v1);
        return parents[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) throws Exception {
        // DONE
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a
       vertex with itself or vertices that are already connected should not
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) throws Exception {
        // DONE
        validate(v1);
        validate(v2);
        int size_v1 = sizeOf(v1); //the size
        int size_v2 = sizeOf(v2);
        if(size_v1 > size_v2){  //Depict the condition of size_v1 > size_v2
            parents[find(v1)] = -(size_v1 + size_v2); //Update the size of set.
            parents[find(v2)] = find(v1); //Modify the root of the set v2 belongs to.
        }else{ //Depict the condition of size_v1 <= size_v2
            parents[find(v2)] = -(size_v1 + size_v2);//Update the size of the set.
            parents[find(v1)] = find(v2);//Modify the root of the set v1 belongs to.
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time.*/
       //Common Implementation
    public int find(int vertex) throws Exception {
        // DONE
        validate(vertex);
        int root = vertex;
        while(parents[root] >= 0){
            root = parents[root];
        }
        //Path Compression
        while(parents[vertex] >= 0){
            int currentIndex = vertex;
            vertex = parents[vertex];
            parents[currentIndex] = root;

        }
        return vertex;
    }
}
