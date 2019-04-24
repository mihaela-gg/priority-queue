import data.SynchronizedPriorityQueue;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Emergency Room example ===");
        emergencyRoomExample();
    }

    private static void emergencyRoomExample() {
        SynchronizedPriorityQueue<String> patients = new SynchronizedPriorityQueue<>();

        patients.add("broken leg", 10);
        System.out.println("New patient comes in: broken leg, 10 priority");
        System.out.println("Remaining patients: " + patients.size());

        patients.add("gunshot wound", 20);
        System.out.println("New patient comes in: gunshot wound, 20 priority");
        System.out.println("Remaining patients: " + patients.size());

        String servedPatient = patients.remove();
        System.out.println("!!!Doctors treated the first patient: " + servedPatient);
        System.out.println("Remaining patients: " + patients.size());

        // patient with lowest priority => he will be processed last
        patients.add("paper cut", 5);
        System.out.println("New patient comes in: paper cut, 5 priority");
        System.out.println("Remaining patients: " + patients.size());

        // patient with highest priority
        patients.add("knife cut", 30);
        System.out.println("New patient comes in: knife cut, 30 priority");
        System.out.println("Remaining patients: " + patients.size());

        // patient with broken leg is updated with highest priority => he will be processed first
        patients.update("broken leg", 40);
        System.out.println("The patient with a broken leg faints => update priority to 40");
        System.out.println("Remaining patients: " + patients.size());

        // no one new comes in => the remaining patients are treated
        while (patients.size() != 0) {
            servedPatient = patients.remove();
            System.out.println("!!!Doctors treated the next patient: " + servedPatient);
            System.out.println("Remaining patients: " + patients.size());

            if (patients.size() == 0) {
                System.out.println("No more patients to treat.");
            }
        }
    }
}
