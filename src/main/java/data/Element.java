package data;

/**
 * Class that holds the element and it's priority
 * @param <E> the type of the element
 */
public class Element<E extends Comparable<? super E>> {
    private E content;
    private int priority;

    public Element() {
        this.content = null;
        this.priority = 0;
    }

    public Element(E content, int priority) {
        this.content = content;
        this.priority = priority;
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return content + ", " + priority;
    }
}

