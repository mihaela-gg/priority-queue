import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import data.SynchronizedPriorityQueue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ConcurrentTestRunner.class)
public class SyncPriorityQueueUpdateMultithreadTest {
    private SynchronizedPriorityQueue<String> priorityQueue = new SynchronizedPriorityQueue<>();
    private static final int THREAD_COUNT = 4;

    @Before
    public void initialElements() {
        priorityQueue.add("element1", 43);
        priorityQueue.add("element2", 42);
        priorityQueue.add("element3", 50);
        assertEquals(3, priorityQueue.size());
    }

    @Test
    public void testUpdateTrue() {
        assertTrue(priorityQueue.update("element2", 70));
    }

    @Test
    public void testUpdateFalse() {
        assertFalse(priorityQueue.update("wrongElement", 50));
    }

    @After
    public void testUpdateSize() {
        assertEquals(3, priorityQueue.size());
    }
}
