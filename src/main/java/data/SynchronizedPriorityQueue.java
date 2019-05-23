package data;

import java.util.Vector;

public class SynchronizedPriorityQueue<E extends Comparable<? super E>> {
    private Vector<Element<E>> queue;

    public SynchronizedPriorityQueue() {
        this.queue = new Vector<>();
    }

    public SynchronizedPriorityQueue(int capacity) { this.queue = new Vector<>(capacity); }

    /**
     * Add a new element to the queue.
     * The queue behaves like a max heap so the root will be the element with the highest priority.
     * Complexity: O(log n) - because of the moveUp functions
     *
     * @param content the content
     * @param priority the priority of the content
     */
    public void add(E content, int priority) {
        Element<E> element = new Element<>(content, priority);

        synchronized (this) {
          queue.addElement(element);

          int index = queue.size() - 1;
          moveUp(index);
        }
    }

    /**
     * Removes the element with the highest priority and returns it.
     * The element with the highest priority is the last root of the array.
     * Complexity: O(log n) - because of the moveDown function
     *
     * @return the element with highest priority
     */
    public E remove() {
        if (queue.isEmpty()) {
            return null;
        }

        synchronized (this) {
          Element<E> removedElement = queue.firstElement();
          queue.setElementAt(queue.lastElement(), 0);
          queue.remove(size() - 1);
          moveDown(0);

          return removedElement.getContent();
        }
    }

    /**
     * Update the priority of an content E with a new priority
     * Complexity: O(n) - search in the array the element
     *             O(log n) - moveDown
     *             O(log n) - moveUp
     *       => O(n) + 2 * O(log n)
     *       => O(n) - final complexity
     *
     * @param content the searched element
     * @param priority the new priority
     */
    public boolean update(E content, int priority) {
        synchronized (this) {
            int index = -1;
            for (int i = 0; i < size(); i++) {
            if (queue.get(i).getContent().compareTo(content) == 0) {
              index = i;
              break;
            }
          }

          if (index >= 0) {
            queue.setElementAt(queue.lastElement(), index);
            queue.remove(size() - 1);
            moveDown(index);
            add(content, priority);
            return true;
          }
        }

        return false;
    }

    /**
     * Get the size on the queue
     * @return the size of the queue
     */
    public int size() {
        return queue.size();
    }

    // ---- HELPER METHODS ----

    /**
     * @param index - the current index
     * @return the root of queue.get(index)
     */
    private int root(int index) {
        if (index == 0) {
            // i is already a root
            return 0;
        }

        return (index - 1) / 2;
    }

    /**
     * @param index - the current index
     * @return the left child of queue.get(index)
     */
    private int getLeft(int index) {
        return (2 * index) + 1;
    }

    /**
     * @param index - the current index
     * @return the right child of queue.get(index)
     */
    private int getRight(int index) {
        return (2 * index) + 2;
    }

    /**
     * Swap the values of the two indexes
     * @param index1 - the first index
     * @param index2 - the second index
     *
     * Complexity: O(1) because it directly access the resources using indexes.
     */
    private void swap(int index1, int index2) {
        Element<E> aux = queue.get(index1);
        queue.setElementAt(queue.get(index2), index1);
        queue.setElementAt(aux, index2);
    }

    /**
     * Checks if the priority of the element at index is greater than the  priority of the element
     * from the current root and if it is it swaps them
     * @param index - the current index
     *
     * Complexity: O(log n) - because the function will be called only on the roots of the subtrees
     *      in a max heap
     */
    private void moveUp(int index) {
        if (index > 0 && queue.get(root(index)).getPriority() < queue.get(index).getPriority()) {
            swap(index, root(index));
            moveUp(root(index));
        }
    }

    /**
     * Check if the priority of the element at index is smaller than the priorities of elements
     * from its children and if it is it swaps it with the child having the greater priority
     * @param index
     *
     * Complexity: O(log n) - because the function will be called only if the root of a subtree
     *      will have the priority smaller than one of the children. The queue is already ordered
     *      as a max heap so only a few changes will be made.
     */
    private void moveDown(int index) {
        // get the children of the index
        int left = getLeft(index);
        int right = getRight(index);

        int greater = index;

        if (left < size() && queue.get(left).getPriority() > queue.get(index).getPriority()) {
            greater = left;
        }

        if (right < size() && queue.get(right).getPriority() > queue.get(greater).getPriority()) {
            greater = right;
        }

        if (greater != index) {
            swap(index, greater);
            moveDown(greater);
        }
    }
}

