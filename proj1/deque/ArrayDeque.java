package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> , Iterable<T>{

    private T[] items;
    private int First;
    private int Last;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        First = 2;
        Last = 2;
    }

    /**
     * Resizes the underlying array to the target capacity.
     */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int p = First;
        for (int i = 0; i < size; i += 1) {
            a[i] = items[p];
            if (p == items.length - 1) {
                p = 0;
            } else {
                p += 1;
            }
        }
        Last = size;
        First = 0;
        items = a;
    }

    @Override
    public void addFirst(T item) {
        growCheck();
        if (First == 0) {
            First = items.length - 1;
        } else {
            First = First - 1;
        }
        items[First] = item;
        size = size + 1;
    }

    /**
     * Inserts X into the back of the list.
     */
    public void addLast(T x) {
        growCheck();
        items[Last] = x;
        if (Last == items.length - 1) {
            Last = 0;
        } else {
            Last = Last + 1;
        }
        size = size + 1;
    }

    private void growCheck() {
        if (size == items.length) {
            // TODO: resize
            resize(size * 2);
        }
    }


    public T removeFirst() {
        shrinkCheck();
        if (size == 0) {
            return null;
        }
        T x = items[First];
        items[First] = null;
        if (First == items.length - 1) {
            First = 0;
        } else {
            First = First + 1;
        }
        size = size - 1;
        return x;
    }

    /**
     * Deletes item from back of the list and
     * returns deleted item.
     */
    public T removeLast() {
        shrinkCheck();
        if (size == 0) {
            return null;
        }
        if (Last == 0) {
            Last = items.length - 1;
        } else {
            Last = Last - 1;
        }
        T x = items[Last];
        items[Last] = null;
        size = size - 1;


        return x;
    }

    private void shrinkCheck() {
        if (items.length >= 16) {
            if (size < (items.length / 4)) {
                resize((Math.max(items.length / 2, 16)));
            }
        }
    }

    // public Iterator<T> iterator() {
    //     return new iterator();
    // }

    /**
     * Gets the ith item in the list (0 is the front).
     */
    public T get(int index) {
        int p = First;
        for (int i = 0; i < index; i += 1) {
            if (p == items.length - 1) {
                p = 0;
            } else {
                p += 1;
            }
        }
        return items[p];
    }


    /**
     * Returns the item from the back of the list.
     */
    public T getLast() {
        if (Last == 0) {
            return items[items.length - 1];
        }
        return items[Last - 1];
    }

    /**
     * Returns the number of items in the list.
     */
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof ArrayDeque)) {
            return false;
        }
        ArrayDeque<T> other = (ArrayDeque<T>) o;
        if (size != other.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            // equals 的知识有点忘了
            if (!items[i].equals(other.items[i])) {
                return false;
            }
        }
        return true;

    }

    public void printDeque() {
        int p = First;
        while (p != Last) {
            System.out.print(items[p] + " ");

            if (p == items.length - 1) {
                p = 0;
            } else {
                p += 1;
            }
        }
        System.out.println();
    }

    public Iterator<T> iterator() {
        return null;
    }
}