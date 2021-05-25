import org.junit.Test;

public class ArrayDeque<T> implements Deque<T> {
    private int size;
    private int firstIndex;
    private int lastIndex;
    private T[] items;

    /** Create an empty array deque. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        firstIndex = items.length / 2;
        lastIndex = firstIndex + 1;
        size = 0;
    }

    /** Create a deep copy with exact same items as other. */
    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[other.size];
        size = other.size;
        firstIndex = other.firstIndex;
        lastIndex = other.lastIndex;
        System.arraycopy(other.items, 0, items, 0, other.size);
    }

    /** index plus one. */
    private int plusOne(int index) {
        index = (index + 1) % items.length;
        return index;
    }

    /** index minus one. */
    private int minusOne(int index) {
        index = (index - 1 + items.length) % items.length;
        return index;
    }

    /** Return true if the deque's usage factor always be at least 25%. */
    private boolean isSparse() {
        if (size >= 16 && size < items.length / 4) {
            return true;
        }
        return false;
    }
    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        int oldIndex = plusOne(firstIndex);
        for (int nextIndex = 0; nextIndex < size; nextIndex++) {
            newItems[nextIndex] = items[oldIndex];
            oldIndex = plusOne(oldIndex);
        }
        items = newItems;
        firstIndex = capacity - 1;
        lastIndex = size;
    }


    /** Upsize the deque. */
    private void upSize() {
        resize(items.length * 2);
    }

    /** Downsize the deque. */
    private void downSize() {
        resize(items.length / 2);
    }

    /** Return true if deque is full, false otherwise. */
    private boolean isFull() {
        if (size == items.length)
            return true;
        return false;
    }

    /** Return true if deque is empty, false otherwise. */

    /** Adds an item of Type T to the front of the deque. */
    @Override
    public void addFirst(T item) {
        if (isFull()) {
            upSize();
        }
        items[firstIndex] = item;
        firstIndex = minusOne(firstIndex);
        size += 1;

    }

    /** Adds an item of Type T to the back of the deque. */
    @Override
    public void addLast(T item) {
        if (isFull()) {
            upSize();
        }
        items[lastIndex] = item;
        lastIndex = plusOne(lastIndex);
        size += 1;
    }

    /** Returns the number of items in the deque. */
    public int size(){
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    @Override
    public void printDeque() {
        int count = firstIndex;
        for (int i = 0; i < size; i++) {
            System.out.print(items[count]);
            System.out.print(" ");
            count = plusOne(count);
        }
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, return null. */
    @Override
    public T removeFirst() {
        if (isSparse()) {
            downSize();
        }
        firstIndex = plusOne(firstIndex);
        T var = items[firstIndex];
        items[firstIndex] = null;
        size -= 1;
        return var;
    }

    /** Removes and returns the item at the back of the deque.
     *If no such item exists, return null.*/
    @Override
    public T removeLast() {
        if (isSparse()) {
            downSize();
        }
        lastIndex = minusOne(lastIndex);
        T var = items[lastIndex];
        items[lastIndex] = null;
        size -= 1;
        return var;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item and so forth.
     *  If no such item exists, returns null.*/
    @Override
    public T get(int index) {
        if (isEmpty() || index > size) {
            return null;
        }
        return items[(firstIndex + index) % items.length];
    }
}
