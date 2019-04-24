import data.SynchronizedPriorityQueue;

public class Main {

    public static void main(String[] args) {
        SynchronizedPriorityQueue<String> priorityQueue = new SynchronizedPriorityQueue<>();
        priorityQueue.add("Broken leg", 9);
        priorityQueue.add("Paper cut", 7);
        priorityQueue.add("Gunshot wound", 20);

        System.out.println(priorityQueue.remove());

        priorityQueue.add("Emergency", 50);
        priorityQueue.update("Paper cut", 70);

        System.out.println(priorityQueue.remove());
    }
}
