import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private HashSet<K> kSet;
    private ArrayList<ArrayList<Entry>> buckets;
    private int size;
    private double loadFactor;

    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    public MyHashMap(int initialSize, double lF) {
        loadFactor = lF;
        kSet = new HashSet<>();
        buckets = new ArrayList<>();
        for (int i = 0; i < initialSize; i++) {
            buckets.add(new ArrayList<Entry>());
        }
    }

    private class Entry {
        K key;
        V val;
        Entry(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    @Override
    public void clear() {
        buckets = new ArrayList<ArrayList<Entry>>();
        kSet = new HashSet<>();
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return kSet.contains(key);
    }

    @Override
    public V get(K key) {
        if (!containsKey(key)) {
            return null;
        } else {
            ArrayList<Entry> tempList = buckets.get(hash(key, buckets.size()));
            for (Entry e: tempList) {
                if (e.key.equals(key)) {
                    return e.val;
                }
            }
            return null;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (containsKey(key)) {
            update(key, value);
        } else {
            if (size() >= loadFactor * buckets.size()) {
                resize();
            }
                add(key, value);
        }
    }

    @Override
    public Set<K> keySet() {
        return kSet;
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
    public Iterator<K> iterator() {
        return kSet.iterator();
    }


    private int hash(K key, int capacity) {
        return Math.floorMod(key.hashCode(), capacity);
    }

    private void resize() {
        int capacity = buckets.size();
        ArrayList<ArrayList<Entry>> newBuckets = new ArrayList<ArrayList<Entry>>();

        for (int i = 0; i < capacity * 2; i++) {
            newBuckets.add(new ArrayList<Entry>());
        }

        for (K k: kSet) {
            ArrayList<Entry> tempList = newBuckets.get(hash(k, newBuckets.size()));
            tempList.add(new Entry(k, get(k)));
        }
        buckets = newBuckets;
    }

    private void update(K key, V val) {
        ArrayList<Entry> tempList = buckets.get(hash(key, buckets.size()));
        for (Entry e: tempList) {
            if (e.key == key) {
                e.val = val;
            }
        }
    }

    private void add(K key, V val) {
        ArrayList<Entry> tempList = buckets.get(hash(key, buckets.size()));
        tempList.add(new Entry(key, val));
        kSet.add(key);
        size += 1;
    }

}