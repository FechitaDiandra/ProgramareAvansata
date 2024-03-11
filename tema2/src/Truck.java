/**
 * Represents a truck vehicle with a specific capacity.
 */
public class Truck extends Vehicle {
    private int capacity;

    /**
     * Constructs a Truck object with the given ID and capacity.
     *
     * @param id       The ID of the truck.
     * @param capacity The capacity of the truck.
     */
    public Truck(String id, int capacity) {
        super(id);
        this.capacity = capacity;
    }

    /**
     * Retrieves the capacity of the truck.
     *
     * @return The capacity of the truck.
     */
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public String toString() {
        return "Truck{" +
                "capacity=" + capacity +
                '}';
    }
}
