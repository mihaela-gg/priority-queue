import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import com.anarsoft.vmlens.concurrent.junit.ConcurrentTestRunner;
import com.anarsoft.vmlens.concurrent.junit.ThreadCount;
import data.SynchronizedPriorityQueue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ConcurrentTestRunner.class)
public class SyncPriorityQueueRemoveMultithreadTest {
    private SynchronizedPriorityQueue<String> priorityQueue = new SynchronizedPriorityQueue<>();
    private static final int THREAD_COUNT = 4;

    @Before
    public void initialElements() {
        priorityQueue.add("element1", 20);
        priorityQueue.add("element2", 40);
        priorityQueue.add("element3", 1);
        priorityQueue.add("element4", 5);
        priorityQueue.add("element5", 50);
    }

    @Test
    @ThreadCount(THREAD_COUNT)
    public void testRemove() {
        priorityQueue.remove();
        assertFalse(priorityQueue.size() == 0);
    }

    @After
    public void testRemoveSize() {
        assertEquals(1, priorityQueue.size());

        // we test if the last element was the element with the lowest priority -> element3
        assertEquals("element3", priorityQueue.remove());
        assertEquals(0, priorityQueue.size());
    }
}
