import java.util.Iterator;
import java.util.Set;


/**
 * <K extends Comparable<K>, V>
 * This means that the type parameter must support comparison with other instances of its own type,
 * via the Comparable interface.
 */

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K,V>{
    int size = 0;
    BST treeRoot = null;
    @Override
    public void clear() {
        size = 0;
        treeRoot = null;
    }

    public void printInOrder() {
        printInOrder(treeRoot);
    }

    /**
     * Helper
     */
    private void printInOrder(BST Node) {
        if (Node.leftPointer == null && Node.rightPointer == null) {
            printNode(Node);
        } else if (Node.leftPointer ==null) {
            printNode(Node);
            printInOrder(Node.rightPointer);
        } else if (Node.rightPointer == null) {
            printInOrder(Node.leftPointer);
            printNode(Node);
        } else {
            printInOrder(Node.leftPointer);
            printNode(Node);
            printInOrder(Node.rightPointer);
        }

    }

    private void printNode(BST Node) {
        System.out.println("1");
    }


    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K k, V v) {
        treeRoot = put(treeRoot, k, v);
    }


    /**
     * Helper Function
     * The purpose of setting Node as parameter is helping me to complete recursion.
     * treeRoot will be consistent, if we don't set.
     */
    private BST put(BST Node,K k, V v) {

        if (Node == null) {
            size += 1;
            return new BST(k, v, null,null);
        } else {
            int cmp = k.compareTo(Node.key);
            if (cmp < 0) {
                Node.leftPointer = put(Node.leftPointer, k, v);
            } else if (cmp > 0) {
                Node.rightPointer = put(Node.rightPointer, k, v);
            } else {
                Node.val = v;
            }
        }
        return Node;
    }

    @Override
    public boolean containsKey(K k) {
        return get(k) != null;
    }

    @Override
    public V get(K k) {
        BST lookup = get(treeRoot, k);
        if (lookup != null) {
            return lookup.val;
        }
        return null;
    }

    /**
     * Helper
     */
    private BST get(BST Node, K k) {
        if (Node == null) {
            return null;
        } else {
            int cmp = k.compareTo(Node.key);
            if (cmp < 0) {
                return get(Node.leftPointer, k);
            } else if (cmp > 0) {
                return get(Node.rightPointer, k);
            }
            return Node;
        }
    }

    class BST{
        BST leftPointer;
        BST rightPointer;
        K key;
        V val;

        BST(K k, V v, BST lp, BST rp) {
            key = k;
            val = v;
            leftPointer = lp;
            rightPointer = rp;
        }
    }

}
