import java.io.PrintStream;

public class Main {



    public static void main(String[] args) {
        Problem problem = new Problem();
        Depot depot1 = new Depot("Depot 1");
        Depot depot2 = new Depot("Depot 2");
        problem.addDepot(depot1);
        problem.addDepot(depot2);
        Truck truck1 = new Truck("Truck 1", 100);
        Truck truck2 = new Truck("Truck 2", 150);
        depot1.addVehicle(truck1);
        depot2.addVehicle(truck2);
        Drone drone1 = new Drone("Drone 1", 60);
        depot1.addVehicle(drone1);
        Client client1 = new Client("Client 1");
        Client client2 = new Client("Client 2");
        problem.addClient(client1);
        problem.addClient(client2);
        problem.allocateClients();
        Vehicle[] allVehicles = problem.getVehicles();
        Vehicle[] var10 = allVehicles;
        int var11 = allVehicles.length;

        for (int var12 = 0; var12 < var11; ++var12) {
            Vehicle vehicle = var10[var12];
            PrintStream var10000;
            String var10001;
            if (vehicle instanceof Truck) {
                Truck truck = (Truck) vehicle;
                var10000 = System.out;
                var10001 = truck.getId();
                var10000.println("Truck: " + var10001 + ", Capacity: " + truck.getCapacity());
            } else if (vehicle instanceof Drone) {
                Drone drone = (Drone) vehicle;
                var10000 = System.out;
                var10001 = drone.getId();
                var10000.println("Drone: " + var10001 + ", Max Flight Duration: " + drone.getMaxFlightDuration());
            }
        }

        // Utilizarea funcției travel pentru calcularea timpului de călătorie între diferite tipuri de locații
        System.out.println("Travel time between Depot 1 and Depot 2: " + problem.travel(problem, "depot", "Depot 1", "depot", "Depot 2") + " minutes");
        System.out.println("Travel time between Client 1 and Depot 1: " + problem.travel(problem, "client", "Client 1", "depot", "Depot 1") + " minutes");
        System.out.println("Travel time between Client 1 and Client 2: " + problem.travel(problem, "client", "Client 1", "client", "Client 2") + " minutes");

    }


}
