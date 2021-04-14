public class ArrayDeque<T>
{
    private int size;
    private int first_index;
    private int last_index;
    private T[] items;
    /** Create an empty array deque. */
    public class ArrayDeque(){
        items = (T[]) new Object[8];
        first_index = items.length / 2;
        last_index = first_index + 1;
        size = 0;
    }


    public int minusOne(int index){
        index = (index + 1) % items.length;
        return index;
    }

    public int plusOne(int index){
        index = (index - 1 + items.length) % items.length;
        return index;
    }

    public T[] resize(T[] item){
        return null;
    }

    /** Return true if deque is full, false otherwise. */
    public boolean isFull(T items[]){
        if(size == items.length())
            return true;
        return false;
    }

    /** Adds an item of Type T to the front of the deque. */
    public void addFirst(T item){
        if(isFull(items))
            resize(items);
        first_index = minusOne(first_index);
        items[first_index] = item;
        size += 1;

    }

    /** Adds an item of Type T to the back of the deque. */
    public void addLast(T item){
        if(isFull(items)){
            resize(items);
        }
        last_index = plusOne(items.length);
        items[last_index] = item;
        size += 1;
    }

    /** Return true if deque is empty, false otherwise. */
    public boolean isEmpty(){
        if (size == 0)
            return true;
        return false;
    }

    /** Returns the number of items in the deque. */
    public int size(){
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque(){
        int count = first_index;
        for(int i = 0; i < size; i++){
            System.out.print(items[count]);
            System.out.print(" ");
            count = plusOne(count);
        }
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, return null. */
    public T removeFirst(){
        if(isEmpty()){
            T var = items[first_index];
            items[first_index] = null;
            first_index = plusOne(first_index);
            size -= 1;
            return var;
        }
        return null;

    }

    /** Removes and returns the item at the back of the deque. If no such item exists, return null.*/
    public T removeLast(){
        if(!isEmpty()){
            T var = items[last_index];
            items[last_index] = null;
            last_index = minusOne(last_index);
            size -= 1;
            return var;
        }
        return null;

    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item and so forth.
    *  If no such item exists, returns null.*/
    public T get(int index){
        if(isEmpty()|| index > size){
            return null;
        }
        return items[(first_index + index) % items.length];
    }
}
