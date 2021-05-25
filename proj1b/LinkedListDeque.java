public class LinkedListDeque<T> implements Deque<T>{
    private class Node {
        private Node prev;
        private T item;
        private Node next;

        private Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;

        }


    }

    private int size = 0;
    private Node sentinel;

    /**
     * Create a empty linked list deque.
     */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }


    /**
     * Adds an item of type T to the front of the deque.
     */

    @Override
    public void addFirst(T i) {
        sentinel.next = new Node(sentinel, i, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    /* Adds an item of type T to the back of the deque. */

    @Override
    public void addLast(T i) {
        sentinel.prev = new Node(sentinel.prev, i, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    /* Returns the number of items in the deque. */

    @Override
    public int size() {
        return size;
    }


    /* Removes and return the items at the front of the deque. If no such item exists,
       return null.*/

    @Override
    public T removeFirst() {
        Node returnItem;
        if (size == 0) {
            return null;
        }
        returnItem = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return returnItem.item;
    }

    /* Removes and return the items at the back of the deque. If no such item exists,
       return null.*/

    @Override
    public T removeLast() {
        Node returnItem;
        if (size == 0) {
            return null;
        }
        returnItem = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return returnItem.item;
    }

    /* Get the item at the given index. If no such item exists, return null.*/

    @Override
    public T get(int index) {
        Node p = sentinel;
        while (index > 0) {
            p = p.next;
        }
        return p.next.item;
    }

    private T getRecursive(int index, Node starter) {
        if (index == 0) {
            return starter.next.item;
        }
        return getRecursive(index - 1, starter.next);
    }


    public T getRecursive(int index) {
        return getRecursive(index, sentinel);
    }

    @Override
    public void printDeque() {
        Node p = sentinel;
        while (size > 0) {
            System.out.print(p.next.item);
            System.out.print(" ");
            p = p.next;
            size = size - 1;
        }
        System.out.print("\n");
    }

    /**
     * Create a deep copy with the exact same items as other.
     */
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;

        for (int i = 0; i < other.size(); i++) {
            addFirst((T) other.get(i)); //
        }
    }
}