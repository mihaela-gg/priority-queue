import data.SynchronizedPriorityQueue;
import org.junit.Test;

public class SynchronizedPriorityQueueTest {

    @Test
    public void testAddPriorityQueue() {
        SynchronizedPriorityQueue<String> priorityQueue = new SynchronizedPriorityQueue<>();
        priorityQueue.add("broken leg", 10);
        priorityQueue.add("paper cut", 5);
        priorityQueue.add("gunshot wound", 20);

        String highestPriority = priorityQueue.remove();
        assert highestPriority.equals("gunshot wound");
    }

    @Test
    public void testIsEmpty() {
        SynchronizedPriorityQueue<Integer> priorityQueue = new SynchronizedPriorityQueue<>();
        assert !priorityQueue.isEmpty();
    }
}
