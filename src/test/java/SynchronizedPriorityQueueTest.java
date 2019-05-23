import data.SynchronizedPriorityQueue;
import org.testng.annotations.Test;

public class SynchronizedPriorityQueueTest {

    @Test
    public void testAddPriorityQueue() {
        SynchronizedPriorityQueue<String> priorityQueue = new SynchronizedPriorityQueue<>();
        assert priorityQueue.size() == 0;

        priorityQueue.add("broken leg", 10);
        assert priorityQueue.size() == 1;

        priorityQueue.add("paper cut", 5);
        assert priorityQueue.size() == 2;

        priorityQueue.add("gunshot wound", 20);
        assert priorityQueue.size() == 3;
    }

    @Test
    public void testRemoveInOrder() {
        SynchronizedPriorityQueue<String> priorityQueue = new SynchronizedPriorityQueue<>();
        priorityQueue.add("broken leg", 10);
        priorityQueue.add("paper cut", 5);
        priorityQueue.add("gunshot wound", 20);

        assert priorityQueue.size() == 3;

        String firstElement = priorityQueue.remove();
        assert firstElement.equals("gunshot wound");
        assert priorityQueue.size() == 2;

        String secondElement = priorityQueue.remove();
        assert secondElement.equals("broken leg");
        assert priorityQueue.size() == 1;

        String thirdElement = priorityQueue.remove();
        assert thirdElement.equals("paper cut");
        assert priorityQueue.size() == 0;
    }

    @Test
    public void testRemoveEmptyQueue() {
        SynchronizedPriorityQueue<String> priorityQueue = new SynchronizedPriorityQueue<>();
        String element = priorityQueue.remove();
        assert element == null;
    }

    @Test
    public void testAddRemoveWithSamePriority() {
            SynchronizedPriorityQueue<String> priorityQueue = new SynchronizedPriorityQueue<>();
            assert priorityQueue.size() == 0;

            priorityQueue.add("broken leg", 10);
            assert priorityQueue.size() == 1;

            // queue: broken leg
            priorityQueue.add("paper cut", 5);
            assert priorityQueue.size() == 2;

            // queue: broken leg, paper cut
            priorityQueue.add("knife cut", 10);
            assert priorityQueue.size() == 3;

            // queue: broken leg, knife cut, paper cut
            priorityQueue.add("emergency", 20);
            assert priorityQueue.size() == 4;

            // queue: emergency, broken leg, knife cut, paper cut
            priorityQueue.add("gunshot wound", 10);
            assert priorityQueue.size() == 5;

            // queue: emergency, broken leg, knife cut, gunshot wound, paper cut
            String firstElement = priorityQueue.remove();
            assert priorityQueue.size() == 4;
            assert firstElement.equals("emergency");

            String secondElement = priorityQueue.remove();
            assert priorityQueue.size() == 3;
            assert secondElement.equals("gunshot wound");

            String thirdElement = priorityQueue.remove();
            assert priorityQueue.size() == 2;
            assert thirdElement.equals("broken leg");

            String fourthElement = priorityQueue.remove();
            assert priorityQueue.size() == 1;
            assert fourthElement.equals("knife cut");

            priorityQueue.remove();
            assert priorityQueue.size() == 0;
    }

    @Test
    public void testUpdatePriorityUp() {
        SynchronizedPriorityQueue<String> priorityQueue = new SynchronizedPriorityQueue<>();

        priorityQueue.add("broken leg", 10);
        priorityQueue.add("paper cut", 5);
        priorityQueue.add("gunshot wound", 20);
        priorityQueue.add("emergency", 50);

        assert priorityQueue.size() == 4;

        assert priorityQueue.update("broken leg", 60);
        assert priorityQueue.size() == 4;

        String element = priorityQueue.remove();
        assert element.equals("broken leg");
        assert priorityQueue.size() == 3;
    }

    @Test
    public void testUpdatePriorityDown() {
        SynchronizedPriorityQueue<String> priorityQueue = new SynchronizedPriorityQueue<>();

        priorityQueue.add("broken leg", 10);
        priorityQueue.add("gunshot wound", 20);
        priorityQueue.add("emergency", 50);

        assert priorityQueue.size() == 3;

        assert priorityQueue.update("emergency", 15);
        assert priorityQueue.size() == 3;

        String firstElement = priorityQueue.remove();
        assert firstElement.equals("gunshot wound");

        String secondElement = priorityQueue.remove();
        assert secondElement.equals("emergency");
    }

    @Test
    public void testUpdatePriorityBad() {
        SynchronizedPriorityQueue<String> priorityQueue = new SynchronizedPriorityQueue<>();

        priorityQueue.add("broken leg", 10);
        priorityQueue.add("paper cut", 5);
        priorityQueue.add("gunshot wound", 20);
        priorityQueue.add("emergency", 50);

        assert priorityQueue.size() == 4;

        assert !priorityQueue.update("infection", 40);
    }

    @Test
    public void testUpdatePriorityNoChange() {
        SynchronizedPriorityQueue<String> priorityQueue = new SynchronizedPriorityQueue<>();

        priorityQueue.add("broken leg", 10);
        priorityQueue.add("paper cut", 5);
        priorityQueue.add("gunshot wound", 20);

        assert priorityQueue.size() == 3;

        assert priorityQueue.update("paper cut", 7);

        String element = priorityQueue.remove();
        assert element.equals("gunshot wound");
    }
}
