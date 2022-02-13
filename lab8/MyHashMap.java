import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class MyHashMap<Key, Val> implements Map61B<Key, Val>{
    int size;
    ArrayList<Node> bucket;
    double loadFactor;
    Set<Key> kSet;

    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    public MyHashMap(int initialSize, double lf) {
        bucket = new ArrayList<>(4);
        loadFactor = lf;
        size = 0;
    }
    @Override
    public void clear() {
        bucket = new ArrayList<Node>(4);
        loadFactor = 0.75;
        size = 0;
    }

    @Override
    public boolean containsKey(Key key) {
        int bucketNum = bucket.size();
        int target = bucket.indexOf(key.hashCode() % bucketNum);
        if  (target == -1) {
            return false;
        }
        return find(key, bucket.get(target)) != null;
    }

    @Override
    public Val get(Key key) {
        int bucketNum = bucket.size();
        int target = bucket.indexOf(key.hashCode() % bucketNum);
        if(target == -1) {
            return null;
        }
        return find(key, bucket.get(target));
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(Key key, Val value) {
        size += 1;
    }

    @Override
    public Set<Key> keySet() {
        return null;
    }

    @Override
    public Val remove(Key key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Val remove(Key key, Val value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<Key> iterator() {
        return null;
    }

    /**
     * These are some helper functions.
     * No need to read.
     */
    private Val find(Key k, Node next) {
        while (next != null) {
            if (next.key == k) {
                return next.val;
            }
            next = next.next;
        }
        if (next.key == k) {
            return next.val;
        }
        return null;
    }

    private class Node {
        Key key;
        Val val;
        Node next;
    }
}
