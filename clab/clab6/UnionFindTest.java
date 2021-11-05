import org.junit.Test;
import static org.junit.Assert.*;

public class UnionFindTest {
    UnionFind uf = new UnionFind(7);
    @Test
    public void testFind() throws Exception {
        uf.union(1, 2);
        assertEquals(uf.sizeOf(2), 2);
        uf.union(3, 4);
        assertEquals(uf.sizeOf(4), 2);
        uf.union(1, 3);
        assertEquals(uf.sizeOf(4), 4);
    }

    @Test
    public void testPathCompression() throws Exception {
        uf.union(1, 2);
        uf.union(3, 4);
        uf.union(1, 4);
        uf.union(5, 6);
        uf.union(5, 1);
        assertEquals(uf.sizeOf(1), 6);
    }

}
