import data.SynchronizedPriorityQueue;
import org.testng.annotations.Test;

public class SynchronizedPriorityQueueTest {

    SynchronizedPriorityQueue<String> testPriorityQueue = new SynchronizedPriorityQueue<>();

    /* Tests */
    @Test
    public void testAddPriorityQueue() {
        testPriorityQueue.add("broken leg", 10);
        testPriorityQueue.add("paper cut", 5);
        testPriorityQueue.add("gunshot wound", 20);

        String highestPriority = testPriorityQueue.remove();
        assert highestPriority.equals("gunshot wound");
    }

    @Test
    public void testRemoveInOrder() {

    }


    /* Multithread test */
    @Test(threadPoolSize = 3, invocationCount = 6, timeOut = 1000)
    public void testIsEmpty() {
        Long id = Thread.currentThread().getId();
        System.out.println("Test method executing on thread with id: " + id);
        SynchronizedPriorityQueue<Integer> priorityQueue = new SynchronizedPriorityQueue<>();
        assert priorityQueue.isEmpty();
    }

    @Test(threadPoolSize = 3, invocationCount = 3)
    public void testParallelAdd() {
        Long id = Thread.currentThread().getId();
        System.out.println("Test method executing on thread with id: " + id);

        testPriorityQueue.add("Broken wound", 30);
        System.out.println(testPriorityQueue.size());
    }

    @Test(threadPoolSize = 2, invocationCount = 4)
    public void testRemove() {
        Long id = Thread.currentThread().getId();
        System.out.println("Test method executing on thread with id: " + id);

        testPriorityQueue.remove();
        System.out.println(testPriorityQueue.size());

    }
}
