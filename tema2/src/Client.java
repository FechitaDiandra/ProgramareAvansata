import java.util.Objects;

/**
 * Represents a client.
 */
public class Client {
    private String id;

    /**
     * Constructs a Client object with the given ID.
     *
     * @param id The ID of the client.
     */
    public Client(String id) {
        this.id = id;
    }

    /**
     * Retrieves the ID of the client.
     *
     * @return The ID of the client.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Checks the equality between two Client objects.
     *
     * @param obj The object to compare.
     * @return True if the objects are equal, false otherwise.
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof Client)) {
            return false;
        } else {
            Client other = (Client) obj;
            return this.getId().equals(other.getId());
        }
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
