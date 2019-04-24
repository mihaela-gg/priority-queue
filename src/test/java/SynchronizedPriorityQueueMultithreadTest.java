import data.SynchronizedPriorityQueue;
import org.testng.annotations.Test;

public class SynchronizedPriorityQueueMultithreadTest {

    private volatile SynchronizedPriorityQueue<String> testPriorityQueue = new SynchronizedPriorityQueue<>();

    @Test(threadPoolSize = 3, invocationCount = 3, timeOut = 1000)
    public void testParallelAdd() {
        Long id = Thread.currentThread().getId();
        System.out.println(">> Add Thread " + id);

        testPriorityQueue.add("Wound", 30);
        System.out.println("Size of the queue after add is: " + testPriorityQueue.size() + " " + id);
    }

    @Test(threadPoolSize = 2, invocationCount = 2, timeOut = 1000)
    public void testRemove() {
        Long id = Thread.currentThread().getId();
        System.out.println(">> Remove Thread " + id);

        String result = testPriorityQueue.remove();
        assert result.equals("Wound");
        System.out.println("Size of the queue after remove is: " + testPriorityQueue.size());
    }

    @Test(threadPoolSize = 3, invocationCount = 3, timeOut = 1000)
    public void testUpdate() {
        Long id = Thread.currentThread().getId();
        System.out.println(">> Update Thread " + id);

        assert testPriorityQueue.size() == 1;
        System.out.println(testPriorityQueue.update("Wound", 10));
        assert testPriorityQueue.update("Wound", 10);
    }
}
