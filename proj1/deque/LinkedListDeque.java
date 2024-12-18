package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T>  {

    private class StuffNode {
        public T item;
        public StuffNode prev;
        public StuffNode next;

        public StuffNode(T i, StuffNode p, StuffNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private StuffNode sentinel;

    private int size;

    public LinkedListDeque() {
        sentinel = new StuffNode(null, null, null);
        size = 0;
    }

    // public LinkedListDeque(T x) {
    //     sentinel = new StuffNode(null, null, null);
    //     sentinel.next = new StuffNode(x, sentinel, sentinel);
    //     size = 1;
    // }

    /**
     * Adds x to the front of the list.
     */
    public void addFirst(T x) {
        StuffNode temp = sentinel.next;
        sentinel.next = new StuffNode(x, sentinel, temp);
        if (temp != null) {
            temp.prev = sentinel.next;
        } else {
            sentinel.prev = sentinel.next;
        }
        size += 1;
    }


    /**
     * Adds an item to the end of the list.
     */
    public void addLast(T x) {
        StuffNode temp = sentinel.prev;
        sentinel.prev = new StuffNode(x, temp, sentinel);
        if (temp != null) {
            temp.next = sentinel.prev;
        } else {
            sentinel.next = sentinel.prev;
        }
        size += 1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        StuffNode p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    // todo
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof ArrayDeque)) {
            return false;
        }
        LinkedListDeque<T> other = (LinkedListDeque<T>) o;
        if (size != other.size()) {
            return false;
        }   
        StuffNode p = sentinel.next;
        StuffNode q = other.sentinel.next;
        for (int i = 0; i < size; i++) {
            if (p.item != q.item){
                return false;
            }
            p = p.next;
            q = q.next;
        }
        return true;
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private StuffNode current = sentinel.next;
        public boolean hasNext() {
            return current != sentinel;
        }
        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }
    }
    
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T temp = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        if (sentinel.next == null) {
            sentinel.next = sentinel;
            sentinel.prev = sentinel;
        } else {
            sentinel.next.prev = sentinel;
        }

        size -= 1;
        return temp;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T temp = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        if (sentinel.prev == null) {
            sentinel.next = sentinel;
            sentinel.prev = sentinel;
        } else {
            sentinel.prev.next = sentinel;
        }
        size -= 1;
        return temp;
    }

    public T get(int index) {
        int i = 0;
        StuffNode temp = sentinel.next;
        while (i < index) {
            temp = temp.next;
            i++;
        }
        return temp.item;
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(StuffNode node, int index) {
        if (index == 0) {
            return node.item;
        }
        return getRecursiveHelper(node.next, index - 1);
    }
}
