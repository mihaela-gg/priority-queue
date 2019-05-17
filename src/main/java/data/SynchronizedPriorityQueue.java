package data;

import java.util.LinkedList;

public class SynchronizedPriorityQueue<E extends Comparable<? super E>> {
    private LinkedList<Element<E>> queue;

    public SynchronizedPriorityQueue() {
        this.queue = new LinkedList<>();
    }

    /**
     * Add a new element to the queue.
     * The queue is ordered decreasingly.
     * Complexity: O(log n) - a binary search is used to determine the position and the element
     * is inserted on the determined index.
     *
     * @param content the content
     * @param priority the priority of the content
     */
    public void add(E content, int priority) {
        Element<E> element = new Element<>(content, priority);

        // use the findIndex function to determine where the element should be inserted
        int index = findIndex(priority);
        queue.add(index, element);
    }

    /**
     * Removes the element with the highest priority and returns it.
     * Complexity: O(1)
     * As the underlying data structure is a linked list the first element can be removed
     * with a complexity of O(1).
     *
     * @return the element with highest priority
     */
    public E remove() {
        if (queue.isEmpty()) {
            return null;
        }

        return queue.removeFirst().getContent();
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
        int searchedIndex = findIndex(priority);
//        this.queue.re

        return false;
    }

    /**
     * Get the size on the queue
     * @return the size of the queue
     */
    public int size() {
        return queue.size();
    }

    /**
     * Function to determine the index where the element with the input priority should be inserted.
     * Complexity: - O(1) if the queue is empty or the element will be inserted on the first/last position
     *             - O(log n) otherwise because a binary search algorithm is used to find the index
     *
     * @param priority - the input priority
     * @return the index where the element should be inserted
     */
    private int findIndex(int priority) {
        // if the queue is empty or the priority of the first element is smaller than the
        // input priority, then the searched index is 0
        if (queue.size() == 0 || queue.peekFirst().getPriority() < priority) {
            return 0;
        }

        // if the priority of the last element of the queue is higher or equal to than the input
        // priority, then the searched index is the size of the queue
        if (queue.peekLast().getPriority() >= priority) {
            return queue.size();
        }

        int left = 0;
        int right = queue.size() - 1;

        while (left < right) {
            int middle = left + (right - left) / 2;
            if (priority <= queue.get(middle).getPriority()) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }

        return left;
    }

}

