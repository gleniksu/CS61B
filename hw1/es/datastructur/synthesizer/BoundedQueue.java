package es.datastructur.synthesizer;

public interface BoundedQueue <T>{
    /**
     * return size of the buffer.
     */
    public int capacity();

    /**
     * return the number of items currently in the buffer.
     */
    public int fillCount();

    /**
     * add item x to the end.
     */
    public void enqueue(T x);

    /**
     * delete and return item from the front.
     */
    public T dequeue();
    /**
     * return the item from the front.
     */
    public T peek();
    /**
     * is the buffer empty(fillCount equals zero)?
     */
    default boolean isEmpty() {
        if (fillCount() == 0) {
            return true;
        }
        return false;
    }

    /**
     * is the buffer full(fillCount is same as capacity?)
     */
    default boolean isFull() {
        if (fillCount() == capacity()) {
            return true;
        }
        return false;
    }
}
