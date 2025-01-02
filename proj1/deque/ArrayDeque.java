package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private T[] items;
    private int first;
    private int last;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        first = 2;
        last = 2;
    }

    // public ArrayDeque(int capacity)  {
    //     items = (T[]) new Object[capacity];
    //     size = 0;
    //     First = 2;
    //     Last = 2;
    // }

    /**
     * Resizes the underlying array to the target capacity.
     */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int p = first;
        for (int i = 0; i < size; i += 1) {
            a[i] = items[p];
            if (p == items.length - 1) {
                p = 0;
            } else {
                p += 1;
            }
        }
        last = size;
        first = 0;
        items = a;
    }

    @Override
    public void addFirst(T item) {
        growCheck();
        if (first == 0) {
            first = items.length - 1;
        } else {
            first = first - 1;
        }
        items[first] = item;
        size = size + 1;
    }

    /**
     * Inserts X into the back of the list.
     */
    @Override
    public void addLast(T x) {
        growCheck();
        items[last] = x;
        if (last == items.length - 1) {
            last = 0;
        } else {
            last = last + 1;
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
        T x = items[first];
        items[first] = null;
        if (first == items.length - 1) {
            first = 0;
        } else {
            first = first + 1;
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
        if (last == 0) {
            last = items.length - 1;
        } else {
            last = last - 1;
        }
        T x = items[last];
        items[last] = null;
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
        int p = first;
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
    // public T getLast() {
    //     if (size == 0) {
    //         return null;
    //     }
    //     if (last == 0) {
    //         return items[items.length - 1];
    //     }
    //     return items[last - 1];
    // }

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
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque other = (Deque) o;
        if (size != other.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!get(i).equals(other.get(i))) {
                return false;
            }
        }
        return true;
    }

    public void printDeque() {
        int p = first;
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
            currentIndex = first;
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
