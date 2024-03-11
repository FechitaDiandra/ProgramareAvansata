import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a depot with a name and a set of vehicles.
 */
public class Depot {
    private String name;
    private Set<Vehicle> vehicles;

    /**
     * Constructs a Depot object with the given name.
     *
     * @param name The name of the depot.
     */
    public Depot(String name) {
        this.name = name;
        this.vehicles = new HashSet<>();
    }

    /**
     * Retrieves the name of the depot.
     *
     * @return The name of the depot.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Adds a vehicle to the depot.
     *
     * @param vehicle The vehicle to add.
     */
    public void addVehicle(Vehicle vehicle) {
        this.vehicles.add(vehicle);
    }

    /**
     * Retrieves an array of vehicles in the depot.
     *
     * @return An array of vehicles in the depot.
     */
    public Vehicle[] getVehicles() {
        return this.vehicles.toArray(new Vehicle[0]);
    }

    /**
     * Checks the equality between two Depot objects.
     *
     * @param obj The object to compare.
     * @return True if the objects are equal, false otherwise.
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof Depot)) {
            return false;
        } else {
            Depot other = (Depot) obj;
            return this.getName().equals(other.getName());
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, vehicles);
    }

    @Override
    public String toString() {
        return "Depot{" +
                "name='" + name + '\'' +
                ", vehicles=" + vehicles +
                '}';
    }
}
