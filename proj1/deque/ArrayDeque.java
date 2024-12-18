package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

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

    public ArrayDeque(int capacity)  {
        items = (T[]) new Object[capacity];
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
    @Override
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
            resize(size * 2);
        }
    }

    @Override
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
    @Override
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

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
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
        if (size == 0) {
            return null;
        }
        if (Last == 0) {
            return items[items.length - 1];
        }
        return items[Last - 1];
    }

    /**
     * Returns the number of items in the list.
     */
    @Override
    public int size() {
        return size;
    }



    @Override
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
        int p1 = First;
        int p2 = other.First;
        for (int i = 0; i < size; i++) {
            if (!items[p1].equals(other.items[p2])) {
                return false;
            }
            if (p1 == items.length - 1) {
                p1 = 0;
            } else {
                p1 += 1;
            }
            if (p2 == other.items.length - 1) {
                p2 = 0;
            } else {
                p2 += 1;
            }
        }
        return true;
    }

    public void printDeque() {
        int p = First;
        for (int i = 0; i < size; i++) {
            System.out.print(items[p] + " ");
            if (p == items.length - 1) {
                p = 0;
            } else {
                p += 1;
            }
        }
        System.out.println();
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int index;
        private int currentIndex;

        ArrayDequeIterator() {
            index = 0;
            currentIndex = First;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            T returnItem = items[currentIndex];
            if (currentIndex == items.length - 1) {
                currentIndex = 0;
            } else {
                currentIndex += 1;
            }
            index += 1;
            return returnItem;
        }
    }
}
