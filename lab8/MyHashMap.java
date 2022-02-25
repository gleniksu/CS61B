import java.util.*;

public class MyHashMap<Key, Val> implements Map61B<Key, Val>{
    int size;
    ArrayList<Node> bucket;
    double loadFactor;
    Set<Key> kSet = new HashSet<Key>();

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
        /*int bucketNum = bucket.size();
        Node target = bucket.get(key.hashCode() % bucketNum); //由于bucket里面存的是Node, 而key.hashCode() % bucketNum
                                                                //是一个数字 并不可能出现在里面 target永远都是-1.我需要看下那个位置是否为null
        return find(key, target) != null;*/
        return kSet.contains(key);
    }

    @Override
    public Val get(Key key) {
        int bucketNum = bucket.size();
        Node target = bucket.get(hash(key, bucketNum));
        return find(key, target).value;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(Key key, Val value) {
        /*double currFactor =  size / bucket.size();
        int bucketNum = bucket.size();
        Node tempNode;
        if (currFactor >= loadFactor) {
            resize(key, value, bucketNum);
        } else {
            tempNode = bucket.get(bucket.hashCode() % bucketNum);
            Node targetNode = find(key, tempNode);
            if (targetNode != null) {       //不等于null就说明链表里已经存在key了
                targetNode.val = value;
            } else {
               targetNode = findLast(tempNode);
               targetNode.key = key;
               targetNode.val = value;
               targetNode.next = null;
               size += 1;
            }
        }*/
        int bucketNum = bucket.size();
        double currFactor = size / bucket.size();
        Node tempNode;
        if (currFactor >= loadFactor) {
            resize(key, bucketNum);
        } else {
            if (kSet.contains(key)) {
                update(key, value);
            } else {
                add(key, value);
            }
        }
    }

    @Override
    public Set<Key> keySet() {
        return kSet;
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
        return keySet().iterator();
    }

    private int hash(Key key, int bucketNum) {
        return 1;
    }

    /**
     * These are some helper functions.
     * No need to read.
     */

    /** resize Function. */
    private void resize(Key k, int cap) {
        bucket = new ArrayList<Node>(cap * 2);
        for (Key kTemp: kSet) {

        }
    }

    /** Returns specify Node if key is equal to k else return null. */
    private Node find(Key k, Node node) {
        while (node.key != null) {
            if (node.key == k) {
                return node;
            }
            node = node.next;
        }
        return node;
    }

    /** Return the last node. */
    private Node findLast(Node node) {
        while (node.key != null) {
            node = node.next;
        }
        return node;
    }

    private class Node {
        Key key;
        Val value;
        Node next;

        Node(Key k, Val v, Node n) {
            key = k;
            value = v;
            next = n;
        }
    }
}
