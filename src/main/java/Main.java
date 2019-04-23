import data.SynchronizedPriorityQueue;

public class Main {

    public static void main(String[] args) {
        SynchronizedPriorityQueue<String> priorityQueue = new SynchronizedPriorityQueue<>();
        priorityQueue.add("Mary", 9);
        priorityQueue.add("Ann", 7);
        priorityQueue.add("Sabrina", 0);
        priorityQueue.add("Dana", 20);

        while(!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.remove());
        }
    }
}
