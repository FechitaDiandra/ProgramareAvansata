import java.util.*;

/**
 * Represents a transportation problem involving depots, clients, vehicles, and travel times.
 */
public class Problem {
    // Instance variables
    private  Set<Depot> depots = new HashSet<>();
    private Set<Client> clients = new HashSet<>();
    private Map<String, Integer> travelTimes = new HashMap<>();

    /**
     * Constructs a Problem object.
     * Initializes the necessary data structures.
     */
    public Problem() {
        // Constructor initializes data structures
    }

    /**
     * Adds a depot to the problem.
     *
     * @param depot The depot to add.
     */
    public void addDepot(Depot depot) {
        this.depots.add(depot);
    }

    /**
     * Adds a client to the problem.
     *
     * @param client The client to add.
     */
    public void addClient(Client client) {
        this.clients.add(client);
    }

    /**
     * Retrieves an array of available vehicles.
     *
     * @return An array of available vehicles.
     */
    public Vehicle[] getVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        for (Depot depot : this.depots) {
            vehicles.addAll(Arrays.asList(depot.getVehicles()));
        }
        return vehicles.toArray(new Vehicle[0]);
    }

    /**
     * Allocates clients to vehicles.
     */
    public void allocateClients() {
        for (Depot depot : this.depots) {
            Vehicle[] depotVehicles = depot.getVehicles();
            for (Client client : this.clients) {
                if (depotVehicles.length > 0) {
                    Vehicle vehicle = depotVehicles[0];
                    allocateClientToVehicle(client, vehicle);
                }
            }
        }
    }

    /**
     * Allocates a client to a vehicle.
     *
     * @param client  The client to allocate.
     * @param vehicle The vehicle to allocate to.
     */
    private void allocateClientToVehicle(Client client, Vehicle vehicle) {
        System.out.println("Client allocated " + client.getId() + " to vehicle " + vehicle.getId());
    }

    /**
     * Adds a random travel time between two locations.
     *
     * @param location1 The first location.
     * @param location2 The second location.
     */
    public void addRandomTravelTime(String location1, String location2) {
        Random random = new Random();
        int travelTime = random.nextInt(180); // Generates a random time between 0 and 180 minutes
        addTravelTime(location1, location2, travelTime);
    }

    /**
     * Adds a travel time between two locations.
     *
     * @param location1   The first location.
     * @param location2   The second location.
     * @param travelTime  The travel time between the locations.
     */
    public void addTravelTime(String location1, String location2, int travelTime) {
        String key1 = location1 + "-depot";
        String key2 = "depot-" + location2;

        // Limit travel time to 180 minutes
        travelTimes.put(key1, Math.min(travelTime, 180));
        travelTimes.put(key2, Math.min(travelTime, 180));
    }

    /**
     * Calculates the travel time between two locations.
     *
     * @param problem The transportation problem.
     * @param depot   The type of the first location (Depot or Client).
     * @param s       The name of the first location.
     * @param depot1  The type of the second location (Depot or Client).
     * @param s1      The name of the second location.
     * @return The travel time between the two locations.
     */
    public static String travel(Problem problem, String depot, String s, String depot1, String s1) {
        // Declaration and initialization of the travel time variable
        int travelTime = 0;

        // Check if the locations are depots or clients
        String locationType1 = depot.equals("Depot") ? "depot" : "client";
        String locationType2 = depot1.equals("Depot") ? "depot" : "client";

        // Calculate travel time (random value between 0 and 180)
        travelTime = (int) (Math.random() * 181);

        return String.valueOf(travelTime);
    }

    /**
     * Returns a string representation of the Problem object.
     *
     * @return A string representation of the Problem object.
     */
    @Override
    public String toString() {
        return "Problem{" +
                "travelTimes=" + travelTimes +
                '}';
    }
}
