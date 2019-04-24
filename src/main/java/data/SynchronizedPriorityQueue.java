package data;

public class SynchronizedPriorityQueue<E extends Comparable<? super E>> {
    private volatile Element<E>[] queue;
    private int index;

    public SynchronizedPriorityQueue() {
        queue = new Element[10];
        index = 0;
    }

    /**
     * Add a new element to the queue.
     * Complexity: O(1)
     *
     * @param content the content o
     * @param priority
     */
    public synchronized void add(E content, int priority) {
        Element<E> element = new Element<>(content, priority);
        if (index == queue.length) {
            resize();
        }

        queue[index] = element;
        index++;
    }

    /**
     * Removes the element with the highest priority and returns it.
     * Complexity: O(n)
     * Best case scenario: the queue is empty => O(1)
     * Worst case scenario the element is somewhere in the queue => O(n)
     *
     * @return the element with highest priority
     */
    public synchronized E remove() {
        if (index == 0) {
            return null;
        }

        int deletedIndex = 0;
        for (int i = 0; i < index; i++) {
            if (queue[i].getPriority() > queue[deletedIndex].getPriority()) {
                deletedIndex = i;
            }
        }

        Element<E> result = queue[deletedIndex];
        index--;
        queue[deletedIndex] = queue[index];

        return result.getContent();
    }

    /**
     * Update the priority of an element E
     * Complexity: O(n)
     *      - search in the array the element
     *
     * @param element the searched element
     * @param priority the new priority
     */
    public synchronized boolean update(E element, int priority) {
        int searchedIndex = index;

        // search for the position of the element E
        for (int i = 0; i < index; i++) {
            if (queue[i].getContent().compareTo(element) == 0) {
                searchedIndex = i;
            }
        }

        if (searchedIndex != index) {
            // set the new priority
            queue[searchedIndex].setPriority(priority);
            return true;
        }

        return false;
    }

    /**
     * Get the size on the queue (the size is represented by the current index in the queue
     * @return the size of the queue
     */
    public int size() {
        return index;
    }

    /**
     * Increase the size of the queue with 1
     * Complexity: O(n) - all the elements from the initial array must be copied
     */
    private synchronized void resize() {
        Element<E>[] newQueue = new Element[index + 1];
        for (int i = 0; i < index; i++) {
            newQueue[i] = queue[i];
        }
        queue = newQueue;
    }
}

