package data;

/**
 * Class that holds the element and it's priority
 * @param <E> the type of the element
 */
public class Element<E extends Comparable<? super E>> {
    private E label;
    private int priority;

    public Element() {
        this.label = null;
        this.priority = 0;
    }

    public Element(E label, int priority) {
        this.label = label;
        this.priority = priority;
    }

    public E getLabel() {
        return label;
    }

    public void setLabel(E label) {
        this.label = label;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return label + ", " + priority;
    }
}

