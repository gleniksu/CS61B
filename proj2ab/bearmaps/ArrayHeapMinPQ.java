package bearmaps;

import com.sun.xml.internal.bind.v2.model.core.MaybeElement;

import java.util.*;

public class ArrayHeapMinPQ<T> implements  ExtrinsicMinPQ<T> {
    Vertex<T>[] minHeap;
    private int size;
    private double LOAD_FACTOR = 0.75;
    Map<T, Integer> keyMap = new HashMap<>();


    /** Constructor */
    public ArrayHeapMinPQ() {
        size = 0;
        minHeap = new Vertex[10];
    }

    public ArrayHeapMinPQ(int initCapacity) {
        size = 0;
        minHeap = minHeap = new Vertex[initCapacity];
    }


    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException("Item already exists in the priority queue.");
        }
        if (size == minHeap.length - 1) {
            resize(minHeap.length * 2);
        }
        minHeap[size] = new Vertex<T>(item, priority);
        keyMap.put(item, size); //store item and its position.
        swim(size);
        size += 1;
    }

    @Override
    public boolean contains(T item) {
        return keyMap.containsKey(item);
    }

    @Override
    public T getSmallest() {
        return minHeap[0].item;
    }

    @Override
    public T removeSmallest() {
        if (size == 0) {
            throw new NoSuchElementException("No item exist.");
        }
        Vertex<T> min = minHeap[0];
        swap(0, size-1);
        size = size - 1;
        sink(0);
        if (minHeap.length * LOAD_FACTOR >= size) {
            resize(minHeap.length / 2);
        }
        return min.item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException("No such item exist. ");
        }
        // First, we need to find the item. but we can't find the item
        // directly. we must iterate the total heap. So to solve this problem,
        // we can create a HashMap store each item and their index in the array,
        // and then, we
        int i = keyMap.get(item);
        minHeap[i].priority = priority;
        //swim && swap
        swim(i);
        sink(i);
    }

    /**
     * Some Helper Function
     *  no need to read
     */

    //与priority更小的那个子节点交换
    //如果两个子节点的priority相同，则和左边的进行交换
    private void sink(int i) {
        /*
        //Need to solve array overflow.
        int leftChild = i * 2 + 1;
        int rightChild = i * 2 + 2;
        if (minHeap[i].priority < minHeap[leftChild].priority && minHeap[i].priority < minHeap[rightChild].priority) {
            if (minHeap[leftChild].priority > minHeap[rightChild].priority) {
                swap(i, rightChild);
                sink(rightChild);
            } else {
                swap(i, leftChild);
                sink(leftChild);
            }
        } else if (minHeap[i].priority < minHeap[leftChild].priority) {
            swap(i, leftChild);
            sink(leftChild);
        } else if (minHeap[i].priority < minHeap[rightChild].priority) {
            swap(i, rightChild);
            sink(rightChild);
        }

         */

        while (i * 2 + 1 <= size - 1) {
            int j = i * 2 + 1;
            if (j < size - 1 && minHeap[j + 1].priority < minHeap[j].priority) {
                j = j + 1;
            }
            if (minHeap[i].priority < minHeap[j].priority) {
                break;
            }
            swap(i, j);
            i = j;
        }
    }

    private void resize(int capacity) {
        Vertex<T>[] temp = new Vertex[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = minHeap[i];
        }
        minHeap = temp;
    }

    private void swim(int i) {
        if (minHeap[i].priority < minHeap[parent(i)].priority) {
            swap(i, parent(i));
            swim(parent(i));
        }
    }

    //To complete changePro
    private void swap(int i1, int i2) {
            //update keyMap
            int p1 = keyMap.get(minHeap[i1].item); //item1 's position
            int p2 = keyMap.get(minHeap[i2].item); //item2 's position
            keyMap.put(minHeap[i1].item, p2);
            keyMap.put(minHeap[i2].item, p1);
            //exchange
            Vertex<T> temp = minHeap[i1];
            minHeap[i1] = minHeap[i2];
            minHeap[i2] = temp;
    }

    private int parent(int k) {
        return (k - 1) / 2;
    }

    public class Vertex<T> implements Comparable<Vertex>{
        T item;
        double priority;

        Vertex(T i, double p) {
            item = i;
            priority = p;
        }

        @Override
        public String toString() {
            return "item: " + item + "  " + "priority: " + priority;
        }


        @Override
        public int compareTo(Vertex o) {
            if (priority == o.priority) {
                return 0;
            } else if (priority > o.priority) {
                return -1;
            } else { //priority < o.priority
                return 1;
            }
        }
    }

}
