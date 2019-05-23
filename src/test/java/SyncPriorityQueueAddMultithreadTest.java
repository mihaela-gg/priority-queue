import static org.junit.Assert.assertEquals;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;
import data.SynchronizedPriorityQueue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ConcurrentTestRunner.class)
public class SyncPriorityQueueAddMultithreadTest {

    private SynchronizedPriorityQueue<String> priorityQueue = new SynchronizedPriorityQueue<>();
    private final static int THREAD_COUNT = 5;

    @Before
    public void initialElements() {
        assertEquals(0, priorityQueue.size());
        priorityQueue.add("element1", 20);
        priorityQueue.add("element2", 30);
        assertEquals(2, priorityQueue.size());
    }

    @Test
    @ThreadCount(THREAD_COUNT)
    public void addToQueue() {
        priorityQueue.add("randomElement", 22);
    }

    @After
    public void testAddSize() {
        assertEquals(2 + THREAD_COUNT, priorityQueue.size());
    }
}
